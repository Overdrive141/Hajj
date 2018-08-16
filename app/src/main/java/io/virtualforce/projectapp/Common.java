package io.virtualforce.projectapp;

import io.virtualforce.projectapp.Remote.Remote;
import io.virtualforce.projectapp.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL = "http://www.islamicfinder.us/index.php/";

    public static Remote getRemote(){
        return RetrofitClient.getClient(BASE_URL).create(Remote.class);
    }
}
