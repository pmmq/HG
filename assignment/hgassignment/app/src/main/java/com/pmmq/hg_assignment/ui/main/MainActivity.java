package com.pmmq.hg_assignment.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.pmmq.hg_assignment.HelloGoldApplication;
import com.pmmq.hg_assignment.R;
import com.pmmq.hg_assignment.data.entity.Detail;
import com.pmmq.hg_assignment.data.repositorty.DetailRepository;
import com.pmmq.hg_assignment.databinding.ActivityMainBinding;
import com.pmmq.hg_assignment.ui.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements SwipeRefreshLayout.OnRefreshListener {
	
	@Inject DetailRepository mDetailRepository;
	@Inject @Named("hg_app") HelloGoldApplication mHelloGoldApplication;
	
	MainViewModel mMainViewModel;
	DetailAdapter detailAdapter;
	Handler dataLoadHandler;
	Runnable runnable;
	final int REFRESH_TIME = 5000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setup view model
		mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
		mMainViewModel.inject(mHelloGoldApplication);
		mMainViewModel.getDetails().observe(this, new Observer<List<Detail>>() {
			@Override
			public void onChanged(@Nullable final List<Detail> details) {
				Log.d("change", details.size() + "");
				if (detailAdapter != null) {
					detailAdapter.notifyDataSetChanged();
					dataBinding.rvDetail.smoothScrollToPosition(mMainViewModel.getDetails().getValue().size() > 0 ? mMainViewModel.getDetails().getValue().size() - 1 : 0);
					dataBinding.swipeContainer.setRefreshing(false);
				}
			}
		});
		// setup list
		if (detailAdapter == null) {
			detailAdapter = new DetailAdapter(mMainViewModel);
		}
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		layoutManager.setReverseLayout(true);
		layoutManager.setStackFromEnd(true);
		dataBinding.rvDetail.setLayoutManager(layoutManager);
		dataBinding.rvDetail.setAdapter(detailAdapter);
		dataBinding.rvDetail.setHasFixedSize(true);
		dataBinding.swipeContainer.setOnRefreshListener(this);
		setupHandler(true);
	}
	
	private void setupHandler(boolean startFromZero){
		if(dataLoadHandler == null){
			dataLoadHandler = new Handler();
			startTimer(startFromZero);
		}
	}
	
	private void startTimer(Boolean startFromZero){
		runnable = new Runnable() {
			@Override
			public void run() {
				if(dataLoadHandler!=null){
					mMainViewModel.loadData(null);
					dataLoadHandler.postDelayed(this,REFRESH_TIME);
				}
			}
		};
		dataLoadHandler.postDelayed(runnable,startFromZero ? 0 : REFRESH_TIME);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if(dataLoadHandler == null){
			setupHandler(true);
		}
	}
	
	@Override
	protected void onStop() {
		if(dataLoadHandler!=null){
			dataLoadHandler.removeCallbacks(runnable);
		}
		dataLoadHandler = null;
		super.onStop();
	}
	
	@Override
	public void onRefresh() {
		if(dataLoadHandler!=null){
			dataLoadHandler.removeCallbacks(runnable);
		}
		dataLoadHandler = null;
		mMainViewModel.loadData(new DetailRepository.OnFetchData() {
			@Override
			public void finish() {
				setupHandler(false);
			}
			
			@Override
			public void failed() {
				setupHandler(false);
			}
		});
	}
	
	@Override
	public void inject() {
		((HelloGoldApplication) getApplication()).getApplicationComponent().inject(this);
	}
	
	@Override
	public int getLayouteRes() {
		return R.layout.activity_main;
	}
}
