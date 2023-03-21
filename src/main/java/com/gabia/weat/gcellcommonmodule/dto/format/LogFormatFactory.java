package com.gabia.weat.gcellcommonmodule.dto.format;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import com.gabia.weat.gcellcommonmodule.dto.format.ApiLogFormatDto.ApiLogFormatDtoBuilder;
import com.gabia.weat.gcellcommonmodule.dto.format.ErrorLogFormatDto.ErrorLogFormatDtoBuilder;
import com.gabia.weat.gcellcommonmodule.dto.format.MessageBrokerLogFormatDto.MessageBrokerLogFormatDtoBuilder;
import com.gabia.weat.gcellcommonmodule.dto.format.TimerLogFormatDto.TimerLogFormatDtoBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LogFormatFactory {

	private final String serverName;
	private final int TRACE_ID_LENGTH = 7;
	private ThreadLocal<String> traceInfoHolder = new ThreadLocal<>();

	public void startTrace() {
		traceInfoHolder.set(RandomStringUtils.random(TRACE_ID_LENGTH, true, true));
	}

	public void startTrace(String traceId) {
		traceInfoHolder.set(traceId);
	}

	public void endTrace() {
		traceInfoHolder.remove();
	}

	public ApiLogFormatDtoBuilder getApiLogFormatBuilder() {
		return ApiLogFormatDto.builder()
			.level(Level.INFO)
			.serverName(serverName)
			.traceId(this.traceInfoHolder.get());
	}

	public ErrorLogFormatDtoBuilder getErrorLogFormatBuilder() {
		return ErrorLogFormatDto.builder()
			.level(Level.ERROR)
			.serverName(serverName)
			.traceId(this.traceInfoHolder.get());
	}

	public MessageBrokerLogFormatDtoBuilder getMessageBrokerLogFormatBuilder() {
		return MessageBrokerLogFormatDto.builder()
			.serverName(serverName)
			.traceId(this.traceInfoHolder.get());
	}

	public TimerLogFormatDtoBuilder getTimerLogFormatBuilder(){
		return TimerLogFormatDto.builder()
			.level(Level.DEBUG)
			.serverName(serverName)
			.traceId(this.traceInfoHolder.get());
	}

	public String getTraceId() {
		return this.traceInfoHolder.get();
	}

}