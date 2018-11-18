package com.merveakgormus.ekutuphanem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.merveakgormus.ekutuphanem.Model.Book;

public class DetailActivity extends AppCompatActivity {

    ImageView back, hmenu;
    RelativeLayout menu;
    TextView close;

    TextView tv_d_bookname, tv_d_price, tv_d_author,tv_d_description, tv_toolbar_name;

    LinearLayout go_home, go_settings, go_profile, go_bucket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        go_home = (LinearLayout)findViewById(R.id.go_home);
        go_bucket = (LinearLayout)findViewById(R.id.go_bucket);
        go_profile = (LinearLayout)findViewById(R.id.go_profile);
        go_settings = (LinearLayout)findViewById(R.id.go_settings);

        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });
        go_bucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, BucketActivity.class));
            }
        });
        go_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, ProfileActivity.class));
            }
        });
        go_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, SettingsActivity.class));
            }
        });

        close = (TextView)findViewById(R.id.close) ;
        hmenu = (ImageView)findViewById(R.id.hmenu);
        back = (ImageView) findViewById(R.id.back);
        menu = (RelativeLayout)findViewById(R.id.detail_menu);

        tv_d_bookname = (TextView)findViewById(R.id.tv_d_bookname);
        tv_d_author = (TextView)findViewById(R.id.tv_d_author);
        tv_d_price = (TextView)findViewById(R.id.tv_d_price);
        tv_d_description = (TextView)findViewById(R.id.tv_d_description) ;
        tv_toolbar_name = (TextView)findViewById(R.id.tv_toolbar_name);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hmenu.setEnabled(true);
                menu.setVisibility(View.GONE);
            }
        });
        hmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hmenu.setEnabled(false);
                menu.setVisibility(View.VISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        Bundle bundle = getIntent().getBundleExtra("bundle");

        Book book = new Book(bundle.getString("bookname"),
                bundle.getString("bookauthor"),bundle.getInt("pagenumber"),
                bundle.getDouble("price"),bundle.getString("covertype"), bundle.getString("productdescription"),
                bundle.getString("storageUrl"));


        tv_d_bookname.setText(book.getBookname());
        tv_d_price.setText(book.getPrice().toString());
        tv_d_author.setText(book.getBookauthor());
        tv_d_description.setText(book.getProductdescription());
        tv_toolbar_name.setText(book.getBookname());

    }
}
