package com.merveakgormus.ekutuphanem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    TextView tv_gologin;
    EditText edt_name, edt_surname, edt_email, edt_password, edt_repassword;
    Button btn_login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_surname = (EditText)findViewById(R.id.edt_surname);
        edt_email = (EditText)findViewById(R.id.edt_mail);
        edt_password = (EditText)findViewById(R.id.edt_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edt_email.getText().toString().equals("") && !edt_password.getText().toString().equals("")) {
                    if (edt_email.getText().toString() != null && edt_password.getText().toString() != null) {
                        final User user = new User(edt_name.getText().toString(), edt_surname.getText().toString(), edt_email.getText().toString(), edt_password.getText().toString());
                        Register(user);
                    }
                }else
                    Toast.makeText(RegisterActivity.this, "Lütfen boş bırakmayınız!", Toast.LENGTH_SHORT).show();
            }
        });

        tv_gologin = (TextView)findViewById(R.id.tv_gologin);

        tv_gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    public void Register(User uuser){
        mAuth.createUserWithEmailAndPassword(uuser.getEmail(), uuser.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                           //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
