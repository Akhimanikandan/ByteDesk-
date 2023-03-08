package com.assessment.bytedeskapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.bytedeskapp.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String>{

Ticket findByTicketId(String ticketId);

Page<Ticket> findUserByUserNameAndStatus(Pageable pageable,String userName,String status);
}
