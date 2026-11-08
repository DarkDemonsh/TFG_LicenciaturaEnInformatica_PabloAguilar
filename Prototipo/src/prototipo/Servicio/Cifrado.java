package prototipo.Servicio;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {

    private static final String ALGORITMO = "AES";

    private static final byte[] VALOR_CLAVE = "MiClaveSecreta16".getBytes();

    public static String encriptar(String valor) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(VALOR_CLAVE, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytesEncriptados = cipher.doFinal(valor.getBytes());
            return Base64.getEncoder().encodeToString(bytesEncriptados);
        } catch (Exception e) {
            System.out.println("Error al encriptar: " + e.getMessage());
            return null;
        }
    }

    public static String desencriptar(String valorEncriptado) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(VALOR_CLAVE, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] bytesDesencriptados = cipher.doFinal(Base64.getDecoder().decode(valorEncriptado));
            return new String(bytesDesencriptados);
        } catch (Exception e) {
            System.out.println("Error al desencriptar: " + e.getMessage());
            return null;
        }
    }

}
