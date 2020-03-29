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

    public static RespBean build(){
        return new RespBean();
    }

    public static RespBean err(String msg){
        return new RespBean(500,msg);
    }
}
