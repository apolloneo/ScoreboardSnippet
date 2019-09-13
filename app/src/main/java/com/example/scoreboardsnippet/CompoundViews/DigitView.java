package com.example.scoreboardsnippet.CompoundViews;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.scoreboard.R;
import com.example.scoreboardsnippet.R;

public class DigitView extends LinearLayout {

    //Text views
    private TextView showdescription, shownumber;
    private LinearLayout showsidebuttons;
    private ImageButton plusbutton, minusbutton;
    private LinearLayout displaysection;
    private int numdigits;
    private boolean descriptionvisible, sidebuttonsvisible, centerdigits = false;
    private String description;
    private int displaycolor;
    private int displayednum = 0;


    public DigitView(Context context) {
        this(context,null);
    }

    public DigitView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        setAttributes(context,attrs);
    }

    public DigitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(context,attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DigitView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAttributes(context,attrs);
        init();
    }


    /**
     * Initialize view
     */
    private void init(){

        //Inflate xml resource, pass "this" as the parent, we use <merge> tag in xml to avoid
        //redundant parent, otherwise a LinearLayout will be added to this LinearLayout ending up
        //with two view groups
        inflate(getContext(), R.layout.digit_view,this);

        //Get references to text views
        showsidebuttons = findViewById(R.id.llDigitViewSideButtons);
        showdescription = findViewById(R.id.tvDigitDescription);
        shownumber = findViewById(R.id.tvDigitNumber);
        plusbutton = findViewById(R.id.btDigitViewPlus);
        minusbutton = findViewById(R.id.btDigitViewMinus);

        showdescription.setTextColor(displaycolor);
        shownumber.setTextColor(displaycolor);



        if(numdigits>1) {
            displayednum = numdigits;
        }else {
            displayednum = 0;
        }

        setNumber(displayednum);
        setNumberOfDigits(numdigits);
        setDescription(description);
        setPlusMinusButtonsVisibility(sidebuttonsvisible);
        setDescriptionVisibility(descriptionvisible);
        centerDigits(centerdigits);





    }



    private void setAttributes(Context context, AttributeSet attrs){
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.DigitView);
        numdigits = typedArray.getInt(R.styleable.DigitView__number_of_digits,3);
        descriptionvisible = typedArray.getBoolean(R.styleable.DigitView__description_visible,false);
        sidebuttonsvisible = typedArray.getBoolean(R.styleable.DigitView__plus_minus_buttons_visible,false);
        description = typedArray.getString(R.styleable.DigitView__description);
        displaycolor = typedArray.getColor(R.styleable.DigitView__display_color,getResources().getColor(R.color.segment_red));
        typedArray.recycle();
    }


    public void setPlusMinusButtonsVisibility(boolean visibility){
        if(visibility){
            showsidebuttons.setVisibility(VISIBLE);
        }else {
            showsidebuttons.setVisibility(GONE);
        }
    }


    public void setDescriptionVisibility(boolean visibility){
        descriptionvisible = visibility;
        if(descriptionvisible){
            showdescription.setVisibility(VISIBLE);
        }else {
            showdescription.setVisibility(GONE);
        }
    }

    public boolean descriptionIsVisible() {
        return descriptionvisible;
    }

    public void setDescription(String s){
        showdescription.setText(s);
    }


    public void setNumber(int i){
        displayednum = i;
        centerDigits(centerdigits);
        shownumber.setText(String.format("%1$"+numdigits+ "s", displayednum));
    }

    public boolean setNumber(String s){
        try {
            setNumber(Integer.parseInt(s));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void setNumber(int i, boolean leadingZeroes){
        displayednum = i;
        centerDigits(centerdigits);
        if(leadingZeroes){
            shownumber.setText(String.format("%0"+numdigits+ "d", displayednum));
        }else {
            shownumber.setText(String.format("%1$"+numdigits+ "s", displayednum));
        }

    }


    public void centerDigits(boolean centered){
        if(centered){
            shownumber.setGravity(Gravity.CENTER);
        }else {
            shownumber.setGravity(Gravity.END| Gravity.CENTER_VERTICAL);
        }
    }

    public void setPlusButtonOnClickListener(OnClickListener o){
        plusbutton.setOnClickListener(o);
    }

    public void setMinusButtonOnClickListener(OnClickListener o){
        minusbutton.setOnClickListener(o);
    }

    public void setDisplayColor(int color){
        showdescription.setTextColor(color);
        shownumber.setTextColor(color);
    }

    protected void setNumberOfDigits(int numdigits){
        this.numdigits = numdigits;
        if(numdigits==1){
            centerdigits = true;
        }
        setNumber(numdigits,true);

    }

    public int getNumber() {
        return displayednum;
    }
}
