package com.jk.service.notice;

import com.jk.dao.notice.NoticeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service
public class NoticeServiceImpl implements NoticeService{

    @Resource
    private NoticeDao noticeDao;
}
