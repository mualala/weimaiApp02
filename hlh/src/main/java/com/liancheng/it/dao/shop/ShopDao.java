package com.liancheng.it.dao.shop;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liancheng.it.entity.shop.Shop;

public interface ShopDao {
	
	/**
	 * 创建店铺
	 * @param params
	 * @return
	 */
	int saveShop(Map<String, Object> params);
	
	/**
	 * 查询所有店铺的分类列表
	 * @return
	 */
	@Select("select distinct shop_type from shop")
	List<String> queryAllShopType();
	
	/**
	 * 分页展示店铺的rows数据
	 * @param shopType
	 * @param start
	 * @param end
	 * @return
	 */
	List<Shop> queryShopPagin(@Param("shopType") String shopType, @Param("start") int start, @Param("end") int end);
	
	/**
	 * 分页展示店铺的total数据
	 * @param shopType
	 * @return
	 */
	int totalShopPagin(@Param("shopType") String shopType);
	
	
	
	
}
