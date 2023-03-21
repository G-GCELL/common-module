package com.gabia.weat.gcellcommonmodule.parser;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SpELExpressionEnvParser implements CustomExpressionParser {

	private final Environment environment;

	@Override
	public Object parse(String value) {
		if (!this.validValueFormat(value)) {
			return value;
		}
		return environment.getProperty(value.trim().substring(2, value.length() - 1));
	}

	private boolean validValueFormat(String value) {
		return value.startsWith("${") && value.endsWith("}");
	}

}