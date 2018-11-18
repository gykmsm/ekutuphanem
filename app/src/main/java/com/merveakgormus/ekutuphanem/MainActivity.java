package com.merveakgormus.ekutuphanem;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Constants;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.merveakgormus.ekutuphanem.Adapter.SimpleRecyclerView;
import com.merveakgormus.ekutuphanem.Model.Book;
import com.merveakgormus.ekutuphanem.Model.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView hmenu, back;
    ViewPager pager_book;
    ViewPagerAdapter viewPagerAdapter;

    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;

    RecyclerView recyclerView;
    Book book;
    List<Book> booklist;

    //navigation Layout
    RelativeLayout menu;
    LinearLayout go_home, go_settings, go_profile, go_bucket;
    ImageView img_logout;
    TextView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go_home = (LinearLayout)findViewById(R.id.go_home);
        go_bucket = (LinearLayout)findViewById(R.id.go_bucket);
        go_profile = (LinearLayout)findViewById(R.id.go_profile);
        go_settings = (LinearLayout)findViewById(R.id.go_settings);

        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent());
            }
        });
        go_bucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BucketActivity.class));
            }
        });
        go_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
        go_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });




        firebaseDatabase    = FirebaseDatabase.getInstance();
        databaseReference   = firebaseDatabase.getReference().child("kutuphanem");
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(linearLayoutManager);

        booklist = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds :dataSnapshot.getChildren())
                {
                    book = new Book(ds.child("bookname").getValue().toString(),ds.child("bookauthor").getValue().toString(),
                            Integer.parseInt(ds.child("pagenumber").getValue().toString()),
                            Double.parseDouble(ds.child("price").getValue().toString()),
                            ds.child("covertype").toString(), ds.child("productdescription").getValue().toString(),
                            ds.child("storageUrl").getValue().toString());
                    booklist.add(book);

                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            book.setStorageUrl(uri.getPath());

                        }
                    });

                    SimpleRecyclerView adapter = new SimpleRecyclerView(booklist, new Book.CustomItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {

                            Book book = booklist.get(position);
                            Bundle bundle = new Bundle();
                            bundle.putString("bookname", book.getBookname());
                            bundle.putString("bookauthor", book.getBookauthor());
                            bundle.putInt("pagenumber", book.getPagenumber());
                            bundle.putDouble("price", book.getPrice());
                            bundle.putString("covertype", book.getCovertype());
                            bundle.putString("productdescription", book.getProductdescription());
                            bundle.putString("storageUrl", book.getStorageUrl());

                            Intent i = new Intent(MainActivity.this, DetailActivity.class);
                            i.putExtra("bundle" ,bundle);
                            startActivity(i);

                        }
                    }, MainActivity.this);

                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back = (ImageView)findViewById(R.id.back);
        img_logout = (ImageView)findViewById(R.id.img_logout);
        back.setVisibility(View.GONE);
        menu = (RelativeLayout) findViewById(R.id.menu);

        hmenu = (ImageView) findViewById(R.id.hmenu);
        close = (TextView) findViewById(R.id.close);

        pager_book = (ViewPager)findViewById(R.id.pager_book);

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
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

    public void GetBooks(Book book){}
}
