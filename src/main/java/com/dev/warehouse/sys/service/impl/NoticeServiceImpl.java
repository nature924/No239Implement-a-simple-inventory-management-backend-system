package com.dev.warehouse.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.warehouse.sys.entity.Notice;
import com.dev.warehouse.sys.mapper.NoticeMapper;
import com.dev.warehouse.sys.service.INoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
