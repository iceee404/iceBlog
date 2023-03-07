package com.ice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.constants.SystemConstants;
import com.ice.domain.ResponseResult;
import com.ice.domain.entity.Link;
import com.ice.domain.vo.LinkVo;
import com.ice.mapper.LinkMapper;
import com.ice.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.ice.service.LinkService;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-03-09 21:00:48
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回


        return ResponseResult.okResult(linkVos);
    }
}
