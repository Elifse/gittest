package com.woniuxy;

import com.woniuxy.dao.UserDao;
import com.woniuxy.entity.User;
import com.woniuxy.util.MybatisUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //mybatis会自动创建dao接口的实现类,并自定创建实现类对象
        UserDao userDao = MybatisUtil.getDao(UserDao.class);
        User user = userDao.selectByAccount("admin");
        System.out.println(user);

        //关闭sqlSession
        MybatisUtil.closeSqlSession();
    }
}
