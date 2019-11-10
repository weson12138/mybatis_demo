package com.weson;

import com.weson.dao.UserDao;
import com.weson.domian5.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;

public class Test {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;
    private  SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        // sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }


    @org.junit.Test
    public void test1(){
        User user1 = userDao.findById(41);
        System.out.println("第一次查询用户："+user1);

        User user2 = userDao.findById(41);
        System.out.println("第二次查询用户："+user2);

        System.out.println( user1 == user2);
    }


    @org.junit.Test
    public void test2(){
        User user1 = userDao.findById(41);
        System.out.println("第一次查询用户："+user1);

        sqlSession.close();
        SqlSession sqlSession = factory.openSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        User user2 = mapper.findById(41);
        System.out.println("第二次查询用户："+user2);

        System.out.println( user1 == user2);
    }

    @org.junit.Test
    public void test3(){
        User user1 = userDao.findById(41);
        System.out.println(user1);

        user1.setUsername("update user clear cache");
        user1.setAddress("北京市海淀区");
        userDao.updateUser(user1);

        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }
}
