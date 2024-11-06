package uz.leeway.jersey.lesson01.service;

import org.apache.ibatis.session.SqlSession;
import uz.leeway.jersey.lesson01.db.DbUtils;
import uz.leeway.jersey.lesson01.entity.UserEntity;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    public UserEntity getUserById(int id) {
        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("id", 1);
            return sqlSession.selectOne("getUserById", param);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public UserEntity getUserByUsername(String username) {
        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("id", 1);
            return sqlSession.selectOne("getUserById", param);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



//    public void createUser(UserEntity user) {
//
//
//
//        users.put(user.getUsername(), user);
//    }
//
//    public void updateUser(UserEntity user) {
//
//
//
//        users.put(user.getUsername(), user);
//    }
//
//    public void deleteUser(String username) {
//
//
//
//        users.remove(username);
//    }
}
