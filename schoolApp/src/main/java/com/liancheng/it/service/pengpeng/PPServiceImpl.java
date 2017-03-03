package com.liancheng.it.service.pengpeng;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liancheng.it.dao.pengpeng.PengPengDao;
import com.liancheng.it.entity.pengpeng.Answer;
import com.liancheng.it.entity.pengpeng.AnswerUsers;
import com.liancheng.it.entity.pengpeng.ChildAnswer;
import com.liancheng.it.entity.pengpeng.OwnAnswer;
import com.liancheng.it.entity.pengpeng.Questions;
import com.liancheng.it.entity.pengpeng.TwoChildAnswer;
import com.liancheng.it.util.DateUtil;

import net.minidev.json.JSONObject;

@Service("pPService")//ɨ��service
@Aspect
@Transactional
public class PPServiceImpl implements PPService {
	
	@Resource(name="pengPengDao")
	private PengPengDao pengPengDao;
	
	public JSONObject questionsReport(int pageSize, int pageNumber, String searchText, 
			String startDate, String endDate, String sortName, String sortOrder) {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("start", start);
		params.put("end", end);
		if (searchText != null) {
			params.put("searchText", "%"+searchText+"%");
		}else {
			params.put("searchText", searchText);
		}
		params.put("sortName", sortName);
		params.put("sortOrder", sortOrder);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		List<Questions> questions = pengPengDao.questionReport(params);
		if(questions.size()>0){
			for(Questions ques:questions){
				try {
					ques.setStrQues_creatime(DateUtil.formatDate(ques.getQues_creatime()));
				} catch (Exception e) {
					System.out.println("��̨���������Ĵ���ʱ��ת��ʧ��!");
				}
			}
		}
		jsonObject.put("rows", questions);
		jsonObject.put("total", pengPengDao.totalQuestions(params));
		return jsonObject;
	}
	
	public JSONObject addQuestion(String question){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("question", question);
		params.put("ques_creatime", new Timestamp(System.currentTimeMillis()));
		
		pengPengDao.addQuestion(params);
		jsonObject.put("msg", "�������ɹ�!");
		return jsonObject;
	}
	
	public JSONObject addAswer(String user_id, int ques_id, String content, String name_type){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("ques_id", ques_id);
		params.put("content", content);
		if("".equals(name_type)){
			name_type = "ʵ��";
		}
		params.put("name_type", name_type);
		params.put("ans_creatime", new Timestamp(System.currentTimeMillis()));
		pengPengDao.saveAnswer(params);
		
		jsonObject.put("status", true);
		jsonObject.put("msg", "���۳ɹ�");
		return jsonObject;
	}
	
	public JSONObject showMain(String user_id, int ques_id, int pageSize, int pageNumber, 
			String name_type, String gender, String hostPath){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Questions question = new Questions();
		if(ques_id==0){//Ĭ���״μ�������ҳ���ʱ��
			question = pengPengDao.randOneQuestion();//�����ȡĳ������
		}
		if(ques_id!=0){
			question = pengPengDao.queryQuestion(ques_id);
		}
		params.put("ques_id", question.getQues_id());
		params.put("start", start);
		params.put("end", end);
		params.put("name_type", name_type);
		params.put("gender", gender);
		
		jsonObject.put("status", true);
		jsonObject.put("question", question.getQuestion());
		jsonObject.put("ques_id", question.getQues_id());
		//���û���ͷ��·��
		List<AnswerUsers> answerUsers = pengPengDao.queryAnswers(params);
		if(answerUsers!=null || answerUsers.size()>0){
			for(AnswerUsers ans:answerUsers){
				if("����".equals(name_type)){
					ans.setProfile(hostPath+"avatar_def.png");
					ans.setUser_nickname("����");
				}else {
					ans.setProfile(hostPath+ans.getProfile());
				}
				Map<String, Object> params02 = new HashMap<String, Object>();
				params02.put("user_id", user_id);
				params02.put("ans_id", ans.getAns_id());
				//��ǰ�û��Ƿ����
				if(pengPengDao.queryIsLaud(params02) != null){
					ans.setIsLaud(1);
				}else {
					ans.setIsLaud(0);
				}
			}
		}
		jsonObject.put("answer", answerUsers);
		
		Map<String, Object> params03 = new HashMap<String, Object>();
		params03.put("ques_id", question.getQues_id());
		params03.put("user_id", user_id);
		if("".equals(name_type)){
			name_type = "ʵ��";
		}
		params03.put("name_type", name_type);
		System.out.println("params03="+params03);
		Answer answer = pengPengDao.queryCurrUserAnswer(params03);
		if(answer != null){
			//��ӵ�ǰ��¼�û�������Ļش�
			jsonObject.put("contentLen", answer.getContent().length());
		}else{
			jsonObject.put("contentLen", 0);
		}
		return jsonObject;
	}
	
	public JSONObject questionList(String user_id, String name_type, int pageSize, int pageNumber){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("name_type", name_type);
		params.put("start", start);
		params.put("end", end);
		List<Questions> questionList = pengPengDao.queryTypeQuestions(params);
		if(questionList!=null || questionList.size()>0){
			for(Questions q:questionList){
				Map<String, Object> params02 = new HashMap<String, Object>();
				params02.put("ques_id", q.getQues_id());
				params02.put("user_id", user_id);
				params02.put("name_type", q.getName_type());
				if(pengPengDao.queryUserIsAns(params02)!=null){
					q.setAnsState(1);
				}else {
					q.setAnsState(0);
				}
				if(q.getName_type()==null){
					q.setName_type(name_type);
				}
			}
		}
		System.out.println("questionList="+questionList);
		jsonObject.put("status", true);
		jsonObject.put("data", questionList);
		jsonObject.put("msg", "�����б��ѯ�ɹ�");
		
		return jsonObject;
	}
	
	public JSONObject addChildAnsLaud(String user_id, int ans_id){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("ans_id", ans_id);
		params.put("child_ans_creatime", new Timestamp(System.currentTimeMillis()));
		pengPengDao.addChildAnsLaud(params);
		jsonObject.put("status", true);
		jsonObject.put("msg", "�û����޳ɹ�");
		return jsonObject;
	}
	
	public JSONObject currUserAnswer(int ques_id, String user_id, String name_type, int pageSize, 
			int pageNumber, String hostPath01){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ques_id", ques_id);
		params.put("user_id", user_id);
		if("".equals(name_type)){
			name_type = "ʵ��";
		}
		params.put("name_type", name_type);
		OwnAnswer ownAnswer =  pengPengDao.queryOwnAnswer(params);
		
		List<ChildAnswer> childAns = new ArrayList<ChildAnswer>();
		if(ownAnswer != null){//������۵�����
			if("����".equals(name_type)){
				ownAnswer.setProfile(hostPath01+"avatar_def.png");
			}else {
				if(ownAnswer.getProfile()==null){
					ownAnswer.setProfile(hostPath01+"avatar_def.png");
				}else {
					ownAnswer.setProfile(hostPath01+ownAnswer.getProfile());
				}
			}
			Map<String, Object> params02 = new HashMap<String, Object>();
			int start = (pageNumber-1)*pageSize;
			int end = pageSize;
			params02.put("start", start);
			params02.put("end", end);
			params02.put("ans_id", ownAnswer.getAns_id());
			params02.put("user_id", user_id);
			childAns = pengPengDao.queryOwnChildAnswers(params02);
			if(childAns!=null || childAns.size()>0){
				for(ChildAnswer c:childAns){//����û�ͷ���url��ַ
					if(c.getProfile()==null){
						c.setProfile(hostPath01+"avatar_def.png");
					}else {
						c.setProfile(hostPath01+c.getProfile());
					}
					//�����������۵�����
					List<TwoChildAnswer> twoChildAns = pengPengDao.queryTwoChildAns(c.getChild_ans_id());
					if(twoChildAns!=null || twoChildAns.size()>0){
						for(TwoChildAnswer twoChild:twoChildAns){
							if(twoChild.getProfile()==null){
								twoChild.setProfile(hostPath01+"avatar_def.png");//���ͷ��url
							}else {
								twoChild.setProfile(hostPath01+twoChild.getProfile());//���ͷ��url
							}
						}
					}
					//��������۵�����
					c.setTwoChildAns(twoChildAns);
				}
			}
		}
		//���������
		ownAnswer.setChild_answers(childAns);
		//��ӵ�������
		ownAnswer.setTotalLaud(pengPengDao.countAnswerLaud(ownAnswer.getAns_id()));
		//�����������
		Map<String, Object> params03 = new HashMap<String, Object>();
		params03.put("ans_id", ownAnswer.getAns_id());
		params03.put("user_id", user_id);
		ownAnswer.setTotalCom(pengPengDao.countChildAnswer(params03));
		
		jsonObject.put("status", true);
		jsonObject.put("data", ownAnswer);
		return jsonObject;
	}
	
	public JSONObject addChildAnswer(String user_id, String parent_user_id, int ans_id, String content){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("parent_user_id", parent_user_id);
		params.put("ans_id", ans_id);
		params.put("content", content);
		params.put("child_ans_creatime", new Timestamp(System.currentTimeMillis()));
		pengPengDao.addChildAnswer(params);
		jsonObject.put("status", true);
		jsonObject.put("msg", "����������۵�������ӳɹ�");
		return jsonObject;
	}
	
	public JSONObject onlyChildAnswer(int ques_id, String own_user_id, String other_user_id, 
			String name_type, int pageSize, int pageNumber, String hostPath01){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ques_id", ques_id);
		params.put("user_id", other_user_id);
		params.put("name_type", name_type);
		OwnAnswer otherAnswer =  pengPengDao.queryOwnAnswer(params);
		System.out.println(otherAnswer);
		List<ChildAnswer> childAns = new ArrayList<ChildAnswer>();
		if(otherAnswer != null){//������۵�����
			Map<String, Object> params02 = new HashMap<String, Object>();
			int start = (pageNumber-1)*pageSize;
			int end = pageSize;
			params02.put("start", start);
			params02.put("end", end);
			params02.put("own_user_id", own_user_id);
			params02.put("other_user_id", other_user_id);
			params02.put("ans_id", otherAnswer.getAns_id());
			childAns = pengPengDao.queryOnlyChildAnswers(params02);
			if(childAns!=null || childAns.size()>0){
				for(ChildAnswer c:childAns){//����û�ͷ���url��ַ
					if(c.getProfile()==null){
						c.setProfile(hostPath01+"avatar_def.png");
					}else {
						c.setProfile(hostPath01+c.getProfile());
					}
					//������۵����ۣ����໥����������
					Map<String, Object> params03 = new HashMap<String, Object>();
					params03.put("child_ans_id", c.getChild_ans_id());
					params03.put("own_user_id", own_user_id);
					params03.put("other_user_id", other_user_id);
					List<TwoChildAnswer> twoChildAns = pengPengDao.queryOnlyTwoChildAns(params03);
					if(twoChildAns!=null || twoChildAns.size()>0){
						for(TwoChildAnswer twoChild:twoChildAns){
							if(twoChild.getProfile()==null){
								twoChild.setProfile(hostPath01+"avatar_def.png");//���ͷ��url
							}else {
								twoChild.setProfile(hostPath01+twoChild.getProfile());//���ͷ��url
							}
						}
					}
					//��������۵����ۣ����໥����������
					c.setTwoChildAns(twoChildAns);
				}
				//���������
				otherAnswer.setChild_answers(childAns);
			}
		}
		//��ӵ�������
		otherAnswer.setTotalLaud(pengPengDao.countAnswerLaud(otherAnswer.getAns_id()));
		//�����������
		Map<String, Object> params03 = new HashMap<String, Object>();
		params03.put("ans_id", otherAnswer.getAns_id());
		params03.put("user_id", own_user_id);
		otherAnswer.setTotalCom(pengPengDao.countChildAnswer(params03));
		
		jsonObject.put("status", true);
		jsonObject.put("data", otherAnswer);
		return jsonObject;
	}
	
	public JSONObject addAnswerSee(int ans_id){
		JSONObject jsonObject = new JSONObject();
		//�Ȳ�ѯ�����
		Answer answer = pengPengDao.queryAnswerById(ans_id);
		if(answer != null){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ans_id", ans_id);
			params.put("see", answer.getSee()+1);
			pengPengDao.saveAnswerSee(params);//���Ӳ鿴��
		}
		jsonObject.put("status", true);
		jsonObject.put("msg", "����������ɹ�");
		return jsonObject;
	}
	
	public JSONObject addTwoChildAns(String user_id, String parent_user_id, int child_ans_id, 
			String content){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("child_ans_id", child_ans_id);
		params.put("grand_user_id", parent_user_id);
		params.put("content", content);
		params.put("two_child_ans_creatime", new Timestamp(System.currentTimeMillis()));
		pengPengDao.addTwoChildAns(params);
		
		jsonObject.put("status", true);
		jsonObject.put("msg", "�ٴ�������۵����۳ɹ�");
		return jsonObject;
	}
	
	public JSONObject batchDelQuestions(String ids){
		JSONObject jsonObject = new JSONObject();
		List<Integer> idList = new ArrayList<Integer>();
		if(!"".equals(ids)){
			String[] spIds = ids.split(",");
			for(int i=0;i<spIds.length;i++){
				idList.add(Integer.valueOf(spIds[i]));
			}
		}
		int count = pengPengDao.batchDelQuestions(idList);
		jsonObject.put("status", true);
		jsonObject.put("msg", "�ɹ�ɾ����"+count+"������");
		return jsonObject;
	}
	
}
