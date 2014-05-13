package com.joy.json.model;

import java.util.List;

/**
 * 调查投票
 * 
 * @author daiye
 * 
 */
public class SurveyAEntity extends TResult {

	private static final long serialVersionUID = 1L;

	// {"retobj":[{"SurveyId":8,"Rate":5,"QuestionIndex":3,"Question":null},{"SurveyId":8,"Rate":3,"QuestionIndex":0,"Question":null},{"SurveyId":8,"Rate":6,"QuestionIndex":2,"Question":null},{"SurveyId":8,"Rate":2,"QuestionIndex":5,"Question":null},{"SurveyId":8,"Rate":2,"QuestionIndex":4,"Question":null},{"SurveyId":8,"Rate":3,"QuestionIndex":1,"Question":null}],"msg":null,"flag":1}

	private List<SurveyDetailEntity.SurveyRate> retobj;

	private String msg;

	private int flag;

	public List<SurveyDetailEntity.SurveyRate> getRetobj() {
		return retobj;
	}

	public void setRetobj(List<SurveyDetailEntity.SurveyRate> retobj) {
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
