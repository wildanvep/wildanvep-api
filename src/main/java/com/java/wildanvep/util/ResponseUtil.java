package com.java.wildanvep.util;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.wildanvep.constant.Constants;
import com.java.wildanvep.response.BaseResponse;

public class ResponseUtil {

	@JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
	private Date FinishedAt;

	@SuppressWarnings("unchecked")
	public static <T> ResponseEntity<T> generateResponse(String message, HttpStatus status, Object responseObj) {

		BaseResponse<Object> response = new BaseResponse<Object>();
		response.setMessage(message);
		response.setStatus(status);
		response.setFinishedAt(new Timestamp(System.currentTimeMillis()));
		response.setData(responseObj);

		return (ResponseEntity<T>) new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public static <T> ResponseEntity<T> generateResponseSuccess(Object responseObj) {

		BaseResponse<Object> response = new BaseResponse<Object>();
		response.setMessage(Constants.SUCCESS_MESSAGE);
		response.setStatus(HttpStatus.OK);
		response.setFinishedAt(new Timestamp(System.currentTimeMillis()));
		response.setData(responseObj);

		return (ResponseEntity<T>) new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
