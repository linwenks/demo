package com.hy.demo.common.base.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonUtil {

	private static Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
//			.setPrettyPrinting()
			.disableHtmlEscaping()
			.create();
	
	private JsonUtil() {
	}

	public static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public static <T> T fromJson(String jsonStr, Class<T> t) {
		return gson.fromJson(jsonStr, t);
	}
	
	public static <T> T fromJson(String jsonStr, Type typeOfT) {
		return gson.fromJson(jsonStr, typeOfT);  
	}
}
