package com.example.olsk7422.lampe;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;


public class BluetoothHandler extends Observable {

    private static BluetoothHandler instance;
    private boolean isConnected;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private ArrayList bluetoothDeviceListe;

    public BluetoothHandler() {
        isConnected = true;
        BA = BluetoothAdapter.getDefaultAdapter();
    }

    public void connect(int possition) throws BluetoothHandlerException {
        Log.d("BluetoothHandler:", "Connecting to: " + bluetoothDeviceListe.get(possition));
        if (!bluetoothDeviceListe.get(possition).equals("BA1")) {
            throw new BluetoothHandlerException(BluetoothExceptions.NO_LAMP);
        }else{
            String bluetoothAdress="";
            for(BluetoothDevice bt: pairedDevices){
                if(bt.getName().equals("BA1")){
                    bluetoothAdress = bt.getAddress();
                    Log.d("BluetoothHandler","Connecting to: "+ bluetoothAdress);
                }
            }


        }


    }

    public void disconnect() {
        Log.d("BluetoothHandler:", "Disconnect has been triggerd");

    }

    public ArrayList scan() throws BluetoothHandlerException {
        Log.d("BluetoothHandler:", "Scan has been triggerd");
        if (!BA.isEnabled()) {
            throw new BluetoothHandlerException(BluetoothExceptions.BLUETOOTH_OFF);
        } else {
            pairedDevices = BA.getBondedDevices();
            bluetoothDeviceListe = new ArrayList();
            for (BluetoothDevice bt : pairedDevices) bluetoothDeviceListe.add(bt.getName());
            return bluetoothDeviceListe;

            /*
            isConnected = !isConnected;
            setChanged();
            notifyObservers();
             */

        }

    }


    public boolean getIsConnected() {
        return isConnected;
    }

    public void sendColor(int red, int blue, int green) throws BluetoothHandlerException {
        if (!isConnected) {
            Log.e("Bluetooth Color", "No connection! Can't send command");
            throw new BluetoothHandlerException(BluetoothExceptions.NO_CONNECTION);
        } else {
            Log.d("Bluetooth Color", "Red:" + red + " Blue:" + blue + " Green:" + green);
            JSONObject command = new JSONObject();
            try {
                command.put("command","color");
                command.put("red",red);
                command.put("blue",red);
                command.put("green",green);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("JSON Color", command.toString());

        }
    }

    public void sendMode(LEDMOUDS modus) throws BluetoothHandlerException {
        if (!isConnected) {
            Log.e("Bluetooth Mode", "No connection! Can't send command");
            throw new BluetoothHandlerException(BluetoothExceptions.NO_CONNECTION);
        } else {
            Log.d("Bluetooth Mode", ":" + modus.toString());

            JSONObject command = new JSONObject();
            try {
                command.put("command","mode");
                command.put("mode",modus.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("Command", command.toString());

        }


    }

    public static BluetoothHandler getInstance() {
        if (instance == null) {
            instance = new BluetoothHandler();
        }
        return instance;
    }

}
