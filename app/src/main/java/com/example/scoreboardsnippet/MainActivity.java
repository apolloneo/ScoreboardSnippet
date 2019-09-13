package com.example.scoreboardsnippet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.scoreboardsnippet.InputFragments.BasketballPFSDialog;
import com.example.scoreboardsnippet.ViewModels.BasketballSharedViewModel;

import android.os.Bundle;
import android.view.View;

import com.example.scoreboardsnippet.CompoundViews.DigitView;
import com.example.scoreboardsnippet.ViewModels.BasketballSharedViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //widget
        DigitView homePlayerNumber = findViewById(R.id.digitView_Home_PlayerNumber);
        //view model declearation
        final BasketballSharedViewModel mBasketballSharedViewModel;
        //view model registration
        mBasketballSharedViewModel = ViewModelProviders.of(this).get(BasketballSharedViewModel.class);

        homePlayerNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasketballPFSDialog basketballPFSDialog = new BasketballPFSDialog();
                mBasketballSharedViewModel.setDialog_title("Home Player Number, Fousl, Score");
                mBasketballSharedViewModel.setDialog_position("right");
                mBasketballSharedViewModel.setStart_up_widget_id(R.id.digitView_Home_PlayerNumber);
                basketballPFSDialog.show(getSupportFragmentManager(),"Home Plyer Status");
            }
        });

    }
}
