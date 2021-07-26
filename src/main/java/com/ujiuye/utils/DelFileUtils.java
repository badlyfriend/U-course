package com.ujiuye.utils;

import javax.servlet.http.Part;
import java.io.File;

/**
 * @Author Bob
 * @Create 2021-07-23-21:45
 */
public class DelFileUtils {

    public static boolean delFile(String fileName) {

        return new File("D:\\User\\stuPhoto\\" + fileName).delete();

    }
}
