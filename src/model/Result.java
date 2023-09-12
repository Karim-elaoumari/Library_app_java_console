package model;

import java.util.List;

public class Result {
    private int status;
    private String message;
    private Object data;
    private List<Object> dataList;

    public Result(int status, String message, Object data, List<Object> dataList){
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public Result(int status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public Result(int status, String message, List<Object> dataList){
        this.status = status;
        this.message = message;
        this.dataList = dataList;
    }
    public Result(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }
    public boolean isSuccess(){
        return this.status == 200 || this.status == 201;
    }
    public boolean isFailure(){
        return this.status == 400 || this.status == 500;
    }

}
