package com.example.pc.greendaotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pc.greendaotest.bean.User;
import com.example.pc.greendaotest.dao.UserDao;
import com.example.pc.greendaotest.db.DbCore;
import com.example.pc.greendaotest.db.DbUtil;
import com.example.pc.greendaotest.db.UserHelper;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserHelper userHelper;
    private List<User> userList;
    private List<User> mUsers;
    private RecyclerView mRvMain;
    private MAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据库
        DbCore.init(this);
//        DbCore.enableQueryBuilderLog(); //开启调试 log

        userHelper = DbUtil.getUserHelper();
        //读取所有的用户
        userList = userHelper.queryAll();

        mRvMain = findViewById(R.id.rv_main);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRvMain.setLayoutManager(lm);
        adapter = new MAdapter(this);
        mRvMain.setAdapter(adapter);

        mUsers = new ArrayList<>();
        for (User s : userList) {
            User item = new User();
            item.setId(s.getId());
            item.setName(s.getName());
            mUsers.add(item);
        }

        if(userList.size()>0)
            adapter.setNewData(mUsers);

        //获取age大于20的数据
        Query<User> query = userHelper.queryBuilder()
                .where(UserDao.Properties.Name.ge("20"))
                .build();
        userList = query.list();
    }

    public void add(View v){
        long id = userHelper.count();
        //界面添加
        User user = new User();
        user.setId(id + 1);
        user.setName("Nauto_" + (id + 1));
        user.setScore(99);
        //保存到数据库s
        userHelper.save(user);
        adapter.add(user);
    }

    public void minus(View v){
        userList = userHelper.queryAll();
        if (userList.size() > 0) {
            User s = userList.get(userList.size() - 1);
            //删除一条记录
            userHelper.delete(s);
            adapter.remove(userList.size()-1);
        } else {
            showToast("no student now");
        }
    }


    private Toast mToast;
    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.show();
        }
    }
}
