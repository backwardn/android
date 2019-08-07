package com.nextcloud.client.di;

import com.owncloud.android.repository.UserInfoRepository;
import com.owncloud.android.ui.viewModel.UserInfoViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserInfoModule {

    private UserInfoDatabase userInfoDatabase;

    @Provides
    public Executor executor() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    UserInfoDatabase providesUserInfoDatabase() {
        return userInfoDatabase;
    }

    @Provides
    public UserInfoViewModel userInfoViewModel(UserInfoRepository userInfoRepository) {
        return new UserInfoViewModel(userInfoRepository);
    }

    @Singleton
    @Provides
    UserInfoDao providesUserInfoDao(UserInfoDatabase userInfoDatabase) {
        return userInfoDatabase.userInfoDao();
    }

    @Singleton
    @Provides
    UserInfoRepository userInfoRepository(UserInfoDao userInfoDao, Executor executor) {
        return new UserInfoRepository(userInfoDao, executor);
    }
}
