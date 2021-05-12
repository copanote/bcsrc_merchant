package com.merchant.demo.xml.cipher;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Ciphers {
	
	public static byte[] rsaEncrypt(String trns, Key pubKey, byte[] plainText) {
		
		byte[] cipherText = null;
		
		try {
			
			Cipher cipher = Cipher.getInstance(trns);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			cipherText = cipher.doFinal(plainText);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}
	
	public static byte[] rsaDecrypt(String trns, Key priKey, byte[] cipherText) {
		
		byte[] plainText = null;
		
		try {
			Cipher cipher = Cipher.getInstance(trns);
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			plainText = cipher.doFinal(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plainText;
	}
	
	
	public static byte[] generateTDesKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		//Get a key generator for Triple DES(a.k.a DESede)
		KeyGenerator keygen = KeyGenerator.getInstance("DESede");
		
		//Using it to generate a key
		
		SecretKey secretKey = keygen.generateKey();
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		DESedeKeySpec keyspec = (DESedeKeySpec) keyfactory.getKeySpec(secretKey, DESedeKeySpec.class);
		return keyspec.getKey();
	}
	
	public static byte[] decryptTDes(byte[] key, byte[] cipherText) {
		
		byte[] plainText = null;
		
		try {
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKeySpec keyspec = new SecretKeySpec(key, "DESede");
			cipher.init(Cipher.DECRYPT_MODE, keyspec);
			plainText = cipher.doFinal(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plainText;
	}
	
	public static byte[] encryptTDes(byte[] key, byte[] plainText) {
		
		byte[] cipherText = null;
		
		try {
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKeySpec keyspec = new SecretKeySpec(key, "DESede");
			cipher.init(Cipher.ENCRYPT_MODE, keyspec);
			plainText = cipher.doFinal(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plainText;
	}
	
	public static boolean verifyRsaSignature(String data, String signature, String key) throws Exception {
		KeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
		PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
		Signature instance = Signature.getInstance("SHA256withRSA");
		instance.initVerify(publicKey);
		instance.update(data.getBytes("utf-8"));
		
		return instance.verify(Base64.getDecoder().decode(signature));
		
	}
	

}
