package com.java.wildanvep.response;

import java.util.Date;
import java.util.List;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.wildanvep.constant.Constants;

import lombok.Data;

@Data
public class SearchResponse<T> {

	@NonNull
	private Integer pageNo;
	@NonNull
	private Integer pageSize;
	@NonNull
	private Integer totalDataInPage;
	@NonNull
	private Long totalData;
	@NonNull
	private Integer totalPages;
	@NonNull
	@JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
	private Date finishedAt;
	@NonNull
	private List<T> listData;

}
