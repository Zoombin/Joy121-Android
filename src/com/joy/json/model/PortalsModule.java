package com.joy.json.model;

import java.util.List;

public class PortalsModule extends TResult {
	int flag;
	String msg;
	List<Module> retobj;

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

	public List<Module> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<Module> retobj) {
		this.retobj = retobj;
	}

	public class Module {
		String Company;
		int ModuleId;
		String UsedStartTime;
		String UsedEndTime;
		String ModuleName;

		public String getCompany() {
			return Company;
		}

		public void setCompany(String company) {
			Company = company;
		}

		public int getModuleId() {
			return ModuleId;
		}

		public void setModuleId(int moduleId) {
			ModuleId = moduleId;
		}

		public String getUsedStartTime() {
			return UsedStartTime;
		}

		public void setUsedStartTime(String usedStartTime) {
			UsedStartTime = usedStartTime;
		}

		public String getUsedEndTime() {
			return UsedEndTime;
		}

		public void setUsedEndTime(String usedEndTime) {
			UsedEndTime = usedEndTime;
		}

		public String getModuleName() {
			return ModuleName;
		}

		public void setModuleName(String moduleName) {
			ModuleName = moduleName;
		}

	}

}
