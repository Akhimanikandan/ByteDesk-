package com.assessment.bytedeskapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.bytedeskapp.model.Ticket;
import com.assessment.bytedeskapp.service.TicketService;

@RestController
@RequestMapping("/user/tickets")
@CrossOrigin(origins="http://localhost:4200")
public class TicketController {
	
	@Autowired
	private TicketService ticketservice;
	
	
	@PostMapping("/raise")
	public Ticket saveRaiseRequest(@RequestBody Ticket ticket){
		return ticketservice.saveRaiseTicket(ticket);
	}
	
	
	@GetMapping("/ticketlist/{pageNumber}/{pageSize}/{userName}/{status}")
	public ResponseEntity<Map<String,Object>> getTicketList(@PathVariable Integer pageNumber, @PathVariable Integer pageSize,
			@PathVariable ("userName")String userName,@PathVariable ("status")String status ){
		Page<Ticket> ticketPage=ticketservice.findByUserId(pageNumber, pageSize, userName, status);
		List<Ticket>list=ticketPage.getContent();
		Map<String,Object>ticketList=new HashMap<>();
		ticketList.put("list", list);
		ticketList.put("currentPage", ticketPage.getNumber());
		ticketList.put("totalItem", ticketPage.getTotalElements());
		ticketList.put("totalPages", ticketPage.getTotalPages());
		return new ResponseEntity<>(ticketList,HttpStatus.OK);
	}

	
	@PutMapping("/update")
	public void  updateTicketStatus(@RequestParam("status")String status,@RequestParam("ticketId")String ticketId) {
		ticketservice.updateTicketReq(status,ticketId);
		
	}
	   
}
