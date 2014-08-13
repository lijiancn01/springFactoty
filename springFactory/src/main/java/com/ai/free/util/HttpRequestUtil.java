package com.ai.free.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class HttpRequestUtil {
	public static String getAsString(HttpServletRequest request, String paramName, String defaultResult) throws UnsupportedEncodingException{
		String value = request.getParameter(paramName);
		if(StringUtils.isNotEmpty(value)){
			return new String(value.getBytes("ISO-8859-1"), "GBK");
		}
		return defaultResult;
	}
	
	public static String getAsString(HttpServletRequest request, String paramName) throws UnsupportedEncodingException{
		return getAsString(request, paramName, "");
	}
	
	public static long getAsLong(HttpServletRequest request, String paramName, long defaultResult){
		String value = request.getParameter(paramName);
		return longValue(value, defaultResult);
	}
	
	public static long getAsLong(HttpServletRequest request, String paramName){
		String value = request.getParameter(paramName);
		return longValue(value);
	}
	
	public static int getAsInt(HttpServletRequest request, String paramName, int defaultResult){
		String value = request.getParameter(paramName);
		return intValue(value, defaultResult);
	}
	
	public static int getAsInt(HttpServletRequest request, String paramName){
		String value = request.getParameter(paramName);
		return intValue(value);
	}
	
	public static long longValue(String value){
		return longValue(value, 0);
	}
	
	public static long longValue(String value, long defaultResult){
		if(StringUtils.isEmpty(value)){
			return defaultResult;
		}
		String regEx = "^[-]?[1-9][0-9]*$";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(value);
		if(mat.find()){
			return Long.parseLong(value);
		}
		return defaultResult;
	}
	
	public static int intValue(String value){
		return intValue(value, 0);
	}
	
	public static int intValue(String value, int defaultResult){
		if(StringUtils.isEmpty(value)){
			return defaultResult;
		}
		String regEx = "^[-]?[1-9][0-9]*$";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(value);
		if(mat.find()){
			return Integer.parseInt(value);
		}
		return defaultResult;
	}
	
	
}
