package at.htl;

import at.htl.fitnesstudio.entity.SportEquipment;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest // lässt QuarkusServer vorher laufen für die Tests nötig wenn ich auf endpoint zugreife wie GET
class SportEquipmentRepositoryTest {

    @BeforeEach
    void setup(){
        System.out.println("vor Methode");
    }


    @BeforeAll
    static void beforeAll() {
        System.out.println("vor Klasse");
    }

    @Test
    void name() {
        List<SportEquipment> equipments =
                given().when().get()
                .then()
                .log()
                .body()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".",SportEquipment.class);

        assertThat(equipments).isNotNull();
    }
    @Test
    void name2() {
        System.out.println("Methode");
    }
    @Test
    void name3() {
        System.out.println("Methode");
    }

}