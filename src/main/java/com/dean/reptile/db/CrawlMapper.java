package com.dean.reptile.db;

import java.util.List;

import com.dean.reptile.bean.RequestRecord;
import com.dean.reptile.bean.db.RequestRecordGroupByProxy;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlMapper {

    @Insert({"insert into crawl_record(`html`, `timestamp`, `result`, `proxy`) "
        + "values(#{html}, #{timestamp}, #{result}, #{proxy})"})
    void insert(@Param("html") String html, @Param("timestamp") long timestamp, @Param("result") int result, @Param("proxy") String proxy);

    @Select("select count(*) from crawl_record;")
    Long count();

    @Select({"select * from crawl_record where result != 200 order by timestamp desc limit #{from}, #{offset};"})
    List<RequestRecord> queryError(@Param("from") Integer from, @Param("offset") Integer offset);

    @Select({"select * from crawl_record order by timestamp desc limit 1;"})
    RequestRecord queryLastSuccess();

    @Select({"select count(*) as count, proxy from crawl_record where result != 200 GROUP BY proxy ORDER BY count(*) desc;"})
    List<RequestRecordGroupByProxy> queryErrorGroupByProxy();
}
