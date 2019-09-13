package com.example.scoreboardsnippet.InputFragments;

//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.DialogFragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.scoreboard.PfsRecyclerViewAdapter;
//import com.example.scoreboard.R;
//import com.example.scoreboard.ViewModels.BasketballSharedViewModel;
import com.example.scoreboardsnippet.PfsRecyclerViewAdapter;
import com.example.scoreboardsnippet.R;
import com.example.scoreboardsnippet.ViewModels.BasketballSharedViewModel;

import java.util.ArrayList;

public class BasketballPFSDialog extends DialogFragment {
    //view model
    private BasketballSharedViewModel basketballSharedViewModel_PFS;

    //widgets
    private TextView pfsDialogTitle;
    private EditText inputLineNumber;
    private Button ok_button, cancel_button, insert_button, remove_button;

    //vars
    private String myPosition = "";
    private Integer StartUpWidgetID;
    ArrayList<PFS_item> pfs_itemArrayList;

    private RecyclerView pfsRecyclerView;
    private PfsRecyclerViewAdapter pfsRecyclerViewAdapter;
    private RecyclerView.LayoutManager pfsRecyclerViewLayoutManager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_player_foul_score, container, false);

        //ScreenManager screenManager = new ScreenManager(getActivity().getWindow());
        //screenManager.enableFullScreen(true);
        createPFSItemArrayList();
        buildPFSRecyclerView(rootView);
        setButtons(rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        basketballSharedViewModel_PFS = ViewModelProviders.of(getActivity()).get(BasketballSharedViewModel.class);
        basketballSharedViewModel_PFS.getDialog_position().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                myPosition = charSequence.toString();
                if(myPosition.equals("right")){
                    getDialog().getWindow().setGravity(Gravity.RIGHT);
                }else if(myPosition.equals("left")){
                    getDialog().getWindow().setGravity(Gravity.LEFT);
                }else if(myPosition.equals("center")){
                    getDialog().getWindow().setGravity(Gravity.CENTER);
                }else{
                    getDialog().getWindow().setGravity(Gravity.CENTER);
                }
            }
        });

        basketballSharedViewModel_PFS.getDialog_title().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                pfsDialogTitle.setText(s);
            }
        });

        basketballSharedViewModel_PFS.getStart_up_widget_id().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                StartUpWidgetID = integer;
            }
        });
    }



    public void insertItem(int position){
        pfs_itemArrayList.add(position, new PFS_item(position+1,0,0));
        pfsRecyclerViewAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position){
        pfs_itemArrayList.remove(position);
        pfsRecyclerViewAdapter.notifyItemRemoved(position);
    }

    //this is the function you defined to handle card click event
    public void changeItem(int position){
        //do something first

        //notify adapter that data changed
        pfsRecyclerViewAdapter.notifyItemChanged(position);
    }

    public void createPFSItemArrayList(){
        pfs_itemArrayList =  new ArrayList<>();
        for (int i=0; i<15; i++){
            pfs_itemArrayList.add(new PFS_item(i+1, 0, 0));
        }
    }

    public void  buildPFSRecyclerView(@NonNull View v){
        pfsRecyclerView = v.findViewById(R.id.recyclerView_PFS);
        pfsRecyclerView.setHasFixedSize(true);
        pfsRecyclerViewLayoutManager = new LinearLayoutManager(getContext());
        pfsRecyclerViewAdapter = new PfsRecyclerViewAdapter(getContext(), pfs_itemArrayList, getViewLifecycleOwner());
        pfsRecyclerView.setLayoutManager(pfsRecyclerViewLayoutManager);
        pfsRecyclerView.setAdapter(pfsRecyclerViewAdapter);

        pfsRecyclerViewAdapter.setOnItemClickListener(new PfsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //handle the whole card click event
                //changeItem(position);
            }

            //handle another click event here
            //@Override
            //public void onDigitViewClick(int position) {
            //
            //}
        });
        //return v;
    }

    public void setButtons(View v){

        pfsDialogTitle = v.findViewById(R.id.textView_PFS_title);
        ok_button = v.findViewById(R.id.btPFS_ok);
        cancel_button = v.findViewById(R.id.btPFS_cancel);
        insert_button = v.findViewById(R.id.btPFS_insert);
        remove_button = v.findViewById(R.id.btPFS_remove);
        inputLineNumber = v.findViewById(R.id.etPFS_insert_remove);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(inputLineNumber.getText().toString());
                insertItem(pos);
            }
        });

        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(inputLineNumber.getText().toString());
                removeItem(pos);
            }
        });
    }
}

