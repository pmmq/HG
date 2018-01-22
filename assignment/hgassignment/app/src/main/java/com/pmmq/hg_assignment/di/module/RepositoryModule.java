package com.pmmq.hg_assignment.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import com.pmmq.hg_assignment.HelloGoldApplication;
import com.pmmq.hg_assignment.data.ApplicationData;
import com.pmmq.hg_assignment.data.repositorty.DetailRepository;
import com.pmmq.hg_assignment.data.service.HelloGoldApiService;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
	
	@Provides
	@Singleton
	DetailRepository provideRepository(@Named("app_data") ApplicationData applicationData,
			@Named("service") HelloGoldApiService apiService,
			@Named("hg_app") HelloGoldApplication helloGoldApplication) {
		return new DetailRepository(apiService, applicationData, helloGoldApplication);
	}
}
