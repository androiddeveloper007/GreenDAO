package com.example.pc.greendaotest.db;

import com.example.pc.greendaotest.bean.User;

import org.greenrobot.greendao.AbstractDao;

/**
 * 说   明: 具体表的实现类
 */
public class UserHelper extends BaseDbHelper<User, Long>{
    public UserHelper(AbstractDao dao) {
        super(dao);
    }
}
