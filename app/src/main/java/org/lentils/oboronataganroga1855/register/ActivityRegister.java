package org.lentils.oboronataganroga1855.register;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.lentils.oboronataganroga1855.MainNavigationActivity;
import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.data.Database;
import org.lentils.oboronataganroga1855.model.User;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {

    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    private String full_name, passport;

    Database databaseHelper;
    SQLiteDatabase db;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        forgotPassword = (TextView) findViewById(R.id.textForgotpassword);
        forgotPassword.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        databaseHelper = new Database(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.signIn:
                userLogin();
                break;
            case R.id.textForgotpassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }

    }

    private void userLogin() {
        final String[] email = {editTextEmail.getText().toString().trim()};
        String password = editTextPassword.getText().toString().trim();

        if(email[0].isEmpty()){
            editTextEmail.setError(getString(R.string.error_text_email));
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email[0]).matches()){
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

      //  progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email[0], password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        reference = FirebaseDatabase.getInstance().getReference("Users");
                        userID = user.getUid();
                        Log.i("USER", userID);
                        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                User userProfile = new User(snapshot.child("fullName").getValue().toString(), snapshot.child("passport").getValue().toString(), snapshot.child("email").getValue().toString());
                                Log.i("USER", userProfile.getEmail());
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        R.string.toast_sign_ok, Toast.LENGTH_SHORT);
                                toast.show();
                                db = databaseHelper.getReadableDatabase();
                                ContentValues cv = new ContentValues();
                                cv.put(Database.COLUMN_NAME, userProfile.getFullName());
                                cv.put(Database.COLUMN_EMAIL, userProfile.getEmail());
                                cv.put(Database.COLUMN_PASSPORT, userProfile.getPassport());
                                db.insert(Database.TABLE_USER, null, cv);
                                Intent intent = new Intent(ActivityRegister.this, MainNavigationActivity.class);
                                db.close();
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(ActivityRegister.this, "Произошла ошибка, попробуйте еще раз!",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                    {
                        user.sendEmailVerification();
                        Toast.makeText(ActivityRegister.this, R.string.toast_sign_verification_email_false, Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(ActivityRegister.this, R.string.toast_sign_failed_login, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}