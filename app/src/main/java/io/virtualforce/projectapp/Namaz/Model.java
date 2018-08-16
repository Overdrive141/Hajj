package io.virtualforce.projectapp.Namaz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("results")
    @Expose
    private Results results;

    public Model(Results results) {
        this.results = results;
    }

    public  Results getResults() {
        return results;
    }
}


