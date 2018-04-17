package com.dimax.retrospectator;


import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.util.UUID;

public class Base64Formater {


    public static String uuidToBase64(String str) {
        Base64 base64 = new Base64();
        UUID uuid = UUID.fromString(str);
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return base64.encodeBase64URLSafeString(bb.array());
    }
    public static String uuidFromBase64(String str) {
        Base64 base64 = new Base64();
        byte[] bytes = base64.decodeBase64(str);
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        UUID uuid = new UUID(bb.getLong(), bb.getLong());
        return uuid.toString();
    }
}
