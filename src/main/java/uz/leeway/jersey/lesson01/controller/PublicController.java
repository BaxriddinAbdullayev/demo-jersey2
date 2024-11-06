package uz.leeway.jersey.lesson01.controller;
import org.apache.ibatis.session.SqlSession;
import uz.leeway.jersey.lesson01.annotation.Public;
import uz.leeway.jersey.lesson01.db.DbUtils;
import uz.leeway.jersey.lesson01.domain.transport.LoginRequest;
import uz.leeway.jersey.lesson01.entity.UserEntity;
import uz.leeway.jersey.lesson01.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


@Path("/public")
public class PublicController {

    @Context
    private HttpServletRequest servletRequest;

    UserService userService=new UserService();

    @POST
    @Public(name = "public",value = "public")
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(LoginRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        if (isValidUser(username, hashPassword(password))) {
            HttpSession session = servletRequest.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return Response.ok("Assalomu alaykum!").build();
        }

        return Response.ok("User topilmadi!").build();

//        String authzHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        String base64Credentials = authzHeader.substring("Basic".length()).trim();
//        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
//        final String[] values = credentials.split(":", 2);
//        String username = values[0];
//        String password = values[1];
//        if (isValidUser(username, hashPassword(password))) {
//            HttpSession session = request.getSession(true);
//            session.setAttribute("username", username);
//        }
//        return Response.ok("Xush kelibsiz!").build();
    }

    @GET
    @Path("/private")
    @Produces({MediaType.APPLICATION_JSON})
    public UserEntity getById() {
        UserEntity userById = userService.getUserById(1);
        return userById;
    }

    @GET

    @Path("/getuser")
    @Produces({MediaType.APPLICATION_JSON})
    public UserEntity getByUsername() {
        UserEntity user = userService.getUserByUsername("bahriddin");
        return user;
    }


    private boolean isValidUser(String username, String password) {
        try (SqlSession sqlSession = DbUtils.getSqlSession()) {
            Map<String, Object> param = new HashMap<>();
            param.put("username", username);
            UserEntity userEntity = sqlSession.selectOne("getUserByUsername", param);

            if (userEntity != null &&
                    userEntity.getUsername().equals(username) &&
                    userEntity.getPassword().equals(password)) {
                return true;
            }
            return false;
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(password.getBytes());

            // Hash natijani hex formatga o'tkazish
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }



}
