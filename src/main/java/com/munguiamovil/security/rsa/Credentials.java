package com.munguiamovil.security.rsa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;




public class Credentials {
	
	protected PublicKey pk = null;
	protected PrivateKey privK = null;
	
	
	
	public Credentials(String pathPK,String pathPrivK) {
		
		try {
			this.getPrivateKey(pathPK);
			this.getPublicKey(pathPrivK);
		}catch(Exception e) {
			
		}
		
	}
	
	 
	private void getPrivateKey(String pathPK) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		
		String privateKeyFileName = pathPK;  
		Path path = Paths.get(privateKeyFileName);
		byte[] privKeyByteArray = Files.readAllBytes(path);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privKeyByteArray);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		this.privK = keyFactory.generatePrivate(keySpec);
		
	}
	
	private void getPublicKey(String pathPrivK) throws FileNotFoundException, CertificateException {
		FileInputStream fin = new FileInputStream(pathPrivK);
		CertificateFactory f = CertificateFactory.getInstance("X.509");
		X509Certificate certificate = (X509Certificate)f.generateCertificate(fin);
		this.pk = certificate.getPublicKey();
	}

}
