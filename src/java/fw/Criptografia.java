/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fw;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author dirceu
 */
public class Criptografia {

    public static String sha1(String value) throws Exception {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            String sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
            return sha1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
