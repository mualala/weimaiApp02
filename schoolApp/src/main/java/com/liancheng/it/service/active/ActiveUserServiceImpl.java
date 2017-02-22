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
 * �û���̬
 */
@Service("activeUserService")//ɨ��service
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
		//�жϺ�̨�Է���̬�Ƿ����˸�����Ϣ��֤
		System.out.println("type_a="+type_a);
		ActiveVerify activeVerify = new ActiveVerify();
		User user = new User();
		
		if("life#".equals(type_a) && 
				(pics!=null || docum!=null || !"".equals(saysay))){//���������Ȧ#����(����Ȧ���Ͳ���Ҫ��̨����̬��Ȩ��)
			//�������淢��̬��ͼƬ
			String picName = String.valueOf(System.currentTimeMillis());
			String picName02 = "";
			if(null!=pics && !"".equals(user_id)){
				//�ȵ������浽����
				for(int i=0;i<pics.length;i++){
					System.out.println(pics[i].isEmpty());
					if(!pics[i].isEmpty()){//ֻҪ�ϴ�ͼƬ��Ϊ��
						picName = picName+"-"+i;
						String suffix = pics[i].getOriginalFilename().substring(pics[i].getOriginalFilename().lastIndexOf("."));//��ȡ�ļ��ĺ�׺��
						System.out.println("��˵˵ͼƬ������="+picName+suffix);
						picName02 = picName02+picName+suffix+",";
						try {
							System.out.println("����̬ͼƬ����ı���·��="+localBasePath+picName+suffix);
							//����ͼƬ������Ŀ¼
							pics[i].transferTo(new File(localBasePath+picName+suffix));
						} catch (Exception e) {
							System.out.println("���淢��Ķ�̬ͼƬʧ�ܣ���");
						}
					}
				}
			}
			//������Դ�ļ�
			String documName02 = "";
			String documSize02 = "";
			String documCount02 = "";
			if(null!=docum && !"".equals(user_id)){
				for(int i=0;i<docum.length;i++){
					if(!docum[i].isEmpty()){//ֻҪ�ϴ���Դ��Ϊ��
						String testName = docum[i].getOriginalFilename();//��ȡ�ϴ��ļ�������
						documName02 = documName02+"__"+testName+",";
						String documSize01 = String.valueOf(docum[i].getSize()/1024+"KB");
						documSize02 = documSize02+documSize01+",";
						documCount02 = documCount02+"0,";
						try {
							System.out.println("����̬ͼƬ����ı���·��="+localBasePath+"__"+testName);
							docum[i].transferTo(new File(localBasePath+"__"+testName));
						} catch (Exception e) {
							System.out.println("���淢��Ķ�̬��Դ�ļ�ʧ�ܣ���");
						}
					}
				}
			}
			Active active = new Active();
			
			active.setUser_id(user_id);
			type_a = "����Ȧ#";
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
			jsonObject.put("msg", "����̬�ɹ�");
			jsonObject.put("status", true);
			return jsonObject;
		}else {//ֻҪ��������Ȧ#���;���Ҫ��鷢��̬��Ȩ��
			activeVerify = activeUserDao.queryStuAndCertiVerify(type_a);
			user = userDao.findByUserId(user_id);
			if("1".equals(activeVerify.getStu_verify()) && "0".equals(activeVerify.getCerti_verify()) 
					&& "0".equals(user.getStu_state())){
				//�����ֻ̨������ѧ��֤��֤
				System.out.println("zou l ................��������ѧ��֤��Ϣ��֤!");
				jsonObject.put("msg", "��������ѧ��֤��Ϣ��֤");
				jsonObject.put("status", false);
				jsonObject.put("code", 1);
				System.out.println("jsonObject="+jsonObject);
				return jsonObject;
			}else if ("0".equals(activeVerify.getStu_verify()) && "1".equals(activeVerify.getCerti_verify()) 
					&& "0".equals(user.getCerti_state())) {
				//�����ֻ̨�����˱�ҵ֤��֤
				System.out.println("zou l ................�������б�ҵ֤��Ϣ��֤!");
				jsonObject.put("msg", "�������б�ҵ֤��Ϣ��֤");
				jsonObject.put("status", false);
				jsonObject.put("code", 2);
				System.out.println("jsonObject="+jsonObject);
				return jsonObject;
			}else if ("1".equals(activeVerify.getStu_verify()) && "1".equals(activeVerify.getCerti_verify()) 
					&& ("0".equals(user.getStu_state()) || "0".equals(user.getCerti_state()))) {
				//��̨������˫����֤
				System.out.println("zou l ................���������Ϣ��֤!");
				jsonObject.put("msg", "���������Ϣ��֤");
				jsonObject.put("status", false);
				jsonObject.put("code", 3);
				System.out.println("jsonObject="+jsonObject);
				return jsonObject;
			}else if("��ҳ��̬".equals(type_a) && "0".equals(user.getVerify_state())) {
				System.out.println("zou l ................����ҳ��̬��Ҫ�����֤!");
				jsonObject.put("code", 4);
				jsonObject.put("msg", "��ҳ��̬�ķ�����Ҫ�����֤Ӵ");
				jsonObject.put("status", false);
				return jsonObject;
			}else if(pics!=null || docum!=null || !"".equals(saysay)) {//���˷���̬
				//�������淢��̬��ͼƬ
				String picName = String.valueOf(System.currentTimeMillis());
				String picName02 = "";
				if(null!=pics && !"".equals(user_id)){
					//�ȵ������浽����
					for(int i=0;i<pics.length;i++){
						System.out.println(pics[i].isEmpty());
						if(!pics[i].isEmpty()){//ֻҪ�ϴ�ͼƬ��Ϊ��
							picName = picName+"-"+i;
							String suffix = pics[i].getOriginalFilename().substring(pics[i].getOriginalFilename().lastIndexOf("."));//��ȡ�ļ��ĺ�׺��
							System.out.println("��˵˵ͼƬ������="+picName+suffix);
							picName02 = picName02+picName+suffix+",";
							try {
								System.out.println("����̬ͼƬ����ı���·��="+localBasePath+picName+suffix);
								//����ͼƬ������Ŀ¼
								pics[i].transferTo(new File(localBasePath+picName+suffix));
							} catch (Exception e) {
								System.out.println("���淢��Ķ�̬ͼƬʧ�ܣ���");
							}
						}
					}
				}
				//������Դ�ļ�
				String documName02 = "";
				String documSize02 = "";
				String documCount02 = "";
				if(null!=docum && !"".equals(user_id)){
					for(int i=0;i<docum.length;i++){
						if(!docum[i].isEmpty()){//ֻҪ�ϴ���Դ��Ϊ��
							String testName = docum[i].getOriginalFilename();//��ȡ�ϴ��ļ�������
							documName02 = documName02+"__"+testName+",";
							String documSize01 = String.valueOf(docum[i].getSize()/1024+"KB");
							documSize02 = documSize02+documSize01+",";
							documCount02 = documCount02+"0,";
							try {
								System.out.println("����̬ͼƬ����ı���·��="+localBasePath+"__"+testName);
								docum[i].transferTo(new File(localBasePath+"__"+testName));
							} catch (Exception e) {
								System.out.println("���淢��Ķ�̬��Դ�ļ�ʧ�ܣ���");
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
				jsonObject.put("msg", "����̬�ɹ�");
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
	 * չʾ���˶�̬
	 * @param path ��Ŀ������·��
	 * @param hostPath ��Ŀ��url·��
	 */
	public JSONObject showOwnActive(String user_id, String v_user_id, int pageSize, 
			int pageNumber, String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> params02 = new HashMap<String, Object>();//������ӷÿ��û��Ĳ�ѯ����,�޶�̬����
		Map<String, Object> params03 = new HashMap<String, Object>();//������ӷÿ��û��Ĳ�ѯ����,�ж�̬����
		params.put("user_id", user_id);
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("start", start);
		params.put("end", end);
		int count = activeUserDao.queryActiveCounts(user_id);
		int v_count = activeUserDao.queryActiveCounts(v_user_id);//���ݷ��û��Ķ�̬����
		if(user_id.equals(v_user_id) && count!=0) {//��ѯ�������û��ĸ��˶�̬,--���Լ��鿴�Լ��Ķ�̬--,���ж�̬
			ArrayList<Active> ownActives = (ArrayList<Active>) activeUserDao.queryOwnActive(params);
			for(int i=0;i<ownActives.size();i++){
				//�����û��ϴ���ͼƬ��Դ
				List<String> aa = new ArrayList<String>();
				String pics = ownActives.get(i).getActive_pic();//��ȡÿ���û���ͼƬ
				if(pics!=null || "".equals(pics)){
					String[] picsName = pics.split(",");
					String resultPicName = "";//url��ַ��ͼƬ
					for(String pic:picsName){
						if(!"".equals(pic)){
							aa.add(resultPicName+hostPath02+pic);
						}
					}
				}
				//�����û��ϴ�����Դ�ļ�
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
				//�ж��û���ĳ��˵˵�ǹ��Ѿ�����
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("active_user_id", ownActives.get(i).getActive_user_id());
				param.put("com_user_id", user_id);
				if(commontDao.queryIsLaud(param) == null){
					ownActives.get(i).setIsLaud(0);
				}else {
					ownActives.get(i).setIsLaud(1);//�ѵ���
				}
				//����û�����֤״̬
				ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(user_id).getVerify_state()));
				//����û��ǳ�
				ownActives.get(i).setUser_nickname(userDao.queryUserNickName(user_id));
				//��ӵ�������
				ownActives.get(i).setTotalLaud(
						commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
				//����û�ͷ��
				String profile = userDao.queryUserProfile(user_id);
				if(profile==null){
					ownActives.get(i).setPrifile(hostPath01+"avatar_def.png");
				}else {
					ownActives.get(i).setPrifile(hostPath01+profile);
				}
				//����û�˵˵����������
				ownActives.get(i).setTotalCommont(
						commontDao.totalCommont(ownActives.get(i).getActive_user_id()));
				//����û��ϴ���ͼƬ��Դ
				ownActives.get(i).setPics(aa);
				//����û��ϴ�����Դ�ļ�
				ownActives.get(i).setDocums(bb);
			}
			jsonObject.put("msg", "���˶�̬չʾ�ɹ�");
			jsonObject.put("data", ownActives);
			jsonObject.put("status", true);
			jsonObject.put("totalActs", activeUserDao.queryTotalActs(user_id));//����ܵĶ�̬����
			jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(user_id));//����ܵ�δ�鿴��������
			return jsonObject;
		}else if (user_id.equals(v_user_id) && count==0) {//��ѯ�������û��ĸ��˶�̬,--���Լ��鿴�Լ��Ķ�̬--,��û�ж�̬
			jsonObject.put("status", true);
			jsonObject.put("msg", "��ǰ�û�û�ж�̬");
			return jsonObject;
		}else if(v_count==0) {//�ÿ�ģʽ�ĸ��˶�̬,�ұ������û�û�ж�̬����
			System.out.println("���˶�̬�Ƿÿ�ģʽ���ÿ�uuid="+v_user_id);
			//���ӷÿ�
			params02.put("v_user_id", v_user_id);
			params02.put("user_id", user_id);
			Visitor visitor = userDao.queryVisitorByUserId(params02);
			if(visitor==null){//ֻҪû�и��û��İݷ���Ϣ�����Ӱݷ���Ϣ
				params02.put("v_creatime", new Timestamp(System.currentTimeMillis()));
				userDao.saveVisitor(params02);
				jsonObject.put("status", true);
				jsonObject.put("msg", "���ݷ��û�û�ж�̬,�������˰ݷ��û�");
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "���ݷ��û�û�ж�̬,���и��û��İݷ�");
			}
			return jsonObject;
		}else if (v_count!=0) {//�ÿ�ģʽ�ĸ��˶�̬,--�ұ������û��ж�̬����--
			System.out.println("�ÿ�ģʽ�ĸ��˶�̬,�ұ������û��ж�̬����");
			//��ӷÿ��û�
			params02.put("v_user_id", v_user_id);
			params02.put("user_id", user_id);
			Visitor visitor = userDao.queryVisitorByUserId(params02);
			if(visitor==null){//ֻҪû�и��û��İݷ���Ϣ�����Ӱݷ���Ϣ
				params02.put("v_creatime", new Timestamp(System.currentTimeMillis()));
				userDao.saveVisitor(params02);
			}
			//���жϰݷ����Ƿ��Ƿ�˿
			Map<String, Object> params04 = new HashMap<String, Object>();
			params04.put("user_id", v_user_id);
			params04.put("f_user_id", user_id);
			if(friendsDao.queryIsAttention(params04) == null){//---����fans,����Ȧ�ɼ�---
				//��ѯ�û��Ķ�̬����
				params03.put("user_id", v_user_id);
				params03.put("start", start);
				params03.put("end", end);
				ArrayList<Active> ownActives = (ArrayList<Active>) activeUserDao.queryOwnActive(params03);
				for(int i=0;i<ownActives.size();i++){
					//�����û��ϴ���ͼƬ��Դ
					List<String> aa = new ArrayList<String>();
					String pics = ownActives.get(i).getActive_pic();//��ȡÿ���û���ͼƬ
					if(pics!=null || "".equals(pics)){
						String[] picsName = pics.split(",");
						String resultPicName = "";//url��ַ��ͼƬ
						for(String pic:picsName){
							if(!"".equals(pic)){
								aa.add(resultPicName+hostPath02+pic);
							}
						}
					}
					//�����û��ϴ�����Դ�ļ�
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
					//�ж��û���ĳ��˵˵�ǹ��Ѿ�����
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("active_user_id", ownActives.get(i).getActive_user_id());
					param.put("com_user_id", user_id);
					if(commontDao.queryIsLaud(param) == null){
						ownActives.get(i).setIsLaud(0);
					}else {
						ownActives.get(i).setIsLaud(1);//�ѵ���
					}
					//����û�����֤״̬
					ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(v_user_id).getVerify_state()));
					//����û��ǳ�
					ownActives.get(i).setUser_nickname(userDao.queryUserNickName(v_user_id));
					//��ӵ�������
					ownActives.get(i).setTotalLaud(
							commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
					//����û�ͷ��
					String profile = userDao.queryUserProfile(v_user_id);
					if(profile==null){
						ownActives.get(i).setPrifile(hostPath01+"avatar_def.png");
					}else {
						ownActives.get(i).setPrifile(hostPath01+profile);
					}
					//����û�˵˵����������
					ownActives.get(i).setTotalCommont(
							commontDao.totalCommont(ownActives.get(i).getActive_user_id()));
					//����û��ϴ���ͼƬ��Դ
					ownActives.get(i).setPics(aa);
					//����û��ϴ�����Դ�ļ�
					ownActives.get(i).setDocums(bb);
				}
				jsonObject.put("msg", "���ݷ��û��ж�̬,�������˰ݷ��û�");
				jsonObject.put("data", ownActives);
				jsonObject.put("status", true);
				jsonObject.put("totalActs", activeUserDao.queryTotalActs(v_user_id));//����ܵĶ�̬����
				jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(v_user_id));//����ܵ�δ�鿴��������
				return jsonObject;
			}else {//---��fans,�����û����Ե�����Ȧ����ֵ�Ƿ�鿴����Ȧ---
				params03.put("user_id", v_user_id);
				params03.put("start", start);
				params03.put("end", end);
				
				User user = userDao.findById(v_user_id);
				if(user.getLife_see()==0){//����Ȧ#��̬��˿���ɼ�
					params03.put("fanSeeState", 0);
				}
				ArrayList<Active> ownActives = (ArrayList<Active>) activeUserDao.queryOtherOneActive(params03);
				for(int i=0;i<ownActives.size();i++){
					//�����û��ϴ���ͼƬ��Դ
					List<String> aa = new ArrayList<String>();
					String pics = ownActives.get(i).getActive_pic();//��ȡÿ���û���ͼƬ
					if(pics!=null || "".equals(pics)){
						String[] picsName = pics.split(",");
						String resultPicName = "";//url��ַ��ͼƬ
						for(String pic:picsName){
							if(!"".equals(pic)){
								aa.add(resultPicName+hostPath02+pic);
							}
						}
					}
					//�����û��ϴ�����Դ�ļ�
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
					//�ж��û���ĳ��˵˵�ǹ��Ѿ�����
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("active_user_id", ownActives.get(i).getActive_user_id());
					param.put("com_user_id", user_id);
					if(commontDao.queryIsLaud(param) == null){
						ownActives.get(i).setIsLaud(0);
					}else {
						ownActives.get(i).setIsLaud(1);//�ѵ���
					}
					//����û�����֤״̬
					ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(v_user_id).getVerify_state()));
					//����û��ǳ�
					ownActives.get(i).setUser_nickname(userDao.queryUserNickName(v_user_id));
					//��ӵ�������
					ownActives.get(i).setTotalLaud(
							commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
					//����û�ͷ��
					String profile = userDao.queryUserProfile(v_user_id);
					if(profile==null){
						ownActives.get(i).setPrifile(hostPath01+"avatar_def.png");
					}else {
						ownActives.get(i).setPrifile(hostPath01+profile);
					}
					//����û�˵˵����������
					ownActives.get(i).setTotalCommont(
							commontDao.totalCommont(ownActives.get(i).getActive_user_id()));
					//����û��ϴ���ͼƬ��Դ
					ownActives.get(i).setPics(aa);
					//����û��ϴ�����Դ�ļ�
					ownActives.get(i).setDocums(bb);
				}
				jsonObject.put("msg", "���ݷ��û��ж�̬");
				jsonObject.put("data", ownActives);
				jsonObject.put("status", true);
				jsonObject.put("totalActs", activeUserDao.queryTotalActs(v_user_id));//����ܵĶ�̬����
				jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(v_user_id));//����ܵ�δ�鿴��������
				return jsonObject;
			}
		}
		return jsonObject;
	}
	
	//������ද̬��չʾ
	public JSONObject showClassActive(String user_id, String type_a, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02, String com_user_id){
		System.out.println("����������ද̬��չʾ��service�㣡����");
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
			//�����û��ϴ���ͼƬ��Դ
			List<String> aa = new ArrayList<String>();
			String pics = classActives.get(i).getActive_pic();//��ȡÿ���û���ͼƬ
			if(pics!=null || "".equals(pics)){
				String[] picsName = pics.split(",");
				
				String resultPicName = "";//url��ַ��ͼƬ
				for(String pic:picsName){
					if(!"".equals(pic)){
						aa.add(resultPicName+hostPath02+pic);
					}
				}
			}
			//�����û��ϴ�����Դ�ļ�
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
			
			//�ж��û���ĳ��˵˵�ǹ��Ѿ�����
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("active_user_id", classActives.get(i).getActive_user_id());
			param.put("com_user_id", com_user_id);
			if(commontDao.queryIsLaud(param) == null){
				classActives.get(i).setIsLaud(0);
			}else {
				classActives.get(i).setIsLaud(1);//�ѵ���
			}
			//����û�����֤״̬
			classActives.get(i).setIsVerify(
					Integer.valueOf(userDao.findByUserId(
							classActives.get(i).getUser_id()).getVerify_state()));
			//��ӵ�������
			classActives.get(i).setTotalLaud(
					commontDao.totalLaud(classActives.get(i).getActive_user_id()));
			//����û�ͷ��
			String profile = userDao.queryUserProfile(classActives.get(i).getUser_id());
			if(profile==null){
				classActives.get(i).setProfile(hostPath01+"avatar_def.png");
			}else {
				classActives.get(i).setProfile(hostPath01+profile);
			}
			//����û�˵˵����������
			classActives.get(i).setTotalCommont(
					commontDao.totalCommont(classActives.get(i).getActive_user_id()));
			//����û��ϴ���ͼƬ��Դ
			classActives.get(i).setPics(aa);
			//����û��ϴ�����Դ�ļ�
			classActives.get(i).setDocums(bb);
		}
		jsonObject.put("msg", "��ҳ����Ķ�̬չʾ�ɹ���");
		jsonObject.put("status", true);
		jsonObject.put("data", classActives);
		System.out.println(classActives.toString());
		return jsonObject;
	}
	
	public JSONObject showFriendsActvie(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02){
		System.out.println("��������Ȧ��̬��չʾ��service�㣡����");
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("user_id", user_id);
		params.put("start", start);
		params.put("end", end);
		
		ArrayList<CycFriendsActvie> friendsActive = 
				(ArrayList<CycFriendsActvie>) activeUserDao.queryFriendsActvie(params);
		List<Integer> cc = new ArrayList<Integer>();//���ڸı�ֱ�����۵Ĳ鿴״̬��dao��ѯ����
		for(int i=0;i<friendsActive.size();i++){
			//�����û��ϴ���ͼƬ��Դ
			List<String> aa = new ArrayList<String>();
			String pics = friendsActive.get(i).getActive_pic();//��ȡÿ���û���ͼƬ
			if(pics!=null || "".equals(pics)){
				String[] picsName = pics.split(",");
				String resultPicName = "";//url��ַ��ͼƬ
				if(picsName.length>0){//ֻҪ�û���ͼƬ
					for(String pic:picsName){
						if(!"".equals(pic)){
							aa.add(resultPicName+hostPath02+pic);
						}
					}
				}
			}
			//�����û��ϴ�����Դ�ļ�
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
			//�ж��û���ĳ��˵˵�ǹ��Ѿ�����
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("active_user_id", friendsActive.get(i).getActive_user_id());
			param.put("com_user_id", user_id);
			if(commontDao.queryIsLaud(param) == null){
				friendsActive.get(i).setIsLaud(0);;//û����
			}else {
				friendsActive.get(i).setIsLaud(1);
			}
			//����û�����֤״̬
			friendsActive.get(i).setIsVerify(Integer.valueOf(
					Integer.valueOf(userDao.findByUserId(
							friendsActive.get(i).getUser_id()).getVerify_state())));
			//��ӵ��޵�����
			friendsActive.get(i).setTotalLaud(
					commontDao.totalLaud(friendsActive.get(i).getActive_user_id()));
			//����û�ͼƬ
			String profile = friendsActive.get(i).getProfile();
			if(profile==null){
				friendsActive.get(i).setProfile(hostPath01+"avatar_def.png");
			}else {
				friendsActive.get(i).setProfile(hostPath01+profile);
			}
			//���˵˵���۵�����
			friendsActive.get(i).setTotalCommont(
					commontDao.totalCommont(friendsActive.get(i).getActive_user_id()));
			//����û��ϴ���ͼƬ��Դ
			friendsActive.get(i).setPics(aa);
			//����û��ϴ�����Դ�ļ�
			friendsActive.get(i).setDocums(bb);
			
			//����޸�ֱ�����۵Ĳ鿴״̬��array����
			cc.add(friendsActive.get(i).getActive_user_id());
		}
		jsonObject.put("msg", "����Ȧ�Ķ�̬չʾ�ɹ���");
		jsonObject.put("data", friendsActive);
		jsonObject.put("status", true);
		jsonObject.put("totalActs", activeUserDao.queryTotalActs(user_id));//����ܵĶ�̬����
		jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(user_id));//����ܵ�δ�鿴��������
		
		System.out.println(friendsActive.toString());
		//�޸�ֱ�����۵Ĳ鿴״̬
		Map<String, Object> params02 = new HashMap<String, Object>();
		params02.put("user_id", user_id);
		params02.put("actIds", cc);
		System.out.println("params02="+params02);
		commontDao.batchModifyComNoSee(params02);
		
		return jsonObject;
	}
	
	public JSONObject addSeeActive(int active_user_id){
		JSONObject jsonObject = new JSONObject();
		Active seeActive = activeUserDao.queryActiveSee(active_user_id);//�Ȳ��˵˵������
		
		seeActive.setActive_user_id(active_user_id);
		seeActive.setSee(seeActive.getSee()+1);
		activeUserDao.updateSee(seeActive);//����˵˵�鿴������
		jsonObject.put("msg", "���˵˵�鿴�������ɹ�");
		return jsonObject;
	}
	
	/*
	 * ���ں�̨��˵˵�����
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
			String resultPicName = "";//url��ַ��ͼƬ
			List<String> aa = new ArrayList<String>();
			if(pics != null && !"".equals(pics)){//ֻҪ�û���ͼƬ
				String[] picsName = pics.split(",");
				for(String pic:picsName){
					aa.add(resultPicName+hostPath+pic);
				}
			}
			
			String docums = act.getDocum();
			String resultDocum = "";//url��Դ�ļ�
			List<String> bb = new ArrayList<String>();
			if(docums != null && !"".equals(docums)){//ֻҪ����Դ�ļ�
				String[] documsName = docums.split(",");
				for(String docum:documsName){
					bb.add(resultDocum+hostPath+docum);
				}
			}
			//���ͼƬ
			act.setPics(aa);
			//�����Դ�ļ�
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
		jsonObject.put("msg", "��̬���ͨ���ɹ�����");
		return jsonObject;
	}
	
	public JSONObject noVerifyActive(int active_user_id){
		JSONObject jsonObject = new JSONObject();
		activeUserDao.oneActiveNoVerify(active_user_id);
		jsonObject.put("msg", "��̬��˲�ͨ���ɹ�����");
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
				//�����������
				if(!"��ҳ��̬".equals(av.getClass_active())){
					ThemeCateg themeCateg = new ThemeCateg();
					themeCateg.setThemeCateg(av.getClass_active().replace("#", ""));
					themeCateg.setThemeCategUrl(hostPath03+av.getTheme_pic());
					allActiveCategs.add(themeCateg);
				}
			}
			//��Ӷ�������
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
			System.out.println("�޸���ѧ��֤��֤����");
			jsonObject.put("msg", "ѧ��֤��֤�޸ĳɹ�����");
		}else if ("certi_verify".equals(verify)) {
			params.put("certi_verify", state);
			activeUserDao.modifyCertiVerify(params);
			System.out.println("�޸��˱�ҵ֤��֤����"+params.toString());
			jsonObject.put("msg", "��ҵ֤��֤�޸ĳɹ�����");
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
				String pics = active.getActive_pic();//��ȡÿ���û���ͼƬ
				String[] picsName = pics.split(",");
				
				String resultPicName = "";//url��ַ��ͼƬ
				List<String> aa = new ArrayList<String>();
				if(picsName.length>0){//ֻҪ�û���ͼƬ
					for(String pic:picsName){
						System.out.println("ĳ�ε�ͼƬ����"+(picsName.length-1));
						System.out.println("picname="+pic);
						aa.add(resultPicName+hostPath02+pic);
					}
				}
				//����û��ǳ�
				active.setUser_nickname(user.getUser_nickname());
				//��ӵ�������
				active.setTotalLaud(commontDao.totalLaud(active.getActive_user_id()));
				//����û�ͷ��
				String profile = user.getProfile();
				//����Ƿ���֤
				active.setIsVerify(Integer.valueOf(user.getVerify_state()));
				if(profile==null){
					active.setPrifile(hostPath01+"avatar_def.png");
				}else {
					active.setPrifile(hostPath01+profile);
				}
				//����û�˵˵����������
				active.setTotalCommont(commontDao.totalCommont(active.getActive_user_id()));
				//�����url��ͼƬ
				active.setPics(aa);
			}
		}
		//���text��Ϊ���򱣴浽textkey����
		if("".equals(text)){
			activeUserDao.saveTextKey(text);
		}
		jsonObject.put("status", true);
		jsonObject.put("msg", "���ݼ����ɹ�");
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
		jsonObject.put("msg", "�ɹ������"+count+"����̬");
		return jsonObject;
	}
	
	public JSONObject deleteOneActive(int active_user_id, String user_id){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("active_user_id", active_user_id);
		params.put("user_id", user_id);
		Active act = activeUserDao.queryOneActive(params);
		if(act==null){
			jsonObject.put("msg", "����ɾ�����˵Ķ�̬");
			jsonObject.put("status", false);
		}else {
			activeUserDao.deleteOneActive(active_user_id);//ɾ����̬
			List<Commont> comms = commontDao.queryCommontsByActId(active_user_id);
			//��ɾ�������۵�����
			ArrayList<Integer> comIds = new ArrayList<Integer>();
			if(comms!=null){
				for(Commont comm:comms){
					comIds.add(comm.getCom_id());
				}
			}
			commontDao.deleteChildCommsByCommId(comIds);
			//��ɾ��������
			commontDao.deleteCommsByActId(active_user_id);
			jsonObject.put("status", true);
			jsonObject.put("msg", "��̬ɾ���ɹ�");
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
		if(favor==null){//ֻҪ���û�û���ղع��ö�̬,������һ���ղؼ�¼
			params.put("favor_creatime", new Timestamp(System.currentTimeMillis()));
			activeUserDao.saveFavorites(params);
		}
		
		jsonObject.put("status", true);
		jsonObject.put("msg", "�ղ���ӳɹ�");
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
				String pics = favor.getActive_pic();//��ȡÿ���û���ͼƬ
				String[] picsName = pics.split(",");
				
				String resultPicName = "";//url��ַ��ͼƬ
				List<String> aa = new ArrayList<String>();
				if(picsName.length>0){//ֻҪ�û���ͼƬ
					for(String pic:picsName){
						aa.add(resultPicName+hostPath02+pic);
					}
				}
				//����û��ǳ�
				favor.setUser_nickname(user.getUser_nickname());
				//��ӵ�������
				favor.setTotalLaud(commontDao.totalLaud(favor.getActive_user_id()));
				//����û�ͷ��
				String profile = user.getProfile();
				//����Ƿ���֤
				favor.setIsVerify(Integer.valueOf(user.getVerify_state()));
				if(profile==null){
					favor.setPrifile(hostPath01+"avatar_def.png");
				}else {
					favor.setPrifile(hostPath01+profile);
				}
				//����û�˵˵����������
				favor.setTotalCommont(commontDao.totalCommont(favor.getActive_user_id()));
				//�����url��ͼƬ
				favor.setPics(aa);
			}
		}
		jsonObject.put("status", true);
		jsonObject.put("msg", "�ղؼв�ѯ�ɹ�");
		jsonObject.put("data", favors);
		return jsonObject;
	}
	
	public JSONObject deleteOneFavor(int favor_id){
		JSONObject jsonObject = new JSONObject();
		activeUserDao.deleteOneFavor(favor_id);
		jsonObject.put("status", true);
		jsonObject.put("msg", "�ղ�ɾ���ɹ�");
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
		jsonObject.put("msg", "�ɹ�ɾ����"+count+"���������");
		return jsonObject;
	}
	
	public JSONObject attachThemeCateg(MultipartFile themePic, String themeCateg, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		
		int count = activeUserDao.queryByThemeName(themeCateg);
		if(count>0){//�����������
			if(themePic != null){
				if(!themePic.isEmpty()){
					//��ɾ��ԭʼui����ͼƬ
					String orgPicName = themePic.getOriginalFilename();
					File file = new File(localBasePath+orgPicName);
					if(file.exists()){
						file.delete();
					}
					//����ui����ͼƬ
					String picName = String.valueOf(System.currentTimeMillis());
					String suffix = themePic.getOriginalFilename().substring(themePic.getOriginalFilename().lastIndexOf("."));//��ȡ�ļ��ĺ�׺��
					try {
						//����ͼƬ������Ŀ¼
						themePic.transferTo(new File(localBasePath+picName+suffix));
						//���浽���ݿ�
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("class_active", themeCateg);
						params.put("theme_pic", picName+suffix);
						params.put("act_lastmodifytime", new Timestamp(System.currentTimeMillis()));
						activeUserDao.updateThemeCateg(params);
						jsonObject.put("status", true);
						jsonObject.put("msg", "�����������ɹ�");
						return jsonObject;
					} catch (Exception e) {
						System.out.println("��̨������������UIͼ�굽����ʧ��!!");
						jsonObject.put("status", false);
						jsonObject.put("msg", "�����������ʧ��");
						return jsonObject;
					}
				}
			}
		}else {
			String picName = String.valueOf(System.currentTimeMillis());
			if (themePic != null) {
				if(!themePic.isEmpty()){
					String suffix = themePic.getOriginalFilename().substring(themePic.getOriginalFilename().lastIndexOf("."));//��ȡ�ļ��ĺ�׺��
					try {
						//����ͼƬ������Ŀ¼
						themePic.transferTo(new File(localBasePath+picName+suffix));
						//���浽���ݿ�
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("class_active", themeCateg);
						params.put("theme_pic", picName+suffix);
						params.put("act_creatime", new Timestamp(System.currentTimeMillis()));
						activeUserDao.saveThemeCateg(params);
						jsonObject.put("status", true);
						jsonObject.put("msg", "����������ɹ�");
						return jsonObject;
					} catch (Exception e) {
						System.out.println("��̨������������UIͼ�굽����ʧ��!!");
						jsonObject.put("status", false);
						jsonObject.put("msg", "����������ʧ��");
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
				String suffix = twoPic.getOriginalFilename().substring(twoPic.getOriginalFilename().lastIndexOf("."));//��ȡ�ļ��ĺ�׺��
				try {
					//����ͼƬ������Ŀ¼
					twoPic.transferTo(new File(localBasePath+picName+suffix));
					//���浽���ݿ�
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("class_active", themeCateg);
					params.put("two_class", twoCateg);
					params.put("two_pic", picName+suffix);
					params.put("act_creatime", new Timestamp(System.currentTimeMillis()));
					System.out.println(params);
					activeUserDao.saveTwoCateg(params);
					jsonObject.put("status", true);
					jsonObject.put("msg", "��Ӷ�������ɹ�");
					return jsonObject;
				} catch (Exception e) {
					System.out.println("��̨�����������UIͼ�굽����ʧ��!!");
					jsonObject.put("status", false);
					jsonObject.put("msg", "��Ӷ�������ʧ��");
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
		acr.setThemeType("�ϼ�");
		acr.setActiveCount(activeUserDao.queryAllNetActive());
		acts.add(acr);
		jsonObject.put("rows", acts);
		jsonObject.put("total", activeUserDao.totalActiveCategReport(params));
		return jsonObject;
	}
	
	
}
