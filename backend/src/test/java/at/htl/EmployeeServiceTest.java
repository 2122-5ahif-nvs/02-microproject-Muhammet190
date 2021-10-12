package at.htl;

import at.htl.fitnesstudio.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeServiceTest {
    @Test
    void testGetByLocation() {
        // Arrange
        Employee company = new Employee("Gary", 250);

        // Act
        String location = given()
                .contentType("application/json")
                .body(company)
                .when()
                .post("/employee")
                .then()
                .statusCode(200)
                .extract()
                .header("emp");

        String response = given()
                .contentType("application/json")
                .when()
                .get("/employee/4")
                .then()
                .statusCode(200)
                .extract()
                .body().asString();
        // Assert
        assertThat(location).isEqualTo("http://localhost:8080/employee/4");
                assertThat(response).isEqualTo("Gary 250");

    }
}
