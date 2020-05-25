package com.lovelyshaking.QRFilesTransfer.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lovelyshaking.QRFilesTransfer.services.FileUploadService;
import com.lovelyshaking.QRFilesTransfer.services.StorageService;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
	
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private StorageService storageService;
	
	@RequestMapping(value = "/getUploadQRCode",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getUploadQRCode() {
		byte[] res = fileUploadService.genUploadQRCode();
		return  res;
	}
	
	@PostMapping("/uploadFile")
	@ResponseBody
	public String handleFileUpload(@RequestParam("fileupload") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		try {
			storageService.store(file);
			return "File "+file.getOriginalFilename()+" Upload Success!";
		} catch (Exception e) {
			// TODO: handle exception
			return "File "+file.getOriginalFilename()+" Upload Failed!";
		}
		
	}
}
