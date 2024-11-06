package uz.leeway.jersey.lesson01.controller;

import uz.leeway.jersey.lesson01.entity.UserEntity;
import uz.leeway.jersey.lesson01.service.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserController {

    UserService userService=new UserService();

    @GET
    @Path("/get")
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
}
