package com.example.demo.dtos;

public class MemeDTO {
	private String memeText;
	public String getMemeText() {
		return memeText;
	}
	public void setMemeText(String memeText) {
		this.memeText = memeText;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	private String imageName;
}
