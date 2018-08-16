package io.virtualforce.projectapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mydrawerLayout;
    AlertDialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mydrawerLayout = findViewById(R.id.myDrawerLayout);
        NavigationView mynavigationView = findViewById(R.id.nav_view);

        mynavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mydrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public void OnClick(View view) {
        Intent intent = null, chooser = null;
        if ((view.getId() == R.id.feedbackImage) || (view.getId() == R.id.feedbackText)) {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"feedback@virtual-force.io"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Feedback");
            intent.putExtra(Intent.EXTRA_TEXT, "Please leave your feedback here.");

            chooser = Intent.createChooser(intent, "Send Email");
            startActivity(chooser);
            return;
        }

        if ((view.getId() == R.id.helpImage) || (view.getId() == R.id.helpText)) {
            String phone = "911";
            Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent1);
            chooser = Intent.createChooser(intent1, "Need Help?");
            startActivity(chooser);
            return;
        }


        if ((view.getId() == R.id.qurbaniImage) || (view.getId() == R.id.qurbaniText)) {
            String url = "http://www.google.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            return;
        }

        if ((view.getId() == R.id.facilityImage) || (view.getId() == R.id.facilityText)) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:31.5103966,74.3524652,16z"));
            chooser = Intent.createChooser(intent, "Find Facility");
            startActivity(chooser);
            return;
        }

        if((view.getId() == R.id.namazImage || view.getId() == R.id.namazText)){
            Intent intent3 = new Intent(this, TimingActivity.class);
            startActivity(intent3);
        }

        if((view.getId() == R.id.languageImage || view.getId() == R.id.languageText)){
            showLanguageDialog();
        }

    }

    private void showLanguageDialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        final String[] listItems = {"English", "اردو"};
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("en");
                    recreate();
                }
                else if(i==1){
                    setLocale("ur");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

      private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration , getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                mydrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}