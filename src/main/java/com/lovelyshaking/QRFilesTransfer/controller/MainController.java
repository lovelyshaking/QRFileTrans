package com.lovelyshaking.QRFilesTransfer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String getMainPage(Model model) {
		return "index";
	}
	@RequestMapping("/fileUpload")
	public String getUploadPage(Model model) {
		return "fileupload";
	}
}
