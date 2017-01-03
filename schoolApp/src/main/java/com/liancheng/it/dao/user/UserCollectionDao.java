package com.liancheng.it.dao.user;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.user.UserCollection;

public interface UserCollectionDao {
	
	public Integer saveCollection(UserCollection collection);
	public List<UserCollection> findUserCo(String userId);
	public List<UserCollection> findGoodsCo(String goodsId);
	public Integer updateStatus(Map<String,String> map);
}
