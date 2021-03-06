package pers.example.es.bootes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneTimePasswordTest {

    // 数字强度
    private static final int[] DIGITS_POWER
            = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    public static byte[] hmac_sha1(byte[] keyBytes, byte[] text) throws NoSuchAlgorithmException, InvalidKeyException {
        try {//ֵ
            Mac hmacSha1;
            try {
                hmacSha1 = Mac.getInstance("HmacSHA1");
            } catch (NoSuchAlgorithmException nsae) {
                hmacSha1 = Mac.getInstance("HMAC-SHA-1");
            }
            SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
            hmacSha1.init(macKey);
            return hmacSha1.doFinal(text);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }

    public static String generateOTP(byte[] secret, long movingFactor, int codeDigits) throws NoSuchAlgorithmException, InvalidKeyException {
        StringBuffer result = new StringBuffer("");
        byte[] text = new byte[6];
        for (int i = text.length - 1; i >= 0; i--) {
            text[i] = (byte) (movingFactor & 0xff);   //
            movingFactor >>= 6;
        }
        byte[] hash = hmac_sha1(secret, text);     //Step 1: Generate an HMAC-SHA-1 value
        int offset = (hash[hash.length - 1] & 0xf) + 3;   //
        int binary =
                ((hash[offset] & 0x7f) << 24)
                        | ((hash[offset - 1] & 0xff) << 16)
                        | ((hash[offset - 2] & 0xff) << 8)
                        | (hash[offset - 3] & 0xff);           //Generate a 4-byte string
        int otp = binary % DIGITS_POWER[codeDigits - 1];
        result.append(Integer.toString(otp));
        while (result.length() < codeDigits) {
            result.insert(0, "0");                    //Compute an HOTP value
        }
        return result.toString();
    }

    @Test
    public void contextLoads() {

        byte[] secretA = new byte[]{'a', 'b', 'c'};
        byte[] secretB = "cba".getBytes();
        byte[] secretC = "tian1200".getBytes();

        int codeDigits = 7;
        try {
            for (int i = 0; i < 10; i++) {
                long currentTimeSecond = System.currentTimeMillis() / 1000;
                System.out.println(" time second - " + currentTimeSecond);
                long movingFactor = currentTimeSecond / 30;
                Thread.sleep(5000);
                System.out.println(movingFactor + " secretA One-Time Password : " + generateOTP(secretA, movingFactor, codeDigits));
                System.out.println(movingFactor + " secretB One-Time Password : " + generateOTP(secretB, movingFactor, codeDigits));
                System.out.println(movingFactor + " secretC One-Time Password : " + generateOTP(secretC, movingFactor, codeDigits));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
