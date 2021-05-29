package org.lentils.oboronataganroga1855.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.model.User;

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void registerUser(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String passport = editTextPassport.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError(getString(R.string.error_text_full_name));
            editTextFullName.requestFocus();
            return;
        }

        if(passport.isEmpty()){
            editTextPassport.setError(getString(R.string.error_text_passport));
            editTextPassport.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError(getString(R.string.error_text_email));
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError(getString(R.string.error_text_email_variable));
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError(getString(R.string.error_text_password));
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError(getString(R.string.error_text_password_length));
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
                                        Toast.makeText(RegisterUser.this, R.string.toast_register_successfully, Toast.LENGTH_LONG)
                                                .show();
                                        progressBar.setVisibility(View.VISIBLE);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterUser.this, R.string.toast_register_failed, Toast.LENGTH_LONG)
                                                .show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }
                        else
                        {
                            Toast.makeText(RegisterUser.this, R.string.toast_register_failed,
                                    Toast.LENGTH_LONG)
                                    .show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
    }
}