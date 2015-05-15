package com.joy.Utils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gejw.android.quickandroid.QActivity;
import gejw.android.quickandroid.widget.Toast;

import com.joy.Dialog.BindMobileDialog;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.ResetPasswordDialog;
import com.joy.Dialog.DialogUtil.MyDialogButtonClickCallback;
import com.joy.Fragment.PersonalFragment;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.BindMobileEntity;
import com.joy.json.model.UserEntity;
import com.joy.json.model.UserInfoEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.BindMobileOp;
import com.joy.json.operation.impl.ForgetConfirmPwdOp;
import com.joy.json.operation.impl.ForgetGetCodeOp;
import com.joy.json.operation.impl.GetCodeOp;
import com.joy.json.operation.impl.UserinfoOp;
import com.umeng.analytics.MobclickAgent;

import com.joy.R;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MobileCodeManager extends QActivity{
	/**
	 * @author rainbow   
	 */
	private Context mContext;
	private QActivity mActivity;
	private DialogUtil dialogUtil;
	private PersonalFragment personalFragment;
	private Resources resources;
	UserInfoEntity userinfo;
	EditText et_nloginpwd,et_securitycode,et_comfirmnewpwd;
	private BindMobileDialog bindmobiledialog;
	private ResetPasswordDialog resetdialog;//声明变量
	
	
	public MobileCodeManager(Context context, QActivity mActivity) {
		this.mContext = context;
		this.mActivity = mActivity;
		dialogUtil = new DialogUtil(mContext);
	}
	
	
	public MobileCodeManager(Context context, QActivity mActivity, PersonalFragment personalFragment) {
		this.mContext = context;
		this.mActivity = mActivity;
		this.personalFragment = personalFragment;
		dialogUtil = new DialogUtil(mContext);
	}
	/**
	 * 忘记密码
	 */
	public void forgetPwd()
	{
		resetdialog=new ResetPasswordDialog(mActivity,R.style.dialog);
		resetdialog.show();
		resetdialog.setTitle("提示");
		resetdialog.setButtonYes("确定");
		resetdialog.setButtonNo("取消");
		resetdialog.setBtnGetCode("获取验证码");
		resetdialog.setONClickButton(new MyDialogButtonClickCallback() {
			@Override
			public void positiveButtonClick() {}
			@Override
			public void negativeButtonClick(){}
			//得到验证码
			@Override
			public void getMycodeButtonClick(String loginname) {
				forgetGetCode(loginname);
			}
			@Override
			public void getBindMobileDialogButtonClickCallback(String MobileCode) {}
			//忘记密码中确认提交
			@Override
			public void forgetPwdDialogButtonClickCallback(String forgetLoignname,String nLoginPwd,
					String confirmCode,String comfirmnewpwd) {
				if(TextUtils.isEmpty(confirmCode))
				{
					Toast.show(mActivity,"验证码不能为空");
					return;
				}else if(TextUtils.isEmpty(nLoginPwd)||TextUtils.isEmpty(comfirmnewpwd)){
					Toast.show(mActivity,"密码不能为空");
					return;
				}else if(!nLoginPwd.equals(comfirmnewpwd)){	
					Toast.show(mActivity, "两次密码不一致");
					return;
				}else{
					forgetConfirmCode(forgetLoignname,nLoginPwd,confirmCode);
					
				}
			}
		});
	}
	/**
	 * 忘记密码中     得到验证码
	 */
	public void forgetGetCode(String loginname)
	{
		if(TextUtils.isEmpty(loginname))
		{
			Toast.show(mActivity,"请输入用户名！");
			return;
		}
		BindMobileEntity entity=new BindMobileEntity();
	    entity.setLoginName(loginname);
		OperationBuilder builder = new OperationBuilder().append(
	    	new ForgetGetCodeOp(), entity);
		Log.d("test",loginname);
		 OnOperationListener listener = new OnOperationListener() {
		 @Override
		 public void onOperationFinished(List<Object> resList) {
			if (mActivity.isFinishing()) {
				return;
			}
			if(resList==null){
				Toast.show(mActivity,"连接超时");
				return;
			}
			BindMobileEntity entity=(BindMobileEntity) resList.get(0);
			String retobj=entity.getRetobj();
			String msg=entity.getMsg();
			int flag=entity.getFlag();
			if(flag!=1)
			{
				//msg=msg.replaceAll("[^\\u4e00-\\u9fa5]", "");//错误信息只显示中文信息
				Toast.show(mActivity, "登录名不存在！");
				return;
			}else
			{
				Toast.show(mActivity, retobj);
				if(retobj.equals("成功发送验证码!"))
				{
					resetdialog.downTimer();
				}
				return;
			}
		}
		@Override
		public void onOperationError(Exception e) {
			e.printStackTrace();
		}
	};
	JsonCommon task = new JsonCommon(mActivity, builder, listener,
			JsonCommon.PROGRESSCOMMIT);
	task.execute(); 	  
}
	
	/**
	 * 忘记密码中    提交验证码
	 */
	 public void forgetConfirmCode(String forgetLoginname,String nloginpwd,String securitycode)
	    {
	    	
	    	BindMobileEntity entity=new BindMobileEntity();
	    	entity.setLoginName(forgetLoginname);
	    	entity.setNewPwd(nloginpwd);
	    	entity.setsecuritycode(securitycode);
	    	OperationBuilder builder = new OperationBuilder().append(
					new ForgetConfirmPwdOp(), entity);
	    	OnOperationListener listener = new OnOperationListener() {

				@Override
				public void onOperationFinished(List<Object> resList) {
					if(mActivity.isFinishing()){
						return;
					}
					if(resList==null){
						Toast.show(mActivity,"连接超时");
						return;
					}
					BindMobileEntity entity=(BindMobileEntity) resList.get(0);  
					String retobj=entity.getRetobj();
					Toast.show(mActivity, retobj);
					if(retobj.equals("修改密码成功!"))
					{
						resetdialog.dismiss();
					}
				}
				@Override
				public void onOperationError(Exception e) {
					e.printStackTrace();
				}
				
	    	};
	    	JsonCommon task = new JsonCommon(self, builder, listener,
					JsonCommon.PROGRESSCOMMIT);
			task.execute();
			
		}
	/**
	 * 绑定手机号
	 * 
	 */
	 
	public void bindMobile()
	{
		//DialogUtil dUti = new DialogUtil(mActivity);
		
		    bindmobiledialog=new BindMobileDialog(mActivity,R.style.dialog);
		    bindmobiledialog.show();
		    bindmobiledialog.setTitle("提示");
		    bindmobiledialog.setButtonYes("确定");
		    bindmobiledialog.setButtonNo("取消");
		    bindmobiledialog.setBtnGetCode("获取验证码");
		    bindmobiledialog.setONClickButton(new MyDialogButtonClickCallback() {
				@Override//得到验证码
				public void getMycodeButtonClick(String bindMobile) {
					 getCode(bindMobile);
				}
				@Override
				public void positiveButtonClick() {}
				@Override
				public void negativeButtonClick() {}
				@Override//提交验证码，绑定手机
				public void getBindMobileDialogButtonClickCallback(String MobileCode) {
					confirmCode(MobileCode);
				}
				@Override
				public void forgetPwdDialogButtonClickCallback(String forgetLoginnae,String nLoginPwd,
						String confirmCode,String comfirmnewpwd) {}
			});   
		
		/*dUti.bindMobileShowDialog("提示","确定","取消","获取验证码",new MyDialogButtonClickCallback() {
			@Override//得到验证码
			public void getMycodeButtonClick(String bindMobile) {
				 getCode(bindMobile);
			}
			@Override
			public void positiveButtonClick() {}
			@Override
			public void negativeButtonClick() {}
			@Override//提交验证码，绑定手机
			public void getBindMobileDialogButtonClickCallback(String MobileCode) {
				confirmCode(MobileCode);
			}
			@Override
			public void forgetPwdDialogButtonClickCallback(String forgetLoginnae,String nLoginPwd,
					String confirmCode,String comfirmnewpwd) {}
		});*/
	}
	/**
	 * 手机号的形式判断
	 * 
	 */
	public boolean isMobile(String mobile)
	{
		Pattern p=Pattern.compile("^(13[0-9]|15[01]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$");//正则表达式验证手机的正确性
		Matcher m=p.matcher(mobile);
		return m.matches();
	}
	/**
	 *绑定手机中        得到验证码
	 * 
	 */
	public void getCode(String mobile)
	{ 
		if(TextUtils.isEmpty(mobile))
		{
			Toast.show(mActivity, "请输入手机号");
			return;
		}else if(isMobile(mobile)){
			BindMobileEntity entity=new BindMobileEntity();
			entity.setBindMobile(mobile);
			OperationBuilder builder = new OperationBuilder().append(
			   	new BindMobileOp(), entity);
			 OnOperationListener listener = new OnOperationListener() {
			 @Override
			 public void onOperationFinished(List<Object> resList) {
				if (mActivity.isFinishing()) {
					return;
				}
				if(resList==null){
					Toast.show(mActivity,"连接超时");
					return;
				}
				BindMobileEntity entity=(BindMobileEntity) resList.get(0);
				String retobj=entity.getRetobj();
				String msg=entity.getMsg();
				int flag=entity.getFlag();
				if(flag!=1)
				{
					Toast.show(mActivity, msg);
					return;
				}else
				{
					Toast.show(mActivity, retobj);
			    	if(retobj.equals("成功发送验证码!"))
				{
							bindmobiledialog.downTimer();
				}
					return;
				}
			} 
				@Override
				public void onOperationError(Exception e) {
					e.printStackTrace();
				}
			};
			JsonCommon task = new JsonCommon(mActivity, builder, listener,
					JsonCommon.PROGRESSCOMMIT);
			task.execute();
			
		}else{
			Toast.show(mActivity, "手机号码格式不正确");
		}
	}
   /**
    * 绑定手机中       提交验证码
    *
    */
    public void confirmCode(String securitycode)
    {
    	if(TextUtils.isEmpty(securitycode)){
    		Toast.show(mActivity, "验证码不能为空");
    		return;
    	}
    	BindMobileEntity entity=new BindMobileEntity();
    	entity.setsecuritycode(securitycode);
    	OperationBuilder builder = new OperationBuilder().append(
				new GetCodeOp(), entity);
    	OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if(mActivity.isFinishing()){
					return;
				}
				if(resList==null){
					Toast.show(mActivity,"连接超时");
					return;
				}
				BindMobileEntity entity=(BindMobileEntity) resList.get(0);
				String retobj=entity.getRetobj(); 
				Toast.show(mActivity, retobj);
				if(retobj.equals("绑定成功!")){
					bindmobiledialog.dismiss();
					personalFragment.setBindMobileString("已绑定");
					personalFragment.initData();
				}
				
			}
			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
    	};
    	
    	JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSCOMMIT);
		task.execute();
	}
}