package com.pmmq.hg_assignment.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.text.DateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pmmq.hg_assignment.HelloGoldApplication;
import com.pmmq.hg_assignment.data.entity.Detail;
import com.pmmq.hg_assignment.data.repositorty.DetailRepository;

public class MainViewModel extends ViewModel {
	
	@Inject DetailRepository mDetailRepository;
	@Inject @Named("hg_app") HelloGoldApplication mApplication;
	DateFormat dateFormat;
	
	public void inject(HelloGoldApplication application) {
		application.getApplicationComponent().inject(this);
	}
	
	public void loadData(DetailRepository.OnFetchData onFetchData) {
		mDetailRepository.fetchData(onFetchData);
	}
	
	public LiveData<List<Detail>> getDetails() {
		return mDetailRepository.getDetails();
	}
}
