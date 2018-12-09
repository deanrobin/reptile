package com.dean.reptile.db;

import com.dean.reptile.bean.Jewelry;
import com.dean.reptile.bean.TaskList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {

    @Insert({"insert into task_list(`page_number`, `from`, `ip`, `create_time`) "
            + "values(#{pageNumber}, #{from}, #{ip}, #{createTime})"})
    void insert(TaskList taskList);

    @Select("<script> "
        + "select * from task_list where 1=1 "
        + "<if test='pageNumber!=null'> and `page_number` = #{pageNumber} </if> "
        + "<if test='from!=null'> and `from` = #{from} </if> "
        + ";</script>")
    List<TaskList> selectByIndex(@Param("pageNumber") String pageNumber, @Param("from") String from);

    @Select("select * from task_list order by id asc")
    List<TaskList> selectAll();

    @Delete("delete from task_list where id = #{id}")
    int deteleById(@Param("id") int id);
}
