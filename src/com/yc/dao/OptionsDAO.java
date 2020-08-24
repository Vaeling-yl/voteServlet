package com.yc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yc.bean.OptionsBean;
import com.yc.bean.VoteBean;
import com.yc.commons.DBHepler;

public class OptionsDAO {
	DBHepler db = new DBHepler();
	/**
	 * 
	 * @param bean
	 * @return
	 * @throws 
	 */
	 public List<OptionsBean> findByTerm(OptionsBean bean) {
			StringBuffer sb  = new StringBuffer();
			sb.append("select * from options where 1=1");
			List<Object> params=null;
			if(null != bean) {
				params = new ArrayList<Object>();
				if(null != bean.getVid()){
					sb.append(" and  vid = ? ");
					params.add(bean.getVid());
				}
				if(null != bean.getOpid()	){
					sb.append(" and  opid = ? ");
					params.add(bean.getOpid()	);
				}
			
			}
			sb.append("order by vid desc ");
			return db.findMutiple(sb.toString(), params,OptionsBean.class);
		}

	
	/**
	 * 
	 * @param parseInt
	 * @return
	 * @throws  
	 */
	public String getUsid(Integer opid){
		// TODO Auto-generated method stub
		 OptionsBean bean = new OptionsBean();
		 bean.setOpid(opid);
		 List<OptionsBean> list = findByTerm(bean);
		 if(null != list && list.size() > 0) {
			 return list.get(0).getUsid();
		 }
		
		return null;
	}
	
	/**
	 * 投票  option存储option uisd 用户id
	 * @param map
	 * @return
	 * @throws SQLException 
	 * @throws  
	 */
	public int updateOptions(Map<String,Object> map) throws SQLException  {
		String sql = "update options set views = views+1 , usid=? where opid=?";
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params =new  ArrayList<List<Object>>();
		List<Object> param = null;
		//投票的选项  ---传过来选项的编号
		String [] options = (String [])map.get("options");
		if(null != options && options.length > 0 ) {
			for (String opid : options) {
				//获取usid
				String usid = getUsid(Integer.parseInt(opid));
				if (null == usid) {
					usid = map.get("usid").toString()+",";
					System.out.println("----");
					System.out.println(usid);
				}else {
					usid = usid + map.get("usid").toString()+",";
				}
				param = new ArrayList<Object>();
				param.add(usid);
				param.add(opid);
				params.add(param);
				sqls.add(sql);
			}
		}
		return db.update(sqls, params);
	}
	
	/**
	 * 根据主题编号统计改主题有多少个网友参与投票  去重操作
	 * @param vid
	 * @return
	 * @throws Exception 
	 */
	   public int getCountUsid(String vid) {
		   OptionsBean bean = new OptionsBean();
		   bean.setVid(vid);
		   List<OptionsBean> list = findByTerm(bean);
		   StringBuffer sb  = new StringBuffer();
		   if (null != list && list.size() > 0) {
			   //循环获取uisd的值
			   for (OptionsBean op : list) {
				   //去出未被投票的选项
				   if (null != op.getUsid()) {
					   sb.append(op.getUsid());
				}
			}
		}
		   //字符分割
		   String [] usid = sb.toString().split(",");
		   //return uisd.length
		   //如果同一用户投了多次  但是我们仅算一次
		   //不允许重复的元素
		   Set<String> set = new HashSet<String>();
		   for (String id : usid) {
			   if (null != id && !"".equals(id)) {
				set.add(id);
			}
		}
		   //去重后的结果
		return set.size();
		   
	   }

	  
}
