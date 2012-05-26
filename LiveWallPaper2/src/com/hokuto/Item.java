package com.hokuto;

public class Item {
	// 記事のタイトル
	private CharSequence mTitle;
	// 記事の本文
	private CharSequence mDescription;
	
	private String url="";

	public Item() {
		mTitle = "";
		mDescription = "";
	}

	public CharSequence getDescription() {
		return mDescription;
	}

	public void setDescription(CharSequence description) {
		mDescription = description;
	}

	public CharSequence getTitle() {
		return mTitle;
	}

	public void setTitle(CharSequence title) {
		mTitle = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
