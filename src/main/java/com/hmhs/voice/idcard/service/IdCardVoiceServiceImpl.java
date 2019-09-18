package com.hmhs.voice.idcard.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmhs.voice.idcard.dialogflow.IdCardDialogFlowApp;
import com.hmhs.voice.idcard.kafka.KafkaProducer;

@Service
public class IdCardVoiceServiceImpl implements IdCardVoiceService {

	private static final Logger logger = Logger.getLogger(IdCardVoiceServiceImpl.class.getName());
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private IdCardDialogFlowApp idCardDialogFlowApp;

	@Override
	public String requestIdCard(String payload, Map<String, String> headers) {
		String jsonResponse = "No Data Found";
		try {
			jsonResponse = idCardDialogFlowApp.handleRequest(payload, headers).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		logger.info(jsonResponse);
		return jsonResponse;
	}

	@Override
	public String requestIdCard(String umi) {
		kafkaProducer.sendMessage("id card request received "+umi);
		return "We received your Id card request and we are processing it. Thanks for getting in touch.";
	}
}