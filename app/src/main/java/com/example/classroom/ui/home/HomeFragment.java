package com.example.classroom.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.classroom.R;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ImageSlider imageSlider;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_home, container, false);
        imageSlider=view.findViewById(R.id.imageslider);
        ArrayList<SlideModel>slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.ic_intro, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ic_campustour, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ic_event, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ic_visit, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ic_connvocation, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        return view;

    }
}