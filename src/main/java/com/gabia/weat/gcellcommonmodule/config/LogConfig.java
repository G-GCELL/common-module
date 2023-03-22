package com.gabia.weat.gcellcommonmodule.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.gabia.weat.gcellcommonmodule.aspect.LogAspect;
import com.gabia.weat.gcellcommonmodule.dto.format.LogFormatFactory;
import com.gabia.weat.gcellcommonmodule.parser.CustomExpressionParser;
import com.gabia.weat.gcellcommonmodule.parser.SpELExpressionEnvParser;
import com.gabia.weat.gcellcommonmodule.printer.ConsoleLogPrinter;
import com.gabia.weat.gcellcommonmodule.printer.LogPrinter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class LogConfig {

	private final String ENV_NAME = "HOSTNAME";
	private final Environment environment;

	@Bean
	public LogPrinter logPrinter(){
		return new ConsoleLogPrinter(logFormatFactory(), customExpressionParser());
	}

	@Bean
	public LogFormatFactory logFormatFactory(){
		String envString = environment.getProperty(ENV_NAME);
		String serverName = envString == null? RandomStringUtils.random(12, true, true) : envString;
		return new LogFormatFactory(serverName);
	}

	@Bean
	public CustomExpressionParser customExpressionParser(){
		return new SpELExpressionEnvParser(environment);
	}

	@Bean
	public LogAspect logAspect() {
		return new LogAspect(logFormatFactory(), logPrinter());
	}

}