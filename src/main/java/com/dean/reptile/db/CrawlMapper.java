package com.dean.reptile.db;

import com.dean.reptile.bean.CrawlRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlMapper {

    @Insert({"insert into crawl_record(`html`, `timestamp`, `result`) "
        + "values(#{html}, #{timestamp}, #{result})"})
    void insert(@Param("html") String html, @Param("timestamp") long timestamp, @Param("result") int result);
}
