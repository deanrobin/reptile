package com.dean.reptile.service.impl;

import java.util.List;

import com.dean.reptile.bean.TaskList;
import com.dean.reptile.db.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    @Autowired
    TaskMapper taskMapper;

    public boolean insertTask(String pageNumber, String from, String IP) {
        List<TaskList> list = query(pageNumber, from);
        if (list != null && list.size() !=0) {
            return false;
        }

        TaskList taskList = new TaskList();
        taskList.setIp(IP);
        taskList.setPageNumber(pageNumber);
        taskList.setFrom(from);
        taskList.setCreateTime(System.currentTimeMillis());

        taskMapper.insert(taskList);
        return true;
    }

    public List<TaskList> query(String pageNumber, String from) {
        return taskMapper.selectByIndex(pageNumber, from);
    }

    public boolean delete(Integer id) {
        return taskMapper.deteleById(id) > 0;
    }

}
