package com.ice.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package:com.ice.domain.vo
 * @ClassName:CategoryVo
 * @Auther:iceee
 * @Date:2022/3/2
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {
    @TableId
    private Long id;
    //标题
    private String name;

}
