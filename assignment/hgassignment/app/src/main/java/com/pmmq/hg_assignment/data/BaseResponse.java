package com.pmmq.hg_assignment.data;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
	@SerializedName("result")
	public RESULT mResult;
	@SerializedName("message")
	public String errMessage;
	@SerializedName("code")
	public int code;
	
	public enum RESULT{
		@SerializedName("ok")
		OK,
		@SerializedName("error")
		ERROR,
	}
	
}
