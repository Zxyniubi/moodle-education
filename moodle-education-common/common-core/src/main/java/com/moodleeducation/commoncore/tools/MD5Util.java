package com.moodleeducation.commoncore.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String MD5Utils(String source,String salt,int Iterations){
        MessageDigest digest;
        try{
            digest = MessageDigest.getInstance("MD5");
            byte[] saltBytes = salt.getBytes("UTF-8");
            byte[] sourceBytes = source.getBytes("UTF-8");
            digest.reset();
            digest.update(saltBytes);
            byte[] hashed = digest.digest(sourceBytes);
            for(int i=0;i<Iterations-1;i++){
                digest.reset();
                hashed = digest.digest(hashed);
            }
            return encodeToString(hashed);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String encodeToString(byte[] bytes) {
        char[] encodedChars = encode(bytes);
        return new String(encodedChars);
    }
    private static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static char[] encode(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;

        for(int var4 = 0; i < l; ++i) {
            out[var4++] = DIGITS[(240 & data[i]) >>> 4];
            out[var4++] = DIGITS[15 & data[i]];
        }

        return out;
    }
}
