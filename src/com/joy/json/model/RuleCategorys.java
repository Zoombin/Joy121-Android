package com.joy.json.model;

import java.util.List;

public class RuleCategorys extends TResult {
	private static final long serialVersionUID = 1L;
	int flag;
	String msg;
	List<RuleCategory> retobj;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public List<RuleCategory> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<RuleCategory> retobj) {
		this.retobj = retobj;
	}

	public class RuleCategory {
		String Id;
		String TypeName;
		String Flag;
		String Sort;
		String Company;
		public String getId() {
			return Id;
		}
		public void setId(String id) {
			Id = id;
		}
		public String getTypeName() {
			return TypeName;
		}
		public void setTypeName(String typeName) {
			TypeName = typeName;
		}
		public String getFlag() {
			return Flag;
		}
		public void setFlag(String flag) {
			Flag = flag;
		}
		public String getSort() {
			return Sort;
		}
		public void setSort(String sort) {
			Sort = sort;
		}
		public String getCompany() {
			return Company;
		}
		public void setCompany(String company) {
			Company = company;
		}

	}

}
