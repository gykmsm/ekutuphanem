package com.merveakgormus.ekutuphanem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.merveakgormus.ekutuphanem.Model.User;

public class LoginActivity extends AppCompatActivity {

    TextView tvgoregister;
    private FirebaseAuth mAuth;

    EditText edt_mail, edt_password;

    @Override
    protected void onStart() {
        super.onStart();
    }

    String mail = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        edt_mail = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);


        tvgoregister = (TextView) findViewById(R.id.tv_goregister);

        Button btn = (Button) findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = edt_mail.getText().toString();
                password = edt_password.getText().toString();
                if (!mail.equals("") && !password.equals("")) {
                    if (mail != null && password != null) {
                        final User user = new User(edt_mail.getText().toString(), edt_password.getText().toString());
                        Login(user);
                        finish();
                    }
                } else
                    Toast.makeText(LoginActivity.this, "Lütfen boş bırakmayınız!", Toast.LENGTH_SHORT).show();
            }
        });

        tvgoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    public void Login(User user) {

        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {

                        }
                    }
                });
    }

}
