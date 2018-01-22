package com.pmmq.hg_assignment.di.module;

import android.util.Log;

import javax.inject.Named;
import javax.inject.Singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pmmq.hg_assignment.BuildConfig;
import com.pmmq.hg_assignment.data.service.HelloGoldApiService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {
	
	@Provides
	@Named("service")
	@Singleton HelloGoldApiService provideService(Gson gson, OkHttpClient client){
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BuildConfig.END_POINT)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.client(client)
				.build();
		return retrofit.create(HelloGoldApiService.class);
	}
	
	
	@Provides
	@Singleton OkHttpClient provideHttpClient(){
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
			@Override
			public void log(final String message) {
				Log.d("RESPONSE" , message);
			}
		});
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		return new OkHttpClient.Builder()
				.addInterceptor(logging)
				.build();
	}
	
	@Provides
	@Singleton
	Gson provideGson(){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
		return gson;
	}
	
}
