package com.noasking.gateway.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MaJing on 2016/12/30.
 */
@RestController
@RequestMapping({"/data"})
public class DataController {

    private static final Log logger = LogFactory.getLog(DataController.class);

    @RequestMapping({"getstring"})
    @ResponseBody
    public String getstring(HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        logger.debug("getString comntroller");
        return "aaa";
    }

}
