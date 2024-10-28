package com.dev.warehouse.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dev.warehouse.sys.entity.User;


public interface IUserService extends IService<User> {

    /**
     * 保存用户和角色的关系
     * @param uid 用户的ID
     * @param ids 用户拥有的角色的ID的数组
     */
    void saveUserRole(Integer uid, Integer[] ids);
}
