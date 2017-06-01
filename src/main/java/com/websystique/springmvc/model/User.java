package com.websystique.springmvc.model;

import java.util.List;



public class User {

	private long id;
	
	private String username;
	
	private String address;
	
	private String email;
	private String owner;
	
	private List<Task> task;
	/*private String taskID;
	
	private String taskName;
	private String taskStatus;*/
	
	/*public User(){
		id=0;
	}*/
	
	public User(long id, String username, String address, String email,String owner, List<Task> task){
		this.id = id;
		this.username = username;
		this.address = address;
		this.email = email;
		this.owner=owner;
		this.task=task;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", address=" + address
				+ ", email=" + email + ", owner=" + owner + ", task=" + task + "]";
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}
	

	
}
