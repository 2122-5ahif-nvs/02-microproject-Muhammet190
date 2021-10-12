package at.htl.fitnesstudio.boundary;

import at.htl.fitnesstudio.EmployeeRepository;
import at.htl.fitnesstudio.entity.Employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/employee")
@RequestScoped
public class EmployeeService {
    @Inject
    EmployeeRepository employeeRepository;

    @POST
    public Response create(Employee emp, @Context UriInfo info) {
        Employee newEmployee = employeeRepository.save(emp);
        return Response
                .created(info.getAbsolutePathBuilder().path(String.valueOf(emp.getId())).build())
                .build();
    }

    @GET
    public List<Employee> readAll() {
        return employeeRepository.findAllEmployees();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, Employee emp) {
        boolean updated = employeeRepository.updateEmployee(id,emp);
        if (!updated){
            return Response
                    .status(400)
                    .header("Reason","courseType with id " + id + " does not exist")
                    .build();
        }
        Employee newType = employeeRepository.findEmpById(id);
        return Response
                .ok(newType)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        boolean deleted = employeeRepository.deleteEmployeeById(id);
        if (!deleted){
            return Response
                    .status(400)
                    .header("Reason","courseType with id " + id + " does not exist")
                    .build();
        }
        return Response
                .noContent()
                .build();
    }


    /*
    @POST
    @Path("NewEmp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee create(JsonObject employeeJson) {
        Employee employee = new Employee(
                employeeJson.getString("name"),
                employeeJson.getInt("salary")
               );

        employeeRepository.save(employee);
        return employee;
    }

    @GET
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    @Path("{id}")
    public Employee find(@PathParam("id") String id) {
        return employeeRepository.findById(Integer.parseInt(id));
    }

    @GET
    @Path("GetAll")
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XHTML_XML)
    public Response exceptionDemo(@PathParam("id") String id) {
        Employee findById =  employeeRepository.findById(Integer.parseInt(id));
        return Response.ok(findById.getName()).build();

    }
     */

}