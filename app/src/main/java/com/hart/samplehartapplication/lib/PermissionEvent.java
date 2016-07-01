package com.hart.samplehartapplication.lib;

/**
 * Created by Alex on 10/27/15.
 * Proprietary (Hart)
 */
public class PermissionEvent {
    protected String type = "";
    protected boolean value = false;
    public PermissionEvent(String type) {
        initProperties(type);
    }
    public void setValue(boolean value) {
        this.value = value;
    }
    protected void initProperties(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public boolean getValue() {
        return value;
    }
}