package com.gabia.weat.gcellcommonmodule.dto.format;

import org.slf4j.event.Level;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogFormatDto {

	protected Level level;
	protected String serverName;
	protected String traceId;

}