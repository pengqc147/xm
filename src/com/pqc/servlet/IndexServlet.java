package com.pqc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pqc.entity.Category;
import com.pqc.entity.Goods;
import com.pqc.service.CateService;
import com.pqc.service.GoodsService;
import com.pqc.utils.CookieUtils;

@WebServlet("/index")
public class IndexServlet  extends HttpServlet{

	//商品类别的service
	private CateService cateService = new CateService();
	//商品名称的service
	private GoodsService goodsService = new GoodsService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String func = request.getParameter("func");
		switch (func) {
			case "findAllCate":
				findAllCate(request,response);
				break;
			case "searchGoods":
				searchGoods(request,response);
				break;
			case "findGoodsById":
				findGoodsById(request,response);
				break;
			default:
				break;
		}
	}
	//通过gid获取商品信息跳转详情页面
	private void findGoodsById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
		Goods goods = goodsService.findGoodsById(gid);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}
	//首页面搜索商品，生成cookie方法
	private void searchGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String search = request.getParameter("search");
		//封装一个工具类专门用来获取cookie中的内容
		CookieUtils.addCookie(search, request, response);
		response.getWriter().print(true);
		
	}
	private void findAllCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> cates = cateService.findAllCate(10);
		//获取小米明星单品商品
		List<Goods> starGoods = goodsService.findGoodsByState(4,5);
		//获取家电商品
		List<Goods> homeGoods = goodsService.findGoodsByCid(4,8);
		//获取智能商品
		List<Goods> AIGoods = goodsService.findGoodsByCid(15,5);
		//获取热门商品
		List<Goods> hotGoods = goodsService.findGoodsByState(1,4);
		//获取为你推荐商品
		String info = CookieUtils.getCookieInfo(request);
		List<Goods> suggestGoods = null;
		if("".equals(info)) {
			suggestGoods = goodsService.findGoodsByState(2, 5);
		}else {
			suggestGoods = goodsService.findGoodsBySearch(info,5);
		}
		request.setAttribute("suggestGoods", suggestGoods);
		request.setAttribute("cates", cates);
		request.setAttribute("starGoods", starGoods);
		request.setAttribute("homeGoods", homeGoods);
		request.setAttribute("AIGoods", AIGoods);
		request.setAttribute("hotGoods", hotGoods);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
