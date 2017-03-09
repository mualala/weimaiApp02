package com.liancheng.it.dao.active;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.active.ActiveCategReport;
import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ClassActive;
import com.liancheng.it.entity.active.CycFriendsActvie;
import com.liancheng.it.entity.active.Favorites;
import com.liancheng.it.entity.active.TextKey;

public interface ActiveUserDao {
	
	/**
	 * 发表动态
	 * @param active
	 */
	public void addActive(Active active);
	
	/**
	 * 查询用户动态数量
	 * @param user_id
	 * @return
	 */
	@Select("select count(*) from active where user_id=#{user_id}")
	public int queryActiveCounts(@Param("user_id") String user_id);
	
	/**
	 * 分页查询个人动态
	 * @param params
	 * @return
	 */
	public List<Active> queryOwnActive(Map<String, Object> params);
	
	/**
	 * 其他不是fans的人可见生活圈
	 * @param params
	 * @return
	 */
	public List<Active> queryOwnActiveAndSee(Map<String, Object> params);
	
	/**
	 * 登录用户查询其他人的个人动态
	 * @param params
	 * @return
	 */
	public List<Active> queryOtherOneActive(Map<String, Object> params);
	
	/**
	 * 分页查询首页分类进去后的全网用户动态;也用来查询首页动态homeActive,type_a=首页动态
	 * @param params
	 * @return
	 */
	public List<ClassActive> queryClassActive(Map<String, Object> params);
	
	/**
	 * 分页查询朋友圈动态
	 * @param params
	 * @return
	 */
	public List<CycFriendsActvie> queryFriendsActvie(Map<String, Object> params);
	
	/**
	 * 查询首页动态，全网用户
	 * @param params
	 * @return
	 */
	public List<ClassActive> queryHomeActive(Map<String, Object> params);
	
	/**
	 * 查看说说的查看的数量
	 * @param active_user_id
	 * @return
	 */
	@Select("select see from active where active_user_id=#{active_user_id}")
	public Active queryActiveSee(@Param("active_user_id") int active_user_id);
	
	/**
	 * 更新说说的查看状态，用于统计查看的数量
	 * @param active
	 */
	public void updateSee(Active active);
	
	/**
	 * 是否查看
	 * @return
	 */
	public Active isSee();
	
	/**
	 * 查询某条说说的动态，在大类里面
	 * @param active_user_id
	 * @return
	 */
	@Select("select * from class_active where active_user_id=#{active_user_id}")
	public ClassActive queryActiveId(int active_user_id);

	/**
	 * 动态用于后台审核
	 * @param params
	 * @return
	 */
	public List<Active> queryAdmin(Map<String, Object> params);
	
	/**
	 * 查询未审核动态的总条数
	 * @param params
	 * @return
	 */
	public int queryAdminTotal(Map<String, Object> params);
	
	/**
	 * 审核通过某一条说说
	 * @param active_user_id
	 */
	@Update("update active set state='1' where active_user_id=#{active_user_id}")
	public void oneActiveVerify(int active_user_id);
	
	/**
	 * 审核不通过某一条说说
	 * @param active_user_id
	 */
	public void oneActiveNoVerify(int active_user_id);
	
	/**
	 * 发动态的验证列表
	 * @param params
	 * @return
	 */
	public List<ActiveVerify> activeVerifies(Map<String, Object> params);
	
	/**
	 * 获取所有的主题分类
	 * @return
	 */
	public List<ActiveVerify> allActiveVerifies();
	
	/**
	 * 一级动态分类的所有二级分类
	 * @param class_active
	 * @return
	 */
	public List<ActiveVerify> allTwoActiveVerifies(String class_active);
	
	/**
	 * 发动态的验证列表的总数量
	 * @return
	 */
	public int activeVerifiesTotal();
	
	/**
	 * 修改学生证验证
	 * @param params
	 */
	public void modifyStuVerify(Map<String, Object> params);
	
	/**
	 * 修改毕业证验证
	 * @param params
	 */
	public void modifyCertiVerify(Map<String, Object> params);
	
	/**
	 * 查询发动态的开关状态
	 * @param classActive
	 * @return
	 */
	public ActiveVerify queryStuAndCertiVerify(String classActive);
	
	/**
	 * 动态的关键词检索
	 * @param params
	 * @return
	 */
	public List<Active> queryKeyActive(Map<String, Object> params);
	
	/**
	 * 查询资源文件的下载次数
	 * @param active_user_id
	 * @return
	 */
	public Active queryDownCount(int active_user_id);
	
	/**
	 * 增加资源文件的下载次数
	 * @param params
	 */
	public void addDownCount(Map<String, Object> params);
	
	/**
	 * 查询某条动态，用于详情页
	 * @param params
	 * @return
	 */
	public Active queryOneActive(Map<String, Object> params);
	
	/**
	 * 批量修改动态的验证状态
	 * @param ids
	 * @return
	 */
	public int batchModifyAcvtive(List<Integer> ids);
	
	/**
	 * 查询某用户的动态用于个人信息展示页面
	 * @param user_id
	 * @return
	 */
	public List<Active> queryActForUserInfo(String user_id);
	
	/**
	 * 查询某用户的所有审核通过的动态数量
	 * @param user_id
	 * @return
	 */
	public int queryTotalActs(String user_id);
	
	/**
	 * 根据某条动态id查询动态数据
	 * @param active_user_id
	 * @return
	 */
	public Active queryActiveByActId(int active_user_id);
	
	/**
	 * 删除某条动态数据
	 * @param active_user_id
	 */
	public void deleteOneActive(int active_user_id);
	
	/**
	 * 保存用户的搜索内容
	 * @param text
	 */
	public void saveTextKey(String text);
	
	/**
	 * 动态的关键词排名
	 * @return
	 */
	public List<TextKey> hotTextKey();
	
	/**
	 * 保存登录用户收藏的动态
	 * @param params
	 */
	public void saveFavorites(Map<String, Object> params);
	
	/**
	 * 查询收藏列表
	 * @param params
	 * @return
	 */
	public List<Active> queryFavoritesList(Map<String, Object> params);
	
	/**
	 * 用户删除动态
	 * @param favor_id
	 */
	public void deleteOneFavor(int favor_id);
	
	/**
	 * 查询某用户的某条收藏
	 * @param params
	 * @return
	 */
	public Favorites queryOneFavor(Map<String, Object> params);
	
	/**
	 * 批量删除主题大类
	 * @param ids
	 * @return
	 */
	public int batchDelThemeCateg(List<Integer> ids);
	
	/**
	 * 添加主题大类
	 * @param params
	 */
	public void saveThemeCateg(Map<String, Object> params);
	
	/**
	 * 全网动态数量
	 * @return
	 */
	public int queryAllNetActive();
	
	/**
	 * 查询总的未审核动态数量
	 * @return
	 */
	public int totalNoVerifyActive();
	
	/**
	 * 添加二级分类
	 * @param params
	 */
	public void saveTwoCateg(Map<String, Object> params);
	
	/**
	 * 查询动态的分类统计报表
	 * @param params
	 * @return
	 */
	public List<ActiveCategReport> queryActiveCategReport(Map<String, Object> params);
	
	/**
	 * 查询总的动态的分类统计报表
	 * @param params
	 * @return
	 */
	public int totalActiveCategReport(Map<String, Object> params);
	
	/**
	 * 查询最近有动态的朋友(只要最近的一个朋友)
	 * @param user_id
	 * @return
	 */
	public CycFriendsActvie queryCurrFriendProfile(String user_id);
	
	/**
	 * 根据主题类型的名称查询是否有主题类别
	 * @param class_active
	 * @return
	 */
	public int queryByThemeName(String class_active);
	
	/**
	 * 后台更新主题大类
	 * @param params
	 */
	public void updateThemeCateg(Map<String, Object> params);
	
	/**
	 * 查询一级分类的所有二级分类
	 * @param class_active
	 * @return
	 */
	public List<ActiveVerify> queryOneThemeHaveTwoClass(String class_active);
	
	/**
	 * 主题分类
	 * @return
	 */
	public List<ActiveVerify> themeCateg();
	
	/**
	 * 二级分类
	 * @param themeCateg
	 * @return
	 */
	public List<ActiveVerify> twoCateg(String themeCateg);
	
	/**
	 * 查询动态的详细报表
	 * @param params
	 * @return
	 */
	public List<Active> detailActiveRepoet(Map<String, Object> params);
	
	/**
	 * 详细动态报表的总的数量
	 * @param params
	 * @return
	 */
	public int totalDetailActiveRepoet(Map<String, Object> params);
	
	
}
