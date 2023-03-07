package com.ice.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Package:com.ice.domain.vo
 * @ClassName:LinkVo
 * @Auther:iceee
 * @Date:2022/3/9
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    private Long id;


    private String name;

    private String logo;
    private String description;
    //网站地址
    private String address;

}
