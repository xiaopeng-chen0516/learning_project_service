package com.mingrisoft.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mingrisoft.bean.Commodity;
import com.mingrisoft.db.DBDao;
import com.mingrisoft.result.HomeResult;
import com.mingrisoft.result.Result;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 返回首页的数据
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 处理Get请求返回广告位图片(返回全部数据)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取商品集合
		DBDao dao = new DBDao();
		List<Commodity> list = dao.selectAll("Commodity", Commodity.class);
		dao.close();
		HomeResult home = new HomeResult();
		home.setTypeNames(new String[]{"服饰内衣","精品鞋靴","食品饮料","美妆个护"});
		home.setBannerUrls(bannerUrls());
		home.setCommoditys(list);
		Result<HomeResult> result = new Result<>();
		result.setReason("成功");
		result.setResult(home);
		String json = new Gson().toJson(result);
		response.getWriter().write(json);
//		doPost(request,response);
	}

	/**
	 * post请求处理(分页)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		String json = null;
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("row")) < 6 ? 6 : Integer.parseInt(request.getParameter("row")) ;
		System.out.println("Row="+row+"page="+page);
		int startIndex = page * row - row;
		// 获取商品集合
		DBDao dao = new DBDao();
		if(page * row > dao.selectCount("Commodity")){
			Result<HomeResult> result = new Result<>();
			result.setReason("失败");
			result.setResult(new HomeResult());
			json = new Gson().toJson(result);
		}else {
			List<Commodity> list = dao.selectPage("Commodity", Commodity.class, startIndex, row);
			dao.close();
			HomeResult home = new HomeResult();
			home.setBannerUrls(bannerUrls());
			home.setTypeNames(new String[]{"服饰内衣","精品鞋靴","美妆个护","食品饮料"});
			home.setCommoditys(list);
			Result<HomeResult> result = new Result<>();
			result.setReason("成功");
			result.setResult(home);
			json = new Gson().toJson(result);
			
		}
		response.getWriter().write(json);
	}

	/**
	 * 广告位图片
	 * @return
	 */
	private String[] bannerUrls() {
		// 获取banner图片
		String bannerUrls[] = new String[6];
		String url_start = "image/banner/banner";
		String url_end = ".jpg";
		for (int i = 0; i < bannerUrls.length; i++) {
			bannerUrls[i] = url_start + (i + 1) + url_end;
		}
		return bannerUrls;
	}
}
