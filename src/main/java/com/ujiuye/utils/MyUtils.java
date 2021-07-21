package com.ujiuye.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

/**
 * @Author Bob
 * @Create 2021-07-21-11:23
 */
public class MyUtils {
    public static final QueryRunner QR = new QueryRunner(new ComboPooledDataSource());
}
