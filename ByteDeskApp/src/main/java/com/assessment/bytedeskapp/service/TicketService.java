package com.assessment.bytedeskapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.assessment.bytedeskapp.model.Ticket;

public interface TicketService {
	
Ticket saveRaiseTicket(Ticket ticket);

void updateTicketReq(String status,String ticketId);

Page<Ticket> findByUserId(int pageNumber,int pageSize,String userName, String status);
}
