package com.thoughtworks.gaia.user.endpoint;

import com.thoughtworks.gaia.product.entity.Product;
import com.thoughtworks.gaia.user.entity.User;
import com.thoughtworks.gaia.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("user")
@Api(value = "user", description = "Access to user resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserEndPoint {
    @Autowired
    private UserService userService;

    @Path("/{user_id}")
    @ApiOperation(value = "Get user by id", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user successfully"),
            @ApiResponse(code = 404, message = "No user matches given id")
    })
    @GET
    public Response getUser(@PathParam("user_id") Long userId) {
        User user = userService.getUser(userId);
        return Response.ok().entity(user).build();
    }

    @Path("/")
    @ApiOperation(value = "Get all users", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get users successfully")
    })
    @GET
    public Response getUserList() {
        List<User> users = userService.getUserList();
        return Response.ok().entity(users).build();
    }

    @Path("/products")
    @ApiOperation(value = "Get all users with prods", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get users with prods successfully")
    })
    @GET
    public Response getUserWithProdList() {
        List<User> users = userService.getUserWithProdList();
        return Response.ok().entity(users).build();
    }

//    @Path("/delete/{user_id}")
//    @ApiOperation(value = "Delete user by id", response = User.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Delete user successfully"),
//            @ApiResponse(code = 404, message = "No user matches given id")
//    })
//    @DELETE
//    public Response deleteUser(@PathParam("user_id") Long userId) {
//        userService.deleteUser(userId);
//        return Response.ok().entity().build();
//    }
}
