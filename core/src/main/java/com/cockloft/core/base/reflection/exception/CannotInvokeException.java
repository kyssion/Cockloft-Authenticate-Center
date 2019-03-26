package com.cockloft.core.base.reflection.exception;

import java.lang.reflect.InvocationTargetException;

/**
 * Thrown when method invocation using the reflection API has thrown
 * an exception.
 *
 */
public class CannotInvokeException extends RuntimeException {

    /** default serialVersionUID */
    private static final long serialVersionUID = 1L;
    private Throwable err = null;

    /**
     * Returns the cause of this exception.  It may return null.
     */
    public Throwable getReason() { return err; }

    /**
     * Constructs a CannotInvokeException with an error message.
     */
    public CannotInvokeException(String reason) {
        super(reason);
    }

    /**
     * Constructs a CannotInvokeException with an InvocationTargetException.
     */
    public CannotInvokeException(InvocationTargetException e) {
        super("by " + e.getTargetException().toString());
        err = e.getTargetException();
    }

    /**
     * Constructs a CannotInvokeException with an IllegalAccessException.
     */
    public CannotInvokeException(IllegalAccessException e) {
        super("by " + e.toString());
        err = e;
    }

    /**
     * Constructs a CannotInvokeException with an ClassNotFoundException.
     */
    public CannotInvokeException(ClassNotFoundException e) {
        super("by " + e.toString());
        err = e;
    }
}