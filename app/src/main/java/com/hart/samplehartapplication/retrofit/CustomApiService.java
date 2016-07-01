package com.hart.samplehartapplication.retrofit;

import com.hart.samplehartapplication.HomeActivity;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedOutput;

/**
 * Created by hudsonhughes on 6/30/16.
 */
public class CustomApiService {
    private static IHartApiService service;
    private static HartApiRestConfig restAdapterConfig = new HartApiRestConfig();

    private static final IHartApiService getService() {
        if (service == null) {
            service = RestAdapterUtil.createRestService(restAdapterConfig, IHartApiService.class);
        }
        return service;
    }

    public static final void login(String email, String password) {
        //getService().login(email, password, RestObjectCallback.create(new UnlinkProviderUserEvent()));
    }

    private static interface IHartApiService {

//        @DELETE("/provider/{provider_id}/user/{user_id}/remove")
//        public void login(@Path("provider_id") String providerId, @Path("user_id") String userId, Callback<UnlinkProviderUserResponse> cb);


    }
}
