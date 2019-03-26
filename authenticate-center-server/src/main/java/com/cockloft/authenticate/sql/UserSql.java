package com.cockloft.authenticate.sql;

public class UserSql {
    public static String find_user_by_id= "select * from user where userId = ?";
    public static String regiter_user = "insert into user set () values (?,?,?,?,?,?)";
}
