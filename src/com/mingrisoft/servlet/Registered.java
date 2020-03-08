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
import com.mingrisoft.bean.User;
import com.mingrisoft.db.DBDao;
import com.mingrisoft.result.Result;

/**
 * 注册界面
 */
@WebServlet("/Registered")
public class Registered extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registered() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 模拟注册
		// User user = new User();
		// user.setName("测试");
		// user.setNick("昵称");
		// user.setGender(2);
		// user.setPassword("asdasdasd");
		// DBDao dao = new DBDao();
		// String result = dao.insert(user);
		// System.out.println(result);

		// 获取商品集合
		DBDao dao = new DBDao();
		List<GoodDetails> list = dao.selectAll("GoodDetails", GoodDetails.class);
		dao.close();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		Result<List<GoodDetails>> result = new Result<>();
		result.setResult(list);
		result.setReason("成功");
		result.setError_code(0);
		response.getWriter().write(new Gson().toJson(list));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
