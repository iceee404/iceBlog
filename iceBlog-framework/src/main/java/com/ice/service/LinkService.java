package com.ice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.domain.ResponseResult;
import com.ice.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-03-09 21:00:47
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

