package io.virtualforce.projectapp.Namaz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {
    @SerializedName("Fajr")
    @Expose
    private String Fajr;
    @SerializedName("Duha")
    @Expose
    private String Duha;
    @SerializedName("Dhuhr")
    @Expose
    private String Dhuhr;
    @SerializedName("Asr")
    @Expose
    private String Asr;
    @SerializedName("Maghrib")
    @Expose
    private String Maghrib;
    @SerializedName("Isha")
    @Expose
    private String Isha;

    public void setFajr(String fajr) {
        Fajr = fajr;
    }

    public void setDuha(String duha) {
        Duha = duha;
    }

    public void setDhuhr(String dhuhr) {
        Dhuhr = dhuhr;
    }

    public void setAsr(String asr) {
        Asr = asr;
    }

    public void setMaghrib(String maghrib) {
        Maghrib = maghrib;
    }

    public void setIsha(String isha) {
        Isha = isha;
    }

    public String getFajr() {
        return Fajr;
    }

    public String getDuha() {
        return Duha;
    }

    public String getDhuhr() {
        return Dhuhr;
    }

    public String getAsr() {
        return Asr;
    }

    public String getMaghrib() {
        return Maghrib;
    }

    public String getIsha() {
        return Isha;
    }
}
