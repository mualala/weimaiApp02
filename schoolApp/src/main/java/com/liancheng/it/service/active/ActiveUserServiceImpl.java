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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.dao.active.ActiveUserDao;
import com.liancheng.it.dao.commont.CommontDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ClassActive;
import com.liancheng.it.entity.active.CycFriendsActvie;
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
	
	
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, 
			String localBasePath) {
		JSONObject jsonObject = new JSONObject();
		//判断后台对发动态是否开启了个人信息验证
		System.out.println("type_a="+type_a);
		ActiveVerify activeVerify = activeUserDao.queryStuAndCertiVerify(type_a);
		User user = userDao.findByUserId(user_id);
		
		System.out.println("ActiveVerify="+activeVerify.toString());
		System.out.println("User="+user.toString());
		
		if("1".equals(activeVerify.getStu_verify()) && "0".equals(activeVerify.getCerti_verify()) 
				&& "0".equals(user.getStu_state())){
			//如果后台只开启了学生证验证
			System.out.println("zou l ................请您进行学生证信息验证!");
			jsonObject.put("msg", "请您进行学生证信息验证");
			jsonObject.put("status", false);
			return jsonObject;
		}else if ("0".equals(activeVerify.getStu_verify()) && "1".equals(activeVerify.getCerti_verify()) 
				&& "0".equals(user.getCerti_state())) {
			//如果后台只开启了毕业证验证
			System.out.println("zou l ................请您进行毕业证信息验证!");
			jsonObject.put("msg", "请您进行毕业证信息验证");
			jsonObject.put("status", false);
			return jsonObject;
		}else if ("1".equals(activeVerify.getStu_verify()) && "1".equals(activeVerify.getCerti_verify()) 
				&& ("0".equals(user.getStu_state()) || "0".equals(user.getCerti_state()))) {
			//后台开启了双重验证
			System.out.println("zou l ................请您完成信息验证!");
			jsonObject.put("msg", "请您完成信息验证");
			jsonObject.put("status", false);
			return jsonObject;
		}else {
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
			active.setActive_pic(picName02);
			active.setDocum(documName02);
			active.setDocum_size(documSize02);
			active.setDoc_down_count(documCount02);
			active.setActive_creatime(new Timestamp(System.currentTimeMillis()));
			
			activeUserDao.addActive(active);
			jsonObject.put("msg", "发动态成功");
			jsonObject.put("status", true);
			return jsonObject;
		}
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
		if(user_id.equals(v_user_id) && count!=0) {//查询被访问用户的个人动态,是自己查看自己的动态,且有动态
			System.out.println("个人动态是自己查看自己的动态。。。");
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
				ownActives.get(i).setPrifile(hostPath01+userDao.queryUserProfile(user_id));
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
			System.out.println(ownActives.toString());
			return jsonObject;
		}else if (user_id.equals(v_user_id) && count==0) {//查询被访问用户的个人动态,是自己查看自己的动态,且没有动态
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
		}else if (v_count!=0) {//访客模式的个人动态,且被访问用户有动态数据
			System.out.println("访客模式的个人动态,且被访问用户有动态数据");
			//添加访客用户
			params02.put("v_user_id", v_user_id);
			params02.put("user_id", user_id);
			Visitor visitor = userDao.queryVisitorByUserId(params02);
			if(visitor==null){//只要没有该用户的拜访信息就增加拜访信息
				params02.put("v_creatime", new Timestamp(System.currentTimeMillis()));
				userDao.saveVisitor(params02);
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
					ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(user_id).getVerify_state()));
					//添加用户昵称
					ownActives.get(i).setUser_nickname(userDao.queryUserNickName(user_id));
					//添加点赞数量
					ownActives.get(i).setTotalLaud(
							commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
					//添加用户头像
					ownActives.get(i).setPrifile(hostPath01+userDao.queryUserProfile(user_id));
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
				return jsonObject;
			}else {
				System.out.println("被拜访用户有动态,已有该用户的拜访");
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
					ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(user_id).getVerify_state()));
					//添加用户昵称
					ownActives.get(i).setUser_nickname(userDao.queryUserNickName(user_id));
					//添加点赞数量
					ownActives.get(i).setTotalLaud(
							commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
					//添加用户头像
					ownActives.get(i).setPrifile(hostPath01+userDao.queryUserProfile(user_id));
					//添加用户说说的评论数量
					ownActives.get(i).setTotalCommont(
							commontDao.totalCommont(ownActives.get(i).getActive_user_id()));
					//添加用户上传的图片资源
					ownActives.get(i).setPics(aa);
					//添加用户上传的资源文件
					ownActives.get(i).setDocums(bb);
				}
				jsonObject.put("msg", "被拜访用户有动态,已有该用户的拜访");
				jsonObject.put("data", ownActives);
				jsonObject.put("status", false);
				return jsonObject;
			}
		}
//		else if (!user_id.equals(v_user_id)) {//查询被访问用户的个人动态
//			return jsonObject;
//		}
		return jsonObject;
	}
	
	//大类动态的展示
	public JSONObject showClassActive(String type_a, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02, String com_user_id){
		System.out.println("进了大类动态的展示的service层！！！");
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
			classActives.get(i).setPrifile(hostPath01+userDao.queryUserProfile(
					classActives.get(i).getUser_id()));
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
			String hostPath01, String hostPath02, String com_user_id){
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
			param.put("com_user_id", com_user_id);
			if(commontDao.queryIsLaud(param) == null){
				friendsActive.get(i).setIsLaud("0");;//没点赞
			}else {
				friendsActive.get(i).setIsLaud("1");
			}
			//添加用户的验证状态
			friendsActive.get(i).setIsVerify(Integer.valueOf(
					Integer.valueOf(userDao.findByUserId(
							friendsActive.get(i).getUser_id()).getVerify_state())));
			//添加点赞的数量
			friendsActive.get(i).setTotalLaud(
					commontDao.totalLaud(friendsActive.get(i).getActive_user_id()));
			//添加用户图片
			friendsActive.get(i).setPrifile(hostPath01+userDao.queryUserProfile(
					friendsActive.get(i).getF_user_id()));
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
		jsonObject.put("msg", "添加说说查看的数量成功！");
		return jsonObject;
	}
	
	/*
	 * 用于后台对说说的审核
	 */
	public JSONObject adminActive(int pageSize, int pageNumber, String hostPath){
		JSONObject jsonObject = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		System.out.println("start="+start+",end="+end);
		List<Active> adminActive = activeUserDao.queryAdmin(params);
		for(Active act:adminActive){
			String pics = act.getActive_pic();
			String resultPicName = "";//url地址的图片
			List<String> aa = new ArrayList<String>();
			if(pics != null || !"".equals(pics)){//只要用户有图片
				String[] picsName = pics.split(",");
				for(String pic:picsName){
					aa.add(resultPicName+hostPath+pic);
				}
			}
			
			String docums = act.getDocum();
			String resultDocum = "";//url资源文件
			List<String> bb = new ArrayList<String>();
			if(docums != null || !"".equals(docums)){//只要有资源文件
				System.out.println(docums);
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
			try {
				act.setC_time(DateUtil.formatDate(act.getActive_creatime()));
			} catch (Exception e) {
				System.out.println("说说的时间转换错误！！");
			}
		}
		jsonObject.put("rows", adminActive);
		jsonObject.put("total", activeUserDao.queryAdminTotal());
		System.out.println(jsonObject.toJSONString());
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
	
	public JSONObject authActive(int pageSize, int pageNumber){
		JSONObject jsonObject = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		List<ActiveVerify> verifies = activeUserDao.activeVerifies(params);
		jsonObject.put("rows", verifies);
		jsonObject.put("total", activeUserDao.activeVerifiesTotal());
		return jsonObject;
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
	
	public JSONObject keyActive(String text, String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		String str = "%"+text+"%";
		List<Active> actives = activeUserDao.queryKeyActive(str);
		if(actives.size()>0){
			for(Active active:actives){
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
				active.setUser_nickname(userDao.queryUserNickName(active.getUser_id()));
				//添加点赞数量
				active.setTotalLaud(commontDao.totalLaud(active.getActive_user_id()));
				//添加用户头像
				active.setPrifile(hostPath01+userDao.queryUserProfile(active.getUser_id()));
				//添加用户说说的评论数量
				active.setTotalCommont(commontDao.totalCommont(active.getActive_user_id()));
				//添加有url的图片
				active.setPics(aa);
			}
		}
		jsonObject.put("msg", "内容检索成功!");
		jsonObject.put("data", actives);
		System.out.println(actives.toString());
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
	
	
}
