package com.example.userprofile.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.userprofile.utility.Utility;

import com.example.userprofile.R;

import java.util.Timer;
import java.util.TimerTask;
import com.example.userprofile.HomePage.HomePage;

public class SplashScreen extends AppCompatActivity implements ISplashScreenView {

    private SplashScreenPresenter presenter;
    ProgressBar progressOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressOverlay=findViewById(R.id.progress_bar);
        presenter = new SplashScreenPresenter();
        presenter.attachView(this);
      }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void openMainActivity() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        }, 500);
    }

    @Override
    public void errorInDownloadData() {
        Toast.makeText(this, "Error in Downloading Data", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public void clicked(View view)
    {
        if(presenter.checkPermissions())
        {
        showProgressBar();
        presenter.initializeDBHelper();
        presenter.networkCall();
        openMainActivity();
        }
    }

    @Override
    public void showProgressBar() {
        Utility.animateView(progressOverlay, View.VISIBLE, 0.4f, 200);
    }

    @Override
    public void hideProgressBar() {
        Utility.animateView(progressOverlay, View.GONE, 0.4f, 200);
    }

}