package com.joy.json.model;

import java.util.List;

/**
 * 订单信息
 * 
 * @author daiye
 * 
 */
public class UserOrderEntity extends TResult {

	private static final long serialVersionUID = 1L;

	// {"retobj":[{"LstCommodity":[],"LoginName":"steven","ExpectDate":"\/Date(-62135596800000+0800)\/","ReceiveLocationId":0,"Operator":null,"TimeFee":0,"ReceiveTime":"\/Date(1396368000000+0800)\/","Deliveryer":"","ReceiverAddressType":0,"CommodityInfo":"22:2:1;","ReceiverId":0,"Company":null,"Flag":"1","OrderId":"20140402061855406","ReceivePhone":"0512-88852121","ExpectTime":0,"Satisfy":0,"Points":80,"OrderProcessRemark":null,"IsBenefit":0,"ReceiverAddress":"苏州工业园区长安街123号","LstCommoditySet":[{"Picture":"D002.png","MarketPrice":100,"Description":"本套餐因为商品的时效性较强，所以请于规定的时间内确定。<br
	// \/>如果不想使用改套餐，员工可以将积分转化为个人积分，与其他节日积分积累使用。<br \/>本套餐统一配送，具体时间另行通知。",
	// "Flag":"1","BuyPersons":null,"CommSetOrderSx":0,"IsDefault":null,"TypeName":"端午节","Points":80,"Commodities":null,"SetType":"15",
	// "AppPicture":"appD002.png","ExpireDate":"\/Date(-62135596800000+0800)\/","StartDate":"\/Date(-62135596800000+0800)\/","EXPIREDDATE":"\/Date(-62135596800000+0800)\/",
	// "CreateTime":"\/Date(1396336841000+0800)\/","SetNo":"D002","SetName":"【端午节】员工福利套餐二","AppDescription":"描述员工福利套餐二","Id":22}],
	// "LocalFee":0,"Receiver":"刘想柱","CreateTime":"\/Date(1396433988000+0800)\/","OrderSX":0,"PaymentWay":"","Remark":""},

	// {"LstCommodity":[],"LoginName":"steven",
	// "ExpectDate":"\/Date(-62135596800000+0800)\/","ReceiveLocationId":0,"Operator":null,"TimeFee":0,"ReceiveTime":"\/Date(1395504000000+0800)\/","Deliveryer":"",
	// "ReceiverAddressType":0,"CommodityInfo":"21:2:1;","ReceiverId":0,"Company":null,"Flag":"1","OrderId":"20140323033158864","ReceivePhone":"0512-88852121-121",
	// "ExpectTime":0,"Satisfy":0,"Points":80,"OrderProcessRemark":null,"IsBenefit":0,"ReceiverAddress":"苏州工业园区长安街123号","LstCommoditySet":[{"Picture":"D001.png",
	// "MarketPrice":60,"Description":"本套餐因为商品的时效性较强，所以请于规定的时间内确定。<br
	// \/>如果不想使用改套餐，员工可以将积分转化为个人积分，与其他节日积分积累使用。<br \/>本套餐统一配送，具体时间另行通知。",
	// "Flag":"1","BuyPersons":null,"CommSetOrderSx":0,"IsDefault":null,"TypeName":"端午节","Points":50,"Commodities":null,"SetType":"15",
	// "AppPicture":"appD001.png","ExpireDate":"\/Date(-62135596800000+0800)\/","StartDate":"\/Date(-62135596800000+0800)\/","EXPIREDDATE":"\/Date(-62135596800000+0800)\/",
	// "CreateTime":"\/Date(1396335928000+0800)\/","SetNo":"D001","SetName":"【端午节】员工福利套餐一","AppDescription":"描述员工福利套餐一","Id":21}],
	// }
	// "LocalFee":0,"Receiver":"刘想柱","CreateTime":"\/Date(1395559924000+0800)\/","OrderSX":0,"PaymentWay":"","Remark":""}]

	// private String LstCommodity;

	private String LoginName;

	private String ExpectDate;

	private int ReceiveLocationId;

	private String Operator;

	private int TimeFee;

	private String ReceiveTime;

	private String Deliveryer;

	private int ReceiverAddressType;

	private String CommodityInfo;

	private int ReceiverId;

	private String Company;

	private String Flag;

	private String OrderId;

	private String ReceivePhone;

	private int ExpectTime;

	private int Satisfy;

	private int points;

	private String OrderProcessRemark;

	private int IsBenefit;

	private String ReceiverAddress;

	private List<CommoditySet> LstCommoditySet;

	private int LocalFee;

	private String Receiver;

	private String CreateTime;

	private int OrderSX;

	private String PaymentWay;

	private String Remark;

	public List<CommoditySet> getLstCommoditySet() {
		return LstCommoditySet;
	}

	public void setLstCommoditySet(List<CommoditySet> lstCommoditySet) {
		LstCommoditySet = lstCommoditySet;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getExpectDate() {
		return ExpectDate;
	}

	public void setExpectDate(String expectDate) {
		ExpectDate = expectDate;
	}

	public int getReceiveLocationId() {
		return ReceiveLocationId;
	}

	public void setReceiveLocationId(int receiveLocationId) {
		ReceiveLocationId = receiveLocationId;
	}

	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	public int getTimeFee() {
		return TimeFee;
	}

	public void setTimeFee(int timeFee) {
		TimeFee = timeFee;
	}

	public String getReceiveTime() {
		return ReceiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		ReceiveTime = receiveTime;
	}

	public String getDeliveryer() {
		return Deliveryer;
	}

	public void setDeliveryer(String deliveryer) {
		Deliveryer = deliveryer;
	}

	public int getReceiverAddressType() {
		return ReceiverAddressType;
	}

	public void setReceiverAddressType(int receiverAddressType) {
		ReceiverAddressType = receiverAddressType;
	}

	public String getCommodityInfo() {
		return CommodityInfo;
	}

	public void setCommodityInfo(String commodityInfo) {
		CommodityInfo = commodityInfo;
	}

	public int getReceiverId() {
		return ReceiverId;
	}

	public void setReceiverId(int receiverId) {
		ReceiverId = receiverId;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public String getReceivePhone() {
		return ReceivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		ReceivePhone = receivePhone;
	}

	public int getExpectTime() {
		return ExpectTime;
	}

	public void setExpectTime(int expectTime) {
		ExpectTime = expectTime;
	}

	public int getSatisfy() {
		return Satisfy;
	}

	public void setSatisfy(int satisfy) {
		Satisfy = satisfy;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getOrderProcessRemark() {
		return OrderProcessRemark;
	}

	public void setOrderProcessRemark(String orderProcessRemark) {
		OrderProcessRemark = orderProcessRemark;
	}

	public int getIsBenefit() {
		return IsBenefit;
	}

	public void setIsBenefit(int isBenefit) {
		IsBenefit = isBenefit;
	}

	public String getReceiverAddress() {
		return ReceiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		ReceiverAddress = receiverAddress;
	}

	public int getLocalFee() {
		return LocalFee;
	}

	public void setLocalFee(int localFee) {
		LocalFee = localFee;
	}

	public String getReceiver() {
		return Receiver;
	}

	public void setReceiver(String receiver) {
		Receiver = receiver;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public int getOrderSX() {
		return OrderSX;
	}

	public void setOrderSX(int orderSX) {
		OrderSX = orderSX;
	}

	public String getPaymentWay() {
		return PaymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		PaymentWay = paymentWay;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
