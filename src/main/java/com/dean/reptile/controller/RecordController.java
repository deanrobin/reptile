package com.dean.reptile.controller;

import java.util.List;

import com.dean.reptile.bean.RequestRecord;
import com.dean.reptile.bean.db.RequestRecordGroupByProxy;
import com.dean.reptile.service.date.RequestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dean
 */
@RequestMapping(value = "admin/record")
@Controller
public class RecordController {

    @Autowired
    RequestRecordService requestRecordService;

    @RequestMapping("/count")
    @ResponseBody
    public Long count() {
        return requestRecordService.queryRecordCount();
    }

    @RequestMapping("/queryError")
    @ResponseBody
    public List<RequestRecord> queryError() {
        return requestRecordService.queryErrorRecord();
    }

    @RequestMapping("/queryLastError")
    @ResponseBody
    public RequestRecord queryLastError() {
        return requestRecordService.queryLastError();
    }

    @RequestMapping("/queryLastSuccess")
    @ResponseBody
    public RequestRecord queryLastSuccess() {
        return requestRecordService.queryLastSuccess();
    }

    @RequestMapping("/queryErrorGroupByProxy")
    @ResponseBody
    public List<RequestRecordGroupByProxy> queryErrorGroupByProxy() {
        return requestRecordService.queryErrorGroupByProxy();
    }

}
