package com.banquito.excepcion;

public class CRUDException extends Exception{
    public CRUDException() {
    }

    public CRUDException(String arg0) {
        super(arg0);
    }

    public CRUDException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}
