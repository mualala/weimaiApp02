package com.liancheng.it.service.commont;

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

import com.liancheng.it.dao.active.ActiveUserDao;
import com.liancheng.it.dao.commont.CommontDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.active.ClassActive;
import com.liancheng.it.entity.active.Favorites;
import com.liancheng.it.entity.commont.ChildCommont;
import com.liancheng.it.entity.commont.Commont;
import com.liancheng.it.entity.commont.LaudUser;


/**
 * 用户评论等
 */
@Service("commontService")//扫描service
@Aspect
@Transactional
public class CommontServiceimpl implements CommontService {
	
	@Resource(name="commontDao")
	private CommontDao commontDao;
	@Resource(name="activeUserDao")
	private ActiveUserDao activeUserDao;
	@Resource(name="userDao")
	private UserDao userDao;
	
	public JSONObject addAction(String com_user_id, String user_id, int active_user_id, String content){
		Commont commont = new Commont();
		
		commont.setCom_user_id(com_user_id);
		commont.setUser_id(user_id);
		commont.setActive_user_id(active_user_id);
		commont.setContent(content);
		commont.setCom_creatime(new Timestamp(System.currentTimeMillis()));
		commontDao.addAction(commont);
		
		//并且添加一次查看
		Active seeActive = activeUserDao.queryActiveSee(active_user_id);//先查出说说的数量
		seeActive.setActive_user_id(active_user_id);
		seeActive.setSee(seeActive.getSee()+1);
		activeUserDao.updateSee(seeActive);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		jsonObject.put("msg", "评论成功且添加查看成功！！");
		return jsonObject;
	}
	
	public JSONObject addLaud(String com_user_id, int active_user_id){
		JSONObject jsonObject = new JSONObject();
		Commont commont = new Commont();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("active_user_id", active_user_id);
		params.put("com_user_id", com_user_id);
		if(commontDao.queryIsLaud(params) == null){
			commont.setCom_user_id(com_user_id);
			commont.setActive_user_id(active_user_id);
			commont.setCom_creatime(new Timestamp(System.currentTimeMillis()));
			commontDao.addLaud(commont);
			
			jsonObject.put("status", true);
			jsonObject.put("msg", "点赞成功！！");
			jsonObject.put("totalLaud", commontDao.totalLaud(active_user_id));
			return jsonObject;
		}else {
			jsonObject.put("status", false);
			jsonObject.put("msg", "该用户已对该条说说点赞！！");
			return jsonObject;
		}
	}
	
	public JSONObject cancelLaud(String com_user_id, int active_user_id){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("com_user_id", com_user_id);
		params.put("active_user_id", active_user_id);
		int delLaudCount = commontDao.deleteLaud(params);
		if(delLaudCount <= 0){
			jsonObject.put("status", false);
			jsonObject.put("msg", "取消点赞失败"+delLaudCount);
			return jsonObject;
		}else {
			jsonObject.put("status", true);
			jsonObject.put("msg", "取消点赞成功"+delLaudCount);
			return jsonObject;
		}
	}
	
	public JSONObject laudList(int active_user_id, String hostPath){
		JSONObject jsonObject = new JSONObject();
		ArrayList<LaudUser> laudList = (ArrayList<LaudUser>) commontDao.queryLauds(active_user_id);
		for(LaudUser laud:laudList){
			if(laud.getProfile()==null){
				laud.setProfile(hostPath+"avatar_def.png");
			}else {
				laud.setProfile(hostPath+laud.getProfile());
			}
		}
		jsonObject.put("status", true);
		jsonObject.put("msg", "点赞用户列表查询成功！");
		jsonObject.put("data", laudList);
		return jsonObject;
	}
	
	public JSONObject addcToc(String child_user_id, int com_id, String parent_user_id, 
			String content){
		JSONObject jsonObject = new JSONObject();
		ChildCommont childCom = new ChildCommont();
		
		childCom.setChild_creatime(new Timestamp(System.currentTimeMillis()));
		childCom.setChild_user_id(child_user_id);
		childCom.setCom_id(com_id);
		childCom.setParent_user_id(parent_user_id);
		childCom.setChild_content(content);
		
		commontDao.addChildCom(childCom);
		jsonObject.put("status", true);
		jsonObject.put("msg", "添加子评论成功！");
		return jsonObject;
	}
	
	public JSONObject cToc(String user_id, String com_user_id, int active_user_id, 
			String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		//添加评论的列表
		ArrayList<Commont> comms = (ArrayList<Commont>) commontDao.queryComs(active_user_id);
		if(comms.size()>0){
			System.out.println("子评论的数量="+comms.size());
			for(Commont com:comms){//每个直接评论
				String profile = userDao.queryUserProfile(com.getCom_user_id());
				if(profile==null){
					com.setProfile(hostPath01+"avatar_def.png");//设置以及评论的用户头像
				}else {
					com.setProfile(hostPath01+profile);//设置以及评论的用户头像
				}
				com.setUser_nickname(userDao.queryUserNickName(com.getCom_user_id()));//添加用户昵称
				
				Map<String, Object> params01 = new HashMap<String, Object>();
				params01.put("com_id", com.getCom_id());
				params01.put("parent_user_id", com.getCom_user_id());
				ArrayList<ChildCommont> childComms = 
						(ArrayList<ChildCommont>) commontDao.queryChildComms(params01);
				for(ChildCommont childComm:childComms){//处理每个直接子评论
					System.out.println(childComm);
					String profile01 = userDao.queryUserProfile(childComm.getChild_user_id());
					if(profile01==null){
						childComm.setProfile(hostPath01+"avatar_def.png");
					}else {
						childComm.setProfile(hostPath01+profile01);
					}
					childComm.setUser_nickname(userDao.queryUserNickName(
							childComm.getChild_user_id()));
					//判断子评论是否点赞
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("parent_user_id", childComm.getParent_user_id());
					params.put("child_user_id", com.getCom_user_id());
					System.out.println("是否点赞的请求参数="+params);
					if(commontDao.queryChildComIsLaud(params)!=null){
						childComm.setIsChildLaud(1);
					}else {
						childComm.setIsChildLaud(0);
					}
					//添加子评论的点赞数量
					childComm.setTotalChildLaud(commontDao.totalChildComLaud(childComm.getParent_user_id()));
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("active_user_id", active_user_id);
				params.put("com_id", com.getCom_id());
				System.out.println("com_id="+com.getCom_id());
				System.out.println("子评论的回复数="+commontDao.totalChildCom(params));
				com.setTotalChildRev(commontDao.totalChildCom(params));//子评论的回复总数
				com.setChildcomms(childComms);//将子评论添加到父评论中
			}
		}
		//增加动态的查看数量
		Active seeActive = activeUserDao.queryActiveSee(active_user_id);//先查出说说的数量
		seeActive.setActive_user_id(active_user_id);
		seeActive.setSee(seeActive.getSee()+1);
		activeUserDao.updateSee(seeActive);//更新说说查看的数量
		//添加某条说说的信息
		ClassActive classOneAct = activeUserDao.queryActiveId(active_user_id);
		if(classOneAct.getProfile()==null || "".equals(classOneAct.getProfile())){
			classOneAct.setProfile(hostPath01+"avatar_def.png");//设置头像路径
		}else {
			classOneAct.setProfile(hostPath01+classOneAct.getProfile());//设置头像路径
		}
		String pics = classOneAct.getActive_pic();
		String resultPicName = "";//url地址的图片
		List<String> aa = new ArrayList<String>();
		if(pics != null){//只要用户有图片
			String[] picsName = pics.split(",");
			System.out.println("图片的数量"+(picsName.length));
			for(String pic:picsName){
				System.out.println("picname="+pic);
				aa.add(resultPicName+hostPath02+pic);
			}
		}
		//添加用户上传的图片
		classOneAct.setPics(aa);
		//解析用户上传的文件
		String docDown = classOneAct.getDocum();//文件名
		String docSize = classOneAct.getDocum_size();//文件大小
		String docCount = classOneAct.getDoc_down_count();//文件的下载次数
		String resultDocName = "";//下载文件url
		List<String> bb = new ArrayList<String>();
		if(!"".equals(docDown) && docDown!=null){
			String[] docDowns = docDown.split(",");
			String[] docSizes = docSize.split(",");
			String[] docCounts = docCount.split(",");
			System.out.println("文件的数量"+(docDowns.length));
			for(int i=0;i<docDowns.length;i++){
				System.out.println("docName="+docDowns[i].replace("__", ""));
				bb.add(resultDocName+docDowns[i].replace("__", "")+" ("+docSizes[i]+",下载次数"+docCounts[i]+")");
			}
		}
		//添加用户上传的文件
		classOneAct.setDocums(bb);
		//判断当前用户是否收藏过
		Map<String, Object> params03 = new HashMap<String, Object>();
		params03.put("user_id", user_id);
		params03.put("active_user_id", active_user_id);
		Favorites favor = activeUserDao.queryOneFavor(params03);
		if(favor==null){
			classOneAct.setIsFavor(0);
			classOneAct.setFavor_id(0);//没有收藏id
		}else {
			classOneAct.setIsFavor(1);
			classOneAct.setFavor_id(favor.getFavor_id());//添加收藏id
		}
		
		jsonObject.put("status", true);
		jsonObject.put("msg", "查询评论列表成功");
		jsonObject.put("oneActive", classOneAct);//添加某条说说的信息
		jsonObject.put("totalCountOfComm", commontDao.totalCom(active_user_id));//直接回复数量
		jsonObject.put("totalCountOfLaud", commontDao.totalLaud(active_user_id));//点赞人数
		jsonObject.put("data", comms);
		System.out.println(jsonObject.toString());
		return jsonObject;
	}
	
	public JSONObject childComLaud(String child_user_id, int com_id, String parent_user_id){
		JSONObject jsonObject = new JSONObject();
		ChildCommont childCom = new ChildCommont();
		childCom.setChild_user_id(child_user_id);
		childCom.setCom_id(com_id);
		childCom.setParent_user_id(parent_user_id);
		childCom.setChild_creatime(new Timestamp(System.currentTimeMillis()));
		commontDao.addChildComLaud(childCom);
		jsonObject.put("status", true);
		jsonObject.put("msg", "用户点赞成功");
		return jsonObject;
	}
	
}
