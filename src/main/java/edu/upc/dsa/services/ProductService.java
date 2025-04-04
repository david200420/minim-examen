package edu.upc.dsa.services;

import edu.upc.dsa.AvionesManager;
import edu.upc.dsa.AvionesManagerImpl;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;
import edu.upc.dsa.models.Maleta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Queue;

@Api(value = "/avion", description = "Endpoint to Maleta Service")
@Path("/avion")
public class ProductService {

    private AvionesManager pm;

    public ProductService() {
        this.pm = AvionesManagerImpl.getInstance();

        pm.addAvion("A1", "Boeing 737", "Iberia");
        pm.addAvion("A2", "Airbus A320", "Vueling");
        pm.addVuelo("V1", "A1", "Madrid", "Paris", "8:00", "10:00");
        pm.addVuelo("V2", "A2", "Paris", "Madrid", "10:00", "13:00");
        pm.facturarMaleta("Maria", "V1");
        pm.facturarMaleta("Juana", "V2");
        pm.facturarMaleta("Ruben", "V1");
    }

    @GET
    @ApiOperation(value = "Veure maletes en un Vol específic", notes = "Hola")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Maleta.class, responseContainer = "Queue")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response maletesVol(@PathParam("id") String id) {
        if(id == null || id.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Queue<Maleta> maletas = this.pm.getMaletasVuelo(id);
        if(maletas == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        GenericEntity<Queue<Maleta>> entity = new GenericEntity<Queue<Maleta>>(maletas) {};
        return Response.ok(entity).build();
    }

    @POST
    @ApiOperation(value = "create a new Maleta", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Maleta.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/maleta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newMaleta(@QueryParam("usuario") String usuario, @QueryParam("idvuelo") String idvuelo) {
        if (usuario == null || idvuelo == null)
            return Response.status(500).build();
        Maleta m = this.pm.facturarMaleta(usuario, idvuelo);
        return Response.status(201).entity(m).build();
    }

    @POST
    @ApiOperation(value = "create a new Vol", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Avion.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/avion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newVol(Vuelo v) {
        if (v.getId() == null)
            return Response.status(500).entity(v).build();
        this.pm.addVuelo(v.getId(),v.getAvion().getId(),v.getOrigen(),v.getDestino(),v.getHoraSalida(),v.getHoraLlegada());
        return Response.status(201).entity(v).build();
    }

    @POST
    @ApiOperation(value = "afegir avio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Avion.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newAvion(Avion a) {
        if (a.getId() == null) {
            return Response.status(500).entity(a).build();
        }
        this.pm.addAvion(a.getId(), a.getModelo(),a.getCompania());
        return Response.status(201).entity(a).build();
    }
}
