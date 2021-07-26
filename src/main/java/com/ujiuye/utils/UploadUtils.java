package com.ujiuye.utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author Bob
 * @Create 2021-07-23-16:05
 */
public class UploadUtils {

    public static String uploadFiles(Part part) {

        String filename = part.getSubmittedFileName();

        File file = new File("D:\\User\\stuPhoto");

        if (!file.exists()) {
            file.mkdir();
        }

        filename = UUID.randomUUID() + filename;

        try {
            part.write(file + "/" + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }

}
