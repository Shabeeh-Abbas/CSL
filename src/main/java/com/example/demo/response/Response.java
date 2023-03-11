package com.example.demo.response;

import java.util.List;

public class Response <T>{

	private String error;
    private T data;
    private List<T> responseDataCollection;
  
     
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
