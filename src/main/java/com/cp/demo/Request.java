package com.cp.demo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name ="repair_request")
public class Request {
	
    @Override
    public String toString() {
        return "Request [rq_id=" + rq_id + ", rq_date=" + rq_date + ", rq_des=" + rq_des + ", rq_loc=" + rq_loc
                + ", rq_status=" + rq_status + ", rq_type=" + rq_type + ", user=" + user + ", hr_id=" + history + "]";
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "rq_id")
    private Integer rq_id;
  
    @Column(name = "rq_date")
    private String rq_date;
  
    @Column(name = "rq_des")
    private String rq_des;
    
    @Column(name = "rq_loc")
    private String rq_loc;
    
    @Column(name = "rq_status")
    private String rq_status;
    
    @Column(name = "rq_type")
    private String rq_type;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
	
    @OneToOne(mappedBy = "request" , cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private History history;

	public Integer getRq_id() {
		return rq_id;
	}

	public void setRq_id(Integer rq_id) {
		this.rq_id = rq_id;
	}

	public String getRq_date() {
		return rq_date;
	}

	public void setRq_date(String rq_date) {
		this.rq_date = rq_date;
	}


	public String getRq_des() {
		return rq_des;
	}

	public void setRq_des(String rq_des) {
		this.rq_des = rq_des;
	}

	public String getRq_loc() {
		return rq_loc;
	}

	public void setRq_loc(String rq_loc) {
		this.rq_loc = rq_loc;
	}

	public String getRq_status() {
		return rq_status;
	}

	public void setRq_status(String rq_status) {
		this.rq_status = rq_status;
	}

	public String getRq_type() {
		return rq_type;
	}

	public void setRq_type(String rq_type) {
		this.rq_type = rq_type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	  
}
