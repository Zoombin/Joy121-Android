package com.joy.json.model;

/**
 * 积分信息
 * @author ryan zhou 2014-12-09
 *
 */

public class AttendanceEntity extends TResult {
	private static final long serialVersionUID = 1L;
	
	private String PunchTime;
	private String PunchType;
	private String Longitude;
	private String Latitude;

	

	public String getPunchTime() {
		return PunchTime;
	}

	public void setPunchTime(String punchTime) {
		PunchTime = punchTime;
	}

	public String getPunchType() {
		return PunchType;
	}

	public void setPunchType(String punchType) {
		PunchType = punchType;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AttendanceEntity() {
		// TODO Auto-generated constructor stub
	}

}
