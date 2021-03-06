package com.liancheng.it.controller.shop;

import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.liancheng.it.service.shop.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping("/createShop.do")
	@ResponseBody
	public JSONObject createShop(@RequestParam(value="shop_profile",required=false) MultipartFile pics, 
			HttpServletRequest req){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		String user_id = multipartRequest.getParameter("token");
		String shop_name = multipartRequest.getParameter("shop_name");
		String shop_type = multipartRequest.getParameter("shop_type");
		String shop_describe = multipartRequest.getParameter("shop_describe");
		String shop_address = multipartRequest.getParameter("shop_address");
		String contacts_name = multipartRequest.getParameter("contacts_name");
		String contacts_phone = multipartRequest.getParameter("contacts_phone");
		//保存文件的本地路径
		String localBasePath = req.getSession().getServletContext().getRealPath("/")+"images/shop/";
		JSONObject jsonObject = shopService.createShop(pics, user_id, shop_name, shop_type, 
				shop_describe, shop_address, contacts_name, contacts_phone, localBasePath);
		return jsonObject;
	}
	
	@RequestMapping("/showShopType.do")
	@ResponseBody
	public JSONObject showShopType(){
		JSONObject jsonObject = shopService.showShopType();
		return jsonObject;
	}
	
	@RequestMapping("/showShopPaginData.do")
	@ResponseBody
	public JSONObject showShopPaginData(@RequestParam(value="shopType",required=false) String shopType, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest req){
		//项目环境下的图片路径
		String path = req.getContextPath();
		String hostPath01 = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/images/shop/";
		JSONObject jsonObject = shopService.showShopPaginData(shopType, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	
	
	
}
