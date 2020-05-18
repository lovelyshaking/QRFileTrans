package com.lovelyshaking.QRFilesTransfer.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lovelyshaking.QRFilesTransfer.Utils.ServerConfig;

@Service
public class FileDownloadService {
	@Autowired
	private ServerConfig serverConfig;
	public byte[] genFileQRCode(String filePath) {
		// TODO Auto-generated constructor stub
		System.out.println(filePath);
		String encodedFilePathString = URLEncoder.encode(filePath);
		String serverip = serverConfig.getUrl();

		String qRString = serverip + "/get/fileDownload?filePath="+encodedFilePathString;

		 Map<EncodeHintType, Object> hints = new HashMap<>();
         hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
         hints.put(EncodeHintType.MARGIN, 0);
         QRCodeWriter qrCodeWriter = new QRCodeWriter();
         byte[] imageByte =null;
         try {
			BitMatrix bm = qrCodeWriter.encode(qRString, BarcodeFormat.QR_CODE, 100, 100, hints);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(bm);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
	        //将图片写入输出流
	        ImageIO.write(image, "jpg", os);
	        //返回结果
	        imageByte = os.toByteArray();
		} catch (WriterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return imageByte;
	}
}
