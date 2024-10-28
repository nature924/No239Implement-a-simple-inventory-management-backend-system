package com.dev.warehouse.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dev.warehouse.bus.entity.Salesback;


public interface ISalesbackService extends IService<Salesback> {

    /**
     * 对商品销售进行退货处理
     * @param id    销售单ID
     * @param number    退货数量
     * @param remark    备注
     */
    void addSalesback(Integer id, Integer number, String remark);

}
