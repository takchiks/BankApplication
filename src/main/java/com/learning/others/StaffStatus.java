package com.learning.others;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.learning.enums.Status;

public class StaffStatus {
	private int staffId;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public StaffStatus() {
		super();
	}
	public StaffStatus(int staffId, Status status) {
		super();
		this.staffId = staffId;
		this.status = status;
	}
	
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	
}
