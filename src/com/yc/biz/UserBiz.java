package com.yc.biz;

import java.util.List;

import com.yc.bean.UserBean;
import com.yc.dao.UserDAO;

public class UserBiz {
	UserDAO dao = new UserDAO();
	
	/**
	 * 
	 */
	
	public int reg(UserBean bean) throws Exception{
		
		return dao.add(bean);
		
	}
	
	public UserBean login(UserBean bean) throws Exception {
		List<UserBean> list = dao.findByTrem(bean);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
		
	}
	
}
