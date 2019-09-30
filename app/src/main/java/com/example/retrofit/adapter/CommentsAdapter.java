package com.example.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.data.model.Owner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private ArrayList<Owner> list;
    private Context context;

    public CommentsAdapter(ArrayList<Owner> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Owner owner = list.get(position);
        holder.name.setText(owner.getDisplayName());
        Picasso.with(context).load(owner.getProfileImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateComments(ArrayList<Owner> lista){
        list = lista;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imgComments);
            name = itemView.findViewById(R.id.txtComments);
        }
    }
}
