package com.shanhai.v2exnotify.service;

import com.shanhai.v2exnotify.entity.SendRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 发送记录表 服务类
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
public interface ISendRecordService extends IService<SendRecord> {

	boolean checkRepeatSend(String topicId, String subscriber);
}