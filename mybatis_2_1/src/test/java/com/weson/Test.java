package com.weson;

import com.weson.dao.AccountDao;
import com.weson.domian2.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;
import java.util.List;

public class Test {

    private InputStream in;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        accountDao = sqlSession.getMapper(AccountDao.class);
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
    public void test(){
        List<Account> all = accountDao.findAll();

        for(Account a:all){
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }

    @org.junit.Test
    public void test2(){
        List<Account> all2 = accountDao.findAll2();

        for(Account a:all2){
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }
}
