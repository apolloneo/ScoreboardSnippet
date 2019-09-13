package com.example.scoreboardsnippet.CompoundViews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.scoreboard.R;
import com.example.scoreboardsnippet.R;

import java.util.concurrent.TimeUnit;

public class TimerView extends LinearLayout {

    private TextView displayTimer;
    private LinearLayout fulllayout, buttonHolderLayout;
    private int mins,secs, tens;
    private int numbuttons;
    private boolean minutesVisible, tenthsVisible, overflowSeconds, autoCorrectFormat;
    private int stoppedcolor,countingcolor, currentcolor;


    private CountDownTimer countDownTimer;
    private boolean builtInTimerIsCounting = false;



    public interface OnBuiltInTimerStatusChangeListener {
        int STATUS_STARTED = 0;
        int STATUS_STOPPED = 1;
        int STATUS_FINISHED = 2;
        void onStatusChange(int status);
    }
    private OnBuiltInTimerStatusChangeListener onBuiltInTimerStatusChangeListener;


    public interface OnBuiltInTimerTickListener {
        void onTick(long totalTimeMilliseconds, int minutes, int seconds, int tenths);
    }
    private OnBuiltInTimerTickListener onBuiltInTimerTickListener;



    public TimerView(Context context) {
        this(context,null);
    }

    public TimerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        setAttributes(context,attrs);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(context,attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TimerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setAttributes(context,attrs);
        init(context);
    }


    private void init(Context context){

        //Inflate xml resource, pass "this" as the parent, we use <merge> tag in xml to avoid
        //redundant parent, otherwise a LinearLayout will be added to this LinearLayout ending up
        //with two view groups
        inflate(getContext(), R.layout.timer_view,this);


        displayTimer = findViewById(R.id.tvTimerViewDisplay);
        fulllayout = findViewById(R.id.llTimerViewFullDisplay);
        buttonHolderLayout = findViewById(R.id.llTimerViewButtonHolder);
        showAsCounting(false);
        setTime(54,32,1);



        if(numbuttons>0){
            //displayTimer.setBackground(getResources().getDrawable(R.drawable.top_integrated_display));
            buttonHolderLayout.setVisibility(VISIBLE);
            buttonHolderLayout.setWeightSum(numbuttons);
            for (int i=0; i<numbuttons; i++){
                Button button = new Button(context);
                button.setId(i);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
                layoutParams.weight = 1;
                button.setLayoutParams(layoutParams);

                button.setPadding(5,5,5,5);
                button.setTextSize(20);
                button.setMaxLines(3);
                button.setText("Button "+i);


                if(numbuttons==1){
                    button.setBackground(getResources().getDrawable(R.drawable.bottom_full_integrated_button));
                }else if(i==0){
                    button.setBackground(getResources().getDrawable(R.drawable.bottom_left_integrated_button));
                }else if(i==numbuttons-1){
                    button.setBackground(getResources().getDrawable(R.drawable.bottom_right_integrated_button));
                }else {
                    button.setBackground(getResources().getDrawable(R.drawable.middle_integrated_button));
                }

                buttonHolderLayout.addView(button);

                if(i<numbuttons-1){
                    LinearLayout linearLayout = new LinearLayout(context);
                    LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
                    linearLayout.setLayoutParams(layoutParams1);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.black));
                    buttonHolderLayout.addView(linearLayout);
                }

            }
        }else {
            //displayTimer.setBackground(getResources().getDrawable(R.drawable.independent_display));
            buttonHolderLayout.setVisibility(GONE);
        }








    }



    private void setAttributes(Context context, AttributeSet attrs){
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimerView);
        minutesVisible = typedArray.getBoolean(R.styleable.TimerView__minutes_visible,true);
        tenthsVisible = typedArray.getBoolean(R.styleable.TimerView__tenths_visible,true);
        stoppedcolor = typedArray.getColor(R.styleable.TimerView__stopped_color,getResources().getColor(R.color.segment_red));
        countingcolor = typedArray.getColor(R.styleable.TimerView__counting_color,getResources().getColor(R.color.segment_green));
        overflowSeconds = typedArray.getBoolean(R.styleable.TimerView__overflow_seconds, true);
        autoCorrectFormat = typedArray.getBoolean(R.styleable.TimerView__autocorrect_format,true);
        numbuttons = typedArray.getInt(R.styleable.TimerView__number_of_timer_buttons,0);
        typedArray.recycle();
    }







    public Button getButton(int buttonNumber){
        return (Button) this.findViewById(buttonNumber);
    }

    public void setTime(int minutes, int seconds, int tenths){

        String sMins, sSecs, sTenths;

        int displayminutes = minutes;
        int displayseconds = seconds;
        int displaytenths = tenths;



        if(autoCorrectFormat && minutesVisible){
            //normally correct format

            if(!tenthsVisible &&displaytenths>0){
                displayseconds+=1;
            }
            if(displayseconds>=60){
                displayseconds-=60;
                displayminutes+=1;
            }


        }else if(overflowSeconds &&!minutesVisible){

            //allow seconds to overflow to 99 if minutes are not displayed
            if(!tenthsVisible &&displaytenths>0){
                displayseconds+=1;
            }
            displayseconds+=(displayminutes*60);
            displayminutes = 0;
            if(displayseconds>99) displayseconds = 99;



        }



        int numspaces = 0;

        if(minutesVisible){

            sMins = String.format("%1$2s", displayminutes)+":";
            numspaces+=3;


        }else {
            sMins = "";
        }


        if(displayminutes>0&& minutesVisible) sSecs = String.format("%02d", displayseconds);
        else sSecs = String.format("%1$2s", displayseconds);
        numspaces+=2;



        if(tenthsVisible){
            sTenths = "."+ String.format("%01d", displaytenths);
            numspaces+=2;
        }else {
            sTenths = "";
        }


        mins = displayminutes;
        secs = displayseconds;
        tens = displaytenths;



        ForegroundColorSpan foregroundColorSpan;
        if(displayminutes<=0){
            foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.clear));
        }else {
            foregroundColorSpan = new ForegroundColorSpan(currentcolor);
        }

        String fullTimerString = String.format("%1$"+numspaces+ "s",  sMins+sSecs+sTenths);

        Spannable spannableTimer = new SpannableString(fullTimerString);
        spannableTimer.setSpan(foregroundColorSpan, 0,sMins.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        displayTimer.setText(spannableTimer);

    }

    public void setTime(long milliseconds){

        int min = (int) TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        int sec = (int) (TimeUnit.MILLISECONDS.toSeconds(milliseconds)- TimeUnit.MINUTES.toSeconds(min));
        int ten = (int) (milliseconds- TimeUnit.MINUTES.toMillis(min)- TimeUnit.SECONDS.toMillis(sec))/100;

        setTime(min,sec,ten);

    }

    public boolean setTime(String s){

        if(s.equals("")) return false;

        String[] strings = s.split("\\.");

        if(strings.length<1) return false;
        if(strings.length>2) return false;
        if(strings.length==2&&strings[1].length()>1) return false;

        int min;
        int sec;
        int ten;

        if(strings.length==2){
            try {
                ten = Integer.parseInt(strings[1]);
            }catch (Exception e){
                return false;
            }
        }else {
            ten = 0;
        }

        String minsec = strings[0];
        if(minsec.length()>2){
            try {
                min = Integer.parseInt(minsec.substring(0,minsec.length()-2));
                sec = Integer.parseInt(minsec.substring(minsec.length()-2));
            }catch (Exception e){
                return false;
            }

        }else {
            min = 0;

            if(minsec.equals("")){
                sec = 0;
            }else {
                try {
                    sec = Integer.parseInt(minsec);
                }catch (Exception e){
                    return false;
                }
            }

        }

        setTime(min, sec, ten);
        return true;
    }

    public long getTimeInMillis(){
        return (long)(mins*60000)+(secs*1000)+(tens*100);
    }

    public String getTimeAsInputString(){
        String sMins="", sSecs = secs+"";
        if(mins>0){
            sMins = ""+mins;
            sSecs = String.format("%02d", secs);
        }
        return sMins+sSecs+"."+tens;

    }

    public int getMinutes(){
        return mins;
    }

    public int getSeconds(){
        return secs;
    }

    public int getTenths(){
        return tens;
    }

    public void enableSecondsOverflow(boolean overflowEnabled){
        overflowSeconds = overflowEnabled;
    }

    public void enableAutoCorrectFormat(boolean correctionEnabled){
        autoCorrectFormat = correctionEnabled;
    }

    public void showAsCounting(boolean counting){

        if(counting){
            currentcolor = countingcolor;
        }else {
            currentcolor = stoppedcolor;
        }
        displayTimer.setTextColor(currentcolor);
        setTime(getTimeInMillis());
    }

    public boolean builtInTimerIsCounting() {
        return builtInTimerIsCounting;
    }

    public void startBuiltInTimer(int millisecondCountingInterval){

        if(getTimeInMillis()>0){
            builtInTimerIsCounting = true;
            showAsCounting(true);
            countDownTimer = new CountDownTimer(getTimeInMillis(),millisecondCountingInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setTime(millisUntilFinished);
                    if(onBuiltInTimerTickListener!=null) onBuiltInTimerTickListener.onTick(millisUntilFinished, getMinutes(), getSeconds(), getTenths());
                }

                @Override
                public void onFinish() {
                    setTime(0);
                    builtInTimerIsCounting = false;
                    showAsCounting(false);

                    if(onBuiltInTimerStatusChangeListener!=null) onBuiltInTimerStatusChangeListener.onStatusChange(OnBuiltInTimerStatusChangeListener.STATUS_FINISHED);

                }
            }.start();
            if(onBuiltInTimerStatusChangeListener!=null) onBuiltInTimerStatusChangeListener.onStatusChange(OnBuiltInTimerStatusChangeListener.STATUS_STARTED);

        }

    }

    public void stopBuiltInTimer(){
        if(countDownTimer!=null){
            countDownTimer.cancel();
            builtInTimerIsCounting =false;
            showAsCounting(false);
            if(onBuiltInTimerStatusChangeListener!=null) onBuiltInTimerStatusChangeListener.onStatusChange(OnBuiltInTimerStatusChangeListener.STATUS_STOPPED);
        }
    }

    public void setOnBuiltInTimerStatusChangeListener(OnBuiltInTimerStatusChangeListener onBuiltInTimerStatusChangeListener) {
        this.onBuiltInTimerStatusChangeListener = onBuiltInTimerStatusChangeListener;
    }

    public void setOnBuiltInTimerTickListener(OnBuiltInTimerTickListener onBuiltInTimerTickListener) {
        this.onBuiltInTimerTickListener = onBuiltInTimerTickListener;
    }
}
