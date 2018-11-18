package com.merveakgormus.ekutuphanem.Model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.merveakgormus.ekutuphanem.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    Activity activity;
    String[] images;
    String[]  booksname;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemv = layoutInflater.inflate(R.layout.view_pager_item, container, false);

        ImageView imageView = (ImageView) itemv.findViewById(R.id.book_img);

        TextView tv_bookname = (TextView) itemv.findViewById(R.id.tv_bookname);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        imageView.setMinimumHeight(height);
        imageView.setMinimumWidth(width);

        try {
            Picasso.with(activity.getApplicationContext()).load(images[position])
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher).into(imageView);

            tv_bookname.setText(booksname[position]);
        } catch (Exception e) {

        }
        container.addView(itemv);

        return itemv;
    }

    LayoutInflater layoutInflater;

    public ViewPagerAdapter(Activity activity, String[] images, String[] booksname) {
        this.activity = activity;
        this.images = images;
        this.booksname = booksname;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
