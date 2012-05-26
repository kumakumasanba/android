package com.hokuto;

import java.util.ArrayList;
import java.util.Collections;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Access {
	private String url = "";
	private Canvas c;
	private Paint p;
	private int m = -50;

	public void setColor(int color) {
		this.color = color;
	}

	private int color=Color.BLACK;

	private ArrayList<Item> mItems = new ArrayList<Item>();
	private int currentMessage = 0;

	public Access(String url) {
		this.url = url;
	}
	
	public Access(String url, int color) {
		this.color = color;
	}

	public void setmItems(ArrayList<Item> mItems) {
		this.mItems = mItems;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Canvas getC() {
		return c;
	}

	public void setC(Canvas c) {
		this.c = c;
	}

	public Paint getP() {
		return p;
	}

	public void setP(Paint p) {
		this.p = p;
	}

	public void setGraphic(Canvas c, Paint p) {
		this.c = c;
		this.p = p;
	}

	public void execute() {
		RssParserTask task = new RssParserTask(this, mItems);
		task.execute(url);

	}
	
	public void shufful() {
		Collections.shuffle(mItems);
	}

	public void draw(int translation) {
		p.setTextSize(20.0f);
		p.setAntiAlias(true);
		p.setColor(color);

		if (mItems.size() != 0) {
			c.drawText((String) mItems.get(currentMessage).getTitle(),
					300 - 20 * m, 400 + translation, p);
		} else {
			c.drawText("“Ç‚Ýž‚Ý’†", 300 - 20 * m, 400 + translation, p);
		}

		m++;
		// ˆêŒÂI‚í‚é
		if (m > 100) {
			m = -20;
			currentMessage++;
			if (currentMessage + 1 > mItems.size()) {
				currentMessage = 0;
			}
		}

	}
}
