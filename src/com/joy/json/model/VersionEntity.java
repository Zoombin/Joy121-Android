package com.joy.json.model;

/**
 * 用户信息
 * @author ryan zhou 2014-12-19
 *
 */
public class VersionEntity extends TResult {

	private static final long serialVersionUID = 1L;

	private VersionInfoEntity retobj;
	
	private String msg;
	
	private int flag;

	public VersionInfoEntity getRetobj() {
		return retobj;
	}

	public void setRetobj(VersionInfoEntity retobj) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public class VersionInfoEntity{
		private String currentavaliable;
		
		private String latestversion;
		
		private String foreupdate;
		
		private String downloadaddr;
		
		private String description;

		public String getCurrentavaliable() {
			return currentavaliable;
		}

		public void setCurrentavaliable(String currentavaliable) {
			this.currentavaliable = currentavaliable;
		}

		public String getLatestversion() {
			return latestversion;
		}

		public void setLatestversion(String latestversion) {
			this.latestversion = latestversion;
		}

		public String getForeupdate() {
			return foreupdate;
		}

		public void setForeupdate(String foreupdate) {
			this.foreupdate = foreupdate;
		}

		public String getDownloadaddr() {
			return downloadaddr;
		}

		public void setDownloadaddr(String downloadaddr) {
			this.downloadaddr = downloadaddr;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		
	}
	
	
}
