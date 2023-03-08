package com.assessment.bytedeskapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="info_table")
public class Info {
 private String ticketId;
 private String adminInfo; 
 private String userInfo;
 @Id
 @SequenceGenerator(name = "ticket_sequence", sequenceName = "ticket_sequence", initialValue = 100, allocationSize = 1)
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
 private long id;
 
public String getTicketId() {
	return ticketId;
}
public void setTicketId(String ticketId) {
	this.ticketId = ticketId;
}
public String getAdminInfo() {
	return adminInfo;
}
public void setAdminInfo(String adminInfo) {
	this.adminInfo = adminInfo;
}
public String getUserInfo() {
	return userInfo;
}
public void setUserInfo(String userInfo) {
	this.userInfo = userInfo;
}
public long getId() {
	return id;
}



public void setId(long id) {
	this.id = id;
}
 
}
