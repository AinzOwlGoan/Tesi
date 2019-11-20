package encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64.*;

//import org.apache.commons.codec.binary.Base64;

public class aes {
	private static String key = "mvLBiZsiTbGwrfJB";
	private static  String sol = "8AnmNnf6b/RJh/LezEXfnA==";
	public static String encrypt(String input) {
		byte[] crypted = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			crypted = cipher.doFinal(input.getBytes());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
		
		if(encoder.encodeToString(crypted).equals(sol)) {
			return "ACCESS";
		}
		return new String(encoder.encodeToString(crypted));
	}

	public static String decrypt(String input) {
		byte[] output = null;
		try {
			java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			output = cipher.doFinal(decoder.decode(input));
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return new String(output);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
// TODO Auto-generated method stub
		//String key = "mvLBiZsiTbGwrfJB";
		String data = "d7Nr7d2k19!?";
		System.out.println(aes.encrypt(data));
		System.out.println(aes.decrypt(aes.encrypt(data)));
	}


}