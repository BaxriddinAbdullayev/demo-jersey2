package uz.leeway.jersey.lesson01.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DbUtils {

    private static SqlSessionFactory factory;

    private static SqlSessionFactory getInstance() {
        if (factory == null) {
            factory = sqlSessionFactory();
        }
        return factory;
    }

    private static SqlSessionFactory sqlSessionFactory() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getSqlSession() {
        return getInstance().openSession(true);
    }

    public static SqlSession getTransaction(){
        return getInstance().openSession(false);
    }
}
