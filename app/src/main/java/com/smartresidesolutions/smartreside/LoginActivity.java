package com.smartresidesolutions.smartreside;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smartresidesolutions.api.LoginApiInterface;
import com.smartresidesolutions.common.RetrofitClient;


import com.smartresidesolutions.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    int loginResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (EditText) findViewById(R.id.email);


        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                LoginApiInterface apiService=
                        RetrofitClient.getClient().create(LoginApiInterface.class);


                Call<User> call = apiService.getUser();
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User>call, Response<User> response) {
                        User User = response.body();
                        if(User.getUsername().equals("abhinav")){
                            navigateActivity(1);
                        }
                        else{
                            loginResponse=0;
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });

     /*   Button contact_button = (Button) findViewById(R.id.contact_button);
        contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityChangeIntent = new Intent(view.getContext(),LoginContactUs.class);
                startActivity(activityChangeIntent);
            }
        });
        */






    }

    void  navigateActivity(int loginResponse){
        if(loginResponse==1) {
            Intent activityChangeIntent = new Intent(getApplicationContext(), Navigation.class);
            startActivity(activityChangeIntent);
        }
    }
}

