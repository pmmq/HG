package com.pmmq.hg_assignment.data.service;

import com.pmmq.hg_assignment.data.entity.Detail;
import com.pmmq.hg_assignment.data.response.HgResponse;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HelloGoldApiService {
	
	@GET("spot_price.json")
	Call<HgResponse> fetchGoldData();

}
