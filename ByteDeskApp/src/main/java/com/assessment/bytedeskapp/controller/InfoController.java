package com.assessment.bytedeskapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.bytedeskapp.model.Info;
import com.assessment.bytedeskapp.service.InfoService;

@RestController
@RequestMapping("/user/info")
@CrossOrigin(origins="http://localhost:4200")
public class InfoController {
    @Autowired
	private InfoService infoService;
    
    @PostMapping("/getinfo")
    public Info getInfo(@RequestBody Info info) {
    return infoService.saveInfo(info);
    }	
    
    @GetMapping("/userInfo/{ticketId}")
    public ResponseEntity<List<Info>> getUserInfo(@PathVariable ("ticketId") String ticketId){
		List<Info> userInfo=infoService.findByTicketId(ticketId);
		
    	return new ResponseEntity<>(userInfo,HttpStatus.OK);
    	
    }
}