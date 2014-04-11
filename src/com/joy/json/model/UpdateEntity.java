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

		private String Version;

		public String getVersion() {
			return Version;
		}

		public void setVersion(String version) {
			this.Version = version;
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
