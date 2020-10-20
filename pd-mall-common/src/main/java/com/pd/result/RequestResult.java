package com.pd.result;

import lombok.Data;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/5 13:56
 */
@Data
public class RequestResult<T> {
    private int code;
    private String msg;
    private T data;
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public T getData() {
        return data;
    }

    public RequestResult(){}
    public RequestResult(Builder<T> builder){
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = builder.data;
    }


    public static class Builder<T> {
        private int code;
        private String msg;
        private T data;

        public Builder(){}

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public Builder<T> code(int code){
            this.code = code;
            return this;
        }

        public Builder<T> meg(String msg){
            this.msg = msg;
            return this;
        }

        public RequestResult<T> Ok(T data) {
            this.code = 200;
            this.msg = "success";
            this.data = data;
            return build();
        }

        public RequestResult<T> Ok(){
            this.code = 200;
            this.msg = "success";
            return build();
        }

        public RequestResult<T> Fail(){
            this.code = -1 ;
            this.msg = "fail";
            return build();
        }

        public RequestResult<T> buildCustomize(int code,String msg){
            this.code=code;
            this.msg=msg;
            return build();
        }
        public RequestResult<T> buildCustomize(String msg){
            this.code= 200;
            this.msg=msg;
            return build();
        }

        public RequestResult<T> build(){
            return new RequestResult<T>(this);
        }

        @Override
        public String toString() {
            return "RequestResultBuilder{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(new Builder().data("asdfas").Ok());
    }
}
