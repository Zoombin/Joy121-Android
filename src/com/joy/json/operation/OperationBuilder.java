package com.joy.json.operation;

import java.util.ArrayList;
import java.util.List;

public class OperationBuilder {

	private ArrayList<Operation> opList = new ArrayList<Operation>();

	public OperationBuilder append(ITaskOperation op, Object obj) {
		opList.add(new Operation(op, obj));
		return this;
	}

	public List<Object> exec() throws Exception {
		ArrayList<Object> resList = new ArrayList<Object>();
		Object res = null;
		for (Operation op : opList) {
			res = op.getOp().exec(op.getObj(), res);
			resList.add(res);
		}
		return resList;
	}

	private class Operation {
		private ITaskOperation op;
		private Object obj;

		public Operation(ITaskOperation op, Object obj) {
			this.op = op;
			this.obj = obj;
		}

		public ITaskOperation getOp() {
			return op;
		}

		public Object getObj() {
			return obj;
		}
	}
}
