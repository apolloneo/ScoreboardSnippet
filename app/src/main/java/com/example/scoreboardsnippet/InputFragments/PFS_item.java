package com.example.scoreboardsnippet.InputFragments;

public class PFS_item {
    //vars
    int mPlayerNumber, mPlayerFouls, mPlayerScore;

    //constructor
    public PFS_item(int player_number, int player_fouls, int player_score){
        mPlayerFouls = player_fouls;
        mPlayerNumber = player_number;
        mPlayerScore = player_score;
    }

    //getters
    public int getPlayerNumber(){
        return mPlayerNumber;
    }
    public int getPlayerFouls(){
        return mPlayerFouls;
    }
    public int getPlayerScore(){
        return mPlayerScore;
    }
}
