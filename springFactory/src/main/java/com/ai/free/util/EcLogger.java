package com.ai.free.util;

import org.apache.log4j.Logger;

public class EcLogger {
	private final static Logger logger = Logger.getLogger(EcLogger.class);
	public static void info(Object message){
		logger.info(message);
	}
	public static void info(Object message,Throwable t){
		logger.info(message,t);
	}
	public static void fmtinfo(String fmtString,Object... args){
		logger.info(String.format(fmtString, args));
	}
	public static void error(Object message){
		logger.info(message);
	}
	
	public static void error(Object message,Throwable t){
		logger.info(message,t);
	}
}
