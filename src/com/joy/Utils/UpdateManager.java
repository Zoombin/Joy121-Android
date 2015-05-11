package com.joy.Utils;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.joy.R;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.UpdateEntity;
import com.joy.json.model.UpdateEntity.Version;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.UpdateOp;

public class UpdateManager {

	private Context mContext;
	private QActivity mActivity;

	//返回的安装包url
	private String apkUrl = "http://cloud.joy121.com/app/Joy.apk";
	
	private Dialog noticeDialog;
	
	private Dialog downloadDialog;
	
	private DialogUtil dialogUtil;
	 /* 下载包安装路径 */
    private static final String savePath = Environment.getExternalStorageDirectory().getPath() + "/joy/update/";

    private static String saveFileName = "joy.apk";
    
    /* 进度条与通知ui刷新的handler和msg常量 */
    private ProgressBar mProgress;
    
    private static final int DOWN_UPDATE = 1;
    
    private static final int DOWN_OVER = 2;
    
    private static final int DOWN_ERROR = 3;
    
    private int progress;
    
    private Thread downLoadThread;
    
    private boolean interceptFlag = false;
    
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
    
	public UpdateManager(Context context, QActivity mActivity) {
		this.mContext = context;
		this.mActivity = mActivity;
		dialogUtil = new DialogUtil(mContext);
	}
	
	public void checkUpdate(final String checkLocation) {
		String currentVersion = "";
		try {
			currentVersion = mContext.getPackageManager().getPackageInfo(
					mContext.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		final String temp = currentVersion;
		OperationBuilder builder = new OperationBuilder().append(
				new UpdateOp(), currentVersion);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(mActivity, "连接超时");
					return;
				}
				UpdateEntity entity = (UpdateEntity) resList.get(0);
				Version latestVersion = entity.getRetobj();
				if (latestVersion.getAppversion() != null && !latestVersion.getAppversion().equals(temp)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dialogUtil.showDownloadDialog("提示", "<font color=#4ba2f2>最新版本：</font>" + latestVersion.getAppversion()
							+ "<br><font color=#4ba2f2>版本大小：</font>" + latestVersion.getVersionSize()
							+ "<br><font color=#4ba2f2>发布日期：</font>" + sdf.format(new Date(Long.parseLong(latestVersion.getCreateTime().substring(6, 19))))
							+ "<br>" + latestVersion.getDescription()
							, "现在升级", "稍后升级",latestVersion.getForceUpdate(), new DialogButtonClickCallback() {
						@Override
						public void positiveButtonClick() {
							showDownloadDialog();
						}
						@Override
						public void negativeButtonClick() {
						}
					});
				} else {
					if (checkLocation != null && "personal".equalsIgnoreCase(checkLocation)) {
						Toast.show(mActivity, "软件是最新版本！");
						
					}
				}
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(mActivity, builder, listener, null);
		task.execute();
	}

	
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
	
	public void showDownloadDialog(){
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