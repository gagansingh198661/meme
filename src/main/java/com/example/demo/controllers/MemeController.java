package com.example.demo.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dtos.MemeDTO;
import com.example.demo.service.CreateMemeService;

@Controller
public class MemeController {
	
	@Autowired
	private CreateMemeService memeService;
	
	@ResponseBody
	@RequestMapping(value="/makememe")
	public void makeMeme(@RequestBody MemeDTO dto) {
		
		String url=memeService.createMeme(dto);
		System.out.println(url);
	}
	
	   @GetMapping("/download3")
	   public void downloadFile3(HttpServletResponse resonse) throws IOException {
		File file = new File("static\\tmp\\test.jpg");

	      resonse.setContentType("image/jpg");
	      resonse.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
	      BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
	      BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
	      
	      byte[] buffer = new byte[1024];
	      int bytesRead = 0;
	      while ((bytesRead = inStrem.read(buffer)) != -1) {
	        outStream.write(buffer, 0, bytesRead);
	      }
	      outStream.flush();
	      inStrem.close();
	   }
}
