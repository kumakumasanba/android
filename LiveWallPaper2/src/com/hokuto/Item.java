package com.hokuto;

public class Item {
	// �L���̃^�C�g��
	private CharSequence mTitle;
	// �L���̖{��
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
