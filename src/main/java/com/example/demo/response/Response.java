package com.example.demo.response;

import java.util.List;

public class Response <T>{

	private Integer status;
	private String error;
    private T data;
    private List<T> responseDataCollection;
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
  
     
     public List<T> getResponseDataCollection() {
 		return responseDataCollection;
 	}

 	public void setResponseDataCollection(List<T> responseDataCollection) {
 		this.responseDataCollection = responseDataCollection;
 	}
     public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
}
