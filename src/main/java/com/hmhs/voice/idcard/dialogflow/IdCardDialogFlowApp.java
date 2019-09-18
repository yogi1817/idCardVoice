package com.hmhs.voice.idcard.dialogflow;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import com.hmhs.voice.idcard.kafka.KafkaProducer;

@Service
public class IdCardDialogFlowApp extends DialogflowApp {

	@Autowired private KafkaProducer producer;
	 
	private static final Logger logger = Logger.getLogger(IdCardDialogFlowApp.class.getName());
	
	private String umi = null;
	@Override
	public ActionRequest createRequest(String inputJson, Map<?, ?> headers) {
		setUmi((String) headers.get("umi"));
		return super.createRequest(inputJson, headers);
	}

	@ForIntent("id_card_request")
	public ActionResponse idCardRequest(ActionRequest request) {
		logger.info("idCardRequest intent start.");
		
		ResponseBuilder responseBuilder = getResponseBuilder(request);
		responseBuilder.add("We received your Id card request and we are processing it. Thanks for getting in touch.");
		logger.info("idCardRequest intent end.");
		
		producer.sendMessage("id card request received "+getUmi());
		return responseBuilder.build();
	}
	
	@ForIntent("Default Welcome Intent")
	public ActionResponse welcome(ActionRequest request) {
		logger.info("doctor_name intent start.");
		
		ResponseBuilder responseBuilder = getResponseBuilder(request);
		responseBuilder.add("Welcome to Identity Card service, you can say \"Send me my identity card\" to request a new identity card at your address");
		logger.info("doctor_name intent end.");
		
		return responseBuilder.build();
	}

	public String getUmi() {
		return umi;
	}

	public void setUmi(String umi) {
		this.umi = umi;
	}
}
