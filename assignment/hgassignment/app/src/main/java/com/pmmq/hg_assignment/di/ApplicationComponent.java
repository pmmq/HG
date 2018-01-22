package com.pmmq.hg_assignment.di;

import javax.inject.Singleton;

import com.pmmq.hg_assignment.di.module.ApplicationModule;
import com.pmmq.hg_assignment.di.module.RepositoryModule;
import com.pmmq.hg_assignment.di.module.ServiceModule;
import com.pmmq.hg_assignment.ui.main.DetailItemViewModel;
import com.pmmq.hg_assignment.ui.main.MainActivity;
import com.pmmq.hg_assignment.ui.main.MainViewModel;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class, RepositoryModule.class})
public interface ApplicationComponent {
	void inject(MainActivity mainActivity);
	void inject(MainViewModel mainViewModel);
	void inject(DetailItemViewModel dtailmodel);
}
