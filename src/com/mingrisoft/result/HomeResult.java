package com.mingrisoft.result;

import java.util.List;

import com.mingrisoft.bean.Commodity;

public class HomeResult {
	private String[] bannerUrls;//banner图片链接
	private String[] typeNames;//分类名称
	private List<Commodity> commoditys;//商品列表
	public String[] getBannerUrls() {
		return bannerUrls;
	}
	public List<Commodity> getCommoditys() {
		return commoditys;
	}
	public void setCommoditys(List<Commodity> commoditys) {
		this.commoditys = commoditys;
	}
	public void setBannerUrls(String[] bannerUrls) {
		this.bannerUrls = bannerUrls;
	}
	public String[] getTypeNames() {
		return typeNames;
	}
	public void setTypeNames(String[] typeNames) {
		this.typeNames = typeNames;
	}

}
