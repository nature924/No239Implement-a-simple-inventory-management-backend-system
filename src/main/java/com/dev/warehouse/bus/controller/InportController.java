package com.dev.warehouse.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.warehouse.bus.entity.Goods;
import com.dev.warehouse.bus.entity.Inport;
import com.dev.warehouse.bus.entity.Provider;
import com.dev.warehouse.bus.service.IGoodsService;
import com.dev.warehouse.bus.service.IInportService;
import com.dev.warehouse.bus.service.IProviderService;
import com.dev.warehouse.bus.vo.InportVo;
import com.dev.warehouse.sys.common.DataGridView;
import com.dev.warehouse.sys.common.ResultObj;
import com.dev.warehouse.sys.common.WebUtils;
import com.dev.warehouse.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("inport")
public class InportController {

    @Autowired
    private IInportService inportService;

    @Autowired
    private IProviderService providerService;

    @Autowired
    private IGoodsService goodsService;

    /**
     * 查询商品进货
     * @param inportVo
     * @return
     */
    @RequestMapping("loadAllInport")
    public DataGridView loadAllInport(InportVo inportVo){
        IPage<Inport> page = new Page<Inport>(inportVo.getPage(),inportVo.getLimit());
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<Inport>();
        //对供应商进行查询
        queryWrapper.eq(inportVo.getProviderid()!=null&&inportVo.getProviderid()!=0,"providerid",inportVo.getProviderid());
        //对商品进行查询
        queryWrapper.eq(inportVo.getGoodsid()!=null&&inportVo.getGoodsid()!=0,"goodsid",inportVo.getGoodsid());
        //对时间进行查询要求大于开始时间小于结束时间
        queryWrapper.ge(inportVo.getStartTime()!=null,"inporttime",inportVo.getStartTime());
        queryWrapper.le(inportVo.getEndTime()!=null,"inporttime",inportVo.getEndTime());
        //通过进货时间对商品进行排序
        queryWrapper.orderByDesc("inporttime");
        IPage<Inport> page1 = inportService.page(page, queryWrapper);
        List<Inport> records = page1.getRecords();
        for (Inport inport : records) {
            Provider provider = providerService.getById(inport.getProviderid());
            if (provider!=null){
                //设置供应商姓名
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods = goodsService.getById(inport.getGoodsid());
            if (goods!=null){
                //设置商品名称
                inport.setGoodsname(goods.getGoodsname());
                //设置商品规格
                inport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page1.getTotal(),page1.getRecords());
    }


    /**
     * 添加进货商品
     * @param inportVo
     * @return
     */
    @RequestMapping("addInport")
    public ResultObj addInport(InportVo inportVo){
        try {
            //获得当前系统用户
            User user = (User) WebUtils.getSession().getAttribute("user");
            //设置操作人
            inportVo.setOperateperson(user.getName());
            //设置进货时间
            inportVo.setInporttime(new Date());
            inportService.save(inportVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新进货商品
     * @param inportVo
     * @return
     */
    @RequestMapping("updateInport")
    public ResultObj updateInport(InportVo inportVo){
        try {
            inportService.updateById(inportVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    /**
     * 删除进货商品
     * @param id
     * @return
     */
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id){
        try {
            inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}

