package com.woniuxy.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybaits工具类
 */

public class MybatisUtil {
    //SqlSessionFactory工厂
    private static SqlSessionFactory sqlSessionFactory=null;
    //创建本地线程变量，保证每次只有一个SqlSession对象
    private static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<SqlSession>();

    static{
        //读取配置文件
        try {
            InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得sqlsession对象
     * @return
     */
    public static SqlSession getSqlSession() {
        //从本地线程变量中取出一个sqlsession对象
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            //将sqlsession保存到threadLocal
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭sqlsession
     */
    public static void closeSqlSession() {
        // 从当前线程获取
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            //提交事务
            sqlSession.commit();
            //释放sqlSession
            sqlSession.close();
            //清空当前线程对应sqlsession
            threadLocal.set(null);
        }
    }

    /**
     * 获取动态代理Dao接口对象
     *
     * @param <T>
     * @param c
     * @return
     */
    public static <T> T getDao(Class<T> c) {
        return getSqlSession().getMapper(c);
    }
}

