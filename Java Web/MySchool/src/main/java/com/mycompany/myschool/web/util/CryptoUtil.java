package com.mycompany.myschool.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class CryptoUtil {
	private static final String CRYPTO_ALGORITHM = "AES";
	private static final int DEFAULT_SIZE = 256;
	
	private static final String getPath() {
		if (CryptoUtil.class.getResource("/") != null) {
			return CryptoUtil.class.getResource("/").getPath();
		} else {
			return "";
		}
	}
	
	public static String encrypt(String input) {
		return new String(Base64.encodeBase64(doCrypto(Cipher.ENCRYPT_MODE, getKen(DEFAULT_SIZE), input.getBytes())));
	}
	
	public static String encrypt(Key key, String input) {
		return new String(Base64.encodeBase64(doCrypto(Cipher.ENCRYPT_MODE, key, input.getBytes())));
	}
	
	public static String decrypt(String input) throws UnsupportedEncodingException {
		return new String(doCrypto(Cipher.DECRYPT_MODE, getKen(DEFAULT_SIZE), Base64.decodeBase64(input)), "UTF-8");
	}
	
	public static String decrypt(Key key, String input) throws UnsupportedEncodingException {
		return new String(doCrypto(Cipher.DECRYPT_MODE, key, Base64.decodeBase64(input)), "UTF-8");
	}
	
	public static byte[] doCrypto(int cipherMode, Key key, byte[] input) {
		try {
			Cipher cipher = Cipher.getInstance(CRYPTO_ALGORITHM);
			cipher.init(cipherMode, key);
			return cipher.doFinal(input);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Key getKen(int size) {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance(CRYPTO_ALGORITHM);
			keyGen.init(size);
			Key key =null;
					
			boolean isRead = false;
			File path = new File(getPath());
			File file = new File(path, "/conf/keystore.jck");
			if (file.exists()) {
				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(new FileInputStream(file));
					Object obj = ois.readObject();
					if (obj != null && obj instanceof Key) {
						key = (Key)obj;
						ois.close();
						isRead = true;
					} 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (!isRead) {
				ObjectOutputStream oos;
				try {
					path.mkdirs();
					oos = new ObjectOutputStream(new FileOutputStream(file));
					key = keyGen.generateKey();
					oos.writeObject(key);
					oos.close();
				} catch (Exception e) {
					
				}
			}
			
			return key;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	
}
