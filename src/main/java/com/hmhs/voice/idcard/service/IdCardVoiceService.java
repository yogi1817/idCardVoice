package com.hmhs.voice.idcard.service;

import java.util.Map;

public interface IdCardVoiceService {

	String requestIdCard(String payload, Map<String, String> headers);
}
