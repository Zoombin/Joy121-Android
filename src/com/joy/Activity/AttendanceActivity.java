package com.joy.Activity;

import java.util.List;

import gejw.android.quickandroid.widget.Toast;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.joy.JoyApplication;
import com.joy.R;
import com.joy.Dialog.CustomConfirmDialog;
import com.joy.Dialog.DialogUtil;
import com.joy.Dialog.DialogUtil.DialogButtonClickCallback;
import com.joy.Utils.Constants;
import com.joy.Widget.AttendanceAdapter;
import com.joy.json.JsonCommon;
import com.joy.json.JsonCommon.OnOperationListener;
import com.joy.json.model.AttendanceEntity;
import com.joy.json.model.AttendanceListEntity;
import com.joy.json.operation.OperationBuilder;
import com.joy.json.operation.impl.AttendanceListOp;
import com.joy.json.operation.impl.AttendancePunchOp;
import com.umeng.analytics.MobclickAgent;

/**
 * APP打卡
 * @author ryan zhou
 * 2014.12.09
 * 
 */
public class AttendanceActivity extends BaseActivity implements OnClickListener, LocationSource,
AMapLocationListener, InfoWindowAdapter{

	private RelativeLayout layout_title;
	private TextView tv_ret;
	private TextView tv_title;
	private Button btn_punchin;
	private Button btn_punchout;
	private LinearLayout layout_attendances;
	private ListView list_attendances;

	private AMap aMap;
	private MapView mapView;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private LatLng currentLatLng = null;
	private double latitude;
	private double longitude;
	private AttendanceEntity attendanceEntity;
	
	private AttendanceAdapter attendanceAdapter;
	
	private Resources resources;
	private DialogUtil dialogUtil;

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activitysub);
		resources = getResources();
		dUtil = new DialogUtil(self);
		initView();
		initData();
	}*/
	
	@Override
	protected View ceateView(LayoutInflater inflater, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_attendance, null);
		setContentView(v);
		resources = getResources();
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		dialogUtil = new DialogUtil(self);
		appSet = JoyApplication.getInstance().getCompAppSet();
		initView();
		initData();
		return v;
	}

	private void initView() {
		//head navigate
		layout_title = (RelativeLayout) findViewById(R.id.layout_title);
		uiAdapter.setMargin(layout_title, LayoutParams.MATCH_PARENT,
				Constants.TitleHeight, 0, 0, 0, 0);
		tv_ret = (TextView) findViewById(R.id.tv_ret);
		tv_ret.setOnClickListener(this);
		uiAdapter.setTextSize(tv_ret, Constants.TitleRetSize);
		uiAdapter.setMargin(tv_ret, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 20, 0, 0, 0);
		tv_title = (TextView) findViewById(R.id.tv_title);
		uiAdapter.setTextSize(tv_title, Constants.TitleSize);
		tv_title.setText(resources.getString(R.string.attence));
		uiAdapter.setMargin(mapView, LayoutParams.WRAP_CONTENT,
				300, 10, 10, 10, 10);
		//btn_punchin 上班打卡按钮
		btn_punchin = (Button) findViewById(R.id.btn_punchin);
		uiAdapter.setMargin(btn_punchin, 0, 40, 10, 0, 5, 0);
		//uiAdapter.setPadding(btn_punchin, 10, 3, 10, 3);
		uiAdapter.setTextSize(btn_punchin, 24);
		//btn_punchout 下班打卡按钮
		btn_punchout = (Button) findViewById(R.id.btn_punchout);
		uiAdapter.setMargin(btn_punchout, 0, 40, 10, 0, 5, 0);
		//uiAdapter.setPadding(btn_punchout, 10, 3, 10, 3);
		uiAdapter.setTextSize(btn_punchout, 24);
		int color = 0;
		if (appSet != null) {
			try {
				color = Color.parseColor(appSet.getColor2());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (color != 0) {
			// 设置颜色
			btn_punchin.setBackgroundColor(color);
			btn_punchout.setBackgroundColor(color);
		}
		btn_punchin.setOnClickListener(this);
		btn_punchout.setOnClickListener(this);
		//layout attendances
		
		//attendance list
		layout_attendances = (LinearLayout) findViewById(R.id.layout_attendances);
		uiAdapter.setMargin(layout_attendances, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 10, 10, 10, 10);
		list_attendances = (ListView) findViewById(R.id.list_attendances);
		uiAdapter.setMargin(list_attendances, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0, 0, 0, 0);
		//list_attendances.setBackgroundColor(Color.parseColor("#BBBBBB"));
	}

	private void initData() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
		attendanceAdapter = new AttendanceAdapter(self, self);
		list_attendances.setAdapter(attendanceAdapter);
		getAttendanceList();
	}
	
	/**
	 * 设置一些amap的属性
	 */
	private void setUpMap() {
		// 自定义系统定位小蓝点
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.location_marker));// 设置小蓝点的图标
		myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色	
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
		// myLocationStyle.anchor(int,int)//设置小蓝点的锚点
		myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
		//aMap.setMyLocationStyle(myLocationStyle);
		aMap.setLocationSource(this);// 设置定位监听
		//aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
		aMap.setInfoWindowAdapter(this);
		//markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow));
		
	    //aMap.setMyLocationType()
	}
	
	/**
	 * 此方法已经废弃
	 */
	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		if (mListener != null && aLocation != null) {
			attendanceEntity = new AttendanceEntity();
			longitude = aLocation.getLongitude();
			latitude = aLocation.getLatitude();
			attendanceEntity.setLongitude(longitude + "");
			attendanceEntity.setLatitude(latitude + "");
			currentLatLng = new LatLng(latitude, longitude);
			if (currentLatLng != null) {
				MarkerOptions markerOption = new MarkerOptions();
				markerOption.position(currentLatLng);
				//markerOption.title("经度:"+currentLatLng.longitude + " 纬度:"+currentLatLng.latitude);
				markerOption.title("经度:"+currentLatLng.longitude);
				markerOption.snippet("纬度:"+currentLatLng.latitude);
				markerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				markerOption.draggable(true);
				aMap.clear();
				Marker marker = aMap.addMarker(markerOption);
				marker.showInfoWindow();
				
				/*Marker marker = aMap.addMarker(new MarkerOptions()
				.position(currentLatLng)
				.title("好好学习")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
				.draggable(true));
				marker.showInfoWindow();// 设置默认显示一个infowinfow*/
		
			}
			mListener.onLocationChanged(aLocation);// 显示系统小蓝点
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			/*
			 * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
			 * API定位采用GPS和网络混合定位方式
			 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
			 */
			mAMapLocationManager.setGpsEnable(true);		
			mAMapLocationManager.requestLocationUpdates(
					LocationProviderProxy.AMapNetwork, 5000, 15, this);
			
		}
	}
	
	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;
	}
	
	/**
	 * 监听自定义infowindow窗口的infowindow事件回调
	 */
	@Override
	public View getInfoWindow(Marker marker) {
		return null;
	}
	
	/**
	 * 监听自定义infowindow窗口的infocontents事件回调
	 */
	@Override
	public View getInfoContents(Marker marker) {
		return null;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_ret:
			finish();
			break;
		case R.id.btn_punchin:
			/*dialogUtil.showSingleChoiceDialog("APP考勤", 0, "确认要打卡吗？", new String[]{ "上班", "下班" }, "确定", "取消", new DialogButtonClickCallback() {
				@Override
				public void positiveButtonClick() {
					if (attendanceEntity == null) {
						Toast.show(self, "您还未定位到当前位置,打卡失败！");
					} else {
						attendanceEntity.setPunchType("0");
						punch(attendanceEntity);
					}
					
				}
				@Override
				public void negativeButtonClick() {
					
				}
			});*/
			CustomConfirmDialog customConfrimDialog = new CustomConfirmDialog(this, R.style.CustomConfirmDialog); 
			customConfrimDialog.show();
			break;
		case R.id.btn_punchout:
			dialogUtil.showSingleChoiceDialog("APP考勤", 0, "确认要打卡吗？", new String[]{ "上班", "下班" }, "确定", "取消", new DialogButtonClickCallback() {
				@Override
				public void positiveButtonClick() {
					if (attendanceEntity == null) {
						Toast.show(self, "您还未定位到当前位置,打卡失败！");
					} else {
						attendanceEntity.setPunchType("1");
						punch(attendanceEntity);
					}
				}
				@Override
				public void negativeButtonClick() {
				}
			});
			break;
		default:
			break;
		}
	}
	
	/**
	 * 取得打卡信息列表
	 */
	private void getAttendanceList() {
		OperationBuilder builder = new OperationBuilder().append(new AttendanceListOp(),
				null);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				attendanceAdapter.clear();
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, resources.getString(R.string.timeout));
					return;
				}
				AttendanceListEntity entity = (AttendanceListEntity) resList.get(0);
				List<AttendanceEntity> attendanceEntityList = entity.getRetobj();
				if (attendanceEntityList == null || attendanceEntityList.size() == 0) {
					return;
				}
				for (AttendanceEntity entity1 : attendanceEntityList) {
					attendanceAdapter.addItem(entity1);
				}
				attendanceAdapter.notifyDataSetChanged();
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
	
	/**
	 * 打卡
	 * @param attendanceEntity
	 */
	private void punch(AttendanceEntity attendanceEntity){
		OperationBuilder builder = new OperationBuilder().append(
				new AttendancePunchOp(), attendanceEntity);
		OnOperationListener listener = new OnOperationListener() {
			@Override
			public void onOperationFinished(List<Object> resList) {
				Toast.show(self, "打卡成功！");
				attendanceAdapter.clear();
				if (self.isFinishing()) {
					return;
				}
				if (resList == null) {
					Toast.show(self, resources.getString(R.string.timeout));
					return;
				}
				AttendanceListEntity entity = (AttendanceListEntity) resList.get(0);
				List<AttendanceEntity> attendanceEntityList = entity.getRetobj();
				if (attendanceEntityList == null || attendanceEntityList.size() == 0) {
					return;
				}
				for (AttendanceEntity entity1 : attendanceEntityList) {
					attendanceAdapter.addItem(entity1);
				}
				attendanceAdapter.notifyDataSetChanged();
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

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
