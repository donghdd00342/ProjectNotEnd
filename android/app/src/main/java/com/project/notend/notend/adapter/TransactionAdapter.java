package com.project.notend.notend.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.notend.notend.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter{
    private Activity activity;
    private List<String> list_transactions;

    public TransactionAdapter(Activity activity, List<String> list_transactions) {
        this.activity = activity;
        this.list_transactions = list_transactions;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.new_transaction, parent, false);
        TransactionHolder holder = new TransactionHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TransactionHolder vh = (TransactionHolder) holder;
        String transaction = list_transactions.get(position);
        vh.transaction.setText(transaction);
    }

    @Override
    public int getItemCount() {
        return list_transactions.size();
    }

    public static class TransactionHolder extends RecyclerView.ViewHolder{

        private TextView transaction;

        public TransactionHolder(View itemView) {
            super(itemView);
            transaction = (TextView) itemView.findViewById(R.id.tvTransaction);
        }
    }
}
