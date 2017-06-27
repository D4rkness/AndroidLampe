package com.example.olsk7422.lampe;

import android.util.Log;



public class BluetoothHandler {

    private static BluetoothHandler instance;
    private boolean isConnected;

    public BluetoothHandler(){
        isConnected = true;
    }

    public void connect(){

    }

    public void disconnect(){

    }

    public void scan(){

    }


    public void sendColor(int red, int blue, int green){
        if(!isConnected){
            Log.e("Bluetooth Color", "No connection! Can't send command");
        }else{
            Log.d("Bluetooth Color","Red:"+red+" Blue:"+blue+" Green:"+green);
        }
    }

    public void sendMode(LEDMOUDS modus){
        if(!isConnected){
            Log.e("Bluetooth Mode", "No connection! Can't send command");
        }else{
            Log.d("Bluetooth Mode",":"+modus.toString());

            switch(modus){
                case CLIMB:
                    break;
                case FADESELECTE:
                    break;
                case RAINBOW:
                    break;
                case RANDOMFADE:
                    break;
                case SETALL:
                    break;
                case SOUNDTOBRIGHTNESS:
                    break;
                case SOUNDTOHEIGHT:
                    break;
                case STARS:
                    break;
                default:
                    Log.e("Bluetooth Mode", "No Mode with"+modus.toString());
            }
        }


    }

    public static BluetoothHandler getInstance(){
        if(instance == null){
            instance= new BluetoothHandler();
        }
        return instance;
    }

}
