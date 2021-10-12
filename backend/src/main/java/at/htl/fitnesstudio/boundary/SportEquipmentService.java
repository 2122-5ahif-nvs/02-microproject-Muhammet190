package at.htl.fitnesstudio.boundary;

import at.htl.SportEquipmentRepository;
import at.htl.fitnesstudio.entity.SportEquipment;

import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resteasy/hello")
public class SportEquipmentService {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello()
    {
        return "";
    }

    @GET
    @Path("EquipmentList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEquipment(){
        return Response.accepted(SportEquipmentRepository.getInstance().getEquipments()).header("tag","Equipments werden angezeigt").build();
    }

    @POST
    @Path("NewEquipment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SportEquipment createEquipment(JsonObject sportEquipment){
        SportEquipment newSportEquipment =  SportEquipmentRepository.getInstance().AddEquipment(sportEquipment.getString("name"),sportEquipment.getString("brand"));
        return newSportEquipment;
    }

    @Transactional
    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEquipment(@PathParam("name") String name){
        SportEquipment deletedEquipment =  SportEquipmentRepository.getInstance().DeleteEquipment(name);
        return Response.ok(deletedEquipment.getName()).build();
    }

    @Transactional
    @PUT
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEquipment(@PathParam("name") String name,JsonObject sportEquipment){
        SportEquipment findEquipmentToUpdate =  SportEquipmentRepository.getInstance().getEquipments().stream()
                .filter(sportEquipment1 -> sportEquipment1.getName().equals(name))
                .findAny().get();
        SportEquipment updateEquipment = SportEquipmentRepository.
                getInstance().
                UpdateEquipment(findEquipmentToUpdate,sportEquipment.getString("name"),sportEquipment.getString("brand"));
        return Response.ok(updateEquipment).build();
    }
}