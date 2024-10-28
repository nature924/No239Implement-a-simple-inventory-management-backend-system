package com.dev.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.warehouse.sys.entity.Loginfo;
import com.dev.warehouse.sys.mapper.LoginfoMapper;
import com.dev.warehouse.sys.service.ILoginfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements ILoginfoService {

}
