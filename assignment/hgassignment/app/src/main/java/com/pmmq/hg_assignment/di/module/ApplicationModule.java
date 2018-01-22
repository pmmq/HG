package com.pmmq.hg_assignment.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import com.pmmq.hg_assignment.HelloGoldApplication;
import com.pmmq.hg_assignment.data.ApplicationData;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
	HelloGoldApplication mApplication;
	
	public ApplicationModule(final HelloGoldApplication application) {
		mApplication = application;
	}
	@Named("hg_app")
	@Provides
	@Singleton
	HelloGoldApplication provideApplication(){
		return mApplication;
	}
	
	@Provides
	@Singleton SharedPreferences provideSharePref(@Named("hg_app") HelloGoldApplication application) {
		return application.getSharedPreferences("DATA_PREF", Context.MODE_PRIVATE);
	}
	
	@Named("app_data")
	@Provides
	@Singleton ApplicationData provideApplicationData(SharedPreferences sharedPreferences){
		return new ApplicationData(sharedPreferences);
	}
	
}
