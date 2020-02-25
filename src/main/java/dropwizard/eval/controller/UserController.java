package dropwizard.eval.controller;

import com.codahale.metrics.annotation.Timed;
import dropwizard.eval.dao.model.User;
import dropwizard.eval.service.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/{userId}")
    @Timed
    public User getUser(@PathParam("userId") Long userId) {
        return userService.getUser(userId);
    }

    @POST
    @Timed
    public User saveUser(User user) {
        return userService.saveUser(user);
    }
}
