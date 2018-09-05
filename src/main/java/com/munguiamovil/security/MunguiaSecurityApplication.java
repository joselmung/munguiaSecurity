package com.munguiamovil.security;
import com.munguiamovil.security.rsa.Rsa;
import com.munguiamovil.security.utils.LoadProperties;

public class MunguiaSecurityApplication {

	public static void main(String[] args) {
		
		
		Rsa rs = new Rsa(LoadProperties.getProperty("rutas", "rsa.key"),LoadProperties.getProperty("rutas", "rsa.cert"));
		String hash = rs.encript("jose luis munguia");
		String udc  = rs.decrypt(hash);
		
		System.out.printf("cifrado: %s \ndesifrado: %s", hash,udc);
	}
}
