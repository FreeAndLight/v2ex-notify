package com.shanhai.v2exnotify.service;

import com.shanhai.v2exnotify.entity.Components;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 组件表 服务类
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
public interface IComponentsService extends IService<Components> {
	String getToken();
}
