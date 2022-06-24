package com.shanhai.v2exnotify.service;

import com.shanhai.v2exnotify.entity.Subscriber;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shanhai.v2exnotify.entity.SubscriptionInfo;

import java.util.List;

/**
 * <p>
 * 订阅人表 服务类
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
public interface ISubscriberService extends IService<Subscriber> {
	List<SubscriptionInfo> getSubscriptionInfo();
}