package wechatOrder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * @author JJ
 * @date 2019/11/25 - 20:57
 */
@Controller
public class ImgUploadController {

    @Value("#{prop.imgPath}")
    private String img_path;

    @RequestMapping(value = "/imgUpload",method = RequestMethod.POST)
    @ResponseBody
    public Map imgUpload(MultipartFile img) {
        String newFileName = "";
        String path = "";
        Map map = new HashMap();

        //获得图片原始名称
        String originalFilename = img.getOriginalFilename();
        //上传图片
        if (img != null && originalFilename != null && originalFilename.length() > 0) {

            //新的图片名称
            newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //新图片全路径
            path=img_path + newFileName;
            //新图片
            File newFile = new File(path);
            //将内存中的数据写入磁盘
            try {
                img.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
                map.put("msg","添加失败");
            }

        }

        map.put("url", path);
        map.put("msg", "添加成功");
        return map;
    }
}
