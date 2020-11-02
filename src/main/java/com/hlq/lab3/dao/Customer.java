package com.hlq.lab3.dao;

public class Customer {
	private Integer id;
    private String username;
    private String jobs;
    private String phone;
    
    // 数据库分页
    private int startRow;
    private int pageSize;
    
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", jobs=" + jobs + ", phone=" + phone + "]";
	}
    
	public Customer() {
		super();
		this.startRow = 0;
		this.pageSize = 3;
	}
	
}
