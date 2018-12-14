package com.example.admin.restful_api_retrofit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.restful_api_retrofit.R;
import com.example.admin.restful_api_retrofit.data.model.Item;

import java.util.List;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder>{

    private List<Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.simple_list_item_1,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(postView,this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item item = mItems.get(i);
        TextView tv = viewHolder.tvName;
        tv.setText(item.getOwner().getDisplayName());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }

    public AnswersAdapter(List<Item> mItems, Context mContext, PostItemListener mItemListener) {
        this.mItems = mItems;
        this.mContext = mContext;
        this.mItemListener = mItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName;
        PostItemListener itemListener;


        public ViewHolder(@NonNull View itemView,PostItemListener itemListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            this.itemListener = itemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Item item = getItem(getAdapterPosition());
            this.itemListener.onPostClick(item.getAnswerId());

        }
    }
}
