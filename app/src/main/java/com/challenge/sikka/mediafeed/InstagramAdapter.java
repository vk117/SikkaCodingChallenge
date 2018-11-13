package com.challenge.sikka.mediafeed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.challenge.sikka.models.Datum;
import com.challenge.sikka.models.Images;

import java.util.List;

public class InstagramAdapter extends RecyclerView.Adapter<InstagramAdapter.ViewHolder> {

    private Context context;
    private List<Datum> data;

    public InstagramAdapter(Context context, List<Datum> imges){
        this.context = context;
        data = imges;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gridview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        new DownloadImageTask(((ViewHolder) holder).img).execute(data.get(position).getImages().getStandardResolution().getUrl());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(position).getLink()));
                intent.setPackage("com.instagram.android");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;

        public ViewHolder(View viewItem){
            super(viewItem);

            img = (ImageView) viewItem.findViewById(R.id.item_img);

        }
    }

}


