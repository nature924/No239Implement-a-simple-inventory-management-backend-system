package com.dev.warehouse.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dev.warehouse.sys.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据权限ID或菜单ID删除sys_role_permission表里面的数据
     * @param id
     */
    void deleteRolePermissionByPid(@Param("id") Serializable id);
}
