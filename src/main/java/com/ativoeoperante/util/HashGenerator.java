package com.ativoeoperante.util;

public class HashGenerator {
	private static final String LOWERS = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPPERS = HashGenerator.LOWERS.toUpperCase();
	private static final String NUMBERS = "0123456789";
	
	private HashGenerator() {}
	
	public static String generate(int length) {
		String ans = "";
		
		for(int i = 0; i < length; i++) {
			int r1 = (int)(Math.random() * 3);
			String s = "";
			
			switch(r1) {
				case 0:
					s += HashGenerator.LOWERS.charAt((int)(Math.random() * LOWERS.length()));
					break;
				case 1:
					s += HashGenerator.UPPERS.charAt((int)(Math.random() * UPPERS.length()));
					break;
				case 2:
					s += HashGenerator.NUMBERS.charAt((int)(Math.random() * NUMBERS.length()));
					break;
			}
			
			ans += s;
		}
		
		return ans; 
	}
}
