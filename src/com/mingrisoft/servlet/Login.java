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
 * Servlet implementation class ShopType
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		Result result = new Result();
//		Result<List<User>> result = new Result<>();
		result.setError_code(0);
		DBDao dao = new DBDao();
		String s_pwd=request.getParameter("pwd");
		String s_user=request.getParameter("user").toString();
		List<User> list = dao.login(s_user);
		dao.close();
		int listSize=list.size();
		if(listSize>0){
			for(int i=0;i<listSize;i++){
				User user=list.get(i);
				if(user.getPassword().equals(s_pwd)){
					result.setReason("成功");
				}else{
					result.setReason("失败");
				}
				break;
			}
			
		}else{
			result.setReason("失败");
		}
//		result.setResult(list);
		
		response.getWriter().write(new Gson().toJson(result));
	}

}
