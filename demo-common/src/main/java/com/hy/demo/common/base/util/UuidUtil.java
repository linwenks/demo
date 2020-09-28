package com.hy.demo.common.base.util;

import java.util.UUID;

/**
 * uuid
 */
public class UuidUtil {
	
	private UuidUtil() {
	}
	
	public static String generateUUID_() {
		return UUID.randomUUID().toString();
	}
	
	public static String generateUUID() {
		return generateUUID_().replaceAll("-", "");
	}
}
