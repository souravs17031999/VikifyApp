package com.anuragbannur.android.vikifyapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Object> values;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public View layout;
        public ViewHolder(View itemView) {
            super(itemView);
            layout=itemView;
            mTextView=itemView.findViewById(R.id.text_view);
        }
    }

    public MyAdapter(List<Object> myDataset) {
        values = myDataset;
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_lyout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String name = (String) values.get(position);
        holder.mTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
