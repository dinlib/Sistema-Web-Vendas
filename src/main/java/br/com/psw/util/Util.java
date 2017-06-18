package br.com.psw.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author F.Carvalho / M. Hirose / V.Camargo
 */
public class Util {

    /**
     * método que encripta a senha para MD5
     *
     * @param senha
     * @return senhaMD5
     */
    public static String converteSenhaDigitadaParaMd5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Não deu certo! Tudo culpa sua!");
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;
    }
}
