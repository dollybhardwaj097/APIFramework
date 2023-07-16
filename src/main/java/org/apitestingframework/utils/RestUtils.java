package org.apitestingframework.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apitestingframework.reporting.ExtentReportManager;

import java.util.Map;

public class RestUtils {

    private static RequestSpecification getRequestSpecification(String endPoints, Object requestPayload, Map<String,String> headers){
        return RestAssured.given()
                .log().all()
                .headers(headers)
                .baseUri(endPoints)
                .contentType(ContentType.JSON)
                .body(requestPayload);
    }
    private static void printRequestLogInReport(RequestSpecification requestSpecification){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint is" + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method is" + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Headers are");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request Body  is");
        ExtentReportManager.logJson( queryableRequestSpecification.getBody());
    }
    private static void printResponseLogDetails(Response response){
        ExtentReportManager.logInfoDetails("Status Code is " + response.getStatusCode());
        ExtentReportManager.logInfoDetails(" Response Headers are");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Response body is " );
        ExtentReportManager.logJson(response.getBody().prettyPrint());


    }
    public static Response performPost(String endPoints, String requestPayload, Map<String,String> headers){
       RequestSpecification requestSpecification =getRequestSpecification(endPoints,requestPayload,headers);
       Response response = requestSpecification.post();
       printRequestLogInReport(requestSpecification);
       printResponseLogDetails(response);
       return response;
    }
    public static Response performPostUsingMap(String endPoints, Map<String,Object> requestPayload, Map<String,String> headers){
        RequestSpecification requestSpecification =getRequestSpecification(endPoints,requestPayload,headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogDetails(response);
        return response;
    }
    public static Response performPostUsingPojo(String endPoints,Object requestPayloadasPojo, Map<String,String> headers){
        RequestSpecification requestSpecification =getRequestSpecification(endPoints,requestPayloadasPojo,headers);
        Response response = requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogDetails(response);
        return response;
    }
}
