package com.merveakgormus.ekutuphanem.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.merveakgormus.ekutuphanem.MainActivity;
import com.merveakgormus.ekutuphanem.Model.Book;
import com.merveakgormus.ekutuphanem.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimpleRecyclerView extends RecyclerView.Adapter<SimpleRecyclerView.ViewHolder> {
    private List<Book> mDataset;
    Book.CustomItemClickListener listener;

    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_item_bookname;
        public ImageView img_item_bookimg;

        public ViewHolder(View view) {
            super(view);

            tv_item_bookname = (TextView) view.findViewById(R.id.tv_item_bookname);
            img_item_bookimg = (ImageView) view.findViewById(R.id.img_item_bookimg);

        }

    }


    public SimpleRecyclerView(List<Book> myDataset, Book.CustomItemClickListener listener, Context context) {
        mDataset = myDataset;
        this.listener = listener;
       this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_books, viewGroup, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_item_bookname.setText(mDataset.get(position).getBookname());
        Picasso.with(context)
                .load(R.drawable.k2)
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img_item_bookimg);
        //mDataset.get(position).getStorageUrl()

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
