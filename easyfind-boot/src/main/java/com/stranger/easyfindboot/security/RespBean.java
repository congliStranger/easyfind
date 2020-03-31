package com.stranger.easyfindboot.security;

public class RespBean {
    private Integer status;
    private String msg;
    private Object object;
    private RespBean(){

    }

    public RespBean(Integer status, String msg) {
        this.status=status;
        this.msg=msg;
        this.object=object;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static RespBean build(){
        return new RespBean();
    }

    public static RespBean err(String msg){
        return new RespBean(500,msg);
    }
    public static RespBean ok(String msg){return new RespBean(200,msg);}
}
