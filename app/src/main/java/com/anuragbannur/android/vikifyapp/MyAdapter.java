package com.anuragbannur.android.vikifyapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG="Adapter";
    private List<String> values;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public View layout;
        public ViewHolder(View itemView) {
            super(itemView);
            layout=itemView;
            mTextView=itemView.findViewById(R.id.text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent mIntent=new Intent(view.getContext(),playVideo.class);
                    mIntent.putExtra("VideoNumber",getAdapterPosition()+1);
                    view.getContext().startActivity(mIntent);
                }
            });
        }
    }

    public MyAdapter(List<String> myDataset) {
        values = myDataset;
        Log.v(TAG,"DATASET:"+myDataset);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String name = (String) values.get(position);
        holder.mTextView.setText(name);

        Log.v(TAG,"Position"+position);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
