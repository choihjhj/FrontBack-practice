package com.my.order.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/addtocart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("prodNo"); //상품번호
		String qt = request.getParameter("qt"); //수량
		
		
		int quantity = 0;
		if(qt != null && !qt.equals("")) {
			quantity = Integer.parseInt(qt);
		}
		HttpSession session = request.getSession();
		Map<String, Integer>cart = (Map)session.getAttribute("cart");
		if(cart == null) {
			cart =  new HashMap<>();
			session.setAttribute("cart", cart);
		}
		Integer totalQuantity  = cart.get(prodNo); //장바구니의 상품번호에 해당 수량 얻기
		if(totalQuantity != null) {
			quantity += totalQuantity;
		}
		cart.put(prodNo, quantity);
		
		System.out.println("현재장바구니:" + cart);
		
		

	}
}
