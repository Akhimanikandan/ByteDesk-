package com.assessment.bytedeskapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.bytedeskapp.model.Info;


@Repository
public interface InfoRepository extends JpaRepository<Info,Long> {
	
 List<Info>findByTicketId(String ticketId);

}
