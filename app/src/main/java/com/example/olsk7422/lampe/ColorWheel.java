package com.example.olsk7422.lampe;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorWheel extends android.support.v4.app.Fragment {


    public ColorWheel() {
        // Required empty public constructor
    }

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_color_wheel, container, false);
        initPicker();
        return myView;
    }

    private void initPicker(){
        ColorPicker picker = (ColorPicker) myView.findViewById(R.id.picker);
        SaturationBar saturationBar = (SaturationBar) myView.findViewById(R.id.saturationbar);
        ValueBar valueBar = (ValueBar) myView.findViewById(R.id.valuebar);
        picker.setShowOldCenterColor(false);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);

        //To get the color
        //picker.getColor();

//To set the old selected color u can do it like this
        //picker.setOldCenterColor(picker.getColor());
// adds listener to the colorpicker which is implemented
//in the activity
        //picker.setOnColorChangedListener(myView);


        //adding onChangeListeners to bars
        /*
        opacitybar.setOnOpacityChangeListener(new OnOpacityChangeListener …)
        valuebar.setOnValueChangeListener(new OnValueChangeListener …)
        saturationBar.setOnSaturationChangeListener(new OnSaturationChangeListener …)
        */
    }

}
