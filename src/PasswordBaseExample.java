import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import com.mysql.cj.util.Base64Decoder;
import sun.misc.BASE64Encoder;

public class PasswordBaseExample {
    static final String ALGO = "FPTaptechMD5AndDES";

    public static void main(String[] args) throws Exception {
        long curt = System.currentTimeMillis();
        /*
    1. Base for password
    2. Create Key based on keyspec by password
    3. Create Salte
    4. Parameter for decrypt/encrypt mode
     */
        String password = "khong co password dau dung tim vo ich";
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGO);
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKey secretKey = skf.generateSecret(keySpec);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salte = random.generateSeed(8);
        PBEParameterSpec param = new PBEParameterSpec(salte,8);
        Cipher cipher = Cipher.getInstance(ALGO);

        //Encrypt
        cipher.init(cipher.ENCRYPT_MODE,secretKey,param);
        String clear = "Encrypt for java";
        byte[] encrypted = cipher.doFinal(clear.getBytes());
        Base64Decoder encoder = new Base64Decoder();

        //Decrypt
    }
}
