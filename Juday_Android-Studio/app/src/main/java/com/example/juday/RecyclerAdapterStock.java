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

public class RecyclerAdapterStock extends RecyclerView.Adapter<RecyclerAdapterStock.MyViewHolder> {

    private ArrayList<Stock> cardList;

    public RecyclerAdapterStock(ArrayList<Stock> cardList) {
        this.cardList = cardList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView typeText, valueText, timeText;

        public MyViewHolder(final View view) {
            super(view);
            typeText = view.findViewById(R.id.stocks_static_RANGE_300);
            valueText = view.findViewById(R.id.stocks_dynamic_RANGE_STOCK_300);
            timeText = view.findViewById(R.id.stocks_static_RANGE_Restock_300);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterStock.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stockrecyclerlayout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Stock card = cardList.get(position);

        holder.typeText.setText(card.getType());
        holder.valueText.setText(card.getValue());
        holder.timeText.setText(card.getTime());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
