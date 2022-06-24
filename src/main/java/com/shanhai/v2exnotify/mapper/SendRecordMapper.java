package com.shanhai.v2exnotify.mapper;

import com.shanhai.v2exnotify.entity.SendRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 发送记录表 Mapper 接口
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
@Mapper
public interface SendRecordMapper extends BaseMapper<SendRecord> {

	List<SendRecord> getRecord(@Param("topicId") String topicId, @Param("subscriber") String subscriber);
}
