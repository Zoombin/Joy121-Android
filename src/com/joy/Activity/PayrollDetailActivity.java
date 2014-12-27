package com.joy.Activity;

import gejw.android.quickandroid.widget.Toast;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.R;
import com.joy.Utils.Constants;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.Payroll;
import com.joy.json.model.PayrollEntity;
import com.joy.json.model.PayrollListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.PayrollDetailOp;
import com.joy.json.operation.impl.PayrollOp;

/******
 * 通讯录详细页面
 * 
 * @author Ryan Zhou 2014-11-26
 * 
 */
public class PayrollDetailActivity extends BaseActivity implements OnClickListener {
	public static final String PayrollDetails = "payrolldetails";
	public static final String Period = "Period";
	private Resources resources;
	private String period;
	private PayrollEntity entity;
	private RelativeLayout layout_title;
	private LinearLayout layout_realwages, realwages_info, layout_basepay, layout_meritpay, layout_secret, layout_positionsalary
		, layout_overtimesalary, layout_subsidy, layout_onechildfee, layout_bonus, layout_annualbonus, layout_leavededuction, layout_endowmentinsurance
		, layout_endowmentinsuranceretroactive, layout_hospitalizationinsurance, layout_hospitalizationinsuranceretroactive, layout_unemploymentinsurance
		, layout_unemploymentinsuranceretroactive, layout_reservefund, layout_reservefundretroactive, layout_salarytax, layout_pretaxwages, layout_incometax
		, layout_others, layout_realwages1, layout_addothers, layout_deductothers;
	private TextView tv_ret, tv_title, tv_peroid_title, tv_realwages, tv_totalpay_title, tv_basepay_title, tv_basepay
		, tv_meritpay_title, tv_meritpay, tv_secret_title, tv_secret, tv_positionsalary_title, tv_positionsalary, tv_salaryadd_title
		, tv_housingsubsidy_title, tv_housingsubsidy, tv_overtimesalary_title, tv_overtimesalary, tv_subsidy_title, tv_subsidy, tv_onechildfee_title
		, tv_onechildfee, tv_bonus_title, tv_bonus, tv_annualbonus_title, tv_annualbonus, tv_salarydeduct_title,tv_leavededuction_title, tv_leavededuction
		, tv_endowmentinsurance_title, tv_endowmentinsurance, tv_endowmentinsuranceretroactive_title, tv_endowmentinsuranceretroactive
		, tv_hospitalizationinsurance_title, tv_hospitalizationinsurance, tv_hospitalizationinsuranceretroactive_title, tv_hospitalizationinsuranceretroactive
		, tv_unemploymentinsuranceretroactive_title, tv_unemploymentinsuranceretroactive, tv_unemploymentinsurance_title, tv_unemploymentinsurance
		, tv_reservefund_title, tv_reservefund, tv_reservefundretroactive_title, tv_reservefundretroactive, tv_salarytax_title, tv_pretaxwages_title
		, tv_pretaxwages, tv_incometax_title, tv_incometax, tv_others_title, tv_others, tv_realwages1_title, tv_realwages1, tv_addothers_title, tv_addothers
		, tv_deductothers_title, tv_deductothers;
	
	
/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods);

		Intent intent = getIntent();
		if (intent.hasExtra("detail")) {
			goods = (CategoriesGoods) intent.getSerializableExtra("detail");
			//Log.e("LSD", goods.getAppPicture());
		}
		templist = new ArrayList<StoreDetail>();
		tempColor = new ArrayList<SelectionModel>();
		tempSize = new ArrayList<SelectionModel>();

		initViews();
		initViewPager();
		if (goods != null) {
			getCategorieStore(goods.getId());
		}
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_payrolldetail, null);
		resources = getResources();
		setContentView(v);
		initView();
		Intent intent = getIntent();
		if (intent.hasExtra(Period)) {
			period = (String) intent.getSerializableExtra(Period);
			initData();
		}
		return v;
	}

	private void initView() {
		//navigate bar
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT, Constants.TitleHeight, 0, 0, 0, 0);
		tv_ret = (TextView) findViewById(R.id.tv_ret);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		tv_ret.setOnClickListener(this);
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(R.string.payrolldetail);//工资单详细
		
		//实发工资
		layout_realwages = (LinearLayout) findViewById(R.id.layout_realwages);
		uiAdapter.setPadding(layout_realwages, 20, 10, 20, 10);
		realwages_info = (LinearLayout) findViewById(R.id.realwages_info);
		uiAdapter.setMargin(realwages_info, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		uiAdapter.setPadding(realwages_info, 0, 5, 0, 5);
		tv_realwages = (TextView) findViewById(R.id.tv_realwages);
		uiAdapter.setTextSize(tv_realwages, 32);
		TextPaint tp_realwages = tv_realwages.getPaint(); 
		tp_realwages.setFakeBoldText(true); 
		tv_peroid_title = (TextView) findViewById(R.id.tv_peroid_title);
		uiAdapter.setTextSize(tv_peroid_title, 18);
		
		//基本薪资
		tv_totalpay_title = (TextView) findViewById(R.id.tv_totalpay_title);
		uiAdapter.setTextSize(tv_totalpay_title, 20);
		TextPaint tp_totalpay = tv_totalpay_title.getPaint(); 
		tp_totalpay.setFakeBoldText(true); 
		uiAdapter.setPadding(tv_totalpay_title, 20, 0, 0, 0);
		
		//基本工资
		layout_basepay = (LinearLayout) findViewById(R.id.layout_basepay);
		tv_basepay_title = (TextView) findViewById(R.id.tv_basepay_title);
		uiAdapter.setTextSize(tv_basepay_title, 18);
		uiAdapter.setMargin(tv_basepay_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_basepay = (TextView) findViewById(R.id.tv_basepay);
		uiAdapter.setTextSize(tv_basepay, 18);
		uiAdapter.setPadding(tv_basepay, 0, 0, 20, 0);
		
		//绩效工资
		layout_meritpay = (LinearLayout) findViewById(R.id.layout_meritpay);
		tv_meritpay_title = (TextView) findViewById(R.id.tv_meritpay_title);
		uiAdapter.setTextSize(tv_meritpay_title, 18);
		uiAdapter.setMargin(tv_meritpay_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_meritpay = (TextView) findViewById(R.id.tv_meritpay);
		uiAdapter.setTextSize(tv_meritpay, 18);
		uiAdapter.setPadding(tv_meritpay, 0, 0, 20, 0);
		
		//特勤工资
		layout_secret = (LinearLayout) findViewById(R.id.layout_secret);
		tv_secret_title = (TextView) findViewById(R.id.tv_secret_title);
		uiAdapter.setTextSize(tv_secret_title, 18);
		uiAdapter.setMargin(tv_secret_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_secret = (TextView) findViewById(R.id.tv_secret);
		uiAdapter.setTextSize(tv_secret, 18);
		uiAdapter.setPadding(tv_secret, 0, 0, 20, 0);
		
		//岗位工资
		layout_positionsalary = (LinearLayout) findViewById(R.id.layout_positionsalary);
		tv_positionsalary_title = (TextView) findViewById(R.id.tv_positionsalary_title);
		uiAdapter.setTextSize(tv_positionsalary_title, 18);
		uiAdapter.setMargin(tv_positionsalary_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_positionsalary = (TextView) findViewById(R.id.tv_positionsalary);
		uiAdapter.setTextSize(tv_positionsalary, 18);
		uiAdapter.setPadding(tv_positionsalary, 0, 0, 20, 0);
		
		//薪资增项
		tv_salaryadd_title = (TextView) findViewById(R.id.tv_salaryadd_title);
		uiAdapter.setTextSize(tv_salaryadd_title, 20);
		TextPaint tp_salaryadd_title= tv_salaryadd_title.getPaint(); 
		tp_salaryadd_title.setFakeBoldText(true); 
		uiAdapter.setPadding(tv_salaryadd_title, 20, 0, 0, 0);
		
		//加班费
		layout_overtimesalary = (LinearLayout) findViewById(R.id.layout_overtimesalary);
		tv_overtimesalary_title = (TextView) findViewById(R.id.tv_overtimesalary_title);
		uiAdapter.setTextSize(tv_overtimesalary_title, 18);
		uiAdapter.setMargin(tv_overtimesalary_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_overtimesalary = (TextView) findViewById(R.id.tv_overtimesalary);
		uiAdapter.setTextSize(tv_overtimesalary, 18);
		uiAdapter.setPadding(tv_overtimesalary, 0, 0, 20, 0);
		
		//津贴
		layout_subsidy = (LinearLayout) findViewById(R.id.layout_subsidy);
		tv_subsidy_title = (TextView) findViewById(R.id.tv_subsidy_title);
		uiAdapter.setTextSize(tv_subsidy_title, 18);
		uiAdapter.setMargin(tv_subsidy_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_subsidy = (TextView) findViewById(R.id.tv_subsidy);
		uiAdapter.setTextSize(tv_subsidy, 18);
		uiAdapter.setPadding(tv_subsidy, 0, 0, 20, 0);
		
		//独生子女费
		layout_onechildfee = (LinearLayout) findViewById(R.id.layout_onechildfee);
		tv_onechildfee_title = (TextView) findViewById(R.id.tv_onechildfee_title);
		uiAdapter.setTextSize(tv_onechildfee_title, 18);
		uiAdapter.setMargin(tv_onechildfee_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_onechildfee = (TextView) findViewById(R.id.tv_onechildfee);
		uiAdapter.setTextSize(tv_onechildfee, 18);
		uiAdapter.setPadding(tv_onechildfee, 0, 0, 20, 0);
		
		//奖金
		layout_bonus = (LinearLayout) findViewById(R.id.layout_bonus);
		tv_bonus_title = (TextView) findViewById(R.id.tv_bonus_title);
		uiAdapter.setTextSize(tv_bonus_title, 18);
		uiAdapter.setMargin(tv_bonus_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_bonus = (TextView) findViewById(R.id.tv_bonus);
		uiAdapter.setTextSize(tv_bonus, 18);
		uiAdapter.setPadding(tv_bonus, 0, 0, 20, 0);
		
		//年终奖
		layout_annualbonus = (LinearLayout) findViewById(R.id.layout_annualbonus);
		tv_annualbonus_title = (TextView) findViewById(R.id.tv_annualbonus_title);
		uiAdapter.setTextSize(tv_annualbonus_title, 18);
		uiAdapter.setMargin(tv_annualbonus_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_annualbonus = (TextView) findViewById(R.id.tv_annualbonus);
		uiAdapter.setTextSize(tv_annualbonus, 18);
		uiAdapter.setPadding(tv_annualbonus, 0, 0, 20, 0);
		
		//其他应发
		layout_addothers = (LinearLayout) findViewById(R.id.layout_addothers);
		tv_addothers_title = (TextView) findViewById(R.id.tv_addothers_title);
		uiAdapter.setTextSize(tv_addothers_title, 18);
		uiAdapter.setMargin(tv_addothers_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_addothers = (TextView) findViewById(R.id.tv_addothers);
		uiAdapter.setTextSize(tv_addothers, 18);
		uiAdapter.setPadding(tv_addothers, 0, 0, 20, 0);
		
		//其他
		layout_others = (LinearLayout) findViewById(R.id.layout_others);
		tv_others_title = (TextView) findViewById(R.id.tv_others_title);
		uiAdapter.setTextSize(tv_others_title, 18);
		uiAdapter.setMargin(tv_others_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_others = (TextView) findViewById(R.id.tv_others);
		uiAdapter.setTextSize(tv_others, 18);
		uiAdapter.setPadding(tv_others, 0, 0, 20, 0);
		
		//薪资减项
		tv_salarydeduct_title = (TextView) findViewById(R.id.tv_salarydeduct_title);
		uiAdapter.setTextSize(tv_salarydeduct_title, 20);
		TextPaint tp_salarydeduct= tv_salarydeduct_title.getPaint(); 
		tp_salarydeduct.setFakeBoldText(true); 
		uiAdapter.setPadding(tv_salarydeduct_title, 20, 0, 0, 0);
		
		//请假扣款合计
		layout_leavededuction = (LinearLayout) findViewById(R.id.layout_leavededuction);
		tv_leavededuction_title = (TextView) findViewById(R.id.tv_leavededuction_title);
		uiAdapter.setTextSize(tv_leavededuction_title, 18);
		uiAdapter.setMargin(tv_leavededuction_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_leavededuction = (TextView) findViewById(R.id.tv_leavededuction);
		uiAdapter.setTextSize(tv_leavededuction, 18);
		uiAdapter.setPadding(tv_leavededuction, 0, 0, 20, 0);
		
		//个人养老保险
		layout_endowmentinsurance = (LinearLayout) findViewById(R.id.layout_endowmentinsurance);
		tv_endowmentinsurance_title = (TextView) findViewById(R.id.tv_endowmentinsurance_title);
		uiAdapter.setTextSize(tv_endowmentinsurance_title, 18);
		uiAdapter.setMargin(tv_endowmentinsurance_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_endowmentinsurance = (TextView) findViewById(R.id.tv_endowmentinsurance);
		uiAdapter.setTextSize(tv_endowmentinsurance, 18);
		uiAdapter.setPadding(tv_endowmentinsurance, 0, 0, 20, 0);
		
		//个人养老保险补缴
		layout_endowmentinsuranceretroactive = (LinearLayout) findViewById(R.id.layout_endowmentinsuranceretroactive);
		tv_endowmentinsuranceretroactive_title = (TextView) findViewById(R.id.tv_endowmentinsuranceretroactive_title);
		uiAdapter.setTextSize(tv_endowmentinsuranceretroactive_title, 18);
		uiAdapter.setMargin(tv_endowmentinsuranceretroactive_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_endowmentinsuranceretroactive = (TextView) findViewById(R.id.tv_endowmentinsuranceretroactive);
		uiAdapter.setTextSize(tv_endowmentinsuranceretroactive, 18);
		uiAdapter.setPadding(tv_endowmentinsuranceretroactive, 0, 0, 20, 0);
		
		//个人医疗保险
		layout_hospitalizationinsurance = (LinearLayout) findViewById(R.id.layout_hospitalizationinsurance);
		tv_hospitalizationinsurance_title = (TextView) findViewById(R.id.tv_hospitalizationinsurance_title);
		uiAdapter.setTextSize(tv_hospitalizationinsurance_title, 18);
		uiAdapter.setMargin(tv_hospitalizationinsurance_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_hospitalizationinsurance = (TextView) findViewById(R.id.tv_hospitalizationinsurance);
		uiAdapter.setTextSize(tv_hospitalizationinsurance, 18);
		uiAdapter.setPadding(tv_hospitalizationinsurance, 0, 0, 20, 0);
		
		//个人医疗保险补缴
		layout_hospitalizationinsuranceretroactive = (LinearLayout) findViewById(R.id.layout_hospitalizationinsuranceretroactive);
		tv_hospitalizationinsuranceretroactive_title = (TextView) findViewById(R.id.tv_hospitalizationinsuranceretroactive_title);
		uiAdapter.setTextSize(tv_hospitalizationinsuranceretroactive_title, 18);
		uiAdapter.setMargin(tv_hospitalizationinsuranceretroactive_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_hospitalizationinsuranceretroactive = (TextView) findViewById(R.id.tv_hospitalizationinsuranceretroactive);
		uiAdapter.setTextSize(tv_hospitalizationinsuranceretroactive, 18);
		uiAdapter.setPadding(tv_hospitalizationinsuranceretroactive, 0, 0, 20, 0);
		
		//个人失业保险
		layout_unemploymentinsurance = (LinearLayout) findViewById(R.id.layout_unemploymentinsurance);
		tv_unemploymentinsurance_title = (TextView) findViewById(R.id.tv_unemploymentinsurance_title);
		uiAdapter.setTextSize(tv_unemploymentinsurance_title, 18);
		uiAdapter.setMargin(tv_unemploymentinsurance_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_unemploymentinsurance = (TextView) findViewById(R.id.tv_unemploymentinsurance);
		uiAdapter.setTextSize(tv_unemploymentinsurance, 18);
		uiAdapter.setPadding(tv_unemploymentinsurance, 0, 0, 20, 0);
		
		//个人失业保险补缴
		layout_unemploymentinsuranceretroactive = (LinearLayout) findViewById(R.id.layout_unemploymentinsuranceretroactive);
		tv_unemploymentinsuranceretroactive_title = (TextView) findViewById(R.id.tv_unemploymentinsuranceretroactive_title);
		uiAdapter.setTextSize(tv_unemploymentinsuranceretroactive_title, 18);
		uiAdapter.setMargin(tv_unemploymentinsuranceretroactive_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_unemploymentinsuranceretroactive = (TextView) findViewById(R.id.tv_unemploymentinsuranceretroactive);
		uiAdapter.setTextSize(tv_unemploymentinsuranceretroactive, 18);
		uiAdapter.setPadding(tv_unemploymentinsuranceretroactive, 0, 0, 20, 0);
		
		//个人公积金
		layout_reservefund = (LinearLayout) findViewById(R.id.layout_reservefund);
		tv_reservefund_title = (TextView) findViewById(R.id.tv_reservefund_title);
		uiAdapter.setTextSize(tv_reservefund_title, 18);
		uiAdapter.setMargin(tv_reservefund_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_reservefund = (TextView) findViewById(R.id.tv_reservefund);
		uiAdapter.setTextSize(tv_reservefund, 18);
		uiAdapter.setPadding(tv_reservefund, 0, 0, 20, 0);
		
		//个人公积金补缴
		layout_reservefundretroactive = (LinearLayout) findViewById(R.id.layout_reservefundretroactive);
		tv_reservefundretroactive_title = (TextView) findViewById(R.id.tv_reservefundretroactive_title);
		uiAdapter.setTextSize(tv_reservefundretroactive_title, 18);
		uiAdapter.setMargin(tv_reservefundretroactive_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_reservefundretroactive = (TextView) findViewById(R.id.tv_reservefundretroactive);
		uiAdapter.setTextSize(tv_reservefundretroactive, 18);
		uiAdapter.setPadding(tv_reservefundretroactive, 0, 0, 20, 0);
		
		//其他应扣
		layout_deductothers = (LinearLayout) findViewById(R.id.layout_deductothers);
		tv_deductothers_title = (TextView) findViewById(R.id.tv_deductothers_title);
		uiAdapter.setTextSize(tv_deductothers_title, 18);
		uiAdapter.setMargin(tv_deductothers_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_deductothers = (TextView) findViewById(R.id.tv_deductothers);
		uiAdapter.setTextSize(tv_deductothers, 18);
		uiAdapter.setPadding(tv_deductothers, 0, 0, 20, 0);
		
		//计税薪资
		tv_salarytax_title = (TextView) findViewById(R.id.tv_salarytax_title);
		uiAdapter.setTextSize(tv_salarytax_title, 20);
		TextPaint tp_salarytax= tv_salarytax_title.getPaint(); 
		tp_salarytax.setFakeBoldText(true); 
		uiAdapter.setPadding(tv_salarytax_title, 20, 0, 0, 0);
		
		//税前工资
		layout_pretaxwages = (LinearLayout) findViewById(R.id.layout_pretaxwages);
		tv_pretaxwages_title = (TextView) findViewById(R.id.tv_pretaxwages_title);
		uiAdapter.setTextSize(tv_pretaxwages_title, 18);
		uiAdapter.setMargin(tv_pretaxwages_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_pretaxwages = (TextView) findViewById(R.id.tv_pretaxwages);
		uiAdapter.setTextSize(tv_pretaxwages, 18);
		uiAdapter.setPadding(tv_pretaxwages, 0, 0, 20, 0);
		
		//所得税
		layout_incometax = (LinearLayout) findViewById(R.id.layout_incometax);
		tv_incometax_title = (TextView) findViewById(R.id.tv_incometax_title);
		uiAdapter.setTextSize(tv_incometax_title, 18);
		uiAdapter.setMargin(tv_incometax_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_incometax = (TextView) findViewById(R.id.tv_incometax);
		uiAdapter.setTextSize(tv_incometax, 18);
		uiAdapter.setPadding(tv_incometax, 0, 0, 20, 0);
		
		//实发工资
		layout_realwages1 = (LinearLayout) findViewById(R.id.layout_realwages1);
		tv_realwages1_title = (TextView) findViewById(R.id.tv_realwages1_title);
		uiAdapter.setTextSize(tv_realwages1_title, 18);
		uiAdapter.setMargin(tv_realwages1_title, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 40, 0, 0, 0);
		tv_realwages1 = (TextView) findViewById(R.id.tv_realwages1);
		uiAdapter.setTextSize(tv_realwages1, 18);
		uiAdapter.setPadding(tv_realwages1, 0, 0, 20, 0);
	}
	
	private void initData() {
		OperationBuilder builder = new OperationBuilder().append(new PayrollDetailOp(period),
				null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, resources.getString(R.string.timeout));
					return;
				}
				Payroll entity = (Payroll) resList.get(0);
				PayrollEntity payrollEntity = (PayrollEntity) entity.getRetobj();
				if (payrollEntity == null) {
					Toast.show(self, resources.getString(R.string.nopayroll));
					//finish();
					return;
				}
				setData(payrollEntity);
			}

			@Override
			public void onOperationError(Exception e) {
				e.printStackTrace();
			}
		};
		JsonCommon task = new JsonCommon(self, builder, listener,
				JsonCommon.PROGRESSQUERY);
		task.execute();
	}
	
	/*****
	 * 设置数据
	 * 
	 * @param payroll entity
	 */
	private void setData(PayrollEntity entity) {
		//实发工资 & 薪资发放月份
		tv_realwages.setText(entity.getRealwages()!=null ? "¥ " + entity.getRealwages() : "");
		tv_peroid_title.setText(getResources().getString(R.string.periodmonth_title) + " " + entity.getPeriod());
		//基本薪资小计
		//基本工资
		if(entity.getBasepay()==null || "".equals(entity.getBasepay())) {
			layout_basepay.setVisibility(View.GONE);
		} else {
			tv_basepay.setText("¥ " + entity.getBasepay());
		}
		//绩效工资
		if(entity.getMeritpay()==null || "".equals(entity.getMeritpay())) {
			layout_meritpay.setVisibility(View.GONE);
		} else {
			tv_meritpay.setText("¥ " + entity.getMeritpay());
		}
		//特勤工资
		if(entity.getSecret()==null || "".equals(entity.getSecret())) {
			layout_secret.setVisibility(View.GONE);
		} else {
			tv_secret.setText("¥ " + entity.getSecret());
		}
		//岗位工资
		if(entity.getPositionsalary()==null || "".equals(entity.getPositionsalary())) {
			layout_positionsalary.setVisibility(View.GONE);
		} else {
			tv_positionsalary.setText("¥ " + entity.getPositionsalary());
		}
		
		//薪资增项小计
		//加班费
		if(entity.getPositionsalary()==null || "".equals(entity.getPositionsalary())) {
			layout_overtimesalary.setVisibility(View.GONE);
		} else {
			tv_positionsalary.setText("¥ " + entity.getPositionsalary());
		}
		//津贴
		if(entity.getSubsidy()==null || "".equals(entity.getSubsidy())) {
			layout_subsidy.setVisibility(View.GONE);
		} else {
			tv_subsidy.setText("¥ " + entity.getSubsidy());
		}
		//独生子女费
		if(entity.getOnechildfee()==null || "".equals(entity.getOnechildfee())) {
			layout_onechildfee.setVisibility(View.GONE);
		} else {
			tv_onechildfee.setText("¥ " + entity.getOnechildfee());
		}
		//奖金
		if(entity.getBonus()==null || "".equals(entity.getBonus())) {
			layout_bonus.setVisibility(View.GONE);
		} else {
			tv_bonus.setText("¥ " + entity.getBonus());
		}
		//年终奖
		if(entity.getAnnualbonus()==null || "".equals(entity.getAnnualbonus())) {
			layout_annualbonus.setVisibility(View.GONE);
		} else {
			tv_annualbonus.setText("¥ " + entity.getAnnualbonus());
		}
		//其他应发
		if(entity.getAddothers()==null || "".equals(entity.getAddothers())) {
			layout_addothers.setVisibility(View.GONE);
		} else {
			tv_addothers.setText("¥ " + entity.getAddothers());
		}
		//其他
		if(entity.getOthers()==null || "".equals(entity.getOthers())) {
			layout_others.setVisibility(View.GONE);
		} else {
			tv_others.setText("¥ " + entity.getOthers());
		}
		
		//薪资增项小计
		//请假扣款合计
		if(entity.getLeavededuction()==null || "".equals(entity.getLeavededuction())) {
			layout_leavededuction.setVisibility(View.GONE);
		} else {
			tv_leavededuction.setText("¥ " + entity.getLeavededuction());
		}
		//个人养老保险
		if(entity.getEndowmentinsurance()==null || "".equals(entity.getEndowmentinsurance())) {
			layout_endowmentinsurance.setVisibility(View.GONE);
		} else {
			tv_endowmentinsurance.setText("¥ " + entity.getEndowmentinsurance());
		}
		//个人养老保险补缴
		if(entity.getEndowmentinsuranceretroactive()==null || "".equals(entity.getEndowmentinsuranceretroactive())) {
			layout_endowmentinsuranceretroactive.setVisibility(View.GONE);
		} else {
			tv_endowmentinsuranceretroactive.setText("¥ " + entity.getEndowmentinsuranceretroactive());
		}
		//个人医疗保险
		if(entity.getHospitalizationinsurance()==null || "".equals(entity.getHospitalizationinsurance())) {
			layout_hospitalizationinsurance.setVisibility(View.GONE);
		} else {
			tv_hospitalizationinsurance.setText("¥ " + entity.getHospitalizationinsurance());
		}
		//个人医疗保险补缴
		if(entity.getHospitalizationinsuranceretroactive()==null || "".equals(entity.getHospitalizationinsuranceretroactive())) {
			layout_hospitalizationinsuranceretroactive.setVisibility(View.GONE);
		} else {
			tv_hospitalizationinsuranceretroactive.setText("¥ " + entity.getHospitalizationinsuranceretroactive());
		}
		//个人失业保险
		if(entity.getUnemploymentinsurance()==null || "".equals(entity.getUnemploymentinsurance())) {
			layout_unemploymentinsurance.setVisibility(View.GONE);
		} else {
			tv_unemploymentinsurance.setText("¥ " + entity.getUnemploymentinsurance());
		}
		//个人失业保险补缴
		if(entity.getHospitalizationinsuranceretroactive()==null || "".equals(entity.getHospitalizationinsuranceretroactive())) {
			layout_unemploymentinsuranceretroactive.setVisibility(View.GONE);
		} else {
			tv_unemploymentinsuranceretroactive.setText("¥ " + entity.getHospitalizationinsuranceretroactive());
		}
		//个人公积金
		if(entity.getReservefund()==null || "".equals(entity.getReservefund())) {
			layout_reservefund.setVisibility(View.GONE);
		} else {
			tv_reservefund.setText("¥ " + entity.getReservefund());
		}
		//个人公积金补缴
		if(entity.getReservefundretroactive()==null || "".equals(entity.getReservefundretroactive())) {
			layout_reservefundretroactive.setVisibility(View.GONE);
		} else {
			tv_reservefundretroactive.setText("¥ " + entity.getReservefundretroactive());
		}
		//其他应扣
		if(entity.getDeductothers()==null || "".equals(entity.getDeductothers())) {
			layout_deductothers.setVisibility(View.GONE);
		} else {
			tv_deductothers.setText("¥ " + entity.getDeductothers());
		}
		
		//计税工资小计
		//税前工资
		if(entity.getPretaxwages()==null || "".equals(entity.getPretaxwages())) {
			layout_pretaxwages.setVisibility(View.GONE);
		} else {
			tv_pretaxwages.setText("¥ " + entity.getPretaxwages());
		}
		//所得税
		if(entity.getIncometax()==null || "".equals(entity.getIncometax())) {
			layout_incometax.setVisibility(View.GONE);
		} else {
			tv_incometax.setText("¥ " + entity.getIncometax());
		}
		//实发工资
		if(entity.getRealwages()==null || "".equals(entity.getRealwages())) {
			layout_realwages1.setVisibility(View.GONE);
		} else {
			tv_realwages1.setText("¥ " + entity.getRealwages());
		}
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
			finish();
			break;
		}
	}
	
}
