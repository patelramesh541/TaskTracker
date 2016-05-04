package com.quinnox.model;

import java.util.List;

public class Result {
	
	private String status;
	private String errorMsg;
	private List listObject;
	
	
	public Result(String status, List listObject) {
		super();
		this.status = status;
		this.listObject = listObject;
	}
	public List getListObject() {
		return listObject;
	}
	public void setListObject(List listObject) {
		this.listObject = listObject;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Result(String status, String errorMsg) {
		super();
		this.status = status;
		this.errorMsg = errorMsg;
	}
	public Result() {
		// TODO Auto-generated constructor stub
	}
	public Result(String status, String errorMsg, List listObject) {
		super();
		this.status = status;
		this.errorMsg = errorMsg;
		this.listObject = listObject;
	}
	

}
