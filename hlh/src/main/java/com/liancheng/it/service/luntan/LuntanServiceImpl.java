package com.liancheng.it.service.luntan;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.dao.luntan.LuntanDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.luntan.Luntan;
import com.liancheng.it.entity.user.User;

@Service("luntanService")
public class LuntanServiceImpl implements LuntanService {
	
	@Autowired
	private LuntanDao luntanDao;
	@Autowired
	private UserDao userDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public JSONObject addCommont(MultipartFile[] pics, String user_id, String lt_content, String type, 
			String lt_type, String area, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		String picName = String.valueOf(System.currentTimeMillis());
		String picName02 = "";
		if (pics != null) {
			//遍历保存发表动态的图片
			for(int i=0;i<pics.length;i++){
				picName = picName+"-"+i;
				if(!pics[i].isEmpty()){//只要上传图片不为空
					String suffix = pics[i].getOriginalFilename().substring(pics[i].getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
					picName02 = picName02+picName+suffix+",";
					try {
						logger.info("发动态图片保存的本地路径="+localBasePath+picName+suffix);
						//保存图片到本地目录
						pics[i].transferTo(new File(localBasePath+picName+suffix));
					} catch (Exception e) {
						logger.error("保存发表的动态图片失败！！");
					}
				}
			}
		}
		Luntan lun = new Luntan();
		lun.setType(type);
		lun.setUser_id(user_id);
		lun.setArea(area);
		lun.setLt_type(Integer.valueOf(lt_type));
		lun.setLt_content(lt_content);
		lun.setPic(picName02);
		lun.setLt_creatime(new Timestamp(System.currentTimeMillis()));
		int rows = luntanDao.saveCommont(lun);
		if (rows == 1) {
			jsonObject.put("status", true);
			if("1".equals(lt_type)){
				jsonObject.put("msg", "添加评论成功");
			}else {
				jsonObject.put("msg", "添加图文直播成功");
			}
			return jsonObject;
		}else {
			jsonObject.put("status", false);
			if("1".equals(lt_type)){
				jsonObject.put("msg", "添加评论失败");
			}else {
				jsonObject.put("msg", "添加图文直播失败");
			}
			return jsonObject;
		}
	}
	
	public JSONObject showPaginationLT(int pageSize, int pageNumber, String type, 
			int lt_type, String area, String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		if(lt_type == 1){
			params.put("type", type);
		}else {
			params.put("type", "");
		}
		params.put("lt_type", lt_type);
		params.put("area", area);
		List<Luntan> luntans = luntanDao.queryPaginationLT(params);
		if(luntans.size()>0){
			for(Luntan luntan:luntans){
				if(luntan.getProfile() != null){
					luntan.setProfile(hostPath01+luntan.getProfile());
				}
				List<String> picList = new ArrayList<String>();
				if(luntan.getPic() != null){
					String[] picDBs = luntan.getPic().split(",");
					for(String picName:picDBs){
						picList.add(hostPath02+picName);
					}
				}
				luntan.setPics(picList);
			}
		}
		jsonObject.put("rows", luntans);
		jsonObject.put("total", luntanDao.totalPaginationLT(params));
		return jsonObject;
	}
	
	public JSONObject showDetailLT(int lt_id, String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		Luntan luntan = luntanDao.queryLTById(lt_id);
		if(luntan.getProfile() != null){
			luntan.setProfile(hostPath01+luntan.getProfile());
		}
		List<String> picList = new ArrayList<String>();
		if(luntan.getPic() != null){
			String[] picDBs = luntan.getPic().split(",");
			for(String picName:picDBs){
				picList.add(hostPath02+picName);
			}
		}
		luntan.setPics(picList);
		jsonObject.put("data", luntan);
		return jsonObject;
	}
	
	
	
	
	
}
