package com.example.foodrepo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String link = "https://www.foodrepo.org/api/v3/products";


        try {
            String mainjson = new Asycdata().execute(link).get();

            JSONObject mainobj = new JSONObject(mainjson);

            JSONArray dataarray = mainobj.getJSONArray("data");

            for(int i=0;i<dataarray.length();i++)
            {
                JSONObject singleChild = dataarray.getJSONObject(i);
                JSONObject nameobj = singleChild.getJSONObject("display_name_translations");

                String name = nameobj.getString("en");

                System.out.println("Product Names :"+name);

            }



            System.out.println("Main Activity :"+mainjson);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
