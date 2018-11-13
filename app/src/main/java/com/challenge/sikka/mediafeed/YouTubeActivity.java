package com.challenge.sikka.mediafeed;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.ListView;
import com.challenge.sikka.models.Example;
import com.challenge.sikka.api.YouTubeApi;
import com.challenge.sikka.api.YouTubeClient;
import com.challenge.sikka.models.Snippet;

import java.util.ArrayList;
import java.util.List;

public class YouTubeActivity extends AppCompatActivity {

    private ListView videos;
    private YouTubeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        videos = (ListView) findViewById(R.id.youtube_list);

        YouTubeClient client = new YouTubeClient();
        YouTubeApi api = client.start();
        Call<Example> call = api.listOfVideos();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()){
                    List<Snippet> snippets = new ArrayList<Snippet>();

                    for(int i = 0; i < response.body().getItems().size(); i++){
                        snippets.add(response.body().getItems().get(i).getSnippet());
                    }

                    adapter = new YouTubeAdapter(getApplicationContext(), snippets);
                    videos.setAdapter(adapter);
                    //System.out.println(response.body().getItems());
                }
                else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
