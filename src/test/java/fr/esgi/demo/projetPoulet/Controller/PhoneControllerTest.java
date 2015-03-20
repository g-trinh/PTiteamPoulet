package fr.esgi.demo.projetPoulet.Controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.esgi.demo.DemoApplication;
import fr.esgi.demo.ProjetPoulet.Entity.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Guillaume on 20/03/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class )
public class PhoneControllerTest {
    @Before
    public void setUp(){
        RestAssured.port = 9000;
    }

    @Test
    public void shouldNot_createPhone(){
        Phone newPhone = new Phone();
        given()
                .contentType(APPLICATION_JSON_VALUE)
                .body(newPhone)
                .log().all()
        .when()
                .post("/phone")
        .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
        ;
    }

    @Test
    public void should_createPhone(){
        Phone newPhone = new Phone();
        newPhone.setNumber("03241658");
        newPhone.setSerialNumber("987654321");
        newPhone.setFirstName("Guillaume");
        newPhone.setLastName("Trinh");
        newPhone.setStolen(true);

        given()
                .contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE)
                .body(newPhone)
                .log().all()
        .when()
                .post("/phone")
        .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("number", is("03241658"))
                .body("serialNumber", is("987654321"))
                .body("firstName", is("Guillaume") )
                .body("lastName", is("Trinh") )
                .body("stolen", is(true) )
        ;
    }

    @Test
    public void should_ReturnStolenPhones(){
        given().log().all().then().log().all();

        Response response = get("/phone");

        List<HashMap<String, Object>> phoneList = response.getBody().path("");
        int responseCode = response.getStatusCode();

        for( HashMap<String, Object> map : phoneList ){
            assertThat( map.get("stolen").toString(), is("true") );
        }

        assertThat( responseCode, is(HttpStatus.OK.value()) );
    }

    @Test
    public void should_ReturnFalse_withId1(){
        given()
                .log().all()
        .when()
                .get("/phone/1")
        .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .body("stolen", is(false) )
        ;
    }

    @Test
    public void should_ReturnFalse_withId2(){
        given()
                .log().all()
        .when()
                .get("/phone/6")
        .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .body("stolen", is(true) )
        ;
    }

    @Test
    public void should_UpdateAndReturnPhone(){
        Response response = get("/phone/5");
        boolean stolen;

        HashMap<String, Object> phoneAttr = response.getBody().path("");

        if( phoneAttr.get("stolen").toString().equals( "true" ) ){
            stolen = false;
        } else {
            stolen = true;
        }

        given()
                .log().all()
        .when()
                .put("/phone/03214685")
        .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .body("stolen", is(stolen))
        ;
    }

    @Test
    public void shouldNot_AccessPhone(){
        given()
                .log().all()
        .when()
                .put("/phone/1000")
        .then()
                .log().all()
                .statusCode(HttpStatus.NO_CONTENT.value())
        ;
    }

    @Test
    public void shouldNot_DeletePhone(){
        given()
                .log().all()
        .when()
                .delete("/phone/1000")
        .then()
                .log().all()
                .statusCode(HttpStatus.NO_CONTENT.value())
        ;
    }

    @Test
    public void should_DeletePhone(){
        given()
                .log().all()
        .when()
                .delete("/phone/2")
        .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
        ;
    }
}
