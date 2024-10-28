package com.dev.warehouse.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.warehouse.bus.entity.Customer;
import com.dev.warehouse.bus.entity.Goods;
import com.dev.warehouse.bus.entity.Salesback;
import com.dev.warehouse.bus.service.ICustomerService;
import com.dev.warehouse.bus.service.IGoodsService;
import com.dev.warehouse.bus.service.ISalesbackService;
import com.dev.warehouse.bus.vo.SalesbackVo;
import com.dev.warehouse.sys.common.DataGridView;
import com.dev.warehouse.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salesback")
public class SalesbackController {

    @Autowired
    private ISalesbackService salesbackService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IGoodsService goodsService;

    /**
     * 添加退货信息
     * @param id    进货单ID
     * @param number    退货数量
     * @param remark    备注
     * @return
     */
    @RequestMapping("addSalesback")
    public ResultObj addSalesback(Integer id, Integer number, String remark){
        try {
            salesbackService.addSalesback(id,number,remark);
            return ResultObj.BACKINPORT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.BACKINPORT_ERROR;
        }
    }

    /**t
     * 查询商品销售退货
     * @param salesbackVo
     * @return
     */
    @RequestMapping("loadAllSalesback")
    public DataGridView loadAllSalesback(SalesbackVo salesbackVo){
        IPage<Salesback> page = new Page<Salesback>(salesbackVo.getPage(),salesbackVo.getLimit());
        QueryWrapper<Salesback> queryWrapper = new QueryWrapper<Salesback>();
        //对客户进行查询
        queryWrapper.eq(salesbackVo.getCustomerid()!=null&&salesbackVo.getCustomerid()!=0,"customerid",salesbackVo.getCustomerid());
        //对商品进行查询
        queryWrapper.eq(salesbackVo.getGoodsid()!=null&&salesbackVo.getGoodsid()!=0,"goodsid",salesbackVo.getGoodsid());
        //对时间进行查询要求大于开始时间小于结束时间
        queryWrapper.ge(salesbackVo.getStartTime()!=null,"salesbacktime",salesbackVo.getStartTime());
        queryWrapper.le(salesbackVo.getEndTime()!=null,"salesbacktime",salesbackVo.getEndTime());
        //通过商品退货时间对商品进行排序
        queryWrapper.orderByDesc("salesbacktime");
        salesbackService.page(page, queryWrapper);
        List<Salesback> records = page.getRecords();
        for (Salesback salesback : records) {
            System.out.println("============================");
            Customer customer = customerService.getById(salesback.getCustomerid());
            if (customer!=null){
                //设置客户姓名
                salesback.setCustomername(customer.getCustomername());
            }
            Goods goods = goodsService.getById(salesback.getGoodsid());
            if (goods!=null){
                //设置商品名称
                salesback.setGoodsname(goods.getGoodsname());
                //设置商品规格
                salesback.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 删除商品销售退回信息
     * @param id
     * @return
     */
    @RequestMapping("deleteSalesback")
    public ResultObj deleteSalesback(Integer id){
        try {
            salesbackService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    
}

