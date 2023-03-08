package com.bytedesk.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import com.assessment.bytedeskapp.model.Ticket;
import com.assessment.bytedeskapp.repository.TicketRepository;
import com.assessment.bytedeskapp.service.TicketService;
import com.assessment.bytedeskapp.serviceimpl.TicketServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TicketServiceImpl.class)
@SpringBootTest
class TicketServiceImplTest {

	@Autowired
	private TicketService ticketService;

	@MockBean
	private TicketRepository ticketRepo;

	@Test
	@DisplayName("Test Update ticket Request success")
	void testUpdateTicketReq() {
		Ticket ticket = new Ticket();
		ticket.setTicketId("123456");
		ticket.setStatus("Open");
		when(ticketRepo.findByTicketId("123456")).thenReturn(ticket);
		ticketService.updateTicketReq("Closed", "123456");
		Ticket updatedTicket = ticketRepo.findByTicketId("123456");
		assertEquals("Closed", updatedTicket.getStatus());
		verify(ticketRepo).save(updatedTicket);
	}

	@Test
	@DisplayName("Test Update ticket Request failure")
	void testUpdateTicketReqFailure() {
		Ticket ticket = new Ticket();
		ticket.setTicketId("123456");
		ticket.setStatus("Open");
		when(ticketRepo.findByTicketId("123456")).thenReturn(ticket);
		ticketService.updateTicketReq("Closed", "123456");
		Ticket updatedTicket = ticketRepo.findByTicketId(null);
		verify(ticketRepo, times(0)).save(updatedTicket);
	}

	@Test
	@DisplayName("Test save ticket success")
	void testSaveTicket() {
		Ticket demoTicket = new Ticket();
		ticketService.saveRaiseTicket(demoTicket);
		verify(ticketRepo, times(1)).save(demoTicket);
	}

	@Test
	@DisplayName("Test save ticket failure")
	void testSaveTicketFailure() {
		Ticket demoTicket = new Ticket();
		ticketService.saveRaiseTicket(null);
		verify(ticketRepo, times(0)).save(demoTicket);
	}

	@Test
	@DisplayName("Test get ticket success")
	void testgetTicket() {
		int pageNumber = 5;
		int pageSize = 3;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Ticket> response = ticketService.findByUserId(pageNumber, pageSize, "abc", "xyz");

		verify(ticketRepo).findUserByUserNameAndStatus(pageable, "abc", "xyz");
	}


	@Test
	@DisplayName("Test get ticket Failure")
	void testgetTicketFailure() {
	int pageNumber = 5;
	int pageSize = 3;
	Pageable pageable = PageRequest.of(pageNumber, pageSize);
	Page<Ticket> response = ticketService.findByUserId(pageNumber, pageSize, "abc", null);

	verify(ticketRepo,times(0)).findUserByUserNameAndStatus(pageable, "abc", "xyz");
}

}
