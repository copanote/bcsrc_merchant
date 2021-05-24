package com.merchant.demo.xml.cipher;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Ciphers {

	public static String bcPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDVK5psKhhUqqQA"
			+ "quX+PbjXdmni50JdWR6PdiJ03cli3+1/iBt5af8smAI1Hm0Wl4oYTQBFmloPpcIg"
			+ "cNaFDF6dpXXnKZUlLoTlRvdAZnDap70oR8oQ8GOj5dHzpFmohy169eCAxOY08lJh"
			+ "M++2L5dl00yLnxEWkRGlhslEYWKhwxJNVLecTmGQnTqP5OkMzzDlH5q+vJrvuDSN"
			+ "H2HsOoG3zej6m+yNcjmvSGsJ3yHnz+xCVyADcLc9+0993Kwt4htPFyoOx/HBlsiz"
			+ "gXdmo6Q4rsktosqmGd1VtWZFu0lPzHGTnbfun1WumiGEzuHJBdetW2yAvOwYffMG"
			+ "yJhLDMKZAgMBAAECggEAPkTmbwI86T6PecJyPLo67HWiqK11LV+DFdUka4797OwM"
			+ "rFxN6kGUUKp2up2QsDDEHiUgFzTnafq5olBT2bmrSdGzhzsWXi4gk/kO8Lj77mNh"
			+ "Vgric+BEuvO/SLSRnDEgj/KTCgp6VK0Uc2CAfW+Zgh0fFwn4K+dkiD2/PUJVVXrl"
			+ "eeZeDNHPvWlY3izAwQ5pYL51DnEruOwWIma8tniMy+OQWRwuKuy5WwBT9XgA/+/K"
			+ "0PrMxRhgSHVtAeqshY9hOc651fz47Oalsw6ScwDy2Y9PYea9Zr/YiR8Sps5SDzog"
			+ "Wiqv98YimZzLMeJbFcx/iPmdw+xse7+4TXH0drwQAQKBgQD4UiL+ILr+CrQK8oB8"
			+ "nYGRztKw0ZfTY53RkeETajy65xn1G3MqJsfTZ/o6ZY6Wr7bJFy4+3n7eKQ4658VX"
			+ "nnx9QKexB3AIkSdlJtpShzeXcoPVRGGJIFLfn1/Od5/PAKDuNlyskR+63D0vEjHb"
			+ "xg1cXk2YomcOkyyr9KiJowSGwQKBgQDbwzFnfpKvmGdWPZI5is7RswbD4iYIxrre"
			+ "BvImUjVcog4vnDyqxU2Xfg5Wb06jgQwMlfTPmekcXDRcst9mvk70Q+zo+OrOoii/"
			+ "hz8xsGm5njxgsTkiiEZOJbRTXfsNOUpUIRdmsS5gTsmSuC6peRSnGMzojFxHUgS7"
			+ "0oy42anJ2QKBgEv2VF4JBSrRe2Id7NAyHoDMFAejFh7ESnLhfUjDJvDF4VrpmsE6"
			+ "O8af+BwFFSljdzbssGjrB0ROqtu6KtQy10I5lRLCg7MbNEHtMLwuQQNDNLD4yh14"
			+ "leFyj4xJa8mXF6PHXXIsstgmp1DO8BGa9FtdPb+DbqM2wmOX5Da2d5wBAoGBAM8L"
			+ "JxCKf0hQHYtylsD/tbp7IHSMK6k5EfT8NkdETGHpi9PvLnj1ShTf/kIQu9XvTprM"
			+ "7MhqCOVfOTXcj/0MbvZSyHD1ksL6o8lOcost2W23VssCLQILwbD+dNXWRSBASXbZ"
			+ "dmKZq0ew8tf4Dn48ROjoxKSTGJ1Y+Hy78Th2WTCBAoGALnAeKoEK+8aHlkRV9Ngf"
			+ "cwEKFm/sz0fYu99woSKul3v3hvIA+qJJRXhUlKE/4xDrzCBY/fcSvinYI5B4KGhl"
			+ "6MnzRjL8ArqzQxpBx2+lR4Fm4XkVrl7ArBYI5c5hXGfGdXrrxrGKssWWmNSLGlQe" 
			+ "RZdE0piYgBTJM52t1qOuzEE=";

	public static String bcPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1SuabCoYVKqkAKrl/j24"
			+ "13Zp4udCXVkej3YidN3JYt/tf4gbeWn/LJgCNR5tFpeKGE0ARZpaD6XCIHDWhQxe"
			+ "naV15ymVJS6E5Ub3QGZw2qe9KEfKEPBjo+XR86RZqIctevXggMTmNPJSYTPvti+X"
			+ "ZdNMi58RFpERpYbJRGFiocMSTVS3nE5hkJ06j+TpDM8w5R+avrya77g0jR9h7DqB"
			+ "t83o+pvsjXI5r0hrCd8h58/sQlcgA3C3PftPfdysLeIbTxcqDsfxwZbIs4F3ZqOk"
			+ "OK7JLaLKphndVbVmRbtJT8xxk5237p9VrpohhM7hyQXXrVtsgLzsGH3zBsiYSwzC" 
			+ "mQIDAQAB";

	public static String lpbDevPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/8s+3EELKBFwUZYrl2fRrzaOL0fKILirtHkJloUjq8AdOqywOhCa5op9g652NVbF73/aDVabHj4o43qODpMmyoQpcURtKJJOYHboWVb5qw+tK10V/FNdlPjxArlniOD2Gn+m4eV3AUykd8uujnznVImi/5U+v6w7/QTW+pJBhPQIDAQAB";

	public static String lpbProdPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqV+mKuv22bG4N/WykEIG"
			+ "qwqRyvia6To8nzyOTzTrIwpYRJAI7qzVgNKBoUh0DI+yqWa+yjWvAWZeQstFDDKs"
			+ "VRQeilDyKVRrxMF4/qW5bFSe+LJpGTqeqT2dPvtQE840OuZUkcewexui/S4KVy1+"
			+ "K3maG98J9nl2R7qnob9EK5AzQ3tfHp4m3leuWZ6LwADzSFOy5Ot2fZ9dIOLZAais"
			+ "BXNEiHa83uhO0FW/b9gMgtTlogE4EH4mgjdGWtCtsTkrWO0uUCRgFiW7lXQUYvcA"
			+ "FRqSPOdvw/n8xcDQKCVlqLqxQUQ38Ro46E8Dx1OiSyf48eK1ursGQ8HQo61pgQ6n" 
			+ "bwIDAQAB";

	public static byte[] rsaEncrypt(byte[] pubKey, byte[] plainText) throws Exception {
		KeySpec keySpec = new X509EncodedKeySpec(pubKey);
		Key publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);

		byte[] cipherText = null;
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		System.out.println(cipher.getAlgorithm());
		cipherText = cipher.doFinal(plainText);
		return cipherText;
	}

	public static byte[] rsaDecrypt(byte[] priKey, byte[] cipherText) throws Exception {

		KeySpec keySpec = new PKCS8EncodedKeySpec(priKey);
		Key privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
		byte[] plainText = null;
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		plainText = cipher.doFinal(cipherText);
		return plainText;
	}

	public static byte[] generateTDesKey() throws NoSuchAlgorithmException, InvalidKeySpecException {

		// Get a key generator for Triple DES(a.k.a DESede)
		KeyGenerator keygen = KeyGenerator.getInstance("DESede");

		// Using it to generate a key
		SecretKey secretKey = keygen.generateKey();
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		DESedeKeySpec keyspec = (DESedeKeySpec) keyfactory.getKeySpec(secretKey, DESedeKeySpec.class);
		return keyspec.getKey();
	}

	public static byte[] decryptTDes(byte[] key, byte[] cipherText) throws Exception {
		SecretKeySpec keyspec = new SecretKeySpec(key, "DESede");
		
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE, keyspec);
		byte[] plainText = cipher.doFinal(cipherText);
		
		return plainText;
	}

	public static byte[] encryptTDes(byte[] key, byte[] plainText) throws Exception {

		byte[] cipherText = null;
		Cipher cipher = Cipher.getInstance("DESede");
		SecretKeySpec keyspec = new SecretKeySpec(key, "DESede");
		cipher.init(Cipher.ENCRYPT_MODE, keyspec);
		plainText = cipher.doFinal(plainText);
		return plainText;
	}

	public static boolean verifyRsaSignature(String data, String signature, String priKey) throws Exception {
		KeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(priKey));
		PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
		Signature instance = Signature.getInstance("SHA256withRSA");
		instance.initVerify(publicKey);
		instance.update(data.getBytes("utf-8"));

		return instance.verify(Base64.getDecoder().decode(signature));
	}

	public static String sha256WithRsaSign(String data, String pubKey) throws Exception {
		KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pubKey));
		PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);

		Signature instance = Signature.getInstance("SHA256withRSA");
		instance.initSign(priKey);
		instance.update(data.getBytes("utf-8"));
		;
		byte[] encodedSignature = Base64.getEncoder().encode(instance.sign());

		return new String(encodedSignature, StandardCharsets.ISO_8859_1);
	}

}
