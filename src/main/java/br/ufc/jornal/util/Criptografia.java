package br.ufc.jornal.util;

import java.security.MessageDigest;

public class Criptografia {
	public static String criptografar(String senha){
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
	        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
	        
	        StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
              hexString.append(String.format("%02X", 0xFF & b));
            }
            String senhahex = hexString.toString();
	        return senhahex;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
}
