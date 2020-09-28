package com.hy.demo.common.base.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

	public static String getRandomCode(int num) {
		var sb = new StringBuilder();
		for (int i=0; i<num; i++) {
		    sb.append(ThreadLocalRandom.current().nextInt(10));
		}  
		return sb.toString();
	}
	
	public static int nextInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}
	
	public static int nextInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(max) % (max - min + 1) + min;
    }
	
	public static void main(String[] args) {
		System.out.println(nextInt(2));
	}
}
