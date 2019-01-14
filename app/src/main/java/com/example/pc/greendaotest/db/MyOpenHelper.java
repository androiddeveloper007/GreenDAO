package com.example.pc.greendaotest.db;

import android.content.Context;
import android.util.Log;

import com.example.pc.greendaotest.dao.DaoMaster;
import com.example.pc.greendaotest.dao.UserDao;

import org.greenrobot.greendao.database.Database;

public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.w("","db version update from " + oldVersion + " to " + newVersion);

        switch (oldVersion) {
            case 1:

                //不能先删除表，否则数据都木了
//                UserDao.dropTable(db, true);

                UserDao.createTable(db, true);

                // 加入新字段 score
                db.execSQL("ALTER TABLE 'USER' ADD 'SCORE' TEXT;");

                break;
        }

    }
}
