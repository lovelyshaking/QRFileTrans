package com.lovelyshaking.QRFilesTransfer.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lovelyshaking.QRFilesTransfer.services.FileDownloadService;

@Controller
@RequestMapping("/get")
public class DownloadFileController {

	@Autowired
	private FileDownloadService fileDownloadService;
	
	@RequestMapping(value = "/getQRCode",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[]  getQRCode(@RequestParam("filePath") String path) {
		System.out.println(path);
		File file = new File(path);
		byte[] res = null;
		InputStream inputStream = null;
		try {
			if (file.exists()) {
				//generate file download QRCode
				res = fileDownloadService.genFileQRCode(path);
				System.out.println(res.length);
			}
			else {
				ClassPathResource classPathResource = new ClassPathResource("static/no.jpg");
				inputStream = classPathResource.getInputStream();
				res = new byte[inputStream.available()];
				inputStream.read(res, 0, inputStream.available());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@RequestMapping(value = "/fileDownload")
	@ResponseBody
	public void getFile(@RequestParam("filePath") String filePath,HttpServletResponse response) {
		System.out.println(filePath);
		File file = new File(filePath);
		String name = file.getName();
		response.reset();
		response.setContentType("application/octet-stream");
		response.setCharacterEncoding("utf-8");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment;filename=" + name);
        byte[] buff = new byte[2048];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }	
	}
}
