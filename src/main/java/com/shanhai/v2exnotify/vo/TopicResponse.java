package com.shanhai.v2exnotify.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Auther hk
 * @Date 2022/6/23 18:48
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TopicResponse {
	private List<TopicListDto> result;
}