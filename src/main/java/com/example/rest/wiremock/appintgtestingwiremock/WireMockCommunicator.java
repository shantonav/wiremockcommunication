package com.example.rest.wiremock.appintgtestingwiremock;

import com.example.rest.wiremock.appintgtestingwiremock.domain.Mapping;
import com.example.rest.wiremock.appintgtestingwiremock.domain.StartRecording;
import com.example.rest.wiremock.appintgtestingwiremock.domain.WireMockCommunicationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class WireMockCommunicator {

    private final Logger log = LoggerFactory.getLogger(WireMockCommunicator.class);

    private RestTemplate restTemplate;

    @Value("${wiremock.host}")
    private String host;
    @Value("${wiremock.port}")
    private String port;

    @Autowired
    public WireMockCommunicator(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String makeARequest(String jsonBody, String uri, HttpMethod method){
        String clientResponse = null;
        try{
            if (method.equals( HttpMethod.GET) ) {
                clientResponse = restTemplate.getForObject("http://"+this.host+":"+this.port+"/"+uri, String.class);
            }
        }catch(HttpClientErrorException ex){
            String respBody = ex.getResponseBodyAsString();
            if (StringUtils.isEmpty(respBody)){
                throw ex;
            }else{
                clientResponse = respBody;
                log.info("(Error)Response from wiremock (strange!!) : "+ respBody);
            }
        }
        return clientResponse;

    }

    public WireMockCommunicationResponse startRecording(URL targetURL) throws MalformedURLException, URISyntaxException {
        URL startRecording = new URL("http://"+this.host+":"+this.port+"/"+"__admin/recordings/start");
        StartRecording startRecordingObj = new StartRecording(targetURL.toString());
        String clientResponse = restTemplate.postForObject(startRecording.toURI() , startRecordingObj,String.class);
        return new WireMockCommunicationResponse(HttpStatus.CREATED,clientResponse);
    }

    public WireMockCommunicationResponse stopRecording()  throws MalformedURLException, URISyntaxException{
        URL stopRecording = new URL("http://"+this.host+":"+this.port+"/"+"__admin/recordings/stop");
        String clientResponse = null;
        try {
            clientResponse = restTemplate.postForObject(stopRecording.toURI(), null, String.class);
        }catch(HttpClientErrorException ex){
            if ( ex.getStatusCode().value() == 400 ){
                return new WireMockCommunicationResponse(HttpStatus.BAD_REQUEST,ex.getResponseBodyAsString());
            }
        }
        return new WireMockCommunicationResponse(HttpStatus.OK,clientResponse);
    }

    public WireMockCommunicationResponse addAMapping(String jsonBody) throws MalformedURLException, URISyntaxException{
        URL addAMapping = new URL("http://"+this.host+":"+this.port+"/"+"__admin/mappings/new");
        String clientResponse =  restTemplate.postForObject(addAMapping.toURI() , jsonBody,String.class);
        return new WireMockCommunicationResponse(HttpStatus.CREATED,clientResponse);
    }
}
