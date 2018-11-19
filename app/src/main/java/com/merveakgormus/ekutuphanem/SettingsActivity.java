package com.merveakgormus.ekutuphanem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    //navigation Layout
    RelativeLayout menu;
    LinearLayout go_home, go_settings, go_profile, go_bucket;
    ImageView img_logout;
    TextView close, tv_toolbar_name;
    ImageView hmenu, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        go_home = (LinearLayout)findViewById(R.id.go_home);
        go_bucket = (LinearLayout)findViewById(R.id.go_bucket);
        go_profile = (LinearLayout)findViewById(R.id.go_profile);
        go_settings = (LinearLayout)findViewById(R.id.go_settings);
        tv_toolbar_name = (TextView)findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("AYARLAR");

        back = (ImageView)findViewById(R.id.back);
        img_logout = (ImageView)findViewById(R.id.img_logout);
        menu = (RelativeLayout) findViewById(R.id.menu);

        hmenu = (ImageView) findViewById(R.id.hmenu);
        close = (TextView) findViewById(R.id.close);


        hmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setVisibility(View.VISIBLE);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setVisibility(View.GONE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
            }
        });

        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                menu.setVisibility(View.GONE);
            }
        });
        go_bucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(BucketActivity.this, BucketActivity.class));
                //menu.setVisibility(View.GONE);
            }
        });
        go_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
                menu.setVisibility(View.GONE);
            }
        });
        go_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
                menu.setVisibility(View.GONE);
            }
        });
    }
}
