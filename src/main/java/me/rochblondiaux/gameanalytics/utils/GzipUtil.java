package me.rochblondiaux.gameanalytics.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GzipUtil {

    public static byte[] gzip(String s) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        OutputStreamWriter osw = new OutputStreamWriter(gzip, StandardCharsets.UTF_8);
        osw.write(s);
        osw.close();
        return bos.toByteArray();
    }
}
