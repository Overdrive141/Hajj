package io.virtualforce.projectapp;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import io.virtualforce.projectapp.Namaz.*;

import dmax.dialog.SpotsDialog;
import io.virtualforce.projectapp.Remote.Remote;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimingActivity extends AppCompatActivity {

    Remote mService;
    ImageView namazI;
    TextView namazT;
    AlertDialog Dialog;
    EditText idFajr;
    EditText idDuha;
    EditText idDuhr;
    EditText idAsr;
    EditText idMaghrib;
    EditText idIsha;
    Map<String, String> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ntiming);

        mService = Common.getRemote();
        namazI = (ImageView) findViewById(R.id.namazImage);
        namazT = (TextView)findViewById(R.id.namazText);
        idFajr = (EditText) findViewById(R.id.txtFajr);
        idDuha = (EditText)findViewById(R.id.txtDuha);
        idDuhr = (EditText)findViewById(R.id.txtDuhr);
        idAsr = (EditText)findViewById(R.id.txtAsr);
        idMaghrib = (EditText)findViewById(R.id.txtMaghrib);
        idIsha = (EditText)findViewById(R.id.txtIsha);
        Dialog = new SpotsDialog(TimingActivity.this);

        Dialog.show();
        getTiming();

    }

   private void getTiming() {
        Map<String, String> queryParams = new HashMap<>();
        Call<Model> call = mService.getTimings(queryParams);

        call.enqueue(new Callback<Model>() {
           @Override
           public void onResponse(Call<Model> call, Response<Model> response) {
               Dialog.dismiss();

               String fajrTime = response.body().getResults().getFajr();
               fajrTime = fajrTime.replace("%", "");
               String duhaTime = response.body().getResults().getDuha();
               duhaTime = duhaTime.replace("%", "");
               String dhuhrTime = response.body().getResults().getDhuhr();
               dhuhrTime = dhuhrTime.replace("%", "");
               String asrTime = response.body().getResults().getAsr();
               asrTime = asrTime.replace("%", "");
               String maghribTime = response.body().getResults().getMaghrib();
               maghribTime = maghribTime.replace("%", "");
               String ishaTime = response.body().getResults().getIsha();
               ishaTime = ishaTime.replace("%", "");

               map.put("Fajr", fajrTime);
               map.put("Duha", duhaTime);
               map.put("Dhuhr", dhuhrTime);
               map.put("Asr", asrTime);
               map.put("Maghrib", maghribTime);
               map.put("Isha", ishaTime);

               updateUI();
           }

           @Override
           public void onFailure(Call<Model> call, Throwable t) {
               Log.e("Error", t.getMessage());
               Dialog.dismiss();

           }
       });
   }

   public void updateUI(){

        idFajr.setText(map.get("Fajr"));
        idDuha.setText(map.get("Duha"));
        idDuhr.setText(map.get("Dhuhr"));
        idAsr.setText(map.get("Asr"));
        idMaghrib.setText(map.get("Maghrib"));
        idIsha.setText(map.get("Isha"));

    }

}
