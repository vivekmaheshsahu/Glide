package com.example.userprofile.HomePage;

import java.util.List;
import com.example.userprofile.Data.Candidate_details;

public interface IHomePage {

    void setAdapter(List<Candidate_details> candidate_data);
    void hideprogressbar();
    void errorDisplay();

}
