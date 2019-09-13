package com.example.scoreboardsnippet.CompoundViews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.scoreboardsnippet.R;

import java.util.ArrayList;

public class MultiDigitView extends LinearLayout {


    private LinearLayout fulllayout;
    private int numdisplays, digitsper;
    private float displaywidth;
    private boolean connectdisplays;
    private ArrayList<DigitView> alldigitviews = new ArrayList<>();




    public MultiDigitView(Context context) {
        this(context,null);
    }

    public MultiDigitView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        setAttributes(context,attrs);
    }

    public MultiDigitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(context,attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultiDigitView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAttributes(context,attrs);
        init(context);
    }


    /**
     * Initialize view
     */
    private void init(Context context){

        //Inflate xml resource, pass "this" as the parent, we use <merge> tag in xml to avoid
        //redundant parent, otherwise a LinearLayout will be added to this LinearLayout ending up
        //with two view groups
        inflate(getContext(), R.layout.multi_digit_view,this);

        fulllayout = findViewById(R.id.llMultiDigitViewFullLayout);

        //fulllayout.setBackgroundColor(getResources().getColor(R.color.bluetooth_blue));
        if(connectdisplays) fulllayout.setBackground(getResources().getDrawable(R.drawable.independent_display));
        //Get references to text views


        if(numdisplays>0){
            if(numdisplays>=3)fulllayout.setWeightSum(numdisplays-1);
            for (int i=1; i<=numdisplays; i++){

                DigitView digitView = new DigitView(context);
                digitView.setId(i);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(((int) displaywidth), LinearLayout.LayoutParams.MATCH_PARENT);
                digitView.setLayoutParams(layoutParams2);
                digitView.setNumberOfDigits(digitsper);
                digitView.setNumber(i,true);

                alldigitviews.add(digitView);

                if(i==numdisplays){
                    fulllayout.addView(digitView);
                }else {
                    LinearLayout linearLayout = new LinearLayout(context);
                    LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
                    layoutParams1.weight = 1;
                    linearLayout.setLayoutParams(layoutParams1);
                    linearLayout.addView(digitView);
                    fulllayout.addView(linearLayout);
                }



            }
        }


    }



    private void setAttributes(Context context, AttributeSet attrs){
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiDigitView);
        numdisplays = typedArray.getInt(R.styleable.MultiDigitView__number_of_displays,3);
        connectdisplays = typedArray.getBoolean(R.styleable.MultiDigitView__connect_displays,false);
        displaywidth = typedArray.getDimension(R.styleable.MultiDigitView__individual_display_width,100);
        digitsper = typedArray.getInt(R.styleable.MultiDigitView__number_of_digits_per_display,2);
        typedArray.recycle();
    }

    public DigitView getDigitView(int digitviewnumber){
        return this.findViewById(digitviewnumber);
    }

    public ArrayList<DigitView> getDigitViews(){
        return alldigitviews;
    }

    public int getNumberOfDigitViews(){
        return numdisplays;
    }




}
