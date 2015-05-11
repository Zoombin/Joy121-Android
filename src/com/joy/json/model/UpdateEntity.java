package com.joy.json.model;

/**
 * 软件更新
 * 
 * @author daiye
 * 
 */
public class UpdateEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private Version retobj;

	private String msg;

	private int flag;

	public class Version extends TResult {
		private static final long serialVersionUID = 1L;

		private String DeviceType;
		
		private String Appversion;
		
		private String ForceUpdate;
		
		private String DownloadAddr;
		
		private String VersionSize;
		
		private String Description;
		
		private String CurrentFlag;
		
		private String Flag;
		
		private String CreateTime;

		

		public String getDeviceType() {
			return DeviceType;
		}



		public void setDeviceType(String deviceType) {
			DeviceType = deviceType;
		}



		public String getAppversion() {
			return Appversion;
		}



		public void setAppversion(String appversion) {
			Appversion = appversion;
		}



		public String getForceUpdate() {
			return ForceUpdate;
		}



		public void setForceUpdate(String forceUpdate) {
			ForceUpdate = forceUpdate;
		}



		public String getDownloadAddr() {
			return DownloadAddr;
		}



		public void setDownloadAddr(String downloadAddr) {
			DownloadAddr = downloadAddr;
		}



		public String getVersionSize() {
			return VersionSize;
		}



		public void setVersionSize(String versionSize) {
			VersionSize = versionSize;
		}



		public String getDescription() {
			return Description;
		}



		public void setDescription(String description) {
			Description = description;
		}



		public String getCurrentFlag() {
			return CurrentFlag;
		}



		public void setCurrentFlag(String currentFlag) {
			CurrentFlag = currentFlag;
		}



		public String getFlag() {
			return Flag;
		}

		public void setFlag(String flag) {
			Flag = flag;
		}


		public String getCreateTime() {
			return CreateTime;
		}

		public void setCreateTime(String createTime) {
			CreateTime = createTime;
		}



		public long getSerialversionuid() {
			return serialVersionUID;
		}
	}

	public Version getRetobj() {
		return retobj;
	}

	public void setRetobj(Version retobj) {
		this.retobj = retobj;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
