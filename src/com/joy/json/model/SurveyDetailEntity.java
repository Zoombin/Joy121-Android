package com.joy.json.model;

import java.util.List;

/**
 * 调查详情
 * @author daiye
 *
 */
public class SurveyDetailEntity extends TResult {

	private static final long serialVersionUID = 1L;
	
//	{"retobj":[{"Description":"根据公司家庭日安排，为了活动参与的广泛性，特发起这次活动投票征询。","ScopeLevel":"DELPHI_SZ",
//		"LoginName":"eason","Questions":"公司组织看电影。^公司组织吃饭。^公司组织游戏比赛。^公司组织旅游。^公司组织球类比赛。^公司组织趣味游戏。",
//		"ExpireTime":"\/Date(1402394400000+0800)\/","SurveyAnswer":{"Answers":"0^0^1^1^0^0","SurveyId":8,"LoginName":"eason"},
//		"SurveyId":8,"SurveyRates":[{"SurveyId":8,"Rate":2,"QuestionIndex":3,"Question":null},
//		                            {"SurveyId":8,"Rate":3,"QuestionIndex":0,"Question":null},{"SurveyId":8,"Rate":3,"QuestionIndex":2,"Question":null},{"SurveyId":8,"Rate":2,"QuestionIndex":5,"Question":null},{"SurveyId":8,"Rate":2,"QuestionIndex":4,"Question":null},{"SurveyId":8,"Rate":3,"QuestionIndex":1,"Question":null}],"OptionType":1,"ResultShow":1,"Title":"家庭日活动投票"}],"msg":null,"flag":1}
	private String Description;
	
	private String ScopeLevel;
	
	private String LoginName;
	
	private String Questions;
	
	private String ExpireTime;
	
	private int SurveyId;
	
	private int OptionType;
	
	private int ResultShow;
	
	private String Title;
	
	private SurveyAns SurveyAnswer;
	
	private String[] answer;
	
	private List<SurveyRate> SurveyRates;
	
	public class SurveyRate {
		private int SurveyId;
		
		private int Rate;
		
		private int QuestionIndex;
		
		private String Question;

		public int getSurveyId() {
			return SurveyId;
		}

		public void setSurveyId(int surveyId) {
			SurveyId = surveyId;
		}

		public int getRate() {
			return Rate;
		}

		public void setRate(int rate) {
			Rate = rate;
		}

		public int getQuestionIndex() {
			return QuestionIndex;
		}

		public void setQuestionIndex(int questionIndex) {
			QuestionIndex = questionIndex;
		}

		public String getQuestion() {
			return Question;
		}

		public void setQuestion(String question) {
			Question = question;
		}
	}
	
	static public class SurveyAns {
		private String Answers;
		
		private int SurveyId;
		
		private String LoginName;

		public String getAnswers() {
			return Answers;
		}

		public void setAnswers(String answers) {
			Answers = answers;
		}

		public int getSurveyId() {
			return SurveyId;
		}

		public void setSurveyId(int surveyId) {
			SurveyId = surveyId;
		}

		public String getLoginName() {
			return LoginName;
		}

		public void setLoginName(String loginName) {
			LoginName = loginName;
		}
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getScopeLevel() {
		return ScopeLevel;
	}

	public void setScopeLevel(String scopeLevel) {
		ScopeLevel = scopeLevel;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getQuestions() {
		return Questions;
	}

	public void setQuestions(String questions) {
		Questions = questions;
	}

	public String getExpireTime() {
		return ExpireTime;
	}

	public void setExpireTime(String expireTime) {
		ExpireTime = expireTime;
	}

	public int getSurveyId() {
		return SurveyId;
	}

	public void setSurveyId(int surveyId) {
		SurveyId = surveyId;
	}

	public int getOptionType() {
		return OptionType;
	}

	public void setOptionType(int optionType) {
		OptionType = optionType;
	}

	public int getResultShow() {
		return ResultShow;
	}

	public void setResultShow(int resultShow) {
		ResultShow = resultShow;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public SurveyAns getSurveyAnswer() {
		return SurveyAnswer;
	}

	public void setSurveyAnswer(SurveyAns surveyAnswer) {
		SurveyAnswer = surveyAnswer;
	}

	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	public List<SurveyRate> getSurveyRates() {
		return SurveyRates;
	}

	public void setSurveyRates(List<SurveyRate> surveyRates) {
		SurveyRates = surveyRates;
	}
}
