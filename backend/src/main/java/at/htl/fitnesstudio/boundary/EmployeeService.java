package at.htl.fitnesstudio.boundary;

import at.htl.fitnesstudio.EmployeeRepository;
import at.htl.fitnesstudio.entity.Employee;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/employee")
@RequestScoped
@OpenAPIDefinition(
        info = @Info(
                title = "Employee API",
                description = "Managing employees",
                version = "1.0",
                contact = @Contact(name = "Muhammet Batuhan Oezdogan", email = "muhammet.batuhan64@outlook.com")
        ),
        externalDocs = @ExternalDocumentation(description = "manage all employees"),
        tags = {
                @Tag(name = "api", description = "Public API"),
                @Tag(name = "employee", description = "Interested in employees")
        }
)
public class EmployeeService {
    @Inject
    EmployeeRepository employeeRepository;

    @POST
    @Operation(
            description = "creates an employee",
            summary = "you have a new employee"
    )
    public Response create(Employee emp, @Context UriInfo info) {
        Employee newEmployee = employeeRepository.save(emp);
        return Response
                .created(info.getAbsolutePathBuilder().path(String.valueOf(emp.getId())).build())
                .build();
    }

    @GET
    @Operation(
            description = "return all employees",
            summary = "you have all employees in a list"
    )
    public List<Employee> readAll() {
        return employeeRepository.findAllEmployees();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            description = "updates an employee",
            summary = "you have an updated employee"
    )
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
    @Operation(
            description = "deletes an employee",
            summary = "you have deleted an employee"
    )
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