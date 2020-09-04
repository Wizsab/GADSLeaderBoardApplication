package com.oyokungroup.gadsleaderboardmobileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.oyokungroup.gadsleaderboardmobileapplication.api.PostClient;
import com.oyokungroup.gadsleaderboardmobileapplication.api.UserService;
import com.oyokungroup.gadsleaderboardmobileapplication.model.Post;

public class SubmitActivity extends AppCompatActivity {

    EditText fName, lName, email, gitUrl;
    ImageView submitBtn;
    UserService apiService;
    Dialog success, notSuccess, confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();
        fName = (EditText)findViewById(R.id.firstname);
        lName = (EditText)findViewById(R.id.lastname);
        email = (EditText)findViewById(R.id.email);
        gitUrl = (EditText)findViewById(R.id.github);
        submitBtn = (ImageView)findViewById(R.id.submitImg);
        success = new Dialog(this);
        notSuccess = new Dialog(this);
        confirm = new Dialog(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmPopup();
            }
        });
    }

    private void SuccessPopup() {
        success.setContentView(R.layout.success_layout);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        success.getWindow().setLayout((6 * width)/7, (3 * height)/5);
        success.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        success.show();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                success.dismiss();
                confirm.dismiss();
                onBackPressed();
            }
        },5000);
    }
    private void NotSuccessPopup() {
        notSuccess.setContentView(R.layout.not_success_layout);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        notSuccess.getWindow().setLayout((6 * width)/7, (3 * height)/5);
        notSuccess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        notSuccess.show();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                notSuccess.dismiss();
                confirm.dismiss();
            }
        },5000);
    }
    private void ConfirmPopup() {
        confirm.setContentView(R.layout.confirm_layout);
        ImageView ClosePopup = confirm.findViewById(R.id.close);
        Button SubmitBTN = confirm.findViewById(R.id.confirm_button);

        SubmitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
            }
        });
        ClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.dismiss();
            }
        });

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        confirm.getWindow().setLayout((6 * width)/7, (2 * height)/5);
        confirm.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        confirm.show();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                notSuccess.dismiss();
            }
        },5000);
    }

    public void createPost() {
        PostClient client = new PostClient();
        UserRequest userRequest = new UserRequest();

        apiService = client.getClient().create(UserService.class);
        Call<Post> call = apiService.createPost(userRequest.setFirst_name(fName.getText().toString()),
                                                userRequest.setLast_name(lName.getText().toString()),
                                                userRequest.setEmail(email.getText().toString()),
                                                userRequest.setGithub(gitUrl.getText().toString())
                                                );

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) {
                    //Toast.makeText(SubmitActivity.this, "Saved successfully", Toast.LENGTH_LONG).show();
                    SuccessPopup();
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                //Toast.makeText(SubmitActivity.this, "Request failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                NotSuccessPopup();
            }
        });
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = true;
                }else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

}