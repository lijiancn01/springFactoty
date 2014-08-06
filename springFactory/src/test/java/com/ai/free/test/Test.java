package com.ai.free.test;

import com.ai.free.util.EcLogger;

public class Test {
	public static void main(String[] args) {
		String path = "\\springFactory\\src\\main\\webapp\\jquery-easyui-1.3.2\\demo\\accordion\\actions.html";
		String[] kv = path.split("\\\\");
		for (String string : kv) {
			EcLogger.info(string);
		}
	}
}
