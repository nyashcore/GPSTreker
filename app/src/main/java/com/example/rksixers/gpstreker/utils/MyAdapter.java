package com.example.rksixers.gpstreker.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rksixers.gpstreker.R;

/**
 * Created by said on 06.03.16.
 */
public class MyAdapter extends SelectableAdapter<MyAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private ViewHolder.ClickListener clickListener;

    private String mNavTitles[];
    private int mIcons[];
    private String name;
    private int profile;
    private String email;

    public MyAdapter(ViewHolder.ClickListener listener, String Titles[],int Icons[],String Name,String Email, int Profile) {
        clickListener = listener;
        mNavTitles = Titles;
        mIcons = Icons;
        name = Name;
        email = Email;
        profile = Profile;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

            return new ViewHolder(clickListener, v,viewType);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false);

            return new ViewHolder(clickListener, v,viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.imageView.setImageResource(mIcons[position -1]);
        } else {
            holder.profile.setImageResource(profile);
            holder.Name.setText(name);
            holder.email.setText(email);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length+1;
    }

    public String getItem(int position) {
        return mNavTitles[position];
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;
        private ClickListener clickListener;
        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;

        public ViewHolder(ClickListener clickListener, View itemView, int ViewType) {
            super(itemView);

            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                Holderid = 1;
            } else {
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.circleView);
                Holderid = 0;
            }
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClicked(getAdapterPosition());
            }
        }

        public interface ClickListener {
            void onItemClicked(int position);
        }
    }
}
