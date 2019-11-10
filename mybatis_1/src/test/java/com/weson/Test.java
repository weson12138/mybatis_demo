package com.weson;

import com.weson.dao.UserDao;
import com.weson.domian2.QueryVo;
import com.weson.domian2.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
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
        User user = new User();
        user.setUserName("%王%");
        user.setUserSex("男");

        List<User> users = userDao.findByCoundition(user);

        for(User u:users){
            System.out.println(u);
        }
    }

    @org.junit.Test
    public void test2(){
        QueryVo vo = new QueryVo();
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(41);
        integers.add(42);

        vo.setIds(integers);

        List<User> users = userDao.findUserInIds(vo);

        for(User u:users){
            System.out.println(u);
        }
    }
}
