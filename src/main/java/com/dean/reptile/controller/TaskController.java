package com.dean.reptile.controller;

import java.util.List;

import com.dean.reptile.bean.TaskList;
import com.dean.reptile.bean.response.ResponseBean;
import com.dean.reptile.constant.ResponseStatus;
import com.dean.reptile.service.impl.TaskService;
import com.dean.reptile.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "task")
@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean insertTask(@RequestParam(value = "page", required = false) String page,
                                   @RequestParam(value = "from", required = false) String from,
                                   HttpServletRequest httpRequest) {
        String IP = IPUtil.getIpAddr(httpRequest);

        boolean res = taskService.insertTask(page, from, IP);
        if (res) {
            return ResponseBean.getSuccess();
        } else {
            ResponseBean responseBean = new ResponseBean();
            responseBean.setStatus(ResponseStatus.OBJECT_IS_EXIST.getstatus());
            responseBean.setMessage("is exist!");
            return responseBean;
        }

    }


    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskList> select(@RequestParam(value = "page", required = false) String page,
                 @RequestParam(value = "from", required = false) String from) {

        return taskService.query(page, from);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(@RequestParam(value = "page", required = true) Integer id) {

        return taskService.delete(id);
    }
}
