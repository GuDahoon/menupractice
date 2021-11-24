package com.example.menupractice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;


public class foodfragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public foodfragment() {
        // Required empty public constructor
    }
    public static scrabfragment newInstance(String param1, String param2) {
        scrabfragment fragment = new scrabfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    MainActivity activity;
    CcActivity activity2;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }
    public void onDetach() {
        super.onDetach();
    }
    View v_d;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_foodfragment, container, false);



        Button foodKind = rootView.findViewById(R.id.foodKind);
        Button tasteKind = rootView.findViewById(R.id.tasteKind);
        Button feelKind = rootView.findViewById(R.id.feelKind);
        Button randomKind = rootView.findViewById(R.id.randomKind);
        final int[] selectedItem = {0};
        foodKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle(" 종류   ");
                dlg.setIcon(R.drawable.applogo);

                //v_d = (View) rootView.inflate(getActivity(), R.layout.dialog, null);

                //dlg.setView(v_d);

                String[] words = new String[] {"한식", "일식", "중식", "양식", "아시안"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = which;
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(activity, CcActivity.class);
                        intent.putExtra("Kind", selectedItem[0]+"food");
                        startActivity(intent);
                    }
                });
                dlg.show();
            }
        });
        tasteKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("       ");
                dlg.setIcon(R.drawable.applogo);

                String[] words = new String[] {"짠맛", "신맛", "시원한맛", "매운맛", "담백한맛", "단맛", "기름진맛"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = which;
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(activity, CcActivity.class);
                        intent.putExtra("Kind", selectedItem[0]+"taste");
                        startActivity(intent);
                    }
                });
                dlg.show();
            }
        });
        feelKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                dlg.setTitle("       ");
                dlg.setIcon(R.drawable.applogo);

                String[] words = new String[] {"좋아요^^", "별로 안좋아요"};
                dlg.setSingleChoiceItems(words, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = which;
                    }
                }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(activity, CcActivity.class);
                        intent.putExtra("Kind", selectedItem[0]+"feel");
                        startActivity(intent);
                    }
                });
                dlg.show();
            }
        });
        randomKind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CcActivity.class);
                intent.putExtra("Kind", "random");
                startActivity(intent);
            }
        });
        return rootView;
    }
}