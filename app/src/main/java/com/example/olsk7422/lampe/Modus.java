package com.example.olsk7422.lampe;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Modus extends android.support.v4.app.Fragment {

    private View myView;
    public Modus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_modus, container, false);
        setupBtns();
        return myView;
    }

    private void setupBtns(){
        myView.findViewById(R.id.btnSetAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothHandler.getInstance().sendMode(LEDMOUDS.SETALL);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.NO_CONNECTION){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        myView.findViewById(R.id.btnAccFade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothHandler.getInstance().sendMode(LEDMOUDS.FADESELECTE);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.NO_CONNECTION){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        myView.findViewById(R.id.btnRandomStars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothHandler.getInstance().sendMode(LEDMOUDS.STARS);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.NO_CONNECTION){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        myView.findViewById(R.id.btnSlowClimb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothHandler.getInstance().sendMode(LEDMOUDS.CLIMB);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.NO_CONNECTION){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        myView.findViewById(R.id.btnRandomFade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothHandler.getInstance().sendMode(LEDMOUDS.RANDOMFADE);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.BLUETOOTH_OFF){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        myView.findViewById(R.id.btnRainbow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothHandler.getInstance().sendMode(LEDMOUDS.RAINBOW);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.BLUETOOTH_OFF){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        myView.findViewById(R.id.btnSoundToHeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothHandler.getInstance().sendMode(LEDMOUDS.SOUNDTOHEIGHT);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.BLUETOOTH_OFF){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        myView.findViewById(R.id.btnSoundToBrightness).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BluetoothHandler.getInstance().sendMode(LEDMOUDS.SOUNDTOBRIGHTNESS);
                } catch (BluetoothHandlerException e) {
                    if(e.getError() == BluetoothExceptions.BLUETOOTH_OFF){
                        Toast.makeText(myView.getContext(), "Lamp not connected",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

}
