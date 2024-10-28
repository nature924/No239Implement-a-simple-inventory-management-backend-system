package com.dev.warehouse.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dev.warehouse.bus.entity.Outport;


public interface IOutportService extends IService<Outport> {

    /**
     * 对商品进货进行退货处理
     * @param id    进货单ID
     * @param number    退货数量
     * @param remark    备注
     */
    void addOutport(Integer id, Integer number, String remark);
}
