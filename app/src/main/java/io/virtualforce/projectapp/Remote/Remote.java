package io.virtualforce.projectapp.Remote;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import io.virtualforce.projectapp.Namaz.*;
import retrofit2.http.QueryMap;

public interface Remote {
    @GET("api/prayer_times?user_ip=202.166.163.179")
    Call<Model> getTimings(@QueryMap Map<String, String> map);

}
