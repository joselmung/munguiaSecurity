package com.munguiamovil.security.rsa;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

public class Rsa extends Credentials {
	private Cipher cipher = null;
	
	public Rsa(String pathPK,String pathPrivK) {
		super(pathPK,pathPrivK);
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String encript(String msj) {
		String encpt = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			encpt = Base64.encodeBase64String(cipher.doFinal(msj.getBytes("UTF-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encpt;
	}
	
	public String decrypt(String encpt) {
		String dec = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, this.privK);
			dec = new String(cipher.doFinal(Base64.decodeBase64(encpt)), "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dec;

	}
	
	public boolean validHash(String encpt) {
		boolean valid = true;
		try {
			cipher.init(Cipher.DECRYPT_MODE, this.privK);
			cipher.doFinal(Base64.decodeBase64(encpt));
			
		}catch(Exception  e) {
			if(e instanceof BadPaddingException)
				valid = false;
		}
		return valid;
		
	}

}
