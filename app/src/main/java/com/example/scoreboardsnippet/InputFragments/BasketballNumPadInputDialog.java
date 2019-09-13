package com.example.scoreboardsnippet.InputFragments;

//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.DialogFragment;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.scoreboardsnippet.R;
import com.example.scoreboardsnippet.ViewModels.BasketballSharedViewModel;


public class BasketballNumPadInputDialog extends DialogFragment implements View.OnClickListener {
    //view model
    private BasketballSharedViewModel basketballSharedViewModel_NumPad;
    //widgets
    private TextView title, input_text;
    private Button ok_button, cancel_button, clr_button;
    private Button num1,num2,num3,num4,num5,num6,num7,num8,num9,num0,plus_minus_one;

    //vars
    private String my_position="";
    private Integer initValue=0;
    private Integer StartUpWidgetID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        View rootView = inflater.inflate(R.layout.fragment_number_input, container, false);

        //build the Num Pad widgets
        buildNumPadPanel(rootView);


        //plus_minus_one.setText(" + 1 ");
        //title.setText("Input Period");

        ok_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);
        clr_button.setOnClickListener(this);
        plus_minus_one.setOnClickListener(this);
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);



        //ok_button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        String str = input_text.getText().toString();
        //        //basketballSharedViewModel_NumPad.setGuest_fouls(str);
        //        basketballSharedViewModel_NumPad.setPeriod(str);
        //    }
        //});
        //
        //cancel_button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        getDialog().dismiss();
        //    }
        //});

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //register the view model
        basketballSharedViewModel_NumPad = ViewModelProviders.of(getActivity()).get(BasketballSharedViewModel.class);

        //set view model observe
        basketballSharedViewModel_NumPad.getDialog_position().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                my_position = charSequence.toString();
                if(my_position.equals("right")){
                    getDialog().getWindow().setGravity(Gravity.RIGHT);
                }else if(my_position.equals("left")){
                    getDialog().getWindow().setGravity(Gravity.LEFT);
                }else if(my_position.equals("center")){
                    getDialog().getWindow().setGravity(Gravity.CENTER);
                }else{
                    getDialog().getWindow().setGravity(Gravity.CENTER);
                }
            }
        });

        basketballSharedViewModel_NumPad.getDialog_title().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                title.setText(s);
            }
        });

        basketballSharedViewModel_NumPad.getStart_up_widget_id().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer startupwidgetid) {
                StartUpWidgetID = startupwidgetid;

                input_text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
                //if (StartUpWidgetID == R.id.digitView_Home_Score ||
                //        StartUpWidgetID == R.id.digitView_Guest_Score){
                //    input_text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3)});
                //}else if (StartUpWidgetID == R.id.digitView_Period){
                //    input_text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
                //}else{
                //    input_text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
                //}
            }
        });

        basketballSharedViewModel_NumPad.getInitial_value().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer initial_value) {
                initValue = initial_value;
                input_text.setText(initValue.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btNumInputOk:
                if (input_text.getText().toString()!=""){
                    //if (StartUpWidgetID == R.id.digitView_Home_Score){
                    //    basketballSharedViewModel_NumPad.setHome_score(Integer.parseInt(input_text.getText().toString()));
                    //}else if (StartUpWidgetID == R.id.digitView_Guest_Score){
                    //    basketballSharedViewModel_NumPad.setGuest_score(Integer.parseInt(input_text.getText().toString()));
                    //}else if (StartUpWidgetID == R.id.digitView_Period){
                    //    basketballSharedViewModel_NumPad.setPeriod(Integer.parseInt(input_text.getText().toString()));
                    //}
                    if (StartUpWidgetID == R.id.digitView_PFS_item_playerScore){
                        basketballSharedViewModel_NumPad.setPlayer_score(Integer.parseInt(input_text.getText().toString()));
                    }
                }else {
                    //if the text box is null, set default value to 0
                    //if (StartUpWidgetID == R.id.digitView_Home_Score){
                    //    basketballSharedViewModel_NumPad.setHome_score(0);
                    //}else if (StartUpWidgetID == R.id.digitView_Guest_Score){
                    //    basketballSharedViewModel_NumPad.setGuest_score(0);
                    //}else if (StartUpWidgetID == R.id.digitView_Period){
                    //    basketballSharedViewModel_NumPad.setPeriod(0);
                    //}
                }
                getDialog().dismiss();
                break;
            case R.id.btNumInputCancel:
                getDialog().dismiss();
                break;
            case R.id.btNumInputClear:
                input_text.setText("");
                break;
            case R.id.btNumInputMinus1:
                if (input_text.getText().toString()!="" && Integer.parseInt(input_text.getText().toString())> 0){
                    input_text.setText(Integer.toString(Integer.parseInt(input_text.getText().toString())-1));
                }else if (Integer.parseInt(input_text.getText().toString())== 0){
                    input_text.setText("0");
                }
                else{
                    input_text.setText("1");
                }
                break;
            case R.id.btNumInput0:
            case R.id.btNumInput1:
            case R.id.btNumInput2:
            case R.id.btNumInput3:
            case R.id.btNumInput4:
            case R.id.btNumInput5:
            case R.id.btNumInput6:
            case R.id.btNumInput7:
            case R.id.btNumInput8:
            case R.id.btNumInput9:
                if (StartUpWidgetID == R.id.digitView_PFS_item_playerScore){
                    Button b = (Button)view;
                    String str = input_text.getText().toString() + b.getText().toString();
                    input_text.setText(str);
                }
                //if (StartUpWidgetID == R.id.digitView_Period){
                //    input_text.setText("");
                //    Button b = (Button)view;
                //    input_text.setText(b.getText().toString());
                //}else if (StartUpWidgetID == R.id.digitView_Home_Score ||
                //        StartUpWidgetID == R.id.digitView_Guest_Score){
                //    Button b = (Button)view;
                //    String str = input_text.getText().toString() + b.getText().toString();
                //    input_text.setText(str);
                //}
                break;

        }
    }

    private void buildNumPadPanel(View v){
        ok_button = v.findViewById(R.id.btNumInputOk);
        cancel_button = v.findViewById(R.id.btNumInputCancel);
        clr_button = v.findViewById(R.id.btNumInputClear);
        plus_minus_one = v.findViewById(R.id.btNumInputMinus1);
        num0 = v.findViewById(R.id.btNumInput0);
        num1 = v.findViewById(R.id.btNumInput1);
        num2 = v.findViewById(R.id.btNumInput2);
        num3 = v.findViewById(R.id.btNumInput3);
        num4 = v.findViewById(R.id.btNumInput4);
        num5 = v.findViewById(R.id.btNumInput5);
        num6 = v.findViewById(R.id.btNumInput6);
        num7 = v.findViewById(R.id.btNumInput7);
        num8 = v.findViewById(R.id.btNumInput8);
        num9 = v.findViewById(R.id.btNumInput9);
        title = v.findViewById(R.id.tvNumInputTitle);
        input_text = v.findViewById(R.id.tvNumInputText);
    }
}
