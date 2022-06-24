package com.shanhai.v2exnotify.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanhai.v2exnotify.entity.Components;
import com.shanhai.v2exnotify.mapper.ComponentsMapper;
import com.shanhai.v2exnotify.service.IComponentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 组件表 服务实现类
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
@Service
public class ComponentsServiceImpl extends ServiceImpl<ComponentsMapper, Components> implements IComponentsService {

	@Override
	public String getToken() {
		QueryWrapper<Components> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(Components::getKeyName, "token");
		List<Components> components = this.baseMapper.selectList(queryWrapper);
		if (CollectionUtils.isEmpty(components)) {
			throw new RuntimeException();
		}
		return components.get(0).getValue();
	}

}