package com.joy.Widget;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class PagerviewAdapter  extends PagerAdapter {

	private Context context;
	private List<String> urls;
	
	public PagerviewAdapter() {
		
	}
	
	public PagerviewAdapter(Context context, List<String> urls) {
		this.context = context;
		this.urls = urls;
	}

	@Override
	public int getCount() {
		return urls.size();
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		ImageView photoView = new ImageView(context);
		try {
			ImageLoader.getInstance().displayImage(urls.get(position), photoView);
			// Now just add PhotoView to ViewPager and return it
			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}
