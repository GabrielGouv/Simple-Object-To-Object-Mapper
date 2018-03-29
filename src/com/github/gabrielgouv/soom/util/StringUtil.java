package com.github.gabrielgouv.soom.util;

public final class StringUtil {

	public static String capitalize(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}
	
	public static String uncapitalize(String string) {
		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}
	
	public static String removeGet(String string) {
		if (string.startsWith("get")) {
			return string.substring(3);
		}
		return string;
	}
	
	public static String removeSet(String string) {
		if (string.startsWith("set")) {
			return string.substring(3);
		}
		return string;
	}
	
}
