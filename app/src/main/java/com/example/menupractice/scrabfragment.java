package com.example.menupractice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link scrabfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class scrabfragment extends Fragment {
    private static String TAG = "phptest_secondActivity";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public scrabfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment scrabfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static scrabfragment newInstance(String param1, String param2) {
        scrabfragment fragment = new scrabfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    MainActivity activity;
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    final int GET_GALLERY = 200;
    ImageView reviewImageView;
    String uri;
    public String getUri() {
        return this.uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_scrabfragment, container, false);



        EditText titleEdit = (EditText) rootView.findViewById(R.id.titleEdit);
        EditText reviewEdit = (EditText) rootView.findViewById(R.id.reviewEdit);
        RatingBar reviewRating = (RatingBar) rootView.findViewById(R.id.reviewRating);
        Button cancel = (Button) rootView.findViewById(R.id.cancelButton);
        Button ok = (Button) rootView.findViewById(R.id.okButton);
        Button reviewlist =(Button)rootView.findViewById(R.id.reviewButton);
        reviewImageView = (ImageView) rootView.findViewById(R.id.reviewImageview);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEdit.getText().toString();
                String review = reviewEdit.getText().toString();
                float number = reviewRating.getRating();
                String usinguri = getUri();
                Log.d(TAG, title+review+number+usinguri);
                Intent intent = new Intent(activity, BbActivity.class);
                intent.putExtra("photo", usinguri);
                intent.putExtra("title", title);
                intent.putExtra("review", review);
                intent.putExtra("rating", number);
                startActivity(intent);




                titleEdit.setText(null);
                reviewEdit.setText(null);
                reviewRating.setRating(0);
                reviewImageView.setImageURI(null);
            }
        });

        reviewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(activity, reviewlist.class);
                startActivity(intent2);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleEdit.setText(null);
                reviewEdit.setText(null);
                reviewRating.setRating(0);
            }
        });

        reviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY);
            }
        });


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_GALLERY) {
            Uri selected = data.getData();
            this.setUri(selected.toString());
            Log.d(TAG, "address = "+selected); // uri값이 스트링으로 알아서 잘 들어옴
            reviewImageView.setImageURI(selected);
        }
    }

}