package edu.upc.dsa.services;

import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;


import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/products", description = "Endpoint to Product Service")
@Path("/tracks")
public class ProductService {

    private ProductManager pm;

    public ProductService() {
        this.pm = ProductManagerImpl.getInstance();
        if (pm.numOrders()==0) {
            pm = new ProductManagerImpl();
            pm.addProduct("C1", "Coca-cola zero", 2);
            pm.addProduct("C2", "Coca-cola", 2.5);
            pm.addProduct("B1", "Lomo queso", 3);
            pm.addProduct("B2", "bacon queso", 3.5);
        }


    }

    @GET
    @ApiOperation(value = "get all Product", notes = "Hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = models.Product.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks() {

        List<models.Product> products = this.pm.findAll();

        GenericEntity<List<models.Product>> entity = new GenericEntity<List<models.Product>>(products) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = models.Product.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") String id) {
        models.Product t = this.pm.getProduct(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        models.Product t = this.pm.getProduct(id);
        if (t == null) return Response.status(404).build();
        else this.pm.deleteProduct(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/")
    public Response updateProduct(models.Product product) {

        models.Product t = this.pm.updateProduct(product);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "create a new Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= models.Product.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct(models.Product product) {

        if (product.getNom()==null || product.getId()==null)  return Response.status(500).entity(product).build();
        this.pm.addProduct(product.getNom(), product.getId(), product.getPreu());
        return Response.status(201).entity(product).build();
    }

    @POST
    @ApiOperation(value = "create a new Order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Order.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newOrder(Order Order) {

        if (Order.getUser1()==null)  return Response.status(500).entity(Order).build();
        this.pm.addOrder(Order);
        return Response.status(201).entity(Order).build();
    }

   @POST
   @ApiOperation(value = "create a new Product", notes = "asdasd")
   @ApiResponses(value = {
           @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")//aun no
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User User) {

        if (User.getNom()==null)  return Response.status(500).entity(User).build();
        this.pm.addUser(User);
       return Response.status(201).entity(User).build();
   }

}