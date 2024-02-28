package com.cp.demo;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", username=" + username + ", email=" + email + ", address=" + address
                + ", tel=" + tel + "]";
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer user_id;
  
    @Column(name = "username")
    private String username;
  
    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;
    
    @Column(name = "tel")
    private String tel;
  
    @OneToMany(mappedBy="user")
    private Set<Request> request;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Set<Request> getRequest() {
		return request;
	}

	public void setRequest(Set<Request> request) {
		this.request = request;
	}



}
