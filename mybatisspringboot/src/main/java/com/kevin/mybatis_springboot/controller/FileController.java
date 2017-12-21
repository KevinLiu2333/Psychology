package com.kevin.mybatis_springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/9/27
 * Time: 14:31
 */
@RestController
@RequestMapping("upload")
public class FileController {

    @RequestMapping
    public String test(@RequestParam("file")MultipartFile file,HttpServletRequest request) {
        System.out.println(request.getParameter("title"));
        return "success";
    }
}
