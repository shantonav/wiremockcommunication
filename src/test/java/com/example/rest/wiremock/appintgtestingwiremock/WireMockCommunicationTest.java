package com.example.rest.wiremock.appintgtestingwiremock;

import com.example.rest.wiremock.appintgtestingwiremock.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WireMockCommunicationTest {
    @Autowired
    private WireMockCommunicator communicator;

   private void testStartRecording() throws MalformedURLException, URISyntaxException {
        WireMockCommunicationResponse resp = communicator.startRecording(new URL("http://10.63.66.100:20062"));
        Assert.assertEquals("{}",resp.getResponseBody());
        Assert.assertEquals(HttpStatus.CREATED,resp.getStatus());

    }

    @Test
    public void testGetDisputeWithAddedMapping() throws IOException, URISyntaxException {
        testStopRecording();
        testStartRecording();
        testAddMapping();
        String resp = communicator.makeARequest("","dispute-service-app/defend/v1/1701/dispute/989",HttpMethod.GET);
        ObjectMapper objectMapper = new ObjectMapper();
        DisputeErrorResponse disputeErrorResponse = objectMapper.readValue(resp,DisputeErrorResponse.class);
        Assert.assertEquals(Integer.valueOf(400),disputeErrorResponse.getStatusCode());
        Assert.assertEquals(Integer.valueOf(10210),disputeErrorResponse.getErrorId());
        Assert.assertEquals("F#### off you bad request",disputeErrorResponse.getMessage());
        testStartRecording();
    }

    @Test
    public void testGetDisputeWithoutAddedMapping() throws IOException, URISyntaxException {

        String resp = communicator.makeARequest("","dispute-service-app/defend/v1/1701/dispute/989",HttpMethod.GET);
        ObjectMapper objectMapper = new ObjectMapper();
        DisputeErrorResponse disputeErrorResponse = objectMapper.readValue(resp,DisputeErrorResponse.class);
        Assert.assertEquals(Integer.valueOf(400),disputeErrorResponse.getStatusCode());
        Assert.assertEquals(Integer.valueOf(10210),disputeErrorResponse.getErrorId());
        Assert.assertEquals("F#### off you bad request",disputeErrorResponse.getMessage());

    }

    private void testStopRecording() throws MalformedURLException, URISyntaxException {
        WireMockCommunicationResponse resp = communicator.stopRecording();


    }

    private void testAddMapping() throws JsonProcessingException, MalformedURLException, URISyntaxException {
        WireMockCommunicationResponse resp = communicator.addAMapping(createDisputeErrorRespnseMapping());
        System.out.println(resp.getResponseBody());

        Assert.assertEquals(HttpStatus.CREATED,resp.getStatus());

    }

    private String createDisputeErrorRespnseMapping() throws JsonProcessingException {
        String id = UUID.randomUUID().toString();
        WireMockMappingRequest request =  new WireMockMappingRequest("/dispute-service-app/defend/v1/1701/dispute/989", HttpMethod.GET.toString());

        DisputeErrorResponse resp = new DisputeErrorResponse(Integer.valueOf(400),Integer.valueOf(10210),"F#### off you bad request");
        ObjectMapper objMapper = new ObjectMapper();

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("X-Application-Context","dispute-service-app:server:20062");
        headerMap.put("Content-Type","application/json");
        headerMap.put("Date",sdf.format(new Date()));


        WireMockMappingResponse response = new WireMockMappingResponse(""+HttpStatus.BAD_REQUEST.value(),objMapper.writeValueAsString(resp)
                ,objMapper.writeValueAsString(headerMap));
        Mapping disputeErrorResponseMapping = new Mapping(id,"dispute-service-app_defend_v1_1701_dispute_989",
                request, response,id,Boolean.FALSE);
        return objMapper.writeValueAsString(disputeErrorResponseMapping);
    }
}
