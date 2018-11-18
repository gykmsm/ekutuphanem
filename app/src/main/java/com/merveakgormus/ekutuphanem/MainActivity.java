package com.merveakgormus.ekutuphanem;

import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.merveakgormus.ekutuphanem.Model.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView hmenu, back;
    TextView close;
    RelativeLayout menu;
    ViewPager pager_book;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back = (ImageView)findViewById(R.id.back);
        back.setVisibility(View.GONE);
        menu = (RelativeLayout) findViewById(R.id.menu);

        hmenu = (ImageView) findViewById(R.id.hmenu);
        close = (TextView) findViewById(R.id.close);

        pager_book = (ViewPager)findViewById(R.id.pager_book);

        String[] books = {"https://www.google.com.tr/search?q=books&source=lnms&tbm=isch&sa=X&ved=0ahUKEwj_jJz2793eAhVowosKHRjBAN4Q_AUIDigB&biw=1920&bih=889#imgrc=RYA7QHhurU6q4M:","https://www.google.com.tr/search?q=books&source=lnms&tbm=isch&sa=X&ved=0ahUKEwj_jJz2793eAhVowosKHRjBAN4Q_AUIDigB&biw=1920&bih=889#imgrc=1BWnX7W4pGxjnM:"};
        String[] booksname = {"asdf", "aslkjd"};

        viewPagerAdapter = new ViewPagerAdapter(MainActivity.this, books, booksname);

        pager_book.setAdapter(viewPagerAdapter);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setVisibility(View.GONE);
                hmenu.setEnabled(true);
            }
        });

        hmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hmenu.setEnabled(false);
                menu.setVisibility(View.VISIBLE);
            }
        });

    }
}
