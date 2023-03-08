package com.assessment.bytedeskapp.service;

import java.util.List;

import com.assessment.bytedeskapp.model.Info;

public interface InfoService {
List<Info> findByTicketId(String ticketId);
	
	Info saveInfo(Info info);
}
