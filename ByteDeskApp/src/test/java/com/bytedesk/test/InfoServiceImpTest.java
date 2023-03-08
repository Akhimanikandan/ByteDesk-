package com.bytedesk.test;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;

import com.assessment.bytedeskapp.model.Info;
import com.assessment.bytedeskapp.repository.InfoRepository;
import com.assessment.bytedeskapp.service.InfoService;
import com.assessment.bytedeskapp.serviceimpl.InfoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = InfoServiceImpl.class)
public class InfoServiceImpTest {
	
	@Autowired
	private InfoService infoService;
	
	@MockBean
	private InfoRepository infoRepo;
	
	@Test
	@DisplayName("Test get infolist success")
	void getInfo() {
		List <Info> info=new ArrayList<Info>();
		Info infoObj =new Info();
		infoObj.setTicketId("TID678683498");
		when(infoRepo.findByTicketId("TID678683498")).thenReturn(info);
		List<Info> nw= infoService.findByTicketId("TID678683498");
		verify(infoRepo).findByTicketId("TID678683498");
		assertEquals(info, nw);
	}

	
	@Test
	@DisplayName("Test get infolist failure")
	void getInfoFailure() {
		List <Info> info=new ArrayList<Info>();
		Info infoObj =new Info();
		infoObj.setTicketId("TID678683498");
		when(infoRepo.findByTicketId("TID678683498")).thenReturn(info);
		 info=infoService.findByTicketId(null);
		verify(infoRepo,times(0)).findByTicketId("TID678683498");
		
	}
	@Test
	@DisplayName("Test to save info success")
	void saveInfo() {
		Info infoObj = new Info();
		infoService.saveInfo(infoObj);
		verify(infoRepo, times(1)).save(infoObj);
	}
	
	@Test
	@DisplayName("Test to save info failure")
	void saveInfoFailure() {
		Info infoObj = new Info();
		infoService.saveInfo(null);
		verify(infoRepo, times(0)).save(infoObj);
	}
}
