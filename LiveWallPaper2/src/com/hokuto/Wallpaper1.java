package com.hokuto;

import java.util.ArrayList;
import java.util.Collections;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

public class Wallpaper1 extends WallpaperService {

	private final Handler mHandler = new Handler();

	@Override
	public Engine onCreateEngine() {
		// TODO Auto-generated method stub
		return new LiveWallpaperEngine();
	}

	public class LiveWallpaperEngine extends Engine {
		private static final String RSS_FEED_URL = "http://business.nikkeibp.co.jp/rss/all_nbo.rdf";
		private Bitmap bg;
		private float x;
		private boolean mVisible;
		private ArrayList<Access> list = new ArrayList<Access>();

		public LiveWallpaperEngine() {
			bg = BitmapFactory.decodeResource(getResources(), R.drawable.aa);
			// TODO Auto-generated constructor stub

		}

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			// TODO Auto-generated method stub
			super.onCreate(surfaceHolder);
			String[] strs = new String[5];
			
			strs[0] = RSS_FEED_URL;
			strs[1] = "http://kyoko-np.net/index.xml";
			strs[2] = "http://allabout.co.jp/rss/entertainment/index.rdf";
			strs[3] = "http://www.barks.jp/RSS/barks_news_jpop.rdf";
			strs[4] = "http://allabout.co.jp/rss/ranking/index.rdf";
			
			for(String x : strs){
			Access ac = new Access(x);
			ac.execute();
			list.add(ac);
			}

		}

		private final Runnable runLiveWallPaper = new Runnable() {
			public void run() {
				drawFrame();
			}
		};

		@Override
		public void onVisibilityChanged(boolean visible) {
			// TODO Auto-generated method stub
			if (visible) {
				mVisible = visible;
				drawFrame();
			} else {
				mHandler.removeCallbacks(runLiveWallPaper);
			}
		}

		void drawFrame() {
			final SurfaceHolder holder = getSurfaceHolder();

			Canvas c = null;

			try {
				c = holder.lockCanvas();
				if (c != null) {
					Matrix matrix = new Matrix();
					matrix.postScale(1, 1);
					matrix.setTranslate(x, 0);

					Paint paint = new Paint();

					c.drawBitmap(bg, matrix, paint);
					c.drawColor(Color.LTGRAY);

					for (Access x : list) {
						x.setGraphic(c, paint);
					}
					
					list.get(1).setColor(Color.RED);
					list.get(3).setColor(Color.GREEN);
					
					int i = 0;
					for (Access x : list) {
						x.draw(50*i);
						i++;
					}
				}
			} finally {
				if (c != null)
					holder.unlockCanvasAndPost(c);
			}

			mHandler.removeCallbacks(runLiveWallPaper);
			if (mVisible) {
				mHandler.postDelayed(runLiveWallPaper, 1000 / 10);
				
			}

		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset,
				float xOffsetStep, float yOffsetStep, int xPixelOffset,
				int yPixelOffset) {
			// TODO Auto-generated method stub
			x = xPixelOffset;
			drawFrame();
		}

	}

}
