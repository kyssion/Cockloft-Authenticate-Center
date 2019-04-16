package com.vert.x.sql;

import com.alibaba.druid.pool.DruidDataSource;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestSql {
    public static void main(String[] args) throws IOException {
        Vertx vertx = Vertx.vertx();
        DruidDataSource druidDataSource = new DruidDataSource();
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = TestSql.class.getClassLoader().getResourceAsStream("db.properties");
        // 使用properties对象加载输入流
        properties.load(in);
        druidDataSource.configFromPropety(properties);
        SQLClient client = JDBCClient.create(vertx, druidDataSource);
        client.getConnection(res->{

        });
        client.query("SELECT * FROM App", ar -> {
            if (ar.succeeded()) {
                ResultSet result = ar.result();
                System.out.println("ok");
            } else {
                // Failed!
                System.out.println("failed");
            }
        });
    }
}
