package com.example.demo.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dtos.MemeDTO;
import com.example.demo.service.CreateMemeService;
import com.example.demo.service.DBStorageService;

@Controller
public class MemeController {
	
	@Autowired
	private CreateMemeService memeService;
	
	
	@Autowired
	private DBStorageService storageService;
	
	@ResponseBody
	@RequestMapping(value="/makememe")
	public void makeMeme(@RequestBody MemeDTO dto) {
		System.out.println(memeService.createMeme(dto));
	}
	
	   @GetMapping("/download3")
	   public void downloadFile3(HttpServletResponse resonse) throws IOException {

	      resonse.setContentType("image/jpg");
	      resonse.setHeader("Content-Disposition", "attachment;filename=" + "test.jpg");
	      InputStream targetStream = new ByteArrayInputStream(storageService.getMeme().getData());
	      BufferedInputStream inStrem = new BufferedInputStream(targetStream);
	      BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
	      
	      byte[] buffer = new byte[1024];
	      int bytesRead = 0;
	      while ((bytesRead = inStrem.read(buffer)) != -1) {
	        outStream.write(buffer, 0, bytesRead);
	      }
	      outStream.flush();
	      inStrem.close();
	      storageService.delete();
	   }
}
