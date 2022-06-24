package com.shanhai.v2exnotify.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanhai.v2exnotify.entity.SendRecord;
import com.shanhai.v2exnotify.mapper.SendRecordMapper;
import com.shanhai.v2exnotify.service.ISendRecordService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 发送记录表 服务实现类
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
@Service
public class SendRecordServiceImpl extends ServiceImpl<SendRecordMapper, SendRecord> implements ISendRecordService {

	@Override
	public boolean checkRepeatSend(String topicId, String subscriber) {
		List<SendRecord> list = this.baseMapper.getRecord(topicId, subscriber);
		return !CollectionUtils.isEmpty(list);
	}
}