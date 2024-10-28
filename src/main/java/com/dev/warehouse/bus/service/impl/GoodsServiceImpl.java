package com.dev.warehouse.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.dev.warehouse.bus.entity.Goods;
import com.dev.warehouse.bus.mapper.GoodsMapper;
import com.dev.warehouse.bus.service.IGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public boolean save(Goods entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Goods entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public Goods getById(Serializable id) {
        return super.getById(id);
    }
}
