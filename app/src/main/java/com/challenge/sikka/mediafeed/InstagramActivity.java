package com.challenge.sikka.mediafeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.challenge.sikka.api.InstagramApi;
import com.challenge.sikka.api.InstagramClient;
import com.challenge.sikka.models.Example2;
import com.challenge.sikka.models.Images;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstagramActivity extends AppCompatActivity {

    private RecyclerView pictures;
    private InstagramAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        InstagramClient client = new InstagramClient();
        InstagramApi api = client.start();
        Call<Example2> call = api.listOfPics();
        call.enqueue(new Callback<Example2>() {
            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {
                if(response.isSuccessful()){
                    List<Images> images = new ArrayList<Images>();

                    for(int i = 0; i < response.body().getData().size(); i++){
                        images.add(response.body().getData().get(i).getImages());

                       pictures = (RecyclerView) findViewById(R.id.grid);
                       pictures.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                       pictures.setHasFixedSize(true);

                       adapter = new InstagramAdapter(InstagramActivity.this, images);
                       pictures.setAdapter(adapter);
                    }
                }
                else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Example2> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
