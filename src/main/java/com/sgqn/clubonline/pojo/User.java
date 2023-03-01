package com.sgqn.clubonline.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表
 *
 * @TableName per_user
 */
@TableName(value = "per_user")
@Data
@Validated
public class User implements Serializable, UserDetails {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 自增主键 id，无意义
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * per_user_detail.id
     */
    private Integer userDetailId;
    /**
     * 邮箱，也可以使用邮箱进行登录
     */
    private String email;
    /**
     * 密码，使用 bcrypt 算法进行加密，默认情况下长度为 60
     */
    private String password;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 个性签名
     */
    private String personalSignature;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDateTime;
    /**
     * 是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 存放授予的权限
     */
    private List<Permission> permissionList;


    /**
     * 实现 UserDetails 需要重写的方法，获取该用户的权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 创建存放权限的列表
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 遍历 角色权限列表
        for (Permission permission:permissionList){
            // 如果角色权限列表中的元素不为空，将其添加到集合中
            if (!permission.getPermissions().isEmpty()) {
                permission.getPermissions().forEach(singlePer ->{
                    authorities.add(new SimpleGrantedAuthority(singlePer));
                });
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserDetailId() == null ? other.getUserDetailId() == null : this.getUserDetailId().equals(other.getUserDetailId()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
                && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
                && (this.getPersonalSignature() == null ? other.getPersonalSignature() == null : this.getPersonalSignature().equals(other.getPersonalSignature()))
                && (this.getCreateDateTime() == null ? other.getCreateDateTime() == null : this.getCreateDateTime().equals(other.getCreateDateTime()))
                && (this.getUpdateDateTime() == null ? other.getUpdateDateTime() == null : this.getUpdateDateTime().equals(other.getUpdateDateTime()))
                && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserDetailId() == null) ? 0 : getUserDetailId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPersonalSignature() == null) ? 0 : getPersonalSignature().hashCode());
        result = prime * result + ((getCreateDateTime() == null) ? 0 : getCreateDateTime().hashCode());
        result = prime * result + ((getUpdateDateTime() == null) ? 0 : getUpdateDateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", userDetailId=" + userDetailId +
                ", email=" + email +
                ", password=" + password +
                ", avatar=" + avatar +
                ", nickName=" + nickName +
                ", personalSignature=" + personalSignature +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                ", isDeleted=" + isDeleted +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }

}