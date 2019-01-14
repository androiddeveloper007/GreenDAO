package com.example.pc.greendaotest.db;

import com.example.pc.greendaotest.dao.UserDao;

/**
 * 说   明:  获取表 Helper 的工具类
 */
public class DbUtil {
    private static UserHelper userHelper;

    private static UserDao getUserDao(){
        return DbCore.getDaoSession().getUserDao();
    }

    public static UserHelper getUserHelper(){
        if(userHelper==null){
            userHelper=new UserHelper(getUserDao());
        }
        return userHelper;
    }
}
