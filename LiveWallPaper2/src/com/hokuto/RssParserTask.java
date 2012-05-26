package com.hokuto;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

public class RssParserTask extends AsyncTask<String, Integer, ArrayList<Item>> {
	private Access ac;
	private ArrayList<Item> list = new ArrayList<Item>();
	private String u;

	@Override
	protected void onPostExecute(ArrayList<Item> result) {
		// TODO Auto-generated method stub
		ac.setmItems(result);
	}

	public RssParserTask(Access ac, ArrayList<Item> list) {
		this.ac = ac;
		this.list = list;
	}

	@Override
	protected ArrayList<Item> doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		ArrayList<Item> result = new ArrayList<Item>();
		try {
			// HTTP経由でアクセスし、InputStreamを取得する
			URL url = new URL(arg0[0]);
			u=arg0[0];
			InputStream is = url.openConnection().getInputStream();
			result = parseXml(is);
		} catch (Exception e) {
			Log.d("error", e.toString());
		}
		// ここで返した値は、onPostExecuteメソッドの引数として渡される
		return result;
	}

	// XMLをパースする
	public ArrayList<Item> parseXml(InputStream is) throws IOException,
			XmlPullParserException {
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, null);
			int eventType = parser.getEventType();
			Item currentItem = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tag = null;
				switch (eventType) {
				case XmlPullParser.START_TAG:
					tag = parser.getName();
					if (tag.equals("item")) {
						currentItem = new Item();
					} else if (currentItem != null) {
						if (tag.equals("title")) {
							currentItem.setTitle(parser.nextText());
						} 
//						else if (tag.equals("description")) {
//							currentItem.setDescription(parser.nextText());
//						}
					}
					break;
				case XmlPullParser.END_TAG:
					tag = parser.getName();
					if (tag.equals("item")) {
						currentItem.setUrl(u);
						list.add(currentItem);
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
