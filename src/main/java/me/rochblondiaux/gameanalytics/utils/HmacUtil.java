package me.rochblondiaux.gameanalytics.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HmacUtil {

    public static String generateHmac(byte[] data, String secretKey) {
        try {
            byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            byte[] hashBytes = mac.doFinal(data);
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC", e);
        }
    }
}
