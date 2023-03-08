package com.assessment.bytedeskapp.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket_request")
public class Ticket {
	
	private String userName;
	@Id
	private String ticketId;
	
	private String department;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String ticketSummary;
	
	private String description;
	
	private String resolution = "pending";
	
	
	private String status = "processing";

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getDepartment() {
		return department;
	}

	
	public String getTicketSummary() {
		return ticketSummary;
	}

	public void setTicketSummary(String ticketSummary) {
		this.ticketSummary = ticketSummary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	

}
