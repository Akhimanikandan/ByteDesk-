package com.assessment.bytedeskapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assessment.bytedeskapp.model.Ticket;
import com.assessment.bytedeskapp.repository.TicketRepository;
import com.assessment.bytedeskapp.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepo;

	@Override
	public Ticket saveRaiseTicket(Ticket ticket) {
		return ticketRepo.save(ticket);

	}

	@Override
	public void updateTicketReq(String status, String ticketId) {
		Ticket ticketreq = ticketRepo.findByTicketId(ticketId);
		ticketreq.setStatus(status);
		ticketRepo.save(ticketreq);
		
	}

	@Override
	public Page<Ticket> findByUserId(int pageNumber, int pageSize, String userName, String status) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return ticketRepo.findUserByUserNameAndStatus(pageable, userName, status);
	}

}
