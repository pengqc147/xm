package com.pqc.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.pqc.entity.Orders;
import com.pqc.entity.User;
import com.pqc.service.OrdersService;
import com.pqc.service.TrolleyService;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService = new OrdersService();
	private TrolleyService trolleyService = new TrolleyService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String func = request.getParameter("func");
		switch (func) {
			case "createOrders":
				createOrders(request,response);
				break;
			case "changeState":
				changeState(request,response);
				break;
			default:
				break;
		}
	}
	//�ı䶩��״̬����
	private void changeState(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ordersNumber = request.getParameter("ordersNumber");
		boolean isSuccess = ordersService.changeState(ordersNumber);
		if(isSuccess) {
			response.sendRedirect("index?func=findAllCate");
		}
	}
	//������������
	private void createOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sumPrice = request.getParameter("sumPrice");//��ȡ��Ʒ�ܼ�
		String goodsCount = request.getParameter("goodsCount");//��ȡ��Ʒ����
		String ordersNumber=UUID.randomUUID().toString();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String ordersName = user.getUname()+"�Ķ���";//��������
		Date create_time = new Date();//��������ʱ��
		Orders orders = new Orders(ordersNumber,user.getUid(), Double.valueOf(sumPrice), Integer.valueOf(goodsCount), ordersName, create_time);
		boolean isSuccess = ordersService.createOrders(orders);
		if(isSuccess) {
			System.out.println("���������ɹ�������");
			boolean isSuccessToo = trolleyService.changeTrolley(ordersNumber,user.getUid());
			if(isSuccessToo) {
				System.out.println("����������ɳɹ�������");
				request.setAttribute("orders", orders);
				request.getRequestDispatcher("pay/index.jsp").forward(request, response);
			}
		}
	}
}
