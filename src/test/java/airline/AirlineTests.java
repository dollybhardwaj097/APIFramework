package airline;

import airline.pojos.Airline;
import airline.pojos.GenderEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apitestingframework.utils.JsonUtils;
import org.apitestingframework.utils.RestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class AirlineTests extends AirlineAPI {
//    @Test
//    public  void postAirline(){
//        String endPoint = "https://api.instantwebtools.net/v1/airlines";
//        String payload = AirlinePayload.getAirlineRequestPayload("224234234","Dolly","Srilanka","ABC",
//                "ABC_GF","India","abcdklj.com","1990");
//        Response response = RestUtils.performPost(endPoint,payload,new HashMap<>());
//        Assert.assertEquals(response.statusCode(),200);
//
//    }
//    @Test
//    public  void postAirlineWithMapPayload() throws IOException {
//        Map<String, Object> payload = AirlinePayload.getAirlineRequestPayloadUsingMap("0988766","Dolly","Srilanka","ABC",
//                "ABC_GF","India","abcdklj.com","1990");
//        Response response = createAirline(payload);
//        Assert.assertEquals(response.statusCode(),200);
//
//    }
//    @Test
//    public  void postAirlineWithDataFakerPayload() throws IOException {
//        Map<String, Object> payload = AirlinePayload.getAirlineRequestPayloadUsingDataFaker();
//        Response response = createAirline(payload);
//        Assert.assertEquals(response.statusCode(),200);
//
//    }
//    @Test
//    public  void postAirlineWithDataFakerPayloadWithPojo() throws IOException {
////       // Airline payload = AirlinePayload.getAirlineRequestFromPojo();
////        //Airline payload = new Airline();
////        Airline payload = new Airline().toBuilder().name("Dolly").build();
////        Response response = createAirlineUsingPojo(payload);
////        Assert.assertEquals(response.statusCode(),200);
//       // Airline payload = new Airline();
//        Airline payload = new Airline().toBuilder().gender(GenderEnum.male).build();
//        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(payload));
//
//    }
    @Test
    public void verifyPostMethodResponse() throws JsonProcessingException {
        Airline payload = new Airline().toBuilder().name("Dolly").build();
        Response response = createAirlineUsingPojo(payload);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(), ContentType.JSON.toString());
        Assert.assertEquals(response.jsonPath().getString("name"),"Dolly");
        Assert.assertEquals(response.jsonPath().getString("country"),"India");
        Assert.assertEquals(response.jsonPath().getString("head_quaters"),"ABC");
        Assert.assertEquals(response.jsonPath().getString("website"),"abcdklj.com");
        Assert.assertEquals(response.jsonPath().getString("established"),"1990");
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("logo"));
    }
    @Test
    public void verifyPostMethodResponseUsingPojo() throws JsonProcessingException {
        Airline payload = new Airline();
        Response response = createAirlineUsingPojo(payload);
        //first way
       // Assert.assertEquals(response.jsonPath().getString("name"),payload.getName());
        //second Way - Deserialization
        //use @JsonIgnoreproperties if the response has more fields than payload
        ObjectMapper objectMapper = new ObjectMapper();
        Airline createAirlineResponse = objectMapper.readValue(response.getBody().asString(),Airline.class);
        Assert.assertEquals(createAirlineResponse,payload);
    }
    @Test
    public void verifyPutMethod() throws JsonProcessingException {
        Airline payload = new Airline();
        Response response = createAirlineUsingPojo(payload);
        ObjectMapper objectMapper = new ObjectMapper();
        Airline createAirlineResponse = objectMapper.readValue(response.getBody().asString(),Airline.class);
        Assert.assertEquals(createAirlineResponse,payload);
        String airlineId = String.valueOf(createAirlineResponse.getId());
        Airline newPayload = new Airline().toBuilder().name("Dolly").build();
        Response updateResponse = updateAirline(airlineId,newPayload);
        Assert.assertEquals(updateResponse.statusCode(),200);
        Airline updateAirlineResponse = objectMapper.readValue(updateResponse.getBody().asString(),Airline.class);
        Assert.assertEquals(updateAirlineResponse,newPayload);
    }
    @Test
    public void verifyFirstname(){
        String apiURL = "https://reqres.in/api/users?page=2";
        String response = given()
                .when()
                .get(apiURL)
                .then()
                .statusCode(200)
                .extract()
                .asString();
        //System.out.println(response);
        JsonPath jsonPathEvaluator = from(response);
        List<String> firstNames = jsonPathEvaluator.getList("data.first_name");
        boolean rachelFound = firstNames.contains("Rachel");
        assert rachelFound : "Expected first name 'Rachel' not found in the response.";
        System.out.println("List of First Names:");
        System.out.println(firstNames);
    }


}
