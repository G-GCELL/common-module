package com.gabia.weat.gcellcommonmodule.printer;

import org.springframework.stereotype.Component;

import com.gabia.weat.gcellcommonmodule.dto.format.LogFormatDto;
import com.gabia.weat.gcellcommonmodule.dto.format.LogFormatFactory;
import com.gabia.weat.gcellcommonmodule.parser.CustomExpressionParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConsoleLogPrinter extends LogPrinter {

	public ConsoleLogPrinter(LogFormatFactory logFormatFactory,
		CustomExpressionParser expressionBeanParser) {
		super(logFormatFactory, expressionBeanParser);
	}

	@Override
	protected void print(LogFormatDto logFormatDto) {
		log.atLevel(logFormatDto.getLevel()).log(logFormatDto.toString());
	}

}