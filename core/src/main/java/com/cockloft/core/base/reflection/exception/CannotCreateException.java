package com.cockloft.core.base.reflection.exception;

/**
 * Signals that <code>ClassMetaobject.newInstance()</code> fails.
 */
public class CannotCreateException extends Exception {
    /** default serialVersionUID */
    private static final long serialVersionUID = 1L;

    public CannotCreateException(String s) {
        super(s);
    }

    public CannotCreateException(Exception e) {
        super("by " + e.toString());
    }
}
