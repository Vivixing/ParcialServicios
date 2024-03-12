package co.com.echeverri.microservice.resolveEnigmaApi.api;

import co.com.echeverri.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.echeverri.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.echeverri.microservice.resolveEnigmaApi.model.Header;
import co.com.echeverri.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.echeverri.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.echeverri.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-03-11T19:32:08.415-05:00[America/Bogota]")
@Controller
public class GetStepApiController implements GetStepApi {

    private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<JsonApiBodyResponseSuccess>> getStep(@ApiParam(value = "request body get enigma step" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
        String accept = request.getHeader("Accept");
        List<JsonApiBodyResponseSuccess> responseList = new ArrayList();
        List<GetEnigmaRequest> enigmas = body.getData();
        
        for(GetEnigmaRequest enigma : enigmas) {
        	Header header = enigma.getHeader();
        	String id = header.getId();
        	String type = header.getType();
        	String enigmaQuestion = enigma.getEnigma();
        	String answerSolution = answerEnigma(enigmaQuestion);
        	
        	GetEnigmaStepResponse enigamaStepResponse = new GetEnigmaStepResponse();
        	enigamaStepResponse.setId(id);
        	enigamaStepResponse.setType(type);
        	enigamaStepResponse.setAnswer(answerSolution);
        	
        	JsonApiBodyResponseSuccess response = new JsonApiBodyResponseSuccess();
        	response.addDataItem(enigamaStepResponse);
        	responseList.add(response);
        	
        }
        
        return new ResponseEntity<>(responseList,HttpStatus.OK);
    }
    
    private String answerEnigma(String enigmaQuestion) {
    	return "Step2: Put the giraffe in";
    }

}
