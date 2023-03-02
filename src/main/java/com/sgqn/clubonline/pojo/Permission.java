package com.sgqn.clubonline.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 权限实体类
 * @author wspstart
 * @create 2023-02-28 21:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {


    /**
     * 所属社团
     */
    private Integer fromClubID;

    /**
     * 所属社团的所具有的角色
     */
    private Integer roleID;


    /**
     * 该角色所具有的权限
     */
    private List<Menu> permissions;

}
