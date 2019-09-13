package com.hmhs.voice.idcard.dialogflow;

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

	@Autowired
	private KafkaProducer producer;
	
	private static final Logger logger = Logger.getLogger(IdCardDialogFlowApp.class.getName());
	
	@ForIntent("id_card_request")
	public ActionResponse welcome(ActionRequest request) {
		logger.info("doctor_name intent start.");
		ResponseBuilder responseBuilder = getResponseBuilder(request);
		//QueryResult qr = request.getWebhookRequest().getQueryResult();
		responseBuilder.add("We received your Id card request and we are processing it. Thanks for getting in touch.");
		logger.info("doctor_name intent end.");
		producer.sendMessage("id card request received");
		return responseBuilder.build();
	}
}