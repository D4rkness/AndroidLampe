package com.example.olsk7422.lampe;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;
import java.util.UUID;

/**
 * Bluetoothhandler class manages all connections to the lamp
 */

public class BluetoothHandler extends Observable {

    private static BluetoothHandler instance;
    private boolean isConnected;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private ArrayList bluetoothDeviceListe;
    private static final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothSocket socket = null;
    private boolean connectionRunning = false;

    public BluetoothHandler() {
        isConnected = false;
        BA = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * Establishes a connection to the lamp
     * @param position position in the Arraylist to get the Name
     * @throws BluetoothHandlerException Exception that gets called when not the lamp was selected
     */
    public void connect(int position) throws BluetoothHandlerException {
        if(isConnected){
            throw new BluetoothHandlerException(BluetoothExceptions.IS_CONNECTED);
        }else{
            if(!connectionRunning){
                Log.d("BluetoothHandler:", "Connecting to: " + bluetoothDeviceListe.get(position));
                if (!bluetoothDeviceListe.get(position).equals("Lampe")) {
                    throw new BluetoothHandlerException(BluetoothExceptions.NO_LAMP);
                }else{
                    BluetoothDevice device = null;
                    for(BluetoothDevice bt: pairedDevices){
                        if(bt.getName().equals("Lampe")){
                            Log.d("BluetoothHandler","Connecting to: "+bt.getAddress());
                            new ConnectionThread(bt).start();
                            break;
                        }
                    }
                }
            }

        }


    }

    /**
     * disconnects from the lamp
     */
    public void disconnect() {
        Log.d("BluetoothHandler:", "Disconnect has been triggerd");

    }

    /**
     * Methos that scans all available paired devices
     * @return arraylist with all paired devices
     * @throws BluetoothHandlerException exception that gets thrown when no bluetooth is enabled
     */
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

    /**
     * @return if the device is connected to the lamp
     */
    public boolean getIsConnected() {
        return isConnected;
    }

    /**
     * mathod that sends the values rgb value in json format to the lamp
     * @throws BluetoothHandlerException gets thrown when no connection is available
     */
    public void sendColor(String hexcolor) throws BluetoothHandlerException {
        if (!isConnected) {
            Log.e("Bluetooth Color", "No connection! Can't send command");
            throw new BluetoothHandlerException(BluetoothExceptions.NO_CONNECTION);
        } else {
            JSONObject command = new JSONObject();
            try {
                command.put("command","color");
                command.put("value",hexcolor.substring(1));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("JSON Color", command.toString());
            try {
                socket.getOutputStream().write(command.toString().getBytes());
            } catch (IOException e) {
                isConnected =false;
                setChanged();
                notifyObservers();
                throw new BluetoothHandlerException(BluetoothExceptions.NO_CONNECTION);
            }

        }
    }

    /**
     * send a modus command in JSON format the lamp
     * @param modus modus from the modus enum
     * @throws BluetoothHandlerException when the device is not connected to the lamp
     */
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
            try {
                socket.getOutputStream().write(command.toString().getBytes());
            } catch (IOException e) {
                isConnected =false;
                setChanged();
                notifyObservers();
                throw new BluetoothHandlerException(BluetoothExceptions.NO_CONNECTION);
            }

        }


    }

    public static BluetoothHandler getInstance() {
        if (instance == null) {
            instance = new BluetoothHandler();
        }
        return instance;
    }

    /**
     * Thread that tries to connect to the lamp
     */
    private class ConnectionThread extends Thread {

        BluetoothDevice btDevice;

        public ConnectionThread(BluetoothDevice btDevice){
            this.btDevice = btDevice;
            connectionRunning = true;
        }

        public void run() {
            try {
                socket = btDevice.createInsecureRfcommSocketToServiceRecord(SPP_UUID);
                socket.connect();
                Log.d("Bluetoothhandler","connected");
                isConnected = true;
                setChanged();
                notifyObservers();
            } catch (IOException e) {
                Log.e("Bluetoothhandler","connect no socket available");
            }finally {
                connectionRunning = false;
            }
        }

    }
}
