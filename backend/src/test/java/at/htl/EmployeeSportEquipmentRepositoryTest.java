package at.htl;

import at.htl.fitnesstudio.EmployeeRepository;
import at.htl.fitnesstudio.entity.Employee;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


import javax.inject.Inject;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
public class EmployeeSportEquipmentRepositoryTest {




    @Inject
    EmployeeRepository repo;

    @Test
    @Order(100)
    void repoExists() {
        assertThat(repo).isNotNull();
    }



    /**
     * https://dzone.com/articles/assertj-and-collections-introduction
     * https://gist.github.com/mhaligowski/a902ed35910b223633c0f187a0cd0947
     */

    @Test
    @Order(110)
    void initRepo() {
        Employee felix = new Employee("Felix", 10);
        Employee patrick = new Employee("Patrick", 10000);

        repo.save(felix);
        repo.save(patrick);
        assertThat(repo.findAll().size()).isEqualTo(5);


        assertThat(repo.findById(8).equals(felix));
        assertThat(repo.findById(5).equals(patrick));
    }

    @Test
    @Order(120)
    void saveVehicleOk() {
        Employee toretto = new Employee("Toretto",345);



        repo.save(toretto);


        assertThat(repo.findByName(toretto.getName())).contains(toretto);
    }



    @Test
    @Order(140)
    void deleteVehicle() {

        Employee employee = new Employee("Batu",200);
        repo.save(employee);


        assertThat(repo.findByName("Batu")).contains(employee);

        repo.delete(employee.getId());
        assertThat(repo.findAll()).doesNotContain(employee);
    }




    @Test
    @Order(170)
    void findByName() {

        List<Employee> actualResult = repo.findByName("Hamudi");

        assertThat(actualResult)
                .extracting(Employee::toString)
                .containsExactly("Hamudi 150");
        assertThat(actualResult.size())
                .isEqualTo(1);
    }

}
