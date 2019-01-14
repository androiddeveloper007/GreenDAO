package com.example.pc.greendaotest.db;

import android.content.Context;

import com.example.pc.greendaotest.dao.DaoMaster;
import com.example.pc.greendaotest.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * 说   明: 核心辅助类，用于获取DaoMaster和DaoSession
 */
public class DbCore {
    private static final String DEFAULT_DB_NAME = "green_dao_test.db";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static Context mContext;

    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        mContext = context.getApplicationContext();
    }

    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            //此处不可用 DaoMaster.DevOpenHelper, 那是开发辅助类，我们要自定义一个，方便升级
            DaoMaster.OpenHelper helper = new MyOpenHelper(mContext, DEFAULT_DB_NAME);
//            daoMaster = new DaoMaster(helper.getEncryptedReadableDb("password"));
            daoMaster = new DaoMaster(helper.getReadableDb());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static void enableQueryBuilderLog(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
