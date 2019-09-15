package com.hmhs.voice.idcard.resources;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmhs.voice.idcard.service.IdCardVoiceService;

@RestController
@RequestMapping(value="boot/api/voice", produces = MediaType.APPLICATION_JSON_VALUE)
public class IdCardVoiceResource {

	private static final Logger logger = Logger.getLogger(IdCardVoiceResource.class.getName());
	
	@Autowired
	private IdCardVoiceService idCardVoiceService;
	
	@PostMapping(value = "requestIdCard")
	public ResponseEntity<String> requestIdCard(@RequestBody Object payload,
			@RequestHeader Map<String, String> headers){
		ObjectMapper objMapper = new ObjectMapper();
		logger.info("payload "+payload);
		String payloadAsString = null;
		try { 
			payloadAsString = objMapper.writeValueAsString(payload); 
        }  catch (IOException e) { 
        	return new ResponseEntity<>("Unable to parse the request", HttpStatus.BAD_REQUEST);
        } 
		return new ResponseEntity<>(idCardVoiceService.requestIdCard(payloadAsString, headers), HttpStatus.OK);
	}
	
	@GetMapping(value = "sample")
	public ResponseEntity<String> sample(){
		return new ResponseEntity<>("The service works", HttpStatus.OK);
	}
}
