package org.cockloft.org.cockloft.user.authentication.sqls;

public class UserSqlSet {
    private UserSqlSet(){
        super();
    }
    public static String insertUserSql = "insert into user (`user_id`,`passwd`,`email`,`tel`,`register_time`) valuse (?,?,?,?,?)";
}
