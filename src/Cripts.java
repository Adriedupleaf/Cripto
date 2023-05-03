import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Cripts {
    public static KeyPair gRSAkey() throws NoSuchAlgorithmException {
        KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
        key.initialize(2048);
        return key.generateKeyPair();
    }

    public static SecretKey gAESkey() throws NoSuchAlgorithmException {
        KeyGenerator key = KeyGenerator.getInstance("AES");
        key.init(256);
        return key.generateKey();
    }

    public static byte[] enAES(String input, SecretKey key) throws Exception {
        Cipher cph = Cipher.getInstance("AES");
        cph.init(Cipher.ENCRYPT_MODE, key);
        return cph.doFinal(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String decAES(byte[] input, SecretKey key) throws Exception {
        Cipher chp = Cipher.getInstance("AES");
        chp.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = chp.doFinal(input);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static byte[] signM(byte[] input, PrivateKey privateKey) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(input);
        return sign.sign();
    }

    public static boolean signV(byte[] input, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(publicKey);
        sign.update(input);
        return sign.verify(signatureBytes);
    }

    public static byte[] enRSA(byte[] input, PublicKey publicKey) throws Exception {
        Cipher cph = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cph.init(Cipher.ENCRYPT_MODE, publicKey);
        return cph.doFinal(input);
    }

    public static byte[] decRSA(byte[] input, PrivateKey privateKey) throws Exception {
        Cipher cph = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cph.init(Cipher.DECRYPT_MODE, privateKey);
        return cph.doFinal(input);
    }
}
