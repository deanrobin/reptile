package com.dean.reptile.service.impl;

import com.dean.reptile.bean.TaskList;
import com.dean.reptile.db.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    @Autowired
    TaskMapper taskMapper;

    public boolean insertTask(String pageNumber, String from, String IP) {
        TaskList taskList = new TaskList();
        taskList.setIp(IP);
        taskList.setPageNumber(pageNumber);
        taskList.setFrom(from);
        taskList.setCreateTime(System.currentTimeMillis());

        taskMapper.insert(taskList);
        return true;
    }
}
