package com.liancheng.it.dao.active;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.active.ActiveCategReport;
import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ClassActive;
import com.liancheng.it.entity.active.CycFriendsActvie;
import com.liancheng.it.entity.active.Favorites;
import com.liancheng.it.entity.active.TextKey;
import com.liancheng.it.entity.admin.Admin;
import com.liancheng.it.entity.commont.Commont;

public interface ActiveUserDao {
	
	public void addActive(Active active);//发表动态
	public int queryActiveCounts(String user_id);//查询用户动态数量
	public List<Active> queryOwnActive(Map<String, Object> params);//分页查询个人动态
	public List<Active> queryOwnActiveAndSee(Map<String, Object> params);//其他不是fans的人可见生活圈
	public List<Active> queryOtherOneActive(Map<String, Object> params);//登录用户查询其他人的个人动态
	//分页查询首页分类进去后的全网用户动态;也用来查询首页动态homeActive,type_a=首页动态
	public List<ClassActive> queryClassActive(Map<String, Object> params);
	public List<CycFriendsActvie> queryFriendsActvie(Map<String, Object> params);//分页查询朋友圈动态
	public List<ClassActive> queryHomeActive(Map<String, Object> params);//查询首页动态，全网用户
	
	public Active queryActiveSee(int active_user_id);//查看说说的查看的数量
	public void updateSee(Active active);//更新说说的查看状态，用于统计查看的数量
	public Active isSee();//是否查看
	
	public ClassActive queryActiveId(int active_user_id);//查询某条说说的动态，在大类里面
	
	
	public List<Active> queryAdmin(Map<String, Object> params);//动态用于后台审核
	public int queryAdminTotal(Map<String, Object> params);//查询未审核动态的总条数
	
	public void oneActiveVerify(int active_user_id);//审核通过某一条说说
	public void oneActiveNoVerify(int active_user_id);//审核不通过某一条说说
	
	public List<ActiveVerify> activeVerifies(Map<String, Object> params);//发动态的验证列表
	public List<ActiveVerify> allActiveVerifies();//获取所有的主题分类
	public List<ActiveVerify> allTwoActiveVerifies(String class_active);//
	public int activeVerifiesTotal();//发动态的验证列表的总数量
	
	public void modifyStuVerify(Map<String, Object> params);//修改学生证验证
	public void modifyCertiVerify(Map<String, Object> params);//修改毕业证验证
	
	public ActiveVerify queryStuAndCertiVerify(String classActive);//查询发动态的开关状态
	public List<Active> queryKeyActive(Map<String, Object> params);//动态的关键词检索
	public Active queryDownCount(int active_user_id);//查询资源文件的下载次数
	public void addDownCount(Map<String, Object> params);//增加资源文件的下载次数
	public Active queryOneActive(Map<String, Object> params);//查询某条动态，用于详情页
	public int batchModifyAcvtive(List<Integer> ids);//批量修改动态的验证状态
	
	public List<Active> queryActForUserInfo(String user_id);//查询某用户的动态用于个人信息展示页面
	
	public int queryTotalActs(String user_id);//查询某用户的所有审核通过的动态数量
	public Active queryActiveByActId(int active_user_id);//根据某条动态id查询动态数据
	public void deleteOneActive(int active_user_id);//删除某条动态数据
	
	public void saveTextKey(String text);//保存用户的搜索内容
	public List<TextKey> hotTextKey();//动态的关键词排名
	public void saveFavorites(Map<String, Object> params);//保存登录用户收藏的动态
	public List<Active> queryFavoritesList(Map<String, Object> params);
	public void deleteOneFavor(int favor_id);//用户删除动态
	public Favorites queryOneFavor(Map<String, Object> params);//查询某用户的某条收藏
	public int batchDelThemeCateg(List<Integer> ids);//批量删除主题大类
	public void saveThemeCateg(Map<String, Object> params);//添加主题大类
	public int queryAllNetActive();//全网动态数量
	public int totalNoVerifyActive();//查询总的未审核动态数量
	public void saveTwoCateg(Map<String, Object> params);//添加二级分类
	public List<ActiveCategReport> queryActiveCategReport(Map<String, Object> params);//查询动态的分类统计报表
	public int totalActiveCategReport(Map<String, Object> params);//查询总的动态的分类统计报表
	public CycFriendsActvie queryCurrFriendProfile(String user_id);//查询最近有动态的朋友(只要最近的一个朋友)
	public int queryByThemeName(String class_active);//根据主题类型的名称查询是否有主题类别
	public void updateThemeCateg(Map<String, Object> params);
}
