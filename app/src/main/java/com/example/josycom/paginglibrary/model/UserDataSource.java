package com.example.josycom.paginglibrary.model;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.josycom.paginglibrary.network.ApiService;
import com.example.josycom.paginglibrary.network.ApiServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource extends PageKeyedDataSource<Long, User> {
    public static int PAGE_SIZE = 10;
    public static long FIRST_PAGE = 1;

    // Load the first page of the data used to initialize the RecyclerView
    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, User> callback) {
        ApiService apiService = ApiServiceBuilder.buildService(ApiService.class);
        Call<UserResponse> call = apiService.getUsers(FIRST_PAGE);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse apiResponse = response.body();
                if (apiResponse != null){
                    List<User> responseItems = apiResponse.getUsers();
                    callback.onResult(responseItems, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    //
    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, User> callback) {
        ApiService apiService = ApiServiceBuilder.buildService(ApiService.class);
        Call<UserResponse> call = apiService.getUsers(params.key);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse apiResponse = response.body();
                if (apiResponse != null){
                    List<User> responseItems = apiResponse.getUsers();
                    long key;
                    if (params.key > 1){
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(responseItems, key);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, User> callback) {
        ApiService apiService = ApiServiceBuilder.buildService(ApiService.class);
        Call<UserResponse> call = apiService.getUsers(params.key);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse apiResponse = response.body();
                if (apiResponse != null){
                    List<User> responseItems = apiResponse.getUsers();
                    callback.onResult(responseItems, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
