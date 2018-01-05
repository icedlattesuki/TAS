package com.se.global.service;

import org.springframework.web.multipart.MultipartFile;
import javax.xml.bind.DatatypeConverter;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class EncryptService {
    public static String getFileMD5(MultipartFile file) {
        int bufferSize = (int)file.getSize();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            DigestInputStream digestInputStream = new DigestInputStream(file.getInputStream(), messageDigest);
            byte[] buffer = new byte[bufferSize];

            while (digestInputStream.read(buffer) > 0) {

            }

            messageDigest = digestInputStream.getMessageDigest();
            byte[] res = messageDigest.digest();
            return DatatypeConverter.printHexBinary(res).toUpperCase();
        } catch (Exception e) {

        }

        return "";
    }
}
