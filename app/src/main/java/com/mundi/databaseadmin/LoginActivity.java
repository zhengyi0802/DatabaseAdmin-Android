package com.mundi.databaseadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mundi.databaseadmin.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private LoginViewModel loginViewModel;
    private String uri;
    private String dbname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.login_button);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.setLoginData(uri,
                        dbname,
                        mUsername.getText().toString(),
                        mPassword.getText().toString());
            }
        });

        uri = getString(R.string.url_dbweb);
        dbname = "stocks";
        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getResponse().observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s.equals("correct")) {
                            successful();
                        } else {
                            ToastError();
                        }
                        return;
                    }
                });
        return;
    }

    private void ToastError() {
        Toast.makeText(this, "Login Error!", Toast.LENGTH_SHORT).show();
        return;
    }

    private void successful() {
        setResult(RESULT_OK, getIntent());
        finish();
    }

}
