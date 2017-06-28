package com.example.olsk7422.lampe;

/**
 * Created by olsk7422 on 28.06.2017.
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
