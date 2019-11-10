package org.cockloft.vertx.router.status;

public enum RouterContextStatus {
    BLOCK("block",0),NO_BLOCK("no_block",1);
    private String statusName;
    private int code;
    RouterContextStatus(String statusName,int code){
        this.statusName = statusName;
        this.code = code;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
