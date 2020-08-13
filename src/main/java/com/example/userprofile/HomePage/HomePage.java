package com.example.userprofile.HomePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.userprofile.Data.Candidate_details;
import com.example.userprofile.R;

import java.util.List;

public class HomePage extends AppCompatActivity implements IHomePage  {

    Context ctx = this;
    private RecyclerView mRecyclerView;
    private HomePagePresenter homePresenter;
    private Candidate_Adapter candidate_adapter;
private SwipeRefreshLayout mySwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 0));
        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomePage.this);
        mRecyclerView.setLayoutManager(layoutManager);
        homePresenter = new HomePagePresenter();
        homePresenter.attachView(this);


        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        myUpdateOperation();
                    }
                }
        );
    }

    public void myUpdateOperation(){
        homePresenter.downloadMoreData();
    }

    @Override
    public void errorDisplay() {
        Toast.makeText(ctx, "Oops something happen", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideprogressbar() {
        mySwipeRefreshLayout.setRefreshing(false);
        //mRecyclerView.invalidate();
        startActivity(getIntent());
        //candidate_adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.fetchCandidateData();
    }

    @Override
    public void setAdapter(List<Candidate_details> candidate_data) {
        if (candidate_data == null || candidate_data.size() < 1) {
            Log.d("EMPTY","DATA Blank");
            return;
        }

        if (candidate_adapter == null) {
            candidate_adapter = new Candidate_Adapter(HomePage.this, candidate_data, new Candidate_Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(String uniqueId, ImageView profileImage) {
                    Toast.makeText(HomePage.this, "Id", Toast.LENGTH_SHORT).show(); }
            });
            mRecyclerView.setAdapter(candidate_adapter);
        } else {
            candidate_adapter.swapDataList(candidate_data);
            candidate_adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ctx);
        builder
                .setTitle(HomePage.this.getString(R.string.back_form))
                .setMessage(HomePage.this.getString(R.string.back_form_message))
                .setIcon(R.mipmap.ic_exitalert)
                .setPositiveButton(HomePage.this.getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton(HomePage.this.getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
}