package com.example.olsk7422.lampe;

/**
 * Class that represents the Exception of the Bluetoothhandler
 */

public class BluetoothHandlerException extends Exception {

    BluetoothExceptions error;

    public BluetoothHandlerException(BluetoothExceptions error){
        this.error = error;
    }

    public BluetoothExceptions getError(){
        return error;
    }




}
