package com.shanhai.v2exnotify.service.impl;

import com.shanhai.v2exnotify.entity.Subscriber;
import com.shanhai.v2exnotify.entity.SubscriptionInfo;
import com.shanhai.v2exnotify.mapper.SubscriberMapper;
import com.shanhai.v2exnotify.service.ISubscriberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订阅人表 服务实现类
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
@Service
public class SubscriberServiceImpl extends ServiceImpl<SubscriberMapper, Subscriber> implements ISubscriberService {

	@Override
	public List<SubscriptionInfo> getSubscriptionInfo() {
		return this.baseMapper.getSubscriptionInfo();
	}
}