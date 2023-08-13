package airline;

import airline.pojos.Airline;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apitestingframework.utils.RestUtils;

import java.util.HashMap;
import java.util.Map;

public class AirlineAPI {
    public Response createAirline(Map<String,Object> createAirlinePayload){
      String endPoint = (String) Base.data.get("createAirLineEndpoint");
        return RestUtils.performPostUsingMap(endPoint,createAirlinePayload,new HashMap<>());
    }
    public Response createAirlineUsingPojo(Airline createAirlinePayload){
        String endPoint = (String) Base.data.get("createAirLineEndpoint");
        return RestUtils.performPostUsingPojo(endPoint,createAirlinePayload,new HashMap<>());
    }
    public Response updateAirline(String airlineId, Airline newPayload) {
        String endPoint = "https://api.instantwebtools.net/v1/airlines/"+airlineId;
        return RestAssured.given().log().all().contentType(ContentType.JSON).body(newPayload).put(endPoint);
    }
}
