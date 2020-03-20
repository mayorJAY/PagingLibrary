package com.example.josycom.paginglibrary.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.josycom.paginglibrary.model.User;
import com.example.josycom.paginglibrary.model.UserDataSource;
import com.example.josycom.paginglibrary.model.UserDataSourceFactory;

public class UserViewModel extends ViewModel {

    public LiveData<PagedList<User>> userPagedList;

    public UserViewModel(){
        init();
    }

    private void init() {
        UserDataSourceFactory itemDataSourceFactory = new UserDataSourceFactory();
        LiveData<UserDataSource> liveDataSource = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(UserDataSource.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
}
