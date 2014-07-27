package com.joy.json.model;


public class CommitResultEntity extends TResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int flag;
	String msg;
	CommitResult retobj;
	

	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public CommitResult getRetobj() {
		return retobj;
	}


	public void setRetobj(CommitResult retobj) {
		this.retobj = retobj;
	}



	public class CommitResult {
		String StatusFlag;
		String StatusName;
		String StatusRemark;
		int IsShowToUser;

		public String getStatusFlag() {
			return StatusFlag;
		}

		public void setStatusFlag(String statusFlag) {
			StatusFlag = statusFlag;
		}

		public String getStatusName() {
			return StatusName;
		}

		public void setStatusName(String statusName) {
			StatusName = statusName;
		}

		public String getStatusRemark() {
			return StatusRemark;
		}

		public void setStatusRemark(String statusRemark) {
			StatusRemark = statusRemark;
		}

		public int getIsShowToUser() {
			return IsShowToUser;
		}

		public void setIsShowToUser(int isShowToUser) {
			IsShowToUser = isShowToUser;
		}

	}
}
