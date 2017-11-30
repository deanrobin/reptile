package com.dean.reptile.db;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Service
public interface JobSearchMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into job_search(id, url, source, resultCode) values(#{id}, #{url}, 'nga',#{resultCode})")
    int insert( @Param(value="id") int id,
                @Param(value="url") String url,
                @Param(value="resultCode") int resultCode);

    @Delete("delete from job_search")
    int delete();

    @Select("select max(id) from job_search")
    int selectMaxId();
}
