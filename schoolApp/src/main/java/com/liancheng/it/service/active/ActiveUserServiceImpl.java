package com.liancheng.it.service.active;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.minidev.json.JSONObject;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.dao.active.ActiveUserDao;
import com.liancheng.it.dao.commont.CommontDao;
import com.liancheng.it.dao.friends.FriendsDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.active.ActiveCategReport;
import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ClassActive;
import com.liancheng.it.entity.active.CycFriendsActvie;
import com.liancheng.it.entity.active.Favorites;
import com.liancheng.it.entity.active.TextKey;
import com.liancheng.it.entity.active.ThemeCateg;
import com.liancheng.it.entity.active.TwoCateg;
import com.liancheng.it.entity.commont.Commont;
import com.liancheng.it.entity.friends.Friends;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.entity.user.Visitor;
import com.liancheng.it.util.DateUtil;

/**
 * 用户动态
 */
@Service("activeUserService")//扫描service
@Aspect
@Transactional
public class ActiveUserServiceImpl implements ActiveUserService {
	
	@Resource(name="activeUserDao")
	private ActiveUserDao activeUserDao;
	@Resource(name="commontDao")
	private CommontDao commontDao;
	@Resource(name="userDao")
	private UserDao userDao;
	@Autowired
	private FriendsDao friendsDao;
	
	
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, String title, 
			String localBasePath) {
		JSONObject jsonObject = new JSONObject();
		//判断后台对发动态是否开启了个人信息验证
		System.out.println("type_a="+type_a);
		ActiveVerify activeVerify = new ActiveVerify();
		User user = new User();
		
		if("life#".equals(type_a) && 
				(pics!=null || docum!=null || !"".equals(saysay))){//如果是生活圈#类型(生活圈类型不需要后台发动态的权限)
			//遍历保存发表动态的图片
			String picName = String.valueOf(System.currentTimeMillis());
			String picName02 = "";
			if(null!=pics && !"".equals(user_id)){
				//先单独保存到磁盘
				for(int i=0;i<pics.length;i++){
					System.out.println(pics[i].isEmpty());
					if(!pics[i].isEmpty()){//只要上传图片不为空
						picName = picName+"-"+i;
						String suffix = pics[i].getOriginalFilename().substring(pics[i].getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
						System.out.println("发说说图片的名称="+picName+suffix);
						picName02 = picName02+picName+suffix+",";
						try {
							System.out.println("发动态图片保存的本地路径="+localBasePath+picName+suffix);
							//保存图片到本地目录
							pics[i].transferTo(new File(localBasePath+picName+suffix));
						} catch (Exception e) {
							System.out.println("保存发表的动态图片失败！！");
						}
					}
				}
			}
			//保存资源文件
			String documName02 = "";
			String documSize02 = "";
			String documCount02 = "";
			if(null!=docum && !"".equals(user_id)){
				for(int i=0;i<docum.length;i++){
					if(!docum[i].isEmpty()){//只要上传资源不为空
						String testName = docum[i].getOriginalFilename();//获取上传文件的名称
						documName02 = documName02+"__"+testName+",";
						String documSize01 = String.valueOf(docum[i].getSize()/1024+"KB");
						documSize02 = documSize02+documSize01+",";
						documCount02 = documCount02+"0,";
						try {
							System.out.println("发动态图片保存的本地路径="+localBasePath+"__"+testName);
							docum[i].transferTo(new File(localBasePath+"__"+testName));
						} catch (Exception e) {
							System.out.println("保存发表的动态资源文件失败！！");
						}
					}
				}
			}
			Active active = new Active();
			
			active.setUser_id(user_id);
			type_a = "生活圈#";
			active.setType_a(type_a);
			active.setType_b(type_b);
			active.setSaysay(saysay);
			active.setTitle(title);
			active.setActive_pic(picName02);
			active.setDocum(documName02);
			active.setDocum_size(documSize02);
			active.setDoc_down_count(documCount02);
			active.setActive_creatime(new Timestamp(System.currentTimeMillis()));
			
			activeUserDao.addActive(active);
			jsonObject.put("msg", "发动态成功");
			jsonObject.put("status", true);
			return jsonObject;
		}else {//只要不是生活圈#类型就需要检查发动态的权限
			activeVerify = activeUserDao.queryStuAndCertiVerify(type_a);
			user = userDao.findByUserId(user_id);
			if("1".equals(activeVerify.getStu_verify()) && "0".equals(activeVerify.getCerti_verify()) 
					&& "0".equals(user.getStu_state())){
				//如果后台只开启了学生证验证
				System.out.println("zou l ................请您进行学生证信息验证!");
				jsonObject.put("msg", "请您进行学生证信息验证");
				jsonObject.put("status", false);
				jsonObject.put("code", 1);
				System.out.println("jsonObject="+jsonObject);
				return jsonObject;
			}else if ("0".equals(activeVerify.getStu_verify()) && "1".equals(activeVerify.getCerti_verify()) 
					&& "0".equals(user.getCerti_state())) {
				//如果后台只开启了毕业证验证
				System.out.println("zou l ................请您进行毕业证信息验证!");
				jsonObject.put("msg", "请您进行毕业证信息验证");
				jsonObject.put("status", false);
				jsonObject.put("code", 2);
				System.out.println("jsonObject="+jsonObject);
				return jsonObject;
			}else if ("1".equals(activeVerify.getStu_verify()) && "1".equals(activeVerify.getCerti_verify()) 
					&& ("0".equals(user.getStu_state()) || "0".equals(user.getCerti_state()))) {
				//后台开启了双重验证
				System.out.println("zou l ................请您完成信息验证!");
				jsonObject.put("msg", "请您完成信息验证");
				jsonObject.put("status", false);
				jsonObject.put("code", 3);
				System.out.println("jsonObject="+jsonObject);
				return jsonObject;
			}else if("首页动态".equals(type_a) && "0".equals(user.getVerify_state())) {
				System.out.println("zou l ................发首页动态需要身份验证!");
				jsonObject.put("code", 4);
				jsonObject.put("msg", "首页动态的发表需要身份验证哟");
				jsonObject.put("status", false);
				return jsonObject;
			}else if(pics!=null || docum!=null || !"".equals(saysay)) {//进了发动态
				//遍历保存发表动态的图片
				String picName = String.valueOf(System.currentTimeMillis());
				String picName02 = "";
				if(null!=pics && !"".equals(user_id)){
					//先单独保存到磁盘
					for(int i=0;i<pics.length;i++){
						System.out.println(pics[i].isEmpty());
						if(!pics[i].isEmpty()){//只要上传图片不为空
							picName = picName+"-"+i;
							String suffix = pics[i].getOriginalFilename().substring(pics[i].getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
							System.out.println("发说说图片的名称="+picName+suffix);
							picName02 = picName02+picName+suffix+",";
							try {
								System.out.println("发动态图片保存的本地路径="+localBasePath+picName+suffix);
								//保存图片到本地目录
								pics[i].transferTo(new File(localBasePath+picName+suffix));
							} catch (Exception e) {
								System.out.println("保存发表的动态图片失败！！");
							}
						}
					}
				}
				//保存资源文件
				String documName02 = "";
				String documSize02 = "";
				String documCount02 = "";
				if(null!=docum && !"".equals(user_id)){
					for(int i=0;i<docum.length;i++){
						if(!docum[i].isEmpty()){//只要上传资源不为空
							String testName = docum[i].getOriginalFilename();//获取上传文件的名称
							documName02 = documName02+"__"+testName+",";
							String documSize01 = String.valueOf(docum[i].getSize()/1024+"KB");
							documSize02 = documSize02+documSize01+",";
							documCount02 = documCount02+"0,";
							try {
								System.out.println("发动态图片保存的本地路径="+localBasePath+"__"+testName);
								docum[i].transferTo(new File(localBasePath+"__"+testName));
							} catch (Exception e) {
								System.out.println("保存发表的动态资源文件失败！！");
							}
						}
					}
				}
				Active active = new Active();
				
				active.setUser_id(user_id);
				active.setType_a(type_a);
				active.setType_b(type_b);
				active.setSaysay(saysay);
				active.setTitle(title);
				active.setActive_pic(picName02);
				active.setDocum(documName02);
				active.setDocum_size(documSize02);
				active.setDoc_down_count(documCount02);
				active.setActive_creatime(new Timestamp(System.currentTimeMillis()));
				
				activeUserDao.addActive(active);
				jsonObject.put("msg", "发动态成功");
				jsonObject.put("status", true);
				System.out.println(active);
				System.out.println(jsonObject);
				return jsonObject;
			}
		}
		jsonObject.put("status", true);
		jsonObject.put("code", 5);
		System.out.println("jsonObject="+jsonObject);
		return jsonObject;
	}
	
	/**
	 * 展示个人动态
	 * @param path 项目环境的路径
	 * @param hostPath 项目的url路径
	 */
	public JSONObject showOwnActive(String user_id, String v_user_id, int pageSize, 
			int pageNumber, String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> params02 = new HashMap<String, Object>();//用于添加访客用户的查询条件,无动态数据
		Map<String, Object> params03 = new HashMap<String, Object>();//用于添加访客用户的查询条件,有动态数据
		params.put("user_id", user_id);
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("start", start);
		params.put("end", end);
		int count = activeUserDao.queryActiveCounts(user_id);
		int v_count = activeUserDao.queryActiveCounts(v_user_id);//被拜访用户的动态数量
		if(user_id.equals(v_user_id) && count!=0) {//查询被访问用户的个人动态,--是自己查看自己的动态--,且有动态
			ArrayList<Active> ownActives = (ArrayList<Active>) activeUserDao.queryOwnActive(params);
			for(int i=0;i<ownActives.size();i++){
				//解析用户上传的图片资源
				List<String> aa = new ArrayList<String>();
				String pics = ownActives.get(i).getActive_pic();//获取每个用户的图片
				if(pics!=null || "".equals(pics)){
					String[] picsName = pics.split(",");
					String resultPicName = "";//url地址的图片
					for(String pic:picsName){
						if(!"".equals(pic)){
							aa.add(resultPicName+hostPath02+pic);
						}
					}
				}
				//解析用户上传的资源文件
				List<String> bb = new ArrayList<String>();
				String docums = ownActives.get(i).getDocum();
				if(docums!=null || "".equals(docums)){
					String[] documsName = docums.split(",");
					String resultDocumsName = "";
					for(String docum:documsName){
						if(!"".equals(docum)){
							bb.add(resultDocumsName+hostPath02+docum.replace("__", ""));
						}
					}
				}
				//判断用户对某个说说是够已经点赞
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("active_user_id", ownActives.get(i).getActive_user_id());
				param.put("com_user_id", user_id);
				if(commontDao.queryIsLaud(param) == null){
					ownActives.get(i).setIsLaud(0);
				}else {
					ownActives.get(i).setIsLaud(1);//已点赞
				}
				//添加用户的验证状态
				ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(user_id).getVerify_state()));
				//添加用户昵称
				ownActives.get(i).setUser_nickname(userDao.queryUserNickName(user_id));
				//添加点赞数量
				ownActives.get(i).setTotalLaud(
						commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
				//添加用户头像
				String profile = userDao.queryUserProfile(user_id);
				if(profile==null){
					ownActives.get(i).setPrifile(hostPath01+"avatar_def.png");
				}else {
					ownActives.get(i).setPrifile(hostPath01+profile);
				}
				//添加用户说说的评论数量
				ownActives.get(i).setTotalCommont(
						commontDao.totalCommont(ownActives.get(i).getActive_user_id()));
				//添加用户上传的图片资源
				ownActives.get(i).setPics(aa);
				//添加用户上传的资源文件
				ownActives.get(i).setDocums(bb);
			}
			jsonObject.put("msg", "个人动态展示成功");
			jsonObject.put("data", ownActives);
			jsonObject.put("status", true);
			jsonObject.put("totalActs", activeUserDao.queryTotalActs(user_id));//添加总的动态数量
			jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(user_id));//添加总的未查看评论数量
			return jsonObject;
		}else if (user_id.equals(v_user_id) && count==0) {//查询被访问用户的个人动态,--是自己查看自己的动态--,且没有动态
			jsonObject.put("status", true);
			jsonObject.put("msg", "当前用户没有动态");
			return jsonObject;
		}else if(v_count==0) {//访客模式的个人动态,且被访问用户没有动态数据
			System.out.println("个人动态是访客模式，访客uuid="+v_user_id);
			//增加访客
			params02.put("v_user_id", v_user_id);
			params02.put("user_id", user_id);
			Visitor visitor = userDao.queryVisitorByUserId(params02);
			if(visitor==null){//只要没有该用户的拜访信息就增加拜访信息
				params02.put("v_creatime", new Timestamp(System.currentTimeMillis()));
				userDao.saveVisitor(params02);
				jsonObject.put("status", true);
				jsonObject.put("msg", "被拜访用户没有动态,且增加了拜访用户");
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "被拜访用户没有动态,已有该用户的拜访");
			}
			return jsonObject;
		}else if (v_count!=0) {//访客模式的个人动态,--且被访问用户有动态数据--
			System.out.println("访客模式的个人动态,且被访问用户有动态数据");
			//添加访客用户
			params02.put("v_user_id", v_user_id);
			params02.put("user_id", user_id);
			Visitor visitor = userDao.queryVisitorByUserId(params02);
			if(visitor==null){//只要没有该用户的拜访信息就增加拜访信息
				params02.put("v_creatime", new Timestamp(System.currentTimeMillis()));
				userDao.saveVisitor(params02);
			}
			//先判断拜访者是否是粉丝
			Map<String, Object> params04 = new HashMap<String, Object>();
			params04.put("user_id", v_user_id);
			params04.put("f_user_id", user_id);
			if(friendsDao.queryIsAttention(params04) == null){//---不是fans,生活圈可见---
				//查询用户的动态数据
				params03.put("user_id", v_user_id);
				params03.put("start", start);
				params03.put("end", end);
				ArrayList<Active> ownActives = (ArrayList<Active>) activeUserDao.queryOwnActive(params03);
				for(int i=0;i<ownActives.size();i++){
					//解析用户上传的图片资源
					List<String> aa = new ArrayList<String>();
					String pics = ownActives.get(i).getActive_pic();//获取每个用户的图片
					if(pics!=null || "".equals(pics)){
						String[] picsName = pics.split(",");
						String resultPicName = "";//url地址的图片
						for(String pic:picsName){
							if(!"".equals(pic)){
								aa.add(resultPicName+hostPath02+pic);
							}
						}
					}
					//解析用户上传的资源文件
					List<String> bb = new ArrayList<String>();
					String docums = ownActives.get(i).getDocum();
					if(docums!=null || "".equals(docums)){
						String[] documsName = docums.split(",");
						String resultDocumsName = "";
						for(String docum:documsName){
							if(!"".equals(docum)){
								bb.add(resultDocumsName+hostPath02+docum.replace("__", ""));
							}
						}
					}
					//判断用户对某个说说是够已经点赞
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("active_user_id", ownActives.get(i).getActive_user_id());
					param.put("com_user_id", user_id);
					if(commontDao.queryIsLaud(param) == null){
						ownActives.get(i).setIsLaud(0);
					}else {
						ownActives.get(i).setIsLaud(1);//已点赞
					}
					//添加用户的验证状态
					ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(v_user_id).getVerify_state()));
					//添加用户昵称
					ownActives.get(i).setUser_nickname(userDao.queryUserNickName(v_user_id));
					//添加点赞数量
					ownActives.get(i).setTotalLaud(
							commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
					//添加用户头像
					String profile = userDao.queryUserProfile(v_user_id);
					if(profile==null){
						ownActives.get(i).setPrifile(hostPath01+"avatar_def.png");
					}else {
						ownActives.get(i).setPrifile(hostPath01+profile);
					}
					//添加用户说说的评论数量
					ownActives.get(i).setTotalCommont(
							commontDao.totalCommont(ownActives.get(i).getActive_user_id()));
					//添加用户上传的图片资源
					ownActives.get(i).setPics(aa);
					//添加用户上传的资源文件
					ownActives.get(i).setDocums(bb);
				}
				jsonObject.put("msg", "被拜访用户有动态,且增加了拜访用户");
				jsonObject.put("data", ownActives);
				jsonObject.put("status", true);
				jsonObject.put("totalActs", activeUserDao.queryTotalActs(v_user_id));//添加总的动态数量
				jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(v_user_id));//添加总的未查看评论数量
				return jsonObject;
			}else {//---是fans,根据用户属性的生活圈开关值是否查看生活圈---
				params03.put("user_id", v_user_id);
				params03.put("start", start);
				params03.put("end", end);
				
				User user = userDao.findById(v_user_id);
				if(user.getLife_see()==0){//生活圈#动态粉丝不可见
					params03.put("fanSeeState", 0);
				}
				ArrayList<Active> ownActives = (ArrayList<Active>) activeUserDao.queryOtherOneActive(params03);
				for(int i=0;i<ownActives.size();i++){
					//解析用户上传的图片资源
					List<String> aa = new ArrayList<String>();
					String pics = ownActives.get(i).getActive_pic();//获取每个用户的图片
					if(pics!=null || "".equals(pics)){
						String[] picsName = pics.split(",");
						String resultPicName = "";//url地址的图片
						for(String pic:picsName){
							if(!"".equals(pic)){
								aa.add(resultPicName+hostPath02+pic);
							}
						}
					}
					//解析用户上传的资源文件
					List<String> bb = new ArrayList<String>();
					String docums = ownActives.get(i).getDocum();
					if(docums!=null || "".equals(docums)){
						String[] documsName = docums.split(",");
						String resultDocumsName = "";
						for(String docum:documsName){
							if(!"".equals(docum)){
								bb.add(resultDocumsName+hostPath02+docum.replace("__", ""));
							}
						}
					}
					//判断用户对某个说说是够已经点赞
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("active_user_id", ownActives.get(i).getActive_user_id());
					param.put("com_user_id", user_id);
					if(commontDao.queryIsLaud(param) == null){
						ownActives.get(i).setIsLaud(0);
					}else {
						ownActives.get(i).setIsLaud(1);//已点赞
					}
					//添加用户的验证状态
					ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(v_user_id).getVerify_state()));
					//添加用户昵称
					ownActives.get(i).setUser_nickname(userDao.queryUserNickName(v_user_id));
					//添加点赞数量
					ownActives.get(i).setTotalLaud(
							commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
					//添加用户头像
					String profile = userDao.queryUserProfile(v_user_id);
					if(profile==null){
						ownActives.get(i).setPrifile(hostPath01+"avatar_def.png");
					}else {
						ownActives.get(i).setPrifile(hostPath01+profile);
					}
					//添加用户说说的评论数量
					ownActives.get(i).setTotalCommont(
							commontDao.totalCommont(ownActives.get(i).getActive_user_id()));
					//添加用户上传的图片资源
					ownActives.get(i).setPics(aa);
					//添加用户上传的资源文件
					ownActives.get(i).setDocums(bb);
				}
				jsonObject.put("msg", "被拜访用户有动态");
				jsonObject.put("data", ownActives);
				jsonObject.put("status", true);
				jsonObject.put("totalActs", activeUserDao.queryTotalActs(v_user_id));//添加总的动态数量
				jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(v_user_id));//添加总的未查看评论数量
				return jsonObject;
			}
		}
		return jsonObject;
	}
	
	//主题大类动态的展示
	public JSONObject showClassActive(String user_id, String type_a, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02, String com_user_id){
		System.out.println("进了主题大类动态的展示的service层！！！");
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("type_a", type_a);
		params.put("start", start);
		params.put("end", end);
		
		ArrayList<ClassActive> classActives = 
				(ArrayList<ClassActive>) activeUserDao.queryClassActive(params);
		for(int i=0;i<classActives.size();i++){
			//解析用户上传的图片资源
			List<String> aa = new ArrayList<String>();
			String pics = classActives.get(i).getActive_pic();//获取每个用户的图片
			if(pics!=null || "".equals(pics)){
				String[] picsName = pics.split(",");
				
				String resultPicName = "";//url地址的图片
				for(String pic:picsName){
					if(!"".equals(pic)){
						aa.add(resultPicName+hostPath02+pic);
					}
				}
			}
			//解析用户上传的资源文件
			List<String> bb = new ArrayList<String>();
			String docums = classActives.get(i).getDocum();
			if(docums!=null || "".equals(docums)){
				String[] documsName = docums.split(",");
				String resultDocumsName = "";
				for(String docum:documsName){
					if(!"".equals(docum)){
						bb.add(resultDocumsName+hostPath02+docum.replace("__", ""));
					}
				}
			}
			
			//判断用户对某个说说是够已经点赞
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("active_user_id", classActives.get(i).getActive_user_id());
			param.put("com_user_id", com_user_id);
			if(commontDao.queryIsLaud(param) == null){
				classActives.get(i).setIsLaud(0);
			}else {
				classActives.get(i).setIsLaud(1);//已点赞
			}
			//添加用户的验证状态
			classActives.get(i).setIsVerify(
					Integer.valueOf(userDao.findByUserId(
							classActives.get(i).getUser_id()).getVerify_state()));
			//添加点赞数量
			classActives.get(i).setTotalLaud(
					commontDao.totalLaud(classActives.get(i).getActive_user_id()));
			//添加用户头像
			String profile = userDao.queryUserProfile(classActives.get(i).getUser_id());
			if(profile==null){
				classActives.get(i).setProfile(hostPath01+"avatar_def.png");
			}else {
				classActives.get(i).setProfile(hostPath01+profile);
			}
			//添加用户说说的评论数量
			classActives.get(i).setTotalCommont(
					commontDao.totalCommont(classActives.get(i).getActive_user_id()));
			//添加用户上传的图片资源
			classActives.get(i).setPics(aa);
			//添加用户上传的资源文件
			classActives.get(i).setDocums(bb);
		}
		jsonObject.put("msg", "首页大类的动态展示成功！");
		jsonObject.put("status", true);
		jsonObject.put("data", classActives);
		System.out.println(classActives.toString());
		return jsonObject;
	}
	
	public JSONObject showFriendsActvie(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02){
		System.out.println("进了朋友圈动态的展示的service层！！！");
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("user_id", user_id);
		params.put("start", start);
		params.put("end", end);
		
		ArrayList<CycFriendsActvie> friendsActive = 
				(ArrayList<CycFriendsActvie>) activeUserDao.queryFriendsActvie(params);
		List<Integer> cc = new ArrayList<Integer>();//用于改变直接评论的查看状态的dao查询参数
		for(int i=0;i<friendsActive.size();i++){
			//解析用户上传的图片资源
			List<String> aa = new ArrayList<String>();
			String pics = friendsActive.get(i).getActive_pic();//获取每个用户的图片
			if(pics!=null || "".equals(pics)){
				String[] picsName = pics.split(",");
				String resultPicName = "";//url地址的图片
				if(picsName.length>0){//只要用户有图片
					for(String pic:picsName){
						if(!"".equals(pic)){
							aa.add(resultPicName+hostPath02+pic);
						}
					}
				}
			}
			//解析用户上传的资源文件
			List<String> bb = new ArrayList<String>();
			String docums = friendsActive.get(i).getDocum();
			if(docums!=null || "".equals(docums)){
				String[] documsName = docums.split(",");
				String resultDocumsName = "";
				for(String docum:documsName){
					if(!"".equals(docum)){
						bb.add(resultDocumsName+hostPath02+docum.replace("__", ""));
					}
				}
			}
			//判断用户对某个说说是够已经点赞
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("active_user_id", friendsActive.get(i).getActive_user_id());
			param.put("com_user_id", user_id);
			if(commontDao.queryIsLaud(param) == null){
				friendsActive.get(i).setIsLaud(0);;//没点赞
			}else {
				friendsActive.get(i).setIsLaud(1);
			}
			//添加用户的验证状态
			friendsActive.get(i).setIsVerify(Integer.valueOf(
					Integer.valueOf(userDao.findByUserId(
							friendsActive.get(i).getUser_id()).getVerify_state())));
			//添加点赞的数量
			friendsActive.get(i).setTotalLaud(
					commontDao.totalLaud(friendsActive.get(i).getActive_user_id()));
			//添加用户图片
			String profile = friendsActive.get(i).getProfile();
			if(profile==null){
				friendsActive.get(i).setProfile(hostPath01+"avatar_def.png");
			}else {
				friendsActive.get(i).setProfile(hostPath01+profile);
			}
			//添加说说评论的数量
			friendsActive.get(i).setTotalCommont(
					commontDao.totalCommont(friendsActive.get(i).getActive_user_id()));
			//添加用户上传的图片资源
			friendsActive.get(i).setPics(aa);
			//添加用户上传的资源文件
			friendsActive.get(i).setDocums(bb);
			
			//添加修改直接评论的查看状态的array参数
			cc.add(friendsActive.get(i).getActive_user_id());
		}
		jsonObject.put("msg", "朋友圈的动态展示成功！");
		jsonObject.put("data", friendsActive);
		jsonObject.put("status", true);
		jsonObject.put("totalActs", activeUserDao.queryTotalActs(user_id));//添加总的动态数量
		jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(user_id));//添加总的未查看评论数量
		
		System.out.println(friendsActive.toString());
		//修改直接评论的查看状态
		Map<String, Object> params02 = new HashMap<String, Object>();
		params02.put("user_id", user_id);
		params02.put("actIds", cc);
		System.out.println("params02="+params02);
		commontDao.batchModifyComNoSee(params02);
		
		return jsonObject;
	}
	
	public JSONObject addSeeActive(int active_user_id){
		JSONObject jsonObject = new JSONObject();
		Active seeActive = activeUserDao.queryActiveSee(active_user_id);//先查出说说的数量
		
		seeActive.setActive_user_id(active_user_id);
		seeActive.setSee(seeActive.getSee()+1);
		activeUserDao.updateSee(seeActive);//更新说说查看的数量
		jsonObject.put("msg", "添加说说查看的数量成功");
		return jsonObject;
	}
	
	/*
	 * 用于后台对说说的审核
	 */
	public JSONObject adminActive(int pageSize, int pageNumber, String searchText, 
			String startDate, String endDate, String sortName, String sortOrder, String hostPath){
		JSONObject jsonObject = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		if (searchText != null) {
			params.put("searchText", "%"+searchText+"%");
		}else {
			params.put("searchText", searchText);
		}
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("sortName", sortName);
		params.put("sortOrder", sortOrder);
		List<Active> adminActive = activeUserDao.queryAdmin(params);
		for(Active act:adminActive){
			String pics = act.getActive_pic();
			String resultPicName = "";//url地址的图片
			List<String> aa = new ArrayList<String>();
			if(pics != null && !"".equals(pics)){//只要用户有图片
				String[] picsName = pics.split(",");
				for(String pic:picsName){
					aa.add(resultPicName+hostPath+pic);
				}
			}
			
			String docums = act.getDocum();
			String resultDocum = "";//url资源文件
			List<String> bb = new ArrayList<String>();
			if(docums != null && !"".equals(docums)){//只要有资源文件
				String[] documsName = docums.split(",");
				for(String docum:documsName){
					bb.add(resultDocum+hostPath+docum);
				}
			}
			//添加图片
			act.setPics(aa);
			//添加资源文件
			act.setDocums(bb);
			act.setUser_nickname(userDao.queryUserNickName(act.getUser_id()));
		}
		jsonObject.put("rows", adminActive);
		jsonObject.put("total", activeUserDao.queryAdminTotal(params));
		return jsonObject;
	}
	
	public JSONObject verifyActive(int active_user_id){
		JSONObject jsonObject = new JSONObject();
		activeUserDao.oneActiveVerify(active_user_id);
		jsonObject.put("msg", "动态审核通过成功！！");
		return jsonObject;
	}
	
	public JSONObject noVerifyActive(int active_user_id){
		JSONObject jsonObject = new JSONObject();
		activeUserDao.oneActiveNoVerify(active_user_id);
		jsonObject.put("msg", "动态审核不通过成功！！");
		return jsonObject;
	}
	
	public JSONObject authActive(int pageSize, int pageNumber, String searchText, 
			String sortName, String sortOrder, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		if (searchText != null) {
			params.put("searchText", "%"+searchText+"%");
		}else {
			params.put("searchText", searchText);
		}
		params.put("sortName", sortName);
		params.put("sortOrder", sortOrder);
		List<ActiveVerify> verifies = activeUserDao.activeVerifies(params);
		if(verifies != null){
			for(ActiveVerify v:verifies){
				if(v.getTheme_pic() != null){
					v.setTheme_pic(hostPath02+v.getTheme_pic());
				}
				if(v.getTwo_pic() != null){
					v.setTwo_pic(hostPath02+v.getTwo_pic());
				}
			}
		}
		jsonObject.put("rows", verifies);
		jsonObject.put("total", activeUserDao.activeVerifiesTotal());
		return jsonObject;
	}
	
	public List<ThemeCateg> allThemeCateg(String hostPath03){
		List<ActiveVerify> verifies = activeUserDao.allActiveVerifies();
		List<ThemeCateg> allActiveCategs = new ArrayList<ThemeCateg>();
		if(verifies != null){
			for(ActiveVerify av:verifies){
				//设置主题分类
				if(!"首页动态".equals(av.getClass_active())){
					ThemeCateg themeCateg = new ThemeCateg();
					themeCateg.setThemeCateg(av.getClass_active().replace("#", ""));
					themeCateg.setThemeCategUrl(hostPath03+av.getTheme_pic());
					allActiveCategs.add(themeCateg);
				}
			}
			//添加二级分类
			for(ThemeCateg tc:allActiveCategs){
				List<TwoCateg> twoCategs = new ArrayList<TwoCateg>();
				List<ActiveVerify> allVerifies = activeUserDao.allTwoActiveVerifies(tc.getThemeCateg());
				if(allVerifies != null){
					for(ActiveVerify av:allVerifies){
						if(av.getTwo_class() != null){
							TwoCateg twoCateg = new TwoCateg();
							twoCateg.setTwoCateg(av.getTwo_class().replace("#", ""));
							twoCateg.setTwoCategUrl(hostPath03+av.getTwo_pic());
							twoCategs.add(twoCateg);
						}
					}
				}
				tc.setTwoCategs(twoCategs);
			}
		}
		return allActiveCategs;
	}
	
	public JSONObject modifyVerify(String state, String class_active, String verify){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("class_active", class_active);
		if("stu_verify".equals(verify)){
			params.put("stu_verify", state);
			activeUserDao.modifyStuVerify(params);
			System.out.println("修改了学生证验证！！");
			jsonObject.put("msg", "学生证验证修改成功！！");
		}else if ("certi_verify".equals(verify)) {
			params.put("certi_verify", state);
			activeUserDao.modifyCertiVerify(params);
			System.out.println("修改了毕业证验证！！"+params.toString());
			jsonObject.put("msg", "毕业证验证修改成功！！");
		}
		return jsonObject;
	}
	
	public JSONObject keyActive(String text, String hostPath01, String hostPath02, 
			int pageSize, int pageNumber){
		JSONObject jsonObject = new JSONObject();
		String str = "%"+text+"%";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("text", str);
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("start", start);
		params.put("end", end);
		List<Active> actives = activeUserDao.queryKeyActive(params);
		if(actives.size()>0){
			for(Active active:actives){
				User user  = userDao.findById(active.getUser_id());
				String pics = active.getActive_pic();//获取每个用户的图片
				String[] picsName = pics.split(",");
				
				String resultPicName = "";//url地址的图片
				List<String> aa = new ArrayList<String>();
				if(picsName.length>0){//只要用户有图片
					for(String pic:picsName){
						System.out.println("某次的图片数量"+(picsName.length-1));
						System.out.println("picname="+pic);
						aa.add(resultPicName+hostPath02+pic);
					}
				}
				//添加用户昵称
				active.setUser_nickname(user.getUser_nickname());
				//添加点赞数量
				active.setTotalLaud(commontDao.totalLaud(active.getActive_user_id()));
				//添加用户头像
				String profile = user.getProfile();
				//添加是否认证
				active.setIsVerify(Integer.valueOf(user.getVerify_state()));
				if(profile==null){
					active.setPrifile(hostPath01+"avatar_def.png");
				}else {
					active.setPrifile(hostPath01+profile);
				}
				//添加用户说说的评论数量
				active.setTotalCommont(commontDao.totalCommont(active.getActive_user_id()));
				//添加有url的图片
				active.setPics(aa);
			}
		}
		//如果text不为空则保存到textkey表中
		if("".equals(text)){
			activeUserDao.saveTextKey(text);
		}
		jsonObject.put("status", true);
		jsonObject.put("msg", "内容检索成功");
		jsonObject.put("data", actives);
		return jsonObject;
	}
	
	public JSONObject batchDoVerify(String ids){
		JSONObject jsonObject = new JSONObject();
		List<Integer> idList = new ArrayList<Integer>();
		if(!"".equals(ids)){
			String[] spIds = ids.split(",");
			for(int i=0;i<spIds.length;i++){
				idList.add(Integer.valueOf(spIds[i]));
			}
		}
		int count = activeUserDao.batchModifyAcvtive(idList);
		jsonObject.put("status", true);
		jsonObject.put("msg", "成功审核了"+count+"条动态");
		return jsonObject;
	}
	
	public JSONObject deleteOneActive(int active_user_id, String user_id){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("active_user_id", active_user_id);
		params.put("user_id", user_id);
		Active act = activeUserDao.queryOneActive(params);
		if(act==null){
			jsonObject.put("msg", "不能删除别人的动态");
			jsonObject.put("status", false);
		}else {
			activeUserDao.deleteOneActive(active_user_id);//删除动态
			List<Commont> comms = commontDao.queryCommontsByActId(active_user_id);
			//先删除子评论的评论
			ArrayList<Integer> comIds = new ArrayList<Integer>();
			if(comms!=null){
				for(Commont comm:comms){
					comIds.add(comm.getCom_id());
				}
			}
			commontDao.deleteChildCommsByCommId(comIds);
			//再删除子评论
			commontDao.deleteCommsByActId(active_user_id);
			jsonObject.put("status", true);
			jsonObject.put("msg", "动态删除成功");
		}
		return jsonObject;
	}
	
	public JSONObject rankActiveKey(){
		JSONObject jsonObject = new JSONObject();
		ArrayList<String> resultHotKeys = new ArrayList<String>();
		List<TextKey> hotKey = activeUserDao.hotTextKey();
		if(hotKey!=null){
			for(TextKey k:hotKey){
				resultHotKeys.add(k.getText());
			}
		}
		jsonObject.put("status", true);
		jsonObject.put("data", resultHotKeys);
		return jsonObject;
	}
	
	public JSONObject addFavor(int active_user_id, String user_id){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("active_user_id", active_user_id);
		Favorites favor = activeUserDao.queryOneFavor(params);
		if(favor==null){//只要改用户没有收藏过该动态,就增加一条收藏记录
			params.put("favor_creatime", new Timestamp(System.currentTimeMillis()));
			activeUserDao.saveFavorites(params);
		}
		
		jsonObject.put("status", true);
		jsonObject.put("msg", "收藏添加成功");
		return jsonObject;
	}
	
	public JSONObject favorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("start", start);
		params.put("end", end);
		List<Active> favors = activeUserDao.queryFavoritesList(params);
		if(favors.size()>0){
			for(Active favor:favors){
				User user  = userDao.findById(favor.getUser_id());
				String pics = favor.getActive_pic();//获取每个用户的图片
				String[] picsName = pics.split(",");
				
				String resultPicName = "";//url地址的图片
				List<String> aa = new ArrayList<String>();
				if(picsName.length>0){//只要用户有图片
					for(String pic:picsName){
						aa.add(resultPicName+hostPath02+pic);
					}
				}
				//添加用户昵称
				favor.setUser_nickname(user.getUser_nickname());
				//添加点赞数量
				favor.setTotalLaud(commontDao.totalLaud(favor.getActive_user_id()));
				//添加用户头像
				String profile = user.getProfile();
				//添加是否认证
				favor.setIsVerify(Integer.valueOf(user.getVerify_state()));
				if(profile==null){
					favor.setPrifile(hostPath01+"avatar_def.png");
				}else {
					favor.setPrifile(hostPath01+profile);
				}
				//添加用户说说的评论数量
				favor.setTotalCommont(commontDao.totalCommont(favor.getActive_user_id()));
				//添加有url的图片
				favor.setPics(aa);
			}
		}
		jsonObject.put("status", true);
		jsonObject.put("msg", "收藏夹查询成功");
		jsonObject.put("data", favors);
		return jsonObject;
	}
	
	public JSONObject deleteOneFavor(int favor_id){
		JSONObject jsonObject = new JSONObject();
		activeUserDao.deleteOneFavor(favor_id);
		jsonObject.put("status", true);
		jsonObject.put("msg", "收藏删除成功");
		return jsonObject;
	}
	
	public JSONObject batchDelThemeCateg(String ids){
		JSONObject jsonObject = new JSONObject();
		List<Integer> idList = new ArrayList<Integer>();
		if(!"".equals(ids)){
			String[] spIds = ids.split(",");
			for(int i=0;i<spIds.length;i++){
				idList.add(Integer.valueOf(spIds[i]));
			}
		}
		int count = activeUserDao.batchDelThemeCateg(idList);
		jsonObject.put("status", true);
		jsonObject.put("msg", "成功删除了"+count+"条主题分类");
		return jsonObject;
	}
	
	public JSONObject attachThemeCateg(MultipartFile themePic, String themeCateg, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		
		int count = activeUserDao.queryByThemeName(themeCateg);
		if(count>0){//更新主题分类
			if(themePic != null){
				if(!themePic.isEmpty()){
					//先删除原始ui主题图片
					String orgPicName = themePic.getOriginalFilename();
					File file = new File(localBasePath+orgPicName);
					if(file.exists()){
						file.delete();
					}
					//更新ui主题图片
					String picName = String.valueOf(System.currentTimeMillis());
					String suffix = themePic.getOriginalFilename().substring(themePic.getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
					try {
						//保存图片到本地目录
						themePic.transferTo(new File(localBasePath+picName+suffix));
						//保存到数据库
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("class_active", themeCateg);
						params.put("theme_pic", picName+suffix);
						params.put("act_lastmodifytime", new Timestamp(System.currentTimeMillis()));
						activeUserDao.updateThemeCateg(params);
						jsonObject.put("status", true);
						jsonObject.put("msg", "更新主题大类成功");
						return jsonObject;
					} catch (Exception e) {
						System.out.println("后台保存主题类别的UI图标到磁盘失败!!");
						jsonObject.put("status", false);
						jsonObject.put("msg", "更新主题大类失败");
						return jsonObject;
					}
				}
			}
		}else {
			String picName = String.valueOf(System.currentTimeMillis());
			if (themePic != null) {
				if(!themePic.isEmpty()){
					String suffix = themePic.getOriginalFilename().substring(themePic.getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
					try {
						//保存图片到本地目录
						themePic.transferTo(new File(localBasePath+picName+suffix));
						//保存到数据库
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("class_active", themeCateg);
						params.put("theme_pic", picName+suffix);
						params.put("act_creatime", new Timestamp(System.currentTimeMillis()));
						activeUserDao.saveThemeCateg(params);
						jsonObject.put("status", true);
						jsonObject.put("msg", "添加主题大类成功");
						return jsonObject;
					} catch (Exception e) {
						System.out.println("后台保存主题类别的UI图标到磁盘失败!!");
						jsonObject.put("status", false);
						jsonObject.put("msg", "添加主题大类失败");
						return jsonObject;
					}
				}
			}
		}
		return jsonObject;
	}
	
	public JSONObject attachActiveTwoCateg(MultipartFile twoPic, String themeCateg, 
			String twoCateg, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		String picName = String.valueOf(System.currentTimeMillis());
		if (twoPic != null) {
			if(!twoPic.isEmpty()){
				String suffix = twoPic.getOriginalFilename().substring(twoPic.getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
				try {
					//保存图片到本地目录
					twoPic.transferTo(new File(localBasePath+picName+suffix));
					//保存到数据库
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("class_active", themeCateg);
					params.put("two_class", twoCateg);
					params.put("two_pic", picName+suffix);
					params.put("act_creatime", new Timestamp(System.currentTimeMillis()));
					System.out.println(params);
					activeUserDao.saveTwoCateg(params);
					jsonObject.put("status", true);
					jsonObject.put("msg", "添加二级分类成功");
					return jsonObject;
				} catch (Exception e) {
					System.out.println("后台保存二级类别的UI图标到磁盘失败!!");
					jsonObject.put("status", false);
					jsonObject.put("msg", "添加二级分类失败");
					return jsonObject;
				}
			}
		}
		return jsonObject;
	}
	
	public JSONObject activeCategReport(int pageSize, int pageNumber, 
			String startDate, String endDate){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("start", start);
		params.put("end", end);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		List<ActiveCategReport> acts = activeUserDao.queryActiveCategReport(params);
		ActiveCategReport acr = new ActiveCategReport();
		acr.setThemeType("合计");
		acr.setActiveCount(activeUserDao.queryAllNetActive());
		acts.add(acr);
		jsonObject.put("rows", acts);
		jsonObject.put("total", activeUserDao.totalActiveCategReport(params));
		return jsonObject;
	}
	
	
}
