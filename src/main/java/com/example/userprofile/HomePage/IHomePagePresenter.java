package com.example.userprofile.HomePage;

import com.example.userprofile.utility.IBasePresenter;

public interface IHomePagePresenter<v> extends IBasePresenter<v> {

    void fetchCandidateData();
    void downloadMoreData();

}
