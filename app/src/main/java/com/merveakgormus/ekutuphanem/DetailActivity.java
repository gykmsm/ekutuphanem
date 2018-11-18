package com.merveakgormus.ekutuphanem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    ImageView back, hmenu;
    RelativeLayout menu;
    TextView close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        close = (TextView)findViewById(R.id.close) ;
        hmenu = (ImageView)findViewById(R.id.hmenu);
        back = (ImageView) findViewById(R.id.back);
        menu = (RelativeLayout)findViewById(R.id.detail_menu);


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


    }
}
