package com.kaishengit.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

/**
 * @author liuyan
 * @date 2018/7/23
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @GetMapping("/upload")
    public String upload(){
        return "file/upload";
    }


    @PostMapping("/upload")
    public String upload(MultipartFile file, Model model, RedirectAttributes attributes){
        if (!file.isEmpty()){
            try {
                // 获得前端页面的name属性的值
                System.out.println(file.getName());
                // 上传文件的大小
                System.out.println(file.getSize());
                // MEMI类型
                System.out.println(file.getContentType());
                // 上传源文件的文件名
                System.out.println(file.getOriginalFilename());

                File fileTemp = new File("E:/temp/img/");
                if (!fileTemp.exists()){
                    fileTemp.mkdirs();
                }
                InputStream inputStream = file.getInputStream();
                OutputStream outputStream = new FileOutputStream(new File(fileTemp, file.getOriginalFilename()));

                IOUtils.copy(inputStream, outputStream);

                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //model.addAttribute("error", "上传文件不能为空");
            attributes.addFlashAttribute("error", "请选择文件进行上传");
        }
        //return "file/upload";
        return "redirect:/hello";
    }



}
