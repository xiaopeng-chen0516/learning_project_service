package com.mingrisoft.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mingrisoft.bean.Commodity;
import com.mingrisoft.bean.GoodDetails;
import com.mingrisoft.db.DBDao;
import com.mingrisoft.result.Result;

/**
 * Servlet implementation class ShopType
 */
@WebServlet("/ShopType")
public class ShopType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		String shop = request.getParameter("shop");
		DBDao dao = new DBDao();
		List<Commodity> list = dao.selectShop(shop);
		dao.close();
		Result<List<Commodity>> result = new Result<>();
		result.setError_code(0);
		result.setResult(list);
		result.setReason("成功");
		response.getWriter().write(new Gson().toJson(result));
	}

}
