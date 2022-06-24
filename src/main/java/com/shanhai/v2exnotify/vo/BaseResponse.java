package com.shanhai.v2exnotify.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther hk
 * @Date 2022/6/23 18:38
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResponse<T> {
	private String success;
	private String message;
	private T result;
}