package com.mycompany.myschool.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static byte[] digest(String a_szAlgorithm, byte[] a_input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(a_szAlgorithm);
        return md.digest(a_input);
    } 
     
    public static String GET_CRYPTO_MD5(String a_szSource) throws Exception {
        if (a_szSource == null) {
            throw new Exception("Can't conver to Message Digest String value!!");
        } 
         
        byte[] bip = digest("MD5", a_szSource.getBytes());
        String eip;
        String result = "";
        int nSize = bip.length;
         
        for (int i = 0; i < nSize; i++) {
            eip = "" + Integer.toHexString((int) bip[i] & 0x000000ff);
            if (eip.length() < 2) {
                eip = "0" + eip;
            } 
            result = result + eip;
        } // end for
         
        return result;
    } 
     
    public static void main(String[] ar) throws Exception {
    	String password = "111";
    	
    	System.out.println(GET_CRYPTO_MD5(password));
    }
} 
