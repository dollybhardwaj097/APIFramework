package airline;

import airline.pojos.Airline;
import airline.pojos.GenderEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apitestingframework.utils.JsonUtils;
import org.apitestingframework.utils.RestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

}
