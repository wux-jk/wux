package com.jk.controller.notice;

import com.jk.service.notice.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping("notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;
}
