package com.pmmq.hg_assignment.data.entity;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Detail {
	@SerializedName("buy")
	Double buy;
	@SerializedName("sell")
	Double sell;
	@SerializedName("timestamp")
	Date timeStamp;
	
	public Double getBuy() {
		return buy;
	}
	
	public void setBuy(final Double buy) {
		this.buy = buy;
	}
	
	public Double getSell() {
		return sell;
	}
	
	public void setSell(final Double sell) {
		this.sell = sell;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(final Date timeStamp) {
		this.timeStamp = timeStamp;
	}
}
