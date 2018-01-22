package com.pmmq.hg_assignment.data.response;

import com.google.gson.annotations.SerializedName;
import com.pmmq.hg_assignment.data.BaseResponse;
import com.pmmq.hg_assignment.data.entity.Detail;

public class HgResponse extends BaseResponse {
	@SerializedName("data")
	public Detail mDetail;
}
