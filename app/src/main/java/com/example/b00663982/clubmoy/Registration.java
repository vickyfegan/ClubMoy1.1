package com.example.b00663982.clubmoy;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private Button button2;
    private EditText editText;
    private EditText editText2;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        button2 = (Button) findViewById(R.id.button2);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        button2.setOnClickListener(this);


    }

    private void registerUser()
    {
        String email = editText.getText().toString().trim();
        String password = editText2.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //if the email address is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();

            return;
        }

        if(TextUtils.isEmpty(password))
        {
            //if the password field is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();

            return;
        }

        progressDialog.setMessage("Registering User ...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Registration.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Registration.this, "Unable to complete registration. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == button2)
        {
            registerUser();
        }

    }
}
