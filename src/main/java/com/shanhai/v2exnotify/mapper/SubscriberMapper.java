package com.shanhai.v2exnotify.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanhai.v2exnotify.entity.Subscriber;
import com.shanhai.v2exnotify.entity.SubscriptionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 订阅人表 Mapper 接口
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
@Mapper
public interface SubscriberMapper extends BaseMapper<Subscriber> {
	List<SubscriptionInfo> getSubscriptionInfo();
}
