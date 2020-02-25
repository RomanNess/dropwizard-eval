package dropwizard.eval.controller;

import dropwizard.eval.dao.model.User;
import dropwizard.eval.service.UserService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    @GET
    public List<User> listAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{userId}")
    public User getUser(@PathParam("userId") Long userId) {
        return userService.getUser(userId);
    }

    @PUT
    @Path("/{userId}")
    public User updateUser(@PathParam("userId") Long userId, User user) {
        return userService.updateUser(userId, user);
    }

    @DELETE
    @Path("/{userId}")
    public void deleteUser(@PathParam("userId") Long userId) {
        userService.deleteUser(userId);
    }
}
