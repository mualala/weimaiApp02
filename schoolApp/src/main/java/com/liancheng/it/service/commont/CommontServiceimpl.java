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
 * �û����۵�
 */
@Service("commontService")//ɨ��service
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
		
		//�������һ�β鿴
		Active seeActive = activeUserDao.queryActiveSee(active_user_id);//�Ȳ��˵˵������
		seeActive.setActive_user_id(active_user_id);
		seeActive.setSee(seeActive.getSee()+1);
		activeUserDao.updateSee(seeActive);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", true);
		jsonObject.put("msg", "���۳ɹ�����Ӳ鿴�ɹ�����");
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
			jsonObject.put("msg", "���޳ɹ�����");
			jsonObject.put("totalLaud", commontDao.totalLaud(active_user_id));
			return jsonObject;
		}else {
			jsonObject.put("status", false);
			jsonObject.put("msg", "���û��ѶԸ���˵˵���ޣ���");
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
			jsonObject.put("msg", "ȡ������ʧ��"+delLaudCount);
			return jsonObject;
		}else {
			jsonObject.put("status", true);
			jsonObject.put("msg", "ȡ�����޳ɹ�"+delLaudCount);
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
		jsonObject.put("msg", "�����û��б��ѯ�ɹ���");
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
		jsonObject.put("msg", "��������۳ɹ���");
		return jsonObject;
	}
	
	public JSONObject cToc(String user_id, String com_user_id, int active_user_id, 
			String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		//������۵��б�
		ArrayList<Commont> comms = (ArrayList<Commont>) commontDao.queryComs(active_user_id);
		if(comms.size()>0){
			System.out.println("�����۵�����="+comms.size());
			for(Commont com:comms){//ÿ��ֱ������
				String profile = userDao.queryUserProfile(com.getCom_user_id());
				if(profile==null){
					com.setProfile(hostPath01+"avatar_def.png");//�����Լ����۵��û�ͷ��
				}else {
					com.setProfile(hostPath01+profile);//�����Լ����۵��û�ͷ��
				}
				com.setUser_nickname(userDao.queryUserNickName(com.getCom_user_id()));//����û��ǳ�
				
				Map<String, Object> params01 = new HashMap<String, Object>();
				params01.put("com_id", com.getCom_id());
				params01.put("parent_user_id", com.getCom_user_id());
				ArrayList<ChildCommont> childComms = 
						(ArrayList<ChildCommont>) commontDao.queryChildComms(params01);
				for(ChildCommont childComm:childComms){//����ÿ��ֱ��������
					System.out.println(childComm);
					String profile01 = userDao.queryUserProfile(childComm.getChild_user_id());
					if(profile01==null){
						childComm.setProfile(hostPath01+"avatar_def.png");
					}else {
						childComm.setProfile(hostPath01+profile01);
					}
					childComm.setUser_nickname(userDao.queryUserNickName(
							childComm.getChild_user_id()));
					//�ж��������Ƿ����
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("parent_user_id", childComm.getParent_user_id());
					params.put("child_user_id", com.getCom_user_id());
					System.out.println("�Ƿ���޵��������="+params);
					if(commontDao.queryChildComIsLaud(params)!=null){
						childComm.setIsChildLaud(1);
					}else {
						childComm.setIsChildLaud(0);
					}
					//��������۵ĵ�������
					childComm.setTotalChildLaud(commontDao.totalChildComLaud(childComm.getParent_user_id()));
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("active_user_id", active_user_id);
				params.put("com_id", com.getCom_id());
				System.out.println("com_id="+com.getCom_id());
				System.out.println("�����۵Ļظ���="+commontDao.totalChildCom(params));
				com.setTotalChildRev(commontDao.totalChildCom(params));//�����۵Ļظ�����
				com.setChildcomms(childComms);//����������ӵ���������
			}
		}
		//���Ӷ�̬�Ĳ鿴����
		Active seeActive = activeUserDao.queryActiveSee(active_user_id);//�Ȳ��˵˵������
		seeActive.setActive_user_id(active_user_id);
		seeActive.setSee(seeActive.getSee()+1);
		activeUserDao.updateSee(seeActive);//����˵˵�鿴������
		//���ĳ��˵˵����Ϣ
		ClassActive classOneAct = activeUserDao.queryActiveId(active_user_id);
		if(classOneAct.getProfile()==null || "".equals(classOneAct.getProfile())){
			classOneAct.setProfile(hostPath01+"avatar_def.png");//����ͷ��·��
		}else {
			classOneAct.setProfile(hostPath01+classOneAct.getProfile());//����ͷ��·��
		}
		String pics = classOneAct.getActive_pic();
		String resultPicName = "";//url��ַ��ͼƬ
		List<String> aa = new ArrayList<String>();
		if(pics != null){//ֻҪ�û���ͼƬ
			String[] picsName = pics.split(",");
			System.out.println("ͼƬ������"+(picsName.length));
			for(String pic:picsName){
				System.out.println("picname="+pic);
				aa.add(resultPicName+hostPath02+pic);
			}
		}
		//����û��ϴ���ͼƬ
		classOneAct.setPics(aa);
		//�����û��ϴ����ļ�
		String docDown = classOneAct.getDocum();//�ļ���
		String docSize = classOneAct.getDocum_size();//�ļ���С
		String docCount = classOneAct.getDoc_down_count();//�ļ������ش���
		String resultDocName = "";//�����ļ�url
		List<String> bb = new ArrayList<String>();
		if(!"".equals(docDown) && docDown!=null){
			String[] docDowns = docDown.split(",");
			String[] docSizes = docSize.split(",");
			String[] docCounts = docCount.split(",");
			System.out.println("�ļ�������"+(docDowns.length));
			for(int i=0;i<docDowns.length;i++){
				System.out.println("docName="+docDowns[i].replace("__", ""));
				bb.add(resultDocName+docDowns[i].replace("__", "")+" ("+docSizes[i]+",���ش���"+docCounts[i]+")");
			}
		}
		//����û��ϴ����ļ�
		classOneAct.setDocums(bb);
		//�жϵ�ǰ�û��Ƿ��ղع�
		Map<String, Object> params03 = new HashMap<String, Object>();
		params03.put("user_id", user_id);
		params03.put("active_user_id", active_user_id);
		Favorites favor = activeUserDao.queryOneFavor(params03);
		if(favor==null){
			classOneAct.setIsFavor(0);
			classOneAct.setFavor_id(0);//û���ղ�id
		}else {
			classOneAct.setIsFavor(1);
			classOneAct.setFavor_id(favor.getFavor_id());//����ղ�id
		}
		
		jsonObject.put("status", true);
		jsonObject.put("msg", "��ѯ�����б�ɹ�");
		jsonObject.put("oneActive", classOneAct);//���ĳ��˵˵����Ϣ
		jsonObject.put("totalCountOfComm", commontDao.totalCom(active_user_id));//ֱ�ӻظ�����
		jsonObject.put("totalCountOfLaud", commontDao.totalLaud(active_user_id));//��������
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
		jsonObject.put("msg", "�û����޳ɹ�");
		return jsonObject;
	}
	
}
