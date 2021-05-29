package org.lentils.oboronataganroga1855.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.lentils.oboronataganroga1855.MainActivity;
import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.User;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private TextView registerUser;
    private EditText editTextFullName, editTextPassport, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.editTextNameUserAuth);
        editTextPassport = (EditText) findViewById(R.id.editTextPassport);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailAuth);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordAuth);

        progressBar = (ProgressBar) findViewById( R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerUser:
                registerUser();
                break;

        }
    }

    private void registerUser(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String passport = editTextPassport.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("Full name is requried!");
            editTextFullName.requestFocus();
            return;
        }

        if(passport.isEmpty()){
            editTextFullName.setError("Passport is requried!");
            editTextFullName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextFullName.setError("Email name is requried!");
            editTextFullName.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Min password lenght should be 6 character");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fullName, passport, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User has " +
                                                "been registered succsessfull!", Toast.LENGTH_LONG)
                                                .show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterUser.this, "Failed to" +
                                                "register! Try again!", Toast.LENGTH_LONG)
                                                .show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }
                        else
                        {
                            Toast.makeText(RegisterUser.this, "Faild to register",
                                    Toast.LENGTH_LONG)
                                    .show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
    }
}