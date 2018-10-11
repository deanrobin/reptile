package com.dean.reptile.db;


import com.dean.reptile.bean.JobRecord;
import org.apache.ibatis.annotations.Insert;


public interface JobRecordMapper {

    @Insert("INSERT INTO job_record(timestamp, type, user, status) VALUES(#{timestamp}, #{type}, #{user}, #{status})")
    int insert(JobRecord jobRecord);
}
