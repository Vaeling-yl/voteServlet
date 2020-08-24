package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.UserBean;
import com.yc.commons.DBHepler;

/**
 * 用户 操作数据库 
 * @author 38929
 *
 */
public class UserDAO implements BaseDAO<UserBean>{

	DBHepler db = new DBHepler();
	
	/**
	 * 用户注册
	 */
	@Override
	public int add(UserBean t) throws Exception {
		String sql = "insert into t_user values(seq_userInfo_usid.nextval,?,?)";
		return db.update(sql, t.getUname(),t.getPwd());
	}

	/**
	 * 多条件查询  根据用户编号 用户姓名 查询 
	 */
	@Override
	public List<UserBean> findByTrem(UserBean t) throws Exception {
		StringBuffer sb  = new StringBuffer();
		sb.append("select * from  t_user where 1 = 1 ");
		List<Object> params =  null;
		if(null != t) {
			params = new ArrayList<Object>();
			if(null != t.getUsid()){
				sb.append(" and  usid = ? ");
				params.add(t.getUsid());
			}
			if(null != t.getUname()){
				sb.append(" and  uname = ? ");
				params.add(t.getUname());
			}
			if(null != t.getPwd()){
				sb.append(" and pwd = ? ");
				params.add(t.getPwd());
			}
		}
		return db.findMutiple(sb.toString(), params,  UserBean.class);
	}

	
	@Override
	public int update(UserBean t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(UserBean t) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	


}
