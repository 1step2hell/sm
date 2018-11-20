package com.step2hell.newsmth.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {

    public static String md5(String src) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(src.getBytes());
            byte[] digestBytes = digest.digest();

            // Create Hex String
            StringBuilder builder = new StringBuilder();
            for (byte digestByte : digestBytes) {
                String hex = Integer.toHexString(0xFF & digestByte);
                while (hex.length() < 2)
                    hex = "0" + hex;
                builder.append(hex);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
