package com.qingqiao.dao;

import com.qingqiao.entity.User;
import com.qingqiao.utils.DbUtil;
import com.qingqiao.utils.PageUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<User> queryAll(PageUtil pageUtil,String mohu){
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username like ? limit ?,?");
            preparedStatement.setString(1,"%"+mohu+"%");
            preparedStatement.setInt(2,pageUtil.getStartIndex());
            preparedStatement.setInt(3,pageUtil.getPageCount());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public User login(String username, String password) {
        User user = null;
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username = ? and password = ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public int getCount(String mohu) {
        int count = 0;
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from user where username like ?");
            preparedStatement.setString(1,"%"+mohu+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
}
