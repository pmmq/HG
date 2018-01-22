package com.pmmq.hg_assignment.data.repositorty;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import com.pmmq.hg_assignment.HelloGoldApplication;
import com.pmmq.hg_assignment.data.ApplicationData;
import com.pmmq.hg_assignment.data.BaseResponse;
import com.pmmq.hg_assignment.data.entity.Detail;
import com.pmmq.hg_assignment.data.response.HgResponse;
import com.pmmq.hg_assignment.data.service.HelloGoldApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRepository {
	
	HelloGoldApiService mHelloGoldApiService;
	ApplicationData mApplicationData;
	HelloGoldApplication mApplication;
	MutableLiveData<List<Detail>> details;
	Call<HgResponse> detailCall;
	public DetailRepository(final HelloGoldApiService helloGoldApiService, ApplicationData applicationData,
			HelloGoldApplication application) {
		mHelloGoldApiService = helloGoldApiService;
		mApplication = application;
		mApplicationData = applicationData;
		details = new MutableLiveData<>();
		details.setValue(mApplicationData.getDetails());
	}
	
	public void fetchData(final OnFetchData  onFetch) {
		detailCall = mHelloGoldApiService.fetchGoldData();
		detailCall.enqueue(new Callback<HgResponse>() {
			@Override
			public void onResponse(final Call<HgResponse> call, final Response<HgResponse> response) {
				if (response != null) {
					if (response.body().mResult == BaseResponse.RESULT.OK) {
						Log.d("response", response.body().toString());
						mApplicationData.addDetail(response.body().mDetail);
						details.setValue(mApplicationData.getDetails());
						if(onFetch!=null)
						onFetch.finish();
					} else {
						Toast.makeText(mApplication.getApplicationContext(), response
								.body().errMessage, Toast.LENGTH_SHORT).show();
						if(onFetch!=null)
						onFetch.failed();
					}
				}
			}
			
			@Override
			public void onFailure(final Call<HgResponse> call, final Throwable t) {
				if(onFetch!=null)
				onFetch.failed();
				t.printStackTrace();
			}
		});
	}
	
	public interface OnFetchData{
		public void finish();
		public void failed();
	}
	
	public LiveData<List<Detail>> getDetails() {
		return details;
	}
}
