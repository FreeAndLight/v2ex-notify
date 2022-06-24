package com.shanhai.v2exnotify.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanhai.v2exnotify.entity.Node;
import com.shanhai.v2exnotify.mapper.NodeMapper;
import com.shanhai.v2exnotify.service.INodeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * api表 服务实现类
 * </p>
 *
 * @author hk
 * @since 2022-06-24
 */
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements INodeService {

}
