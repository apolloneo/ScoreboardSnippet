package com.example.scoreboardsnippet.ViewModels;

//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.MutableLiveData;
//import android.arch.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BasketballSharedViewModel extends ViewModel {
    private final MutableLiveData<Integer> home_fouls = new MutableLiveData<>();
    private final MutableLiveData<Integer> home_timeout_30sec = new MutableLiveData<>();
    private final MutableLiveData<Integer> home_timeout_60sec = new MutableLiveData<>();
    private final MutableLiveData<Integer> home_player_number = new MutableLiveData<>();
    private final MutableLiveData<Integer> home_player_foul = new MutableLiveData<>();
    private final MutableLiveData<Integer> home_score = new MutableLiveData<>();
    private final MutableLiveData<Integer> period = new MutableLiveData<>();
    private final MutableLiveData<Integer> guest_fouls = new MutableLiveData<>();
    private final MutableLiveData<Integer> guest_score = new MutableLiveData<>();
    private final MutableLiveData<Integer> guest_timeout_30sec = new MutableLiveData<>();
    private final MutableLiveData<Integer> guest_timeout_60sec = new MutableLiveData<>();
    private final MutableLiveData<Integer> guest_player_number = new MutableLiveData<>();
    private final MutableLiveData<Integer> guest_player_foul = new MutableLiveData<>();
    private final MutableLiveData<CharSequence> dialog_position = new MutableLiveData<>();
    private final MutableLiveData<String> dialog_title = new MutableLiveData<>();
    private final MutableLiveData<Integer> start_up_widget_id = new MutableLiveData<>();
    private final MutableLiveData<Integer> player_number = new MutableLiveData<>();
    private final MutableLiveData<Integer> player_fouls = new MutableLiveData<>();
    private final MutableLiveData<Integer> player_score = new MutableLiveData<>();
    private final MutableLiveData<Integer> initial_value = new MutableLiveData<>();

    public void setPlayer_score(Integer input_player_score){
        player_score.setValue(input_player_score);
    }
    public LiveData<Integer> getPlayer_score(){
        return player_score;
    }

    public void setInitial_value(Integer input_initial_value){
        initial_value.setValue(input_initial_value);
    }
    public LiveData<Integer> getInitial_value(){
        return initial_value;
    }

    public void setHome_score(Integer input_home_score){
        home_score.setValue(input_home_score);
    }
    public LiveData<Integer> getHome_score(){
        return home_score;
    }

    public void setGuest_score(Integer input_guest_score){
        guest_score.setValue(input_guest_score);
    }
    public LiveData<Integer> getGuest_score(){
        return guest_score;
    }

    public void setPlayer_number(Integer input_player_number){
        player_number.setValue(input_player_number);
    }
    public LiveData<Integer> getPlayer_number() {
        return player_number;
    }

    public void setPlayer_fouls(Integer input_player_fouls){
        player_fouls.setValue(input_player_fouls);
    }
    public LiveData<Integer> getPlayer_fouls() {
        return player_fouls;
    }
    public void setHome_player_number(Integer input_home_player_number){
        home_player_number.setValue(input_home_player_number);
    }
    public LiveData<Integer> getHome_player_number(){
        return home_player_number;
    }

    public void setHome_player_foul(Integer input_home_player_foul){
        home_player_foul.setValue(input_home_player_foul);
    }
    public LiveData<Integer> getHome_player_foul(){
        return home_player_foul;
    }

    public void setGuest_player_number(Integer input_guest_player_number){
        guest_player_number.setValue(input_guest_player_number);
    }
    public LiveData<Integer> getGuest_player_number(){
        return guest_player_number;
    }

    public void setGuest_player_foul(Integer input_guest_player_foul){
        guest_player_foul.setValue(input_guest_player_foul);
    }
    public LiveData<Integer> getGuest_player_foul(){
        return guest_player_foul;
    }

    public void setStart_up_widget_id(Integer input_start_up_widget_id){
        start_up_widget_id.setValue(input_start_up_widget_id);
    }
    public LiveData<Integer> getStart_up_widget_id(){
        return start_up_widget_id;
    }

    public void setDialog_title(String input_diaglog_title){
        dialog_title.setValue(input_diaglog_title);
    }
    public LiveData<String> getDialog_title(){
        return dialog_title;
    }

    public void setDialog_position(CharSequence input_dialog_position){
        dialog_position.setValue(input_dialog_position);
    }
    public LiveData<CharSequence> getDialog_position(){
        return dialog_position;
    }

    public void setPeriod(Integer input_period){
        period.setValue(input_period);
    }
    public LiveData<Integer> getPeriod(){
        return period;
    }

    public void setHome_fouls(Integer input_home_fouls){
        home_fouls.setValue(input_home_fouls);
    }
    public LiveData<Integer> getHome_fouls(){
        return home_fouls;
    }

    public void setHome_timeout_30sec(Integer input_home_timeout_30sec){
        home_timeout_30sec.setValue(input_home_timeout_30sec);
    }
    public LiveData<Integer> getHome_timeout_30sec(){
        return home_timeout_30sec;
    }

    public void setHome_timeout_60sec(Integer input_home_timeout_60sec){
        home_timeout_60sec.setValue(input_home_timeout_60sec);
    }
    public LiveData<Integer> getHome_timeout_60sec(){
        return home_timeout_60sec;
    }

    public void setGuest_fouls(Integer input_guest_fouls){
        guest_fouls.setValue(input_guest_fouls);
    }
    public LiveData<Integer> getGuest_fouls(){
        return guest_fouls;
    }

    public void setGuest_timeout_30sec(Integer input_guest_timeout_30sec){
        guest_timeout_30sec.setValue(input_guest_timeout_30sec);
    }
    public LiveData<Integer> getGuest_timeout_30sec(){
        return guest_timeout_30sec;
    }

    public void setGuest_timeout_60sec(Integer input_guest_timeout_60sec){
        guest_timeout_60sec.setValue(input_guest_timeout_60sec);
    }
    public LiveData<Integer> getGuest_timeout_60sec(){
        return guest_timeout_60sec;
    }
}
