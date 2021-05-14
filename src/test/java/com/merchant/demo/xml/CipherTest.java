package com.merchant.demo.xml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import com.merchant.demo.xml.cipher.Ciphers;

class CipherTest {
	
	private static String bcPrivateKey= 
			"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDVK5psKhhUqqQA"
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
	
	private static String bcPublicKey = 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1SuabCoYVKqkAKrl/j24"
			+ "13Zp4udCXVkej3YidN3JYt/tf4gbeWn/LJgCNR5tFpeKGE0ARZpaD6XCIHDWhQxe"
			+ "naV15ymVJS6E5Ub3QGZw2qe9KEfKEPBjo+XR86RZqIctevXggMTmNPJSYTPvti+X"
			+ "ZdNMi58RFpERpYbJRGFiocMSTVS3nE5hkJ06j+TpDM8w5R+avrya77g0jR9h7DqB"
			+ "t83o+pvsjXI5r0hrCd8h58/sQlcgA3C3PftPfdysLeIbTxcqDsfxwZbIs4F3ZqOk"
			+ "OK7JLaLKphndVbVmRbtJT8xxk5237p9VrpohhM7hyQXXrVtsgLzsGH3zBsiYSwzC"
			+ "mQIDAQAB";
	
	private static String lpbDevPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/8s+3EELKBFwUZYrl2fRrzaOL0fKILirtHkJloUjq8AdOqywOhCa5op9g652NVbF73/aDVabHj4o43qODpMmyoQpcURtKJJOYHboWVb5qw+tK10V/FNdlPjxArlniOD2Gn+m4eV3AUykd8uujnznVImi/5U+v6w7/QTW+pJBhPQIDAQAB";

	private static String lpbProdPublicKey = 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqV+mKuv22bG4N/WykEIG"
			+ "qwqRyvia6To8nzyOTzTrIwpYRJAI7qzVgNKBoUh0DI+yqWa+yjWvAWZeQstFDDKs"
			+ "VRQeilDyKVRrxMF4/qW5bFSe+LJpGTqeqT2dPvtQE840OuZUkcewexui/S4KVy1+"
			+ "K3maG98J9nl2R7qnob9EK5AzQ3tfHp4m3leuWZ6LwADzSFOy5Ot2fZ9dIOLZAais"
			+ "BXNEiHa83uhO0FW/b9gMgtTlogE4EH4mgjdGWtCtsTkrWO0uUCRgFiW7lXQUYvcA"
			+ "FRqSPOdvw/n8xcDQKCVlqLqxQUQ38Ro46E8Dx1OiSyf48eK1ursGQ8HQo61pgQ6n"
			+ "bwIDAQAB";
	
	@Test
	void decResKey() throws Exception {
		
		String resKey ="ynduOZph2rP64YsuiNPsU+Dudl0ZuuH34Wm/3D7AKr0bAbzatdJTDuttwztm/LbK3ieku64lTNB8eggFemask0ZEMmFOTna1vD82XWRFfPRPT40Nq7JQbpEHmSuvp+8I45AzMiSTf6EHmt+Qz5/EKS9eLqw9sotCDYegwjkuc0kuhaEzM07LZ1FFJGDyXHZ2sHG6XBO3qDwgDGQEdNW51qPh0hTKGCxNyH8+6qOWxKHcyt30Od/n+0ksAEt94/u0TcK7aoNYdS+NVmy9sBjQfXxXIUCU1D4r6splaquPDWYQTggHJzW4kcBghZ3QA6t38rLm9dS6qJO0F31FETV8Sg==";

	
		String hello = "wuBvwVhU9EisY/ynT4342TXbsKUe8xhmI5zj+59wM2JoMeD9yihWZgDL0tl7zagSrGo8TNlyxULByodTFVpsbSgr7Szu6OMflcDibTcIeeZIardb/ezo53WiQCZPJ93DbMeEFOIKBGalA5Di03aupiY7rEkS3hgfUhaqzArg54PcLST2ZSxejaxrLR8PrwgQAHyw3kaVw3f+oNwy+06zx7qjw7rKELiWZpd1YHaCUE+V0cDqBkReVtzzVhfblrRoKnwsABJ4X4wktAU9+XMe49WqxpIi2LitSbN9Lp1mFqDSfgRAl6va8FCX+S2aMyyVJRv1npgy+5vtDy8yToILXw==";
		
		String hell2 = "T221EXs0OZsCpWDXynzUymz6iMmuqUBj6AOns72JwoTX9oGUXJNCSQg8HbYKObGjVj05Sn9ORqQBSDWdfmitOSC3B1dqTnvL5ZGGrg0D0ft9EJsKPRgkneM4zpcjdPWvKRhkLO6p59jxtIcnqzTIgDIEf4u6o+PbwnDGD0YawcedNpPJ1ZkFLXSVhhM16jipRuch+t4X7EYUYzbcq0K22MbGKjzqzcZ/hzMBXcM+5P8wqxi//29rw+yzvXx56gaOSlEiLncn/2N6sA1uagIjlVv3XVkLytoZ+fGpCtVDQ+jlwQ2XP3i+H/AFHEIR9pJ/WFU3mTJb2yLoSv52g7aJtQ==";
		
		String hello4 = "fvx8Hsg/XJS+MUxg3/E/H2qGvb1VSZzSHzzwc/WCanurdH+6Kq5R6K/Thvbx6II7BcTKA2VLTR5p/vZDCh5Zo8SsVgzu94FFlPC4PK1PvLLeK98uAWeV2I40ZxXqCF9LKrKfO1o48R1EtTjB+X7avCO7oV5lzZ10QWnQjQkpJP6FMex5d5JUDWcwKb8r48Aq8f2q+0MiDrVemV6eoOrprD6jjC3ZDqdREaYgsDv3nBuR2iQI59MR6GKe16585J2ium2yokm5wrght8AGjSZSlXb4imd6z7+pkJldZvzRc07Z92s3Cvz1XKl85f+39L4cjnXGde+pMjvPQujQ2EE/UQ==";
		
		KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(bcPrivateKey));
		Key priKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
		
		byte[] d= Ciphers.rsaDecrypt( Base64.getDecoder().decode(bcPrivateKey), Base64.getDecoder().decode(hello4));
		System.out.println("Hell2:  " + new String(d));
		
		String vvv = "SGVsbG8gV29ybGQ=";
		System.out.println("vvv:" + new String(Base64.getDecoder().decode(vvv)));
		
		assertNotNull(d);
		
	}
	
	private static byte[] getTdesKey()  throws Exception {
		String resKey ="ynduOZph2rP64YsuiNPsU+Dudl0ZuuH34Wm/3D7AKr0bAbzatdJTDuttwztm/LbK3ieku64lTNB8eggFemask0ZEMmFOTna1vD82XWRFfPRPT40Nq7JQbpEHmSuvp+8I45AzMiSTf6EHmt+Qz5/EKS9eLqw9sotCDYegwjkuc0kuhaEzM07LZ1FFJGDyXHZ2sHG6XBO3qDwgDGQEdNW51qPh0hTKGCxNyH8+6qOWxKHcyt30Od/n+0ksAEt94/u0TcK7aoNYdS+NVmy9sBjQfXxXIUCU1D4r6splaquPDWYQTggHJzW4kcBghZ3QA6t38rLm9dS6qJO0F31FETV8Sg==";

		
		KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(bcPrivateKey));
		Key priKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
		
		byte[] d= Ciphers.rsaDecrypt( Base64.getDecoder().decode(bcPrivateKey), Base64.getDecoder().decode(resKey));
		
		return d;
	}
	
	@Test 
	void decTDES() throws Exception {
		String resData = "ee1EaPFstGYoLbIwqowuaXlnCeLvYf8K5IIUAm/Xp6mHS+mMv1ImVgPCVMC9PrLp9PnI+bDUDQuyn86/L6lo94vzXliZCCnNvUePvMUrW+JdQXSvA4o8Jr1Hj7zFK1vi6tMMx0Qe8Ywg9MFSr4XNXQZtOAK4Ae3CVUGe3dyBG6YzfIlxyCKPg5da4NvmZHybzEco9LIfJbPfyciRN77i1/i9MHR584i9K6PlHWpOlbRL0gp6dnJ9jjaAXJLVVwM1xFODPGYdDdPZR2TO7pvZecvylJrlBjD3cKPRapISeF2Y+vFMDO0hhLvGD8u52QHnvUePvMUrW+KyhtaVakh4XLNYLr/sFe1oN6cLtyRq4dsiOQfzuwzrriBYh364iHp5pCtHPLIe2Cv8WXh39y9ibrqQV345Z0XRsYWOGGhh/OyRgPy7tBLGfAxXcHyvAWsT9LGrHbHmYHq26lNGVt/rtDx41TnB6hrWz6Zan1V02c3asyyrTzpLMHaE0EgDCA7nGkm1Fb6ixQbKoN6OuorSl+6eV9feC/ZQd6nhdrUVDd86vUL/gqnWBIlXH/XcXAFJ+ck4LrXxQPZ++XiUj9DuL5dmin6NiCicMZxKunOSIZuxL+ptqUKhLzTTv0XW3DLUui2L1aCfR1Q=";
		
		byte[] d = Ciphers.decryptTDes(getTdesKey(), Base64.getDecoder().decode(resData));
		System.out.println(new String(d));
		
		assertNotNull(d);
	}
	
	@Test
	void getRsaPublicKey() throws Exception  {
		KeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(lpbDevPublicKey));
		Key publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
		System.out.println(publicKey);
		
		
		KeySpec keySpec2 = new X509EncodedKeySpec(Base64.getDecoder().decode(bcPublicKey));
		Key publicKey2 = KeyFactory.getInstance("RSA").generatePublic(keySpec2);
		
		System.out.println("HelloWorld: " + 
		new String(Base64.getEncoder().encode(Ciphers.rsaEncrypt(Base64.getDecoder().decode(bcPublicKey), "Hello World".getBytes()))));
		
		
		
		System.out.println(publicKey2);
		
		KeySpec keySpec3 = new X509EncodedKeySpec(Base64.getDecoder().decode(lpbProdPublicKey));
		Key publicKey3 = KeyFactory.getInstance("RSA").generatePublic(keySpec3);
		System.out.println(publicKey3);
		
		assertNotNull(publicKey);
		
	}
	
	private static String sigData = "<CommonResponse><ResponseHeader><ResponseDateTime>20210422133706</ResponseDateTime><ResponseCode>CONNECT-1102</ResponseCode><ResponseMessage>Không có quyền truy cập hệ thống</ResponseMessage></ResponseHeader>";
	private static String sig = "JYRcj9dpzo8PwISNJIIjsSXLysvLbuwtPAh/ewJRKkKahW8pa9FS1CoX1NkDmjYLFPFSw45RdK9qmxhOz15dpuz7crIk0zmQLebxHO+bHiiW+Ew/4X7cvVGfh52ovSqd2gM50XMEzyAH7FBtf878VnvVA0bPaJiPx84FiKNKdoM=";

	private static String sigData2 =   "ResponseDateTime" + "|" + "20210422133706" +"ResponseCode" + "|" + "CONNECT-1102" + "|" +   "|" + "ResponseMessage" + "|" + "Không có quyền truy cập hệ thống"  ;
	private static String sigData3 =  "CONNECT-1102" + "|" +"20210422133706" + "|" +"Không có quyền truy cập hệ thống"  ;

//			"ResponseCode"  +  "|" + "ResponseDateTime" + "|" +"ResponseMessage" + "|"; 
	private static String sigData4 =   "ResponseDateTime"  +  "|" +"ResponseCode" + "|" +"ResponseMessage" +"|" + "20210422133706" + "|" +"CONNECT-1102" + "|" +"Không có quyền truy cập hệ thống"  ;

	@Test
	void signature() throws Exception {
		assertTrue((Ciphers.verifyRsaSignature( sigData3  , sig, lpbDevPublicKey)));
	}
	
	
}
