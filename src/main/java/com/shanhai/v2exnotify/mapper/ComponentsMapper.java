package com.shanhai.v2exnotify.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanhai.v2exnotify.entity.Components;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 组件表 Mapper 接口
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
@Mapper
@Repository
public interface ComponentsMapper extends BaseMapper<Components> {

}
