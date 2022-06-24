package com.shanhai.v2exnotify.service.impl;

import com.shanhai.v2exnotify.entity.SendRecord;
import com.shanhai.v2exnotify.entity.SubscriptionInfo;
import com.shanhai.v2exnotify.service.IBusinessService;
import com.shanhai.v2exnotify.service.IComponentsService;
import com.shanhai.v2exnotify.service.ISendRecordService;
import com.shanhai.v2exnotify.service.ISubscriberService;
import com.shanhai.v2exnotify.util.IdGenerator;
import com.shanhai.v2exnotify.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther hk
 * @Date 2022/6/24 09:36
 * @Description
 */
@Service
public class BusinessServiceImpl implements IBusinessService {
	@Autowired
	private RestTemplate restTemplate;

	private static final String HEADER_NAME = "Authorization" ;

	private static final String HEADER_PREFIX = "Bearer " ;

	@Autowired
	private ISubscriberService iSubscriberService;

	@Autowired
	private IComponentsService iComponentsService;

	@Autowired
	private ISendRecordService iSendRecordService;

	@Autowired
	private IdGenerator idGenerator;

	/**
	 * 任务监听
	 * 每两分钟监听一次
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void taskPass() {
		System.out.println("执行");
		run();
	}

	public void run() {
		List<SubscriptionInfo> subscriptionInfoList = iSubscriberService.getSubscriptionInfo();
		if (CollectionUtils.isEmpty(subscriptionInfoList)) {
			return;
		}

		for (SubscriptionInfo subscriptionInfo : subscriptionInfoList) {
			List<Map> response = sendRequest(subscriptionInfo.getUrl());
			matchingKeywordsAndSend(response, subscriptionInfo.getKeywords(), subscriptionInfo.getSubscriber());
		}

	}

	/**
	 * 发送请求
	 *
	 * @param url
	 * @return
	 */
	public List<Map> sendRequest(String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HEADER_NAME, HEADER_PREFIX + iComponentsService.getToken());
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);
		Map<String, Object> body = exchange.getBody();
		if (!(body.containsKey("success") && (boolean) body.get("success"))) {
			return null;
		}
		List<Map> result = (List) body.get("result");
		return result;
	}

	/**
	 * 匹配关键词并发送邮件
	 *
	 * @param origin
	 * @param keywords
	 * @param subscriber
	 */
	public void matchingKeywordsAndSend(List<Map> origin, String keywords, String subscriber) {
		List<Map> resultList = new ArrayList<>();
		List<String> topicIdList = new ArrayList<>();
		for (Map topic : origin) {
			String titleLower = topic.get("title").toString().toLowerCase();
			String contentRenderedLower = topic.get("content_rendered").toString().toLowerCase();
			String topicId = String.valueOf(topic.get("id"));
			if ((titleLower.contains(keywords) || contentRenderedLower.contains(keywords))
					&& !checkRepeatSend(topicId, subscriber)) {
				resultList.add(topic);
				topicIdList.add(topicId);
			}
		}
		try {
			if (CollectionUtils.isEmpty(resultList)) {
				return;
			}
			MailUtil.sendMail(subscriber, keywords, resultList.toString());
			addRecord(topicIdList, subscriber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addRecord(List<String> topicIdList, String subscriber) {
		for (String topicId : topicIdList) {
			SendRecord sendRecord = new SendRecord();
			sendRecord.setId(idGenerator.getNumberId());
			sendRecord.setSubscriber(subscriber);
			sendRecord.setTopicId(topicId);
			sendRecord.insert();
		}

	}

	/**
	 * 校验重复发送
	 *
	 * @return
	 */
	public boolean checkRepeatSend(String topicId, String subscriber) {
		return iSendRecordService.checkRepeatSend(topicId, subscriber);
	}
}