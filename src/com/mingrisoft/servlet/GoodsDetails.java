package com.mingrisoft.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mingrisoft.bean.GoodDetails;
import com.mingrisoft.db.DBDao;
import com.mingrisoft.result.Result;

/**
 * Servlet implementation class GoodsDetails
 */
@WebServlet("/GoodsDetails")
public class GoodsDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsDetails() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		response.getWriter().write("无");
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		DBDao dao = new DBDao();
		GoodDetails goodsDetails = dao.getGoodDetails(request.getParameter("id"));
		dao.close();
		Result<GoodDetails> result = new Result<>();
		result.setError_code(0);
		result.setResult(goodsDetails);
		result.setReason("成功");
		response.getWriter().write(new Gson().toJson(result));
	}

}
