package com.ujiuye.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujiuye.pojo.ResultVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Bob
 * @Create 2021-07-21-15:29
 */
public class WriteJsonUtils {

    /**
     * 将字符串转换为json 并传给前端
     * @param vo
     * @param resp
     */
    public static void writeJson(ResultVo vo, HttpServletResponse resp) {
        try {
            String json = new ObjectMapper().writeValueAsString(vo);
            resp.getWriter().print(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
