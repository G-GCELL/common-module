package com.gabia.weat.gcellcommonmodule.printer;

import org.slf4j.event.Level;
import org.springframework.http.HttpMethod;

import com.gabia.weat.gcellcommonmodule.dto.format.ApiLogFormatDto;
import com.gabia.weat.gcellcommonmodule.dto.format.MessageBrokerLogFormatDto.MessageBrokerLogFormatDtoBuilder;
import com.gabia.weat.gcellcommonmodule.dto.format.TimerLogFormatDto;
import com.gabia.weat.gcellcommonmodule.error.exception.CustomException;
import com.gabia.weat.gcellcommonmodule.dto.format.LogFormatDto;
import com.gabia.weat.gcellcommonmodule.dto.format.LogFormatFactory;
import com.gabia.weat.gcellcommonmodule.parser.CustomExpressionParser;
import com.gabia.weat.gcellcommonmodule.type.TargetType;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class LogPrinter {

	private final String X_FORWARDED_FOR_HEADER = "X-FORWARDED-FOR";
	private final LogFormatFactory logFormatFactory;
	private final CustomExpressionParser expressionBeanParser;

	protected abstract void print(LogFormatDto logFormatDto);

	private String getRemoteAddr(HttpServletRequest request) {
		String header = request.getHeader(X_FORWARDED_FOR_HEADER);
		return (header != null) ? header : request.getRemoteAddr();
	}

	public void printApiLog(HttpServletRequest request, boolean success, int status, int detailStatus, long time, String input) {
		ApiLogFormatDto apiLogFormatDto = logFormatFactory.getApiLogFormatBuilder()
			.httpMethod(HttpMethod.valueOf(request.getMethod()))
			.userIp(this.getRemoteAddr(request))
			.apiUri(request.getRequestURI())
			.success(success)
			.status(status)
			.detailStatus(detailStatus)
			.time(time)
			.input(input)
			.build();

		this.print(apiLogFormatDto);
	}

	public void printErrorLog(Exception e) {
		String message = e.getMessage();
		if (e instanceof CustomException ce) {
			message = ce.getErrorCode().getMessage();
		}
		StackTraceElement stackTraceElement = e.getStackTrace()[0];
		this.print(logFormatFactory.getErrorLogFormatBuilder()
			.className(stackTraceElement.getClassName())
			.methodName(stackTraceElement.getMethodName())
			.exceptionName(e.getClass().getName())
			.message(message)
			.build());
	}

	public void printMessageBrokerLog(TargetType type, String target, String input, Exception exception) {
		String targetName = (String) expressionBeanParser.parse(target);
		MessageBrokerLogFormatDtoBuilder logFormatDtoBuilder = logFormatFactory.getMessageBrokerLogFormatBuilder()
			.level(exception == null ? Level.INFO : Level.ERROR)
			.type(type)
			.exchangeName(type == TargetType.PRODUCER ? targetName : null)
			.queueName(type == TargetType.CONSUMER ? targetName : null)
			.exception(exception)
			.input(input);

		this.print(logFormatDtoBuilder.build());
	}

	public void printTimerLog(String methodName, String input, boolean isStart, long time){
		TimerLogFormatDto timerLogFormatDto = logFormatFactory.getTimerLogFormatBuilder()
			.methodName(methodName)
			.input(input)
			.isStart(isStart)
			.time(time)
			.build();

		this.print(timerLogFormatDto);
	}

}