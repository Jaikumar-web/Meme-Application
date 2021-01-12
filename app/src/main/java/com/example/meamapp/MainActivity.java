package com.example.meamapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    ImageView meamImage;
    Button next,share;
    String link = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meamImage = findViewById(R.id.meam);
        next = findViewById(R.id.nextbtn);
        share = findViewById(R.id.sharebtn);

        loadimage();
    }
    public void loadimage(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        String url = "https://meme-api.herokuapp.com/gimme";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {

                    try {
                        link = response.getString("url");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Glide.with(this).load(link).into(meamImage);
                }, error -> Log.d("myapp", "something went wrong"));
        requestQueue.add(jsonObjectRequest);

    }

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Hey Checkout this cool meam  "   +link);
        startActivity(Intent.createChooser(intent,"share this meam with"));
    }

    public void next(View view) {
        loadimage();
    }
}




