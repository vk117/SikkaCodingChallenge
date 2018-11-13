package com.challenge.sikka.mediafeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.challenge.sikka.models.Images;

import java.util.List;

public class InstagramAdapter extends RecyclerView.Adapter<InstagramAdapter.ViewHolder> {

    private Context context;
    private List<Images> pics;

    public InstagramAdapter(Context context, List<Images> imges){
        this.context = context;
        pics = imges;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_gridview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        new DownloadImageTask(((ViewHolder) holder).img).execute(pics.get(position).getStandardResolution().getUrl());
    }

    @Override
    public int getItemCount(){
        return pics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;

        public ViewHolder(View viewItem){
            super(viewItem);

            img = (ImageView) viewItem.findViewById(R.id.item_img);

        }
    }

}


