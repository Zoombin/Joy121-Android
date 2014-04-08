package com.joy.json.model;

/**
 * 福利详情
 * 
 * @author daiye
 * 
 */
public class Commodities extends TResult {

	private static final long serialVersionUID = 1L;

	// "Commodities":[{"Picture":"L00001.png","ComOrderSx":6,"Service":"","MarketPrice":60,"CategoryName":null,"Flag":"1","ComNo":"L00001",
	// "ComCount":0,"Points":50,"ComName":"苏州东西山枇杷","ComtCategory2":0,

	// "ComDesc":"<p align=\"left\"><img alt=\"\" "
	// + "src=\"http:\/\/www.joy121.com\/SYS\/Files\/img\/L00001_desc1.png\"
	// \/><\/p><p align=\"left\">东山枇杷的种类：<\/p><p align="
	// +
	// "\"left\">白玉枇杷：是白沙经过改良后的品种，成熟相对比较早，相比其他品种，更容易剥皮，而且果形大，果肉洁白甜嫩，汁多核少，光照下像玉雕般透明，所以绝对是枇杷中的最佳品。<"
	// + "\/p><p
	// align=\"left\">白沙枇杷：相比白玉，白沙枇杷成熟较晚，大概晚一个星期不到，但真正成熟后，味道绝对是最甜，肉质细嫩，真正成熟后的白沙枇杷估计不会亚于白玉枇杷，毕"
	// + "竟是东山老祖宗传下来的最优品种。<\/p><p
	// align=\"left\">青种枇杷：枇杷果成熟后，果蒂的位置还泛着青绿色，果个均匀，不易裂果，果肉细腻爽滑，含糖量高，成熟后味道不错，相"
	// + "比白沙白玉，口感稍逊一筹，但也是白沙的一个变种，所以白沙的许多优点在他身上也能体现。<\/p><p align=\"left\"><img
	// alt=\"\" src=\"http:\/\/www.joy121."
	// + "com\/SYS\/Files\/img\/L00001_desc2.png\" \/><\/p><p align=\"left\"><br
	// \/><\/p><p align=\"left\"><br \/><\/p>",

	// "ComtCategory1":7,
	// "SpecialHint":"<p align=\"left\">枇杷可治肺燥咳嗽<br
	// \/>　　据上海中医药大学附属曙光医院传统中医诊疗中心主任余小萍介绍，枇杷性凉，味甘、"
	// + "微酸，具有润肺止咳、生津止渴、清热健胃的功效。常见的止咳中成药川贝枇杷膏，就是以枇杷叶为主要原料加工而成，其功效与枇杷果类似。<\/p><p
	// align=\"left\">　　"
	// +
	// "慢性支气管炎、支气管哮喘等慢性肺病的患者，由于常年反复发作，久病耗伤肺阴，如果每天吃四五个枇杷，润肺止咳化痰，未尝不是一种养肺的好方法。而呼吸道急性感染时出现咳嗽、咯"
	// + "粘痰等肺热咳嗽的症状时，也可采用枇杷食疗的方法，改善症状或缓解病情。<\/p><p
	// align=\"left\">　　胡萝卜素含量居第三位<br \/>　　据营养学研"
	// +
	// "究分析，枇杷果实可食部分每100克含蛋白质0.8克、脂肪0.2克、碳水化合物8.2克、膳食纤维0.8克、钙17毫克、磷8毫克、铁1.1毫克、类胡萝卜素1.5毫克，并"
	// + "含有人体所必需的8种氨基酸。<\/p><p
	// align=\"left\">　　枇杷的胡萝卜素含量在水果中占第三位。胡萝卜素摄入人体消化器官后，可以转化成维生素A，具有"
	// +
	// "防止皮肤干燥、粗糙;有效促进健康及细胞发育，预防先天不足;有助于提高人体免疫力，预防感冒;促进骨骼及牙齿健康成长;改善生殖功能;有助于良好的视觉功能;预防胃、"
	// + "食道、肺、肝等癌症，预防心脑血管疾病等多种作用。<\/p><p align=\"left\">　　慢支、哮喘枇杷食疗<br
	// \/>　　夏季原本是呼吸道疾病的低发期，但由"
	// +
	// "于空调的普遍使用，呼吸道感染、支气管哮喘等疾病也时有发生。因此，余主任提醒，夏季也不可放松对呼吸系统疾病的预防，合理使用空调之外，饮食上也要注意清淡，忌油"
	// +
	// "腻食物，可多食枇杷、百合、绿豆、山药、金银花茶、菊花茶等润肺、清肺。而对于慢性支气管炎、肺气肿、支气管哮喘、反复呼吸道感染者，这个时候经常吃些枇杷，润肺养肺"
	// + "，对疾病的恢复很有好处。<br \/><\/p>",

	// "CreateTime":"\/Date(1396338369000+0800)\/","ComBrand":701,
	// "Id":3288,
	// "BrandName":null}],

	private String Picture;

	private int ComOrderSx;

	private String Service;

	private int MarketPrice;

	private String CategoryName;

	private int Flag;

	private String ComNo;

	private int ComCount;

	private int Points;

	private String ComName;

	private int ComtCategory2;

	private String ComDesc;

	private int ComtCategory1;

	private String SpecialHint;

	private String CreateTime;

	private int ComBrand;

	private int Id;

	private String BrandName;

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public int getComOrderSx() {
		return ComOrderSx;
	}

	public void setComOrderSx(int comOrderSx) {
		ComOrderSx = comOrderSx;
	}

	public String getService() {
		return Service;
	}

	public void setService(String service) {
		Service = service;
	}

	public int getMarketPrice() {
		return MarketPrice;
	}

	public void setMarketPrice(int marketPrice) {
		MarketPrice = marketPrice;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public int getFlag() {
		return Flag;
	}

	public void setFlag(int flag) {
		Flag = flag;
	}

	public String getComNo() {
		return ComNo;
	}

	public void setComNo(String comNo) {
		ComNo = comNo;
	}

	public int getComCount() {
		return ComCount;
	}

	public void setComCount(int comCount) {
		ComCount = comCount;
	}

	public int getPoints() {
		return Points;
	}

	public void setPoints(int points) {
		Points = points;
	}

	public String getComName() {
		return ComName;
	}

	public void setComName(String comName) {
		ComName = comName;
	}

	public int getComtCategory2() {
		return ComtCategory2;
	}

	public void setComtCategory2(int comtCategory2) {
		ComtCategory2 = comtCategory2;
	}

	public String getComDesc() {
		return ComDesc;
	}

	public void setComDesc(String comDesc) {
		ComDesc = comDesc;
	}

	public int getComtCategory1() {
		return ComtCategory1;
	}

	public void setComtCategory1(int comtCategory1) {
		ComtCategory1 = comtCategory1;
	}

	public String getSpecialHint() {
		return SpecialHint;
	}

	public void setSpecialHint(String specialHint) {
		SpecialHint = specialHint;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public int getComBrand() {
		return ComBrand;
	}

	public void setComBrand(int comBrand) {
		ComBrand = comBrand;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

}
