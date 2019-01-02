package com.dean.reptile.service.date;

import java.util.List;

import com.dean.reptile.bean.RequestRecord;
import com.dean.reptile.bean.db.RequestRecordGroupByProxy;
import com.dean.reptile.db.CrawlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dean
 */
@Service
public class RequestRecordService {

    @Autowired
    CrawlMapper crawlMapper;

    public Long queryRecordCount() {
        return crawlMapper.count();
    }

    // 暂时只查询最近100条就好
    public List<RequestRecord> queryErrorRecord() {
        return crawlMapper.queryError(0, 100);
    }

    public RequestRecord queryLastError() {
        try {
            return crawlMapper.queryError(0, 1).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public RequestRecord queryLastSuccess() {
        return crawlMapper.queryLastSuccess();
    }

    public List<RequestRecordGroupByProxy> queryErrorGroupByProxy() {
        return crawlMapper.queryErrorGroupByProxy();
    }
}
