package com.cp.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="history_request")
public class History {

	@Override
	public String toString() {
		return "History [hr_id=" + hr_id + ", hr_date=" + hr_date + ", hr_solve=" + hr_solve + ", repairman="
				+ repairman + ", request=" + request + "]";
	}

	@Id
	@Column(name = "hr_id")
	private Integer hr_id;
	
	@Column(name = "hr_date")
	private String hr_date;
	
	@Column(name = "hr_solve")
	private String hr_solve;	

    @ManyToOne
    @JoinColumn(name="rm_id", nullable=false)
    private Repairman repairman;

	@OneToOne
	@MapsId
	@JoinColumn(name = "rq_id")
	private Request request;

	public Integer getHr_id() {
		return hr_id;
	}

	public void setHr_id(Integer hr_id) {
		this.hr_id = hr_id;
	}

	public String getHr_date() {
		return hr_date;
	}

	public void setHr_date(String hr_date) {
		this.hr_date = hr_date;
	}

	public String getHr_solve() {
		return hr_solve;
	}

	public void setHr_solve(String hr_solve) {
		this.hr_solve = hr_solve;
	}

	public Repairman getRepairman() {
		return repairman;
	}

	public void setRepairman(Repairman repairman) {
		this.repairman = repairman;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
}
