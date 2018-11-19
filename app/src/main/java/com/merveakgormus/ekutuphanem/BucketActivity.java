package com.merveakgormus.ekutuphanem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.merveakgormus.ekutuphanem.Adapter.SimpleRecyclerView;
import com.merveakgormus.ekutuphanem.Model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BucketActivity extends AppCompatActivity {

    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    RecyclerView recyclerView;
    Book book;
    List<Book> booklist;

    //navigation Layout
    RelativeLayout menu;
    LinearLayout go_home, go_settings, go_profile, go_bucket;
    ImageView img_logout;
    TextView close, tv_toolbar_name;
    ImageView hmenu, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);


        go_home = (LinearLayout)findViewById(R.id.go_home);
        go_bucket = (LinearLayout)findViewById(R.id.go_bucket);
        go_profile = (LinearLayout)findViewById(R.id.go_profile);
        go_settings = (LinearLayout)findViewById(R.id.go_settings);
        tv_toolbar_name = (TextView)findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("SEPETİM");

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
                startActivity(new Intent(BucketActivity.this, LoginActivity.class));
            }
        });

        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BucketActivity.this, MainActivity.class));
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
                startActivity(new Intent(BucketActivity.this, ProfileActivity.class));
                menu.setVisibility(View.GONE);
            }
        });
        go_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BucketActivity.this, SettingsActivity.class));
                menu.setVisibility(View.GONE);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase    = FirebaseDatabase.getInstance();
        databaseReference   = firebaseDatabase.getReference().child("Orders").child(firebaseUser.getUid());



        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(linearLayoutManager);

        booklist = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot ds :dataSnapshot.getChildren())
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
                            bundle.putString("book_id", ds.getKey());
                            Intent i = new Intent(BucketActivity.this, DetailActivity.class);
                            i.putExtra("bundle" ,bundle);
                            startActivity(i);

                        }
                    }, BucketActivity.this);

                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    class SimpleRecyclerView extends RecyclerView.Adapter<SimpleRecyclerView.ViewHolder> {
        private List<Book> mDataset;
        Book.CustomItemClickListener listener;

        public Context context;

        @NonNull
        @Override
        public SimpleRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bucket_list, viewGroup, false);
            final SimpleRecyclerView.ViewHolder view_holder = new SimpleRecyclerView.ViewHolder(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, view_holder.getPosition());
                }
            });

            return view_holder;
        }

        @Override
        public void onBindViewHolder(@NonNull SimpleRecyclerView.ViewHolder viewHolder, int i) {
            viewHolder.tv_b_bookname.setText(mDataset.get(i).getBookname());
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView tv_b_price;
            public TextView tv_b_bookname;

            public ViewHolder(View view) {
                super(view);
                tv_b_bookname = (TextView)view.findViewById(R.id.tv_b_bookname);
                tv_b_price = (TextView)view.findViewById(R.id.tv_b_price);

            }

        }


        public SimpleRecyclerView(List<Book> myDataset, Book.CustomItemClickListener listener, Context context) {
            mDataset = myDataset;
            this.listener = listener;
            this.context = context;
        }


    }

}
