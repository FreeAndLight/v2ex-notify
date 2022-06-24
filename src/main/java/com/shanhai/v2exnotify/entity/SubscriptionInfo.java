package com.shanhai.v2exnotify.entity;

import lombok.Data;

/**
 * 订阅信息
 *
 * @Auther hk
 * @Date 2022/6/24 10:15
 * @Description
 */
@Data
public class SubscriptionInfo {
	private String url;
	private String keywords;
	private String subscriber;
}