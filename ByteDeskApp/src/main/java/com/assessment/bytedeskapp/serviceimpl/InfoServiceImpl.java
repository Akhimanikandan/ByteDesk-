package com.assessment.bytedeskapp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.bytedeskapp.model.Info;
import com.assessment.bytedeskapp.repository.InfoRepository;
import com.assessment.bytedeskapp.service.InfoService;
@Service
public class InfoServiceImpl implements InfoService {
	@Autowired
	private InfoRepository infoRepo;

	@Override
	public Info saveInfo(Info info) {
		return infoRepo.save(info);
	}

	@Override
	public List<Info> findByTicketId(String ticketId) {
		return infoRepo.findByTicketId(ticketId);
	}

}
