package com.zheng.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class utils {
    private static Properties p = new Properties();
    private static DataSource dataSource = null;
    static {
        try {
        p.load(utils.class.getResourceAsStream("/druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
    public static void  getSleep(int time){
        try {Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
