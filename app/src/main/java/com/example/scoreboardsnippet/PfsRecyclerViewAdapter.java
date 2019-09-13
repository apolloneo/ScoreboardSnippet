package com.example.scoreboardsnippet;

//import android.arch.lifecycle.LifecycleOwner;
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.scoreboard.CompoundViews.DigitView;
//import com.example.scoreboard.InputFragments.PFS_item;
//import com.example.scoreboard.ViewModels.BasketballSharedViewModel;
import com.example.scoreboardsnippet.CompoundViews.DigitView;
import com.example.scoreboardsnippet.InputFragments.BasketballNumPadInputDialog;
import com.example.scoreboardsnippet.InputFragments.PFS_item;
import com.example.scoreboardsnippet.ViewModels.BasketballSharedViewModel;

import java.util.ArrayList;

public class PfsRecyclerViewAdapter extends RecyclerView.Adapter<PfsRecyclerViewAdapter.PfsViewHolder> {
    private BasketballSharedViewModel basketballSharedViewModel_pfsRVAdapter;
    private ArrayList<PFS_item> mPfsItemArrayList;
    private OnItemClickListener mListener;
    private Context mContext;
    private Integer mStartUpWidgetID;
    private LifecycleOwner mLifecycleOwner;

    public interface OnItemClickListener{
        void onItemClick(int position);
        //void onDigitViewClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class PfsViewHolder extends RecyclerView.ViewHolder{
        public DigitView mPlayerNumber, mPlayerFouls, mPlayerScore;
        public PfsViewHolder(@NonNull View itemView, @NonNull final OnItemClickListener listener) {
            super(itemView);
            mPlayerNumber = itemView.findViewById(R.id.digitView_PFS_item_playerNumber);
            mPlayerFouls = itemView.findViewById(R.id.digitView_PFS_item_playerFoul);
            mPlayerScore = itemView.findViewById(R.id.digitView_PFS_item_playerScore);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });

            //mPlayerFouls.setPlusButtonOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        if (mPlayerFouls.getNumber() < 10){
            //            mPlayerFouls.setNumber(mPlayerFouls.getNumber()+1);
            //        }else{
            //            mPlayerFouls.setNumber(mPlayerFouls.getNumber());
            //        }
            //
            //    }
            //});
//
            //mPlayerFouls.setMinusButtonOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        if (mPlayerFouls.getNumber() == 0){
            //            mPlayerFouls.setNumber(mPlayerFouls.getNumber());
            //        }else {
            //            mPlayerFouls.setNumber(mPlayerFouls.getNumber()-1);
            //        }
            //    }
            //});
//
            //mPlayerScore.setPlusButtonOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        if (mPlayerScore.getNumber() < 10){
            //            mPlayerScore.setNumber(mPlayerScore.getNumber()+1);
            //        }else{
            //            mPlayerScore.setNumber(mPlayerScore.getNumber());
            //        }
            //    }
            //});
//
            //mPlayerScore.setMinusButtonOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        if (mPlayerScore.getNumber() == 0){
            //            mPlayerScore.setNumber(mPlayerScore.getNumber());
            //        }else {
            //            mPlayerScore.setNumber(mPlayerScore.getNumber()-1);
            //        }
            //    }
            //});
        }
    }

    //constructor
    public PfsRecyclerViewAdapter(Context context, ArrayList<PFS_item> pfsItemArrayList, LifecycleOwner lifecycleOwner){
        mPfsItemArrayList = pfsItemArrayList;
        mContext = context;
        //mStartUpWidgetID = starupwidgetid;
        mLifecycleOwner = lifecycleOwner;
        basketballSharedViewModel_pfsRVAdapter = ViewModelProviders.of((FragmentActivity)mContext).get(BasketballSharedViewModel.class);
        basketballSharedViewModel_pfsRVAdapter.getStart_up_widget_id().observe(mLifecycleOwner, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mStartUpWidgetID= integer;
            }
        });
    }

    @NonNull
    @Override
    public PfsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_foul_score_item, parent, false);
        PfsViewHolder mPfsViewHolder = new PfsViewHolder(v,mListener);
        return mPfsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PfsViewHolder pfsViewHolder, int i) {
        PFS_item current_PFS_item = mPfsItemArrayList.get(i);
        pfsViewHolder.mPlayerNumber.setNumber(current_PFS_item.getPlayerNumber());
        pfsViewHolder.mPlayerFouls.setNumber(current_PFS_item.getPlayerFouls());
        pfsViewHolder.mPlayerScore.setNumber(current_PFS_item.getPlayerScore());

        pfsViewHolder.mPlayerFouls.setPlusButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pfsViewHolder.mPlayerFouls.getNumber() < 5){
                    pfsViewHolder.mPlayerFouls.setNumber(pfsViewHolder.mPlayerFouls.getNumber()+1);
                }else{
                    pfsViewHolder.mPlayerFouls.setNumber(pfsViewHolder.mPlayerFouls.getNumber());
                }
                if (mStartUpWidgetID == R.id.digitView_Home_PlayerNumber ){
                    basketballSharedViewModel_pfsRVAdapter.setHome_player_number(pfsViewHolder.mPlayerNumber.getNumber());
                    basketballSharedViewModel_pfsRVAdapter.setHome_player_foul(pfsViewHolder.mPlayerFouls.getNumber());
                }
                basketballSharedViewModel_pfsRVAdapter.setPlayer_number(pfsViewHolder.mPlayerNumber.getNumber());
                basketballSharedViewModel_pfsRVAdapter.setPlayer_fouls(pfsViewHolder.mPlayerFouls.getNumber());
            }
        });

        pfsViewHolder.mPlayerFouls.setMinusButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pfsViewHolder.mPlayerFouls.getNumber() == 0){
                    pfsViewHolder.mPlayerFouls.setNumber(pfsViewHolder.mPlayerFouls.getNumber());
                }else {
                    pfsViewHolder.mPlayerFouls.setNumber(pfsViewHolder.mPlayerFouls.getNumber()-1);
                }
                if (mStartUpWidgetID == R.id.digitView_Home_PlayerNumber ){
                    basketballSharedViewModel_pfsRVAdapter.setHome_player_number(pfsViewHolder.mPlayerNumber.getNumber());
                    basketballSharedViewModel_pfsRVAdapter.setHome_player_foul(pfsViewHolder.mPlayerFouls.getNumber());
                }
                basketballSharedViewModel_pfsRVAdapter.setPlayer_number(pfsViewHolder.mPlayerNumber.getNumber());
                basketballSharedViewModel_pfsRVAdapter.setPlayer_fouls(pfsViewHolder.mPlayerFouls.getNumber());
            }
        });

        pfsViewHolder.mPlayerScore.setPlusButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pfsViewHolder.mPlayerScore.getNumber() < 99){
                    pfsViewHolder.mPlayerScore.setNumber(pfsViewHolder.mPlayerScore.getNumber()+1);
                }else{
                    pfsViewHolder.mPlayerScore.setNumber(pfsViewHolder.mPlayerScore.getNumber());
                }
            }
        });

        pfsViewHolder.mPlayerScore.setMinusButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pfsViewHolder.mPlayerScore.getNumber() == 0){
                    pfsViewHolder.mPlayerScore.setNumber(pfsViewHolder.mPlayerScore.getNumber());
                }else {
                    pfsViewHolder.mPlayerScore.setNumber(pfsViewHolder.mPlayerScore.getNumber()-1);
                }
            }
        });

        pfsViewHolder.mPlayerScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasketballNumPadInputDialog basketballNumPadInputDialog = new BasketballNumPadInputDialog();
                basketballSharedViewModel_pfsRVAdapter.setDialog_title("Input Player Score");
                basketballSharedViewModel_pfsRVAdapter.setDialog_position("right");
                basketballSharedViewModel_pfsRVAdapter.setStart_up_widget_id(pfsViewHolder.mPlayerScore.getId());
                basketballSharedViewModel_pfsRVAdapter.setInitial_value(pfsViewHolder.mPlayerScore.getNumber());
                FragmentActivity fragmentActivity = (FragmentActivity)mContext;
                basketballNumPadInputDialog.show(fragmentActivity.getSupportFragmentManager(),"Player Score");
            }
        });
    }

    @Override
    public int getItemCount() {
        //return 0;
        return mPfsItemArrayList.size();
    }
}
