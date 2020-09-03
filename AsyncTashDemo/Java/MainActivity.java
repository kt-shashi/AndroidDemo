package com.shashi.networkingdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button button;
    String fetchedData = "";
    String resultData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    public void initViews() {
        textView = findViewById(R.id.textViewData);
        button = findViewById(R.id.buttonGetData);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        GetJsonApiData process = new GetJsonApiData();
        process.execute();

    }

    public class GetJsonApiData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("myappsk", "Starting");
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String url_string = "https://api.androidhive.info/contacts/";

            try {

                //Open connection
                URL url = new URL(url_string);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");

                //Check if we get proper response
                int code = httpURLConnection.getResponseCode();
                if (code != 200) {
                    throw new Exception("Invalid response from server: " + code);
                }

                //Get data
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line = "";

                while (line != null) {
                    line = bufferedReader.readLine();
                    fetchedData += line;
                }

                //Fetch Json
                JSONObject jsonObjectRoot = new JSONObject(fetchedData);
                //Getting contact arrayn from Json ogject
                JSONArray contacts = jsonObjectRoot.getJSONArray("contacts");
                //looping thriugh all contact
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject contact = contacts.getJSONObject(i);

                    String id = contact.getString("id");
                    String name = contact.getString("name");
                    String email = contact.getString("email");

                    resultData += "\nId: " + id + "\nName: " + name + "\nEmail: " + email;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            textView.setText(resultData);

        }

    }
}