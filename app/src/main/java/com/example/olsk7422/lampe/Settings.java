package com.example.olsk7422.lampe;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * Settings class represents the Settings screen
 */
public class Settings extends android.support.v4.app.Fragment implements Observer {
    ListView lv;
    View myView;
    Button btnStatus;

    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_settings, container, false);
        initBtn();
        return myView;
    }

    /**
     * Initializes all buttons with a onClick listener
     * an the Connection status button
     */
    private void initBtn() {
        btnStatus = (Button) myView.findViewById(R.id.btnSTatus);
        btnStatus.setBackgroundColor(Color.RED);
        btnStatus.setText("No Connection");
        lv = (ListView) myView.findViewById(R.id.lvSearchResult);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Toast.makeText(myView.getContext(),
                            "Trying to connect", Toast.LENGTH_LONG).show();
                    BluetoothHandler.getInstance().connect(position);
                } catch (BluetoothHandlerException e) {
                    if (e.getError() == BluetoothExceptions.NO_LAMP) {
                        Toast.makeText(myView.getContext(),
                                "Ob du Behindert bist ? Das ist keine Lampe",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        myView.findViewById(R.id.btnSettingsSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // bluetoothhandler returns search results if scan was successful
                    ArrayList scanResult = BluetoothHandler.getInstance().scan();
                    ArrayAdapter adapter = new ArrayAdapter(myView.getContext(),
                            android.R.layout.simple_list_item_1, scanResult);
                    lv.setAdapter(adapter);
                } catch (BluetoothHandlerException e) {
                    if (e.getError() == BluetoothExceptions.BLUETOOTH_OFF) {
                        Toast.makeText(myView.getContext(), "Boi turn the bluetooth on",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        Handler mainHandler = new Handler(getContext().getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                if (BluetoothHandler.getInstance().getIsConnected()) {
                    btnStatus.setBackgroundColor(Color.GREEN);
                    btnStatus.setText("Connected");
                } else {
                    btnStatus.setBackgroundColor(Color.RED);
                    btnStatus.setText("No Connection");
                }
            }
        };
        mainHandler.post(myRunnable);


    }
}
