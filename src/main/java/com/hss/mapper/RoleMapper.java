package com.hss.mapper;

import com.hss.bean.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    /**
     * 查询用户角色信息
     * @param userId
     * @return
     */
    List<Role> findRolesByUid(@Param(value = "userId") Long userId);

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    void addRole(Role role);
}
