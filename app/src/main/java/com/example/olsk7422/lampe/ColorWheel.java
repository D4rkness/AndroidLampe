package com.example.olsk7422.lampe;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;


/**
 * Fragment that represents the colorwheel screen
 */
public class ColorWheel extends android.support.v4.app.Fragment {

    private ColorPicker picker;
    public ColorWheel() {
        // Required empty public constructor
    }


    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_color_wheel, container, false);
        initPicker();
        initBtn();
        return myView;
    }

    /**
     * Initializes the Colorpicker with the bars
     */
    private void initPicker(){
        picker = (ColorPicker) myView.findViewById(R.id.picker);
        SaturationBar saturationBar = (SaturationBar) myView.findViewById(R.id.saturationbar);
        ValueBar valueBar = (ValueBar) myView.findViewById(R.id.valuebar);
        picker.setShowOldCenterColor(false);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);
    }

    /**
     * Initializes the Button and connects it to bluetoothhandler
     */
    private void initBtn(){
        myView.findViewById(R.id.btn_wheel_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = picker.getColor();
                String hexColor = String.format("#%06X", (0xFFFFFF & color));
                Log.d("color",hexColor);
                try {
                    BluetoothHandler.getInstance().sendColor(hexColor);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.NO_CONNECTION){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }



}
