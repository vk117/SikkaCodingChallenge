package com.challenge.sikka.mediafeed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.sikka.models.Item;
import com.challenge.sikka.models.Snippet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class YouTubeAdapter extends ArrayAdapter<Snippet> {

    private Context context;
    private List<Snippet> videoList = new ArrayList<Snippet>();

    public YouTubeAdapter(@NonNull Context context, List<Snippet> list){
        super(context, 0, list);
        this.context = context;
        videoList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.activity_listview, parent, false);

        final Snippet currentSnippet = videoList.get(position);
        new DownloadImageTask((ImageView)listItem.findViewById(R.id.thumbnail)).execute(currentSnippet.getThumbnails().getDefault().getUrl());

        TextView title = (TextView) listItem.findViewById(R.id.title);
        title.setText(currentSnippet.getTitle());

        TextView description = (TextView) listItem.findViewById(R.id.description);
        description.setText(currentSnippet.getDescription());

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + currentSnippet.getResourceId().getVideoId()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
                context.startActivity(intent);
            }
        });


        return listItem;
    }

}



