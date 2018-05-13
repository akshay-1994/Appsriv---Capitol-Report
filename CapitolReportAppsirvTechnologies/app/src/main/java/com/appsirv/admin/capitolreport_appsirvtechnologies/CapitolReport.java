package com.appsirv.admin.capitolreport_appsirvtechnologies;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CapitolReport extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CapitolReport_DataModel> capitolReport_dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitol_report);

        recyclerView = findViewById(R.id.RecyclerView_Feeds);


        // Data model

        capitolReport_dataModels = new ArrayList<>();

        adapter = new CapitolReport_Adaptor(capitolReport_dataModels,getApplicationContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);

        // if internet is connected then get the data from the api.
        if (isNetworkAvailable()) {
            new GetDataTask().execute();

        }else
            Snackbar.make(findViewById(R.id.parentLayout),"Internet Connection Not Working",Snackbar.LENGTH_LONG);

    }
   // function to check the internet connection
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();


    }


    class GetDataTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Till the feeds activity loads!

            progressDialog = new ProgressDialog(CapitolReport.this);
            progressDialog.setTitle("Loading...");
            progressDialog.setMessage("Please wait while Capitol Report Feeds is being loaded!!!");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // Call the MakeHttpRequests' function of getDataFromAPI to get the data using okHttp!!!

            JSONObject jsonObject = MakeHttpRequest.getDataFromAPI();
            try {

                // null check

                if (jsonObject != null) {

                    // length check

                    if (jsonObject.length() > 0) {

                        // get the items array!

                        JSONArray jsonArray = jsonObject.getJSONArray(Configurations.KEY_ITEMS);

                        // check the length of jsonArray
                        int lenArray= jsonArray.length();
                        if(lenArray>0){
                            for(int jIndex = 0 ; jIndex < lenArray ; jIndex++ ){

                                // get inner objects of items array and get all the details!

                                JSONObject innerObject = jsonArray.getJSONObject(jIndex);
                                //Log.e("Main",String.valueOf(innerObject));

                                String date_without_time = innerObject.getString(Configurations.KEY_DATE);
                                date_without_time =  date_without_time.substring(0,16);
                                CapitolReport_DataModel cp_DataModel = new CapitolReport_DataModel(
                                 innerObject.getString(Configurations.KEY_ID),
                                 innerObject.getString(Configurations.KEY_TITLE),
                                 innerObject.getString(Configurations.KEY_LINK),
                                 date_without_time);

                                 capitolReport_dataModels.add(cp_DataModel);

                            }

                            // setting adapter on the view!
                            /*CapitolReport.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {


                                }
                            });*/



                        }

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

            if(capitolReport_dataModels.size()>0){

                adapter.notifyDataSetChanged();
            }

            else {
                Snackbar.make(findViewById(R.id.parentLayout),"No Data Found",Snackbar.LENGTH_LONG);
            }
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}