package com.kaishengit.erp.dto;

/**
 * @author liuyan
 * @date 2018/7/28
 */
public class ResponseBean {

    private static final String RESPONSEBEAN_STATE_SUCCESS = "success";
    private static final String RESPONSEBEAN_STATE_ERROR = "error";

    private String state;
    private Object data;
    private String message;

    public static ResponseBean success(){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(RESPONSEBEAN_STATE_SUCCESS);
        return responseBean;
    }

    public static ResponseBean error(String message){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(RESPONSEBEAN_STATE_ERROR);
        // 发生错误时向前端传输的信息
        responseBean.setMessage(message);
        return responseBean;
    }

    public static ResponseBean error(Object data){
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(RESPONSEBEAN_STATE_ERROR);
        // 发生错误时向前端传输的数据
        responseBean.setData(data);
        return responseBean;
    }




    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
