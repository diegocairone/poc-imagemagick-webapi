package com.cairone.im.util;


import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;

public class ImageUtil {
	
	public static String determineMimeType(InputStream inputStream, Metadata metadata) {
        DefaultDetector detector = new DefaultDetector();
        String mimeType = null;

        try {
            MediaType e = detector.detect(inputStream, metadata);
            if (e != null) {
                mimeType = e.getType() + "/" + e.getSubtype();
            }
        } catch (IOException e) {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    public static String detectMimeType(InputStream inputStream) {
        return detectMimeType(inputStream, "");
    }
    
    public static String detectMimeType(InputStream inputStream, String filename) {
        Metadata e = new Metadata();
        e.set("resourceName", filename);
        return determineMimeType(inputStream, e);
    }
}
