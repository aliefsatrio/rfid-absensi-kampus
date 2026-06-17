/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author bolem
 */
public class SecurityUtils {

    public static final String SHA_256 = "SHA-256";

    public static String getHash(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);

            byte[] hashBytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    sb.append('0');
                }

                sb.append(hex);
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}