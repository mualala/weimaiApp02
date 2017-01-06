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
	
	
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, 
			String localBasePath) {
		JSONObject jsonObject = new JSONObject();
		//�жϺ�̨�Է���̬�Ƿ����˸�����Ϣ��֤
		System.out.println("type_a="+type_a);
		ActiveVerify activeVerify = activeUserDao.queryStuAndCertiVerify(type_a);
		User user = userDao.findByUserId(user_id);
		
		System.out.println("ActiveVerify="+activeVerify.toString());
		System.out.println("User="+user.toString());
		
		if("1".equals(activeVerify.getStu_verify()) && "0".equals(activeVerify.getCerti_verify()) 
				&& "0".equals(user.getStu_state())){
			//�����ֻ̨������ѧ��֤��֤
			System.out.println("zou l ................��������ѧ��֤��Ϣ��֤!");
			jsonObject.put("msg", "��������ѧ��֤��Ϣ��֤");
			jsonObject.put("status", false);
			return jsonObject;
		}else if ("0".equals(activeVerify.getStu_verify()) && "1".equals(activeVerify.getCerti_verify()) 
				&& "0".equals(user.getCerti_state())) {
			//�����ֻ̨�����˱�ҵ֤��֤
			System.out.println("zou l ................�������б�ҵ֤��Ϣ��֤!");
			jsonObject.put("msg", "�������б�ҵ֤��Ϣ��֤");
			jsonObject.put("status", false);
			return jsonObject;
		}else if ("1".equals(activeVerify.getStu_verify()) && "1".equals(activeVerify.getCerti_verify()) 
				&& ("0".equals(user.getStu_state()) || "0".equals(user.getCerti_state()))) {
			//��̨������˫����֤
			System.out.println("zou l ................���������Ϣ��֤!");
			jsonObject.put("msg", "���������Ϣ��֤");
			jsonObject.put("status", false);
			return jsonObject;
		}else {
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
			active.setActive_pic(picName02);
			active.setDocum(documName02);
			active.setDocum_size(documSize02);
			active.setDoc_down_count(documCount02);
			active.setActive_creatime(new Timestamp(System.currentTimeMillis()));
			
			activeUserDao.addActive(active);
			jsonObject.put("msg", "����̬�ɹ�");
			jsonObject.put("status", true);
			return jsonObject;
		}
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
		if(user_id.equals(v_user_id) && count!=0) {//��ѯ�������û��ĸ��˶�̬,���Լ��鿴�Լ��Ķ�̬,���ж�̬
			System.out.println("���˶�̬���Լ��鿴�Լ��Ķ�̬������");
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
				ownActives.get(i).setPrifile(hostPath01+userDao.queryUserProfile(user_id));
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
			System.out.println(ownActives.toString());
			return jsonObject;
		}else if (user_id.equals(v_user_id) && count==0) {//��ѯ�������û��ĸ��˶�̬,���Լ��鿴�Լ��Ķ�̬,��û�ж�̬
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
		}else if (v_count!=0) {//�ÿ�ģʽ�ĸ��˶�̬,�ұ������û��ж�̬����
			System.out.println("�ÿ�ģʽ�ĸ��˶�̬,�ұ������û��ж�̬����");
			//��ӷÿ��û�
			params02.put("v_user_id", v_user_id);
			params02.put("user_id", user_id);
			Visitor visitor = userDao.queryVisitorByUserId(params02);
			if(visitor==null){//ֻҪû�и��û��İݷ���Ϣ�����Ӱݷ���Ϣ
				params02.put("v_creatime", new Timestamp(System.currentTimeMillis()));
				userDao.saveVisitor(params02);
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
					ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(user_id).getVerify_state()));
					//����û��ǳ�
					ownActives.get(i).setUser_nickname(userDao.queryUserNickName(user_id));
					//��ӵ�������
					ownActives.get(i).setTotalLaud(
							commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
					//����û�ͷ��
					ownActives.get(i).setPrifile(hostPath01+userDao.queryUserProfile(user_id));
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
				return jsonObject;
			}else {
				System.out.println("���ݷ��û��ж�̬,���и��û��İݷ�");
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
					ownActives.get(i).setIsVerify(Integer.valueOf(userDao.findByUserId(user_id).getVerify_state()));
					//����û��ǳ�
					ownActives.get(i).setUser_nickname(userDao.queryUserNickName(user_id));
					//��ӵ�������
					ownActives.get(i).setTotalLaud(
							commontDao.totalLaud(ownActives.get(i).getActive_user_id()));
					//����û�ͷ��
					ownActives.get(i).setPrifile(hostPath01+userDao.queryUserProfile(user_id));
					//����û�˵˵����������
					ownActives.get(i).setTotalCommont(
							commontDao.totalCommont(ownActives.get(i).getActive_user_id()));
					//����û��ϴ���ͼƬ��Դ
					ownActives.get(i).setPics(aa);
					//����û��ϴ�����Դ�ļ�
					ownActives.get(i).setDocums(bb);
				}
				jsonObject.put("msg", "���ݷ��û��ж�̬,���и��û��İݷ�");
				jsonObject.put("data", ownActives);
				jsonObject.put("status", false);
				return jsonObject;
			}
		}
//		else if (!user_id.equals(v_user_id)) {//��ѯ�������û��ĸ��˶�̬
//			return jsonObject;
//		}
		return jsonObject;
	}
	
	//���ද̬��չʾ
	public JSONObject showClassActive(String type_a, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02, String com_user_id){
		System.out.println("���˴��ද̬��չʾ��service�㣡����");
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
			classActives.get(i).setPrifile(hostPath01+userDao.queryUserProfile(
					classActives.get(i).getUser_id()));
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
			String hostPath01, String hostPath02, String com_user_id){
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
			param.put("com_user_id", com_user_id);
			if(commontDao.queryIsLaud(param) == null){
				friendsActive.get(i).setIsLaud("0");;//û����
			}else {
				friendsActive.get(i).setIsLaud("1");
			}
			//����û�����֤״̬
			friendsActive.get(i).setIsVerify(Integer.valueOf(
					Integer.valueOf(userDao.findByUserId(
							friendsActive.get(i).getUser_id()).getVerify_state())));
			//��ӵ��޵�����
			friendsActive.get(i).setTotalLaud(
					commontDao.totalLaud(friendsActive.get(i).getActive_user_id()));
			//����û�ͼƬ
			friendsActive.get(i).setPrifile(hostPath01+userDao.queryUserProfile(
					friendsActive.get(i).getF_user_id()));
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
		jsonObject.put("msg", "���˵˵�鿴�������ɹ���");
		return jsonObject;
	}
	
	/*
	 * ���ں�̨��˵˵�����
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
			String resultPicName = "";//url��ַ��ͼƬ
			List<String> aa = new ArrayList<String>();
			if(pics != null || !"".equals(pics)){//ֻҪ�û���ͼƬ
				String[] picsName = pics.split(",");
				for(String pic:picsName){
					aa.add(resultPicName+hostPath+pic);
				}
			}
			
			String docums = act.getDocum();
			String resultDocum = "";//url��Դ�ļ�
			List<String> bb = new ArrayList<String>();
			if(docums != null || !"".equals(docums)){//ֻҪ����Դ�ļ�
				System.out.println(docums);
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
			try {
				act.setC_time(DateUtil.formatDate(act.getActive_creatime()));
			} catch (Exception e) {
				System.out.println("˵˵��ʱ��ת�����󣡣�");
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
		jsonObject.put("msg", "��̬���ͨ���ɹ�����");
		return jsonObject;
	}
	
	public JSONObject noVerifyActive(int active_user_id){
		JSONObject jsonObject = new JSONObject();
		activeUserDao.oneActiveNoVerify(active_user_id);
		jsonObject.put("msg", "��̬��˲�ͨ���ɹ�����");
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
	
	public JSONObject keyActive(String text, String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		String str = "%"+text+"%";
		List<Active> actives = activeUserDao.queryKeyActive(str);
		if(actives.size()>0){
			for(Active active:actives){
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
				active.setUser_nickname(userDao.queryUserNickName(active.getUser_id()));
				//��ӵ�������
				active.setTotalLaud(commontDao.totalLaud(active.getActive_user_id()));
				//����û�ͷ��
				active.setPrifile(hostPath01+userDao.queryUserProfile(active.getUser_id()));
				//����û�˵˵����������
				active.setTotalCommont(commontDao.totalCommont(active.getActive_user_id()));
				//�����url��ͼƬ
				active.setPics(aa);
			}
		}
		jsonObject.put("msg", "���ݼ����ɹ�!");
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
		jsonObject.put("msg", "�ɹ������"+count+"����̬");
		return jsonObject;
	}
	
	
}
