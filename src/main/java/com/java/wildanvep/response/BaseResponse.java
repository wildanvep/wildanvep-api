package com.java.wildanvep.response;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.wildanvep.constant.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {

	private HttpStatus status;
	private String message;
	@JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
	private Date finishedAt;
	private T data;

}
