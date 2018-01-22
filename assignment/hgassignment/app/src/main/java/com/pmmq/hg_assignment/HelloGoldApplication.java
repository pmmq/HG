package com.pmmq.hg_assignment;

import android.app.Application;

import com.pmmq.hg_assignment.di.ApplicationComponent;
import com.pmmq.hg_assignment.di.DaggerApplicationComponent;
import com.pmmq.hg_assignment.di.module.ApplicationModule;
import com.pmmq.hg_assignment.di.module.RepositoryModule;
import com.pmmq.hg_assignment.di.module.ServiceModule;

public class HelloGoldApplication extends Application {
	
	private static HelloGoldApplication mApplication;
	private ApplicationComponent mApplicationComponent;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
		mApplicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(mApplication))
				.serviceModule(new ServiceModule())
				.repositoryModule(new RepositoryModule())
				.build();
	}
	
	public ApplicationComponent getApplicationComponent() {
		return mApplicationComponent;
	}
}
