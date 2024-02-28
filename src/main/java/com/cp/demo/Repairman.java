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
@Table(name="repairman")
public class Repairman {
	
	@Override
	public String toString() {
		return "Repairman [rm_id=" + rm_id + ", rm_name=" + rm_name + ", rm_email=" + rm_email + ", rm_address="
				+ rm_address + ", rm_tel=" + rm_tel + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rm_id")
	private Integer rm_id;
	
	@Column(name="rm_name")
	private String rm_name;
	
	@Column(name="rm_email")
	private String rm_email;

	@Column(name="rm_address")
	private String rm_address;
	
	@Column(name="rm_tel")
	private String rm_tel;
	
    @OneToMany(mappedBy="repairman")
    private Set<History> history;

    
	public Integer getRm_id() {
		return rm_id;
	}

	public void setRm_id(Integer rm_id) {
		this.rm_id = rm_id;
	}

	public String getRm_name() {
		return rm_name;
	}

	public void setRm_name(String rm_name) {
		this.rm_name = rm_name;
	}

	public String getRm_email() {
		return rm_email;
	}

	public void setRm_email(String rm_email) {
		this.rm_email = rm_email;
	}

	public String getRm_address() {
		return rm_address;
	}

	public void setRm_address(String rm_address) {
		this.rm_address = rm_address;
	}

	public String getRm_tel() {
		return rm_tel;
	}

	public void setRm_tel(String rm_tel) {
		this.rm_tel = rm_tel;
	}

	public Set<History> getHistory() {
		return history;
	}

	public void setHistory(Set<History> history) {
		this.history = history;
	}
    
    
}