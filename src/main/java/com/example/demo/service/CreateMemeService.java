package com.example.demo.service;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.demo.dtos.Meme;
import com.example.demo.dtos.MemeDTO;
import com.example.demo.repository.DbRepository;

@Service
public class CreateMemeService {
	
	private static String fileName = "test.jpg";
	
	@Autowired
	private ResourceLoader loader;
	
	@Autowired
	private DbRepository repository;
	
	public boolean createMeme(MemeDTO dto) {
		boolean flag=true;
		BufferedImage scaleimg = scaleImage(dto.getImageName());
		scaleimg=addTextToImage(scaleimg, dto.getMemeText());
		Meme meme= new Meme();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write( scaleimg, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			meme.setData(imageInByte);
			repository.save(meme);
		} catch (IOException e1) {
			flag=false;
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		/*String fileLocation = new File("static\\tmp\\").getAbsolutePath() ;
		
		try {
			Resource resource = loader.getResource("classpath:static/tmp/"+fileName);
			fileLocation=resource.getURL().getFile();
			ImageIO.write(scaleimg, "jpg", new File(fileLocation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return flag;

	}

	private BufferedImage scaleImage(String imageName) {
		BufferedImage scaleimg=null;
		BufferedImage resized = null;
		try {
			
			Resource resource = loader.getResource("classpath:static/"+imageName);
			InputStream inputStream = resource.getInputStream();
			File temporaryFile = File.createTempFile("temporary", "temp");
			temporaryFile.deleteOnExit();
			FileOutputStream out = new FileOutputStream(temporaryFile);
			IOUtils.copy(inputStream, out);
			scaleimg  = ImageIO.read(temporaryFile);
		    int dWidth =  (int) (scaleimg.getWidth()*(0.75)) ; 
		    int dHeight = (int) (scaleimg.getHeight()*(0.75));
		    resized = new BufferedImage(dWidth, dHeight, scaleimg.getType());
		    Graphics2D graphics2D = resized.createGraphics();
            graphics2D.drawImage(scaleimg, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		return resized;

	}
	
	private BufferedImage addTextToImage(BufferedImage img ,String text) {
		Graphics2D grphcs = img.createGraphics();
		grphcs.setFont(grphcs.getFont().deriveFont(20f));
		grphcs.drawString(text, 170, 30);
		grphcs.dispose();
		return img;
	}
}
