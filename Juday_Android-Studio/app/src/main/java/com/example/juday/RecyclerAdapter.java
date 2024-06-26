package com.example.juday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Cards> cardList;

    public RecyclerAdapter(ArrayList<Cards> cardList) {
        this.cardList = cardList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView orderText, timeText;

        public MyViewHolder(final View view) {
            super(view);

            orderText = view.findViewById(R.id.recyclerCardDynamicOrder);
            timeText = view.findViewById(R.id.recyclerCardDynamicEst);

        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlayout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        Cards card = cardList.get(position);

        holder.orderText.setText(card.getOrderNumber());
        holder.timeText.setText(card.getTime());

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
