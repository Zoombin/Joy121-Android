package com.joy.Utils;

import gejw.android.quickandroid.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.joy.R;

public class UpdateManager {

	private Context mContext;
	
	// 获取服务器版本url
	private final String URL_UPDATE = "http://app.sunlighten.com/sunlight111.php";
	
	//返回的安装包url
	private String apkUrl = "http://app.sunlighten.com/Sunlighten.apk";
	
	private Dialog noticeDialog;
	
	private Dialog downloadDialog;
	 /* 下载包安装路径 */
    private static final String savePath = Environment.getExternalStorageDirectory().getPath() + "/121/update/";

    private static String saveFileName = "Sunlighten.apk";
    
    /* 进度条与通知ui刷新的handler和msg常量 */
    private ProgressBar mProgress;
    
    private static final int DOWN_UPDATE = 1;
    
    private static final int DOWN_OVER = 2;
    
    private static final int DOWN_ERROR = 3;
    
    private int progress;
    
    private Thread downLoadThread;
    
    private boolean interceptFlag = false;
    
    private Resources resource;
    
    private Handler mHandler = new Handler(){
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:
				downloadDialog.dismiss();
				installApk();
				break;
			case DOWN_ERROR:
				downloadDialog.dismiss();
			default:
				break;
			}
    	};
    };
    
	public UpdateManager(Context context) {
		this.mContext = context;
		resource = context.getResources();
	}
	
    public void checkUpdate() {
		downLoadThread = new Thread(mupcheckRunnable);
		downLoadThread.start();
    }
    
	private Runnable mupcheckRunnable = new Runnable() {
		@Override
		public void run() {
	    	HttpURLConnection l_connection = null;
			StringBuffer sTotalString = new StringBuffer();
			BufferedReader l_reader = null;
			try {
				URL l_url = new URL(URL_UPDATE);
				l_connection = (HttpURLConnection) l_url.openConnection();
				l_connection.connect();
				InputStream l_urlStream = l_connection.getInputStream();
				l_reader = new BufferedReader(new InputStreamReader(l_urlStream, "utf-8"), 8192);
				String sCurrentLine = "";
				while ((sCurrentLine = l_reader.readLine()) != null) {
					sTotalString.append(sCurrentLine);
				}
				String version = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
				if (sTotalString.equals("")) {
					handler.sendEmptyMessage(4);
					return;
				}
				String lastversion = sTotalString.toString();
				Message e = new Message();
				e.what = 2;
				e.obj = lastversion;
				handler.sendMessage(e);
				if (!lastversion.equals(version)) {
//					showNoticeDialog();
					handler.sendEmptyMessage(1);
					return;
				} else {
					return;
				}
			} catch (Exception ex1) {
				handler.sendEmptyMessage(3);
				ex1.printStackTrace();
				return;
			} finally {
				l_connection.disconnect();
			}
		}
	};

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				showNoticeDialog();
			} else if (msg.what == 3) {
				Toast.show(mContext, "连接超时！");
			} else if (msg.what == 4) {
				Toast.show(mContext, "该软件是最新版本！");
			}
		}
	};
	
	public void showNoticeDialog(){
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("提醒");
		builder.setMessage("是否下载新版本");
		builder.setPositiveButton("下载", new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showDownloadDialog();			
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();				
			}
		});
		noticeDialog = builder.create();
		noticeDialog.setCanceledOnTouchOutside(false);
		noticeDialog.show();
	}
	
	private void showDownloadDialog(){
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("正在下载。。。");
		
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.updateprogress, null);
		mProgress = (ProgressBar)v.findViewById(R.id.progress);
		
		builder.setView(v);
		builder.setNegativeButton("取消", new OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				interceptFlag = true;
			}
		});
		downloadDialog = builder.create();
		downloadDialog.setCanceledOnTouchOutside(false);
		downloadDialog.show();
		
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}
	
	private Runnable mdownApkRunnable = new Runnable() {	
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);

				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();
				
				File file = new File(savePath);
				if(!file.exists()){
					file.mkdirs();
				}
				File ApkFile = new File(savePath + saveFileName);
				FileOutputStream fos = new FileOutputStream(ApkFile);
				
				int count = 0;
				byte buf[] = new byte[1024];
				
				do{   		   		
		    		int numread = is.read(buf);
		    		count += numread;
		    	    progress =(int)(((float)count / length) * 100);
		    	    //更新进度
		    	    mHandler.sendEmptyMessage(DOWN_UPDATE);
		    		if(numread <= 0){	
		    			//下载完成通知安装
		    			mHandler.sendEmptyMessage(DOWN_OVER);
		    			break;
		    		}
		    		fos.write(buf,0,numread);
		    	}while(!interceptFlag);//点击取消就停止下载.
				
				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				mHandler.sendEmptyMessage(DOWN_ERROR);
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
				mHandler.sendEmptyMessage(DOWN_ERROR);
			}
		}
	};
	
	/**
     * 安装apk
     * @param url
     */
	private void installApk(){
		File apkfile = new File(savePath + saveFileName);
        if (!apkfile.exists()) {
            return;
        }    
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.fromFile(new File(savePath, saveFileName)), "application/vnd.android.package-archive"); 
        mContext.startActivity(i);
	}
}