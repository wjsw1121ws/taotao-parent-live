package com.wcc.taotao.manager.controller;

import com.wcc.taotao.common.utils.FastDFSClient;
import com.wcc.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 图片上传
 * @ClassName: PictureUploadController
 * @Auther: changchun_wu
 * @Date: 2019/3/16 1:19
 * @Version: 1.0
 **/

@Controller
public class PictureUploadController {
    @Value("${IMAGE_SERVER_LOCATION}")
    private String IMAGE_SERVER_LOCATION;
    /**
     * @Author: changchun_wu
     * @Date: 2018/12/27 22:19
     * @Description: 实现图片上传
     * 请求路径: /pic/upload
     * 请求参数: uploadFile
     * 返回值类型Map
     * 返回格式(JSON)
     * //成功时
     * {
     *         "error" : 0,
     *         "url" : "http://www.example.com/path/to/file.ext"
     * }
     * //失败时
     * {
     *         "error" : 1,
     *         "message" : "错误信息"
     * }
     **/
    @RequestMapping(value = "/pic/upload",method = RequestMethod.POST,
    produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        try {
            //1.设置
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fast_dfs.conf");
            //获取文件的全路径以及文件名
            byte[] fileContent = uploadFile.getBytes();
            //获取文件名
            String originalFilename = uploadFile.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".") + 1;
            //得到文件扩展名
            String extend = originalFilename.substring(i);
            String url = fastDFSClient.uploadFile(fileContent, extend);
            //完整的url
            url = IMAGE_SERVER_LOCATION+url;
            //封装到map中返回
            Map map = new HashMap<>();
            map.put("error",0);
            map.put("url",url);
            String json = JsonUtils.objectToJson(map);
            return json;
        } catch (Exception e) {
            Map map = new HashMap();
            map.put("error",1);
            map.put("message","上传失败");
            String json = JsonUtils.objectToJson(map);
            return json;
        }
    }
}
