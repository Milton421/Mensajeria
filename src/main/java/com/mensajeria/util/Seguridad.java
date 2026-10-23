package com.mensajeria.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/** Hash seguro de contrasenas con PBKDF2 (formato: iteraciones:salt:hash). */
public final class Seguridad {

    private static final int ITERACIONES = 120_000;
    private static final int LONGITUD_BITS = 256;

    private Seguridad() {
    }

    public static String hash(String clave) {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        byte[] h = derivar(clave.toCharArray(), salt, ITERACIONES);
        return ITERACIONES + ":" + Base64.getEncoder().encodeToString(salt)
                + ":" + Base64.getEncoder().encodeToString(h);
    }

    public static boolean verificar(String clave, String almacenado) {
        try {
            String[] partes = almacenado.split(":");
            int iter = Integer.parseInt(partes[0]);
            byte[] salt = Base64.getDecoder().decode(partes[1]);
            byte[] esperado = Base64.getDecoder().decode(partes[2]);
            byte[] calculado = derivar(clave.toCharArray(), salt, iter);
            return MessageDigest.isEqual(esperado, calculado);
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static byte[] derivar(char[] clave, byte[] salt, int iter) {
        try {
            PBEKeySpec spec = new PBEKeySpec(clave, salt, iter, LONGITUD_BITS);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("No se pudo calcular el hash", e);
        }
    }
}
