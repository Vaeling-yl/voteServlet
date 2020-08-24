package com.yc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.UserBean;
import com.yc.bean.VoteBean;
import com.yc.commons.DBHepler;
import com.yc.util.StringUtil;
import com.yc.vo.VoteVO;

@SuppressWarnings(value = { "all" })
public class VoteDAO {
	
	DBHepler db = new DBHepler();

	public int addVote(Map<String, Object> map) throws SQLException {
		// 添加主题的vsql
		String vsql = "insert into vote values(?,?,?,to_date(?,'yyyy-MM-dd'),to_date(?,'yyyy-MM-dd'))";
		// 添加选项的osql
		String osql = "insert into options values(seq_options_opid.nextval,?,?,0,null)";
		//获取主题生成的参数
		String vid = StringUtil.getVid();
		String startDate = StringUtil.getStartDate();
		String endDate = StringUtil.getEndDate();

		List<String> sqls = new ArrayList<String>();
		//
		List<List<Object>> params = new ArrayList<List<Object>>();
		//设置投票主题表的参数
		List<Object> vparams = new ArrayList<Object>();
		vparams.add(vid);
		vparams.add(map.get("vname"));
		vparams.add(map.get("vtype"));
		vparams.add(startDate);
		vparams.add(endDate);
		//将参数添加到params集合中
		params.add(vparams);
		//添加对应的sql语句
		sqls.add(vsql);
		//设置投票选项表的参数
		List<Object> oparams = null;

		String[] options = (String[]) map.get("options");

		if (null != options && options.length > 0) {
			
			
			for (String op : options) {
				oparams = new ArrayList<Object>();
				oparams.add(vid);
				oparams.add(op);
				params.add(oparams);
				sqls.add(osql);
			}
		}
		return db.update(sqls, params);
	}
	
	/**
	 * 查看主题信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public List<VoteBean> findByTrem(VoteBean bean) throws Exception {
		StringBuffer sb  = new StringBuffer();
		String sql="select vid,vname,vtype,to_char(startDate,'yyyy-MM-dd') startDate,"
				+ "to_char(endDate,'yyyy-MM-dd')endDate from vote where 1=1";
		sb.append(sql);
		List<Object> params=null;
		if(null != bean) {
			params = new ArrayList<Object>();
			if(null != bean.getVid()){
				sb.append(" and  vid = ? ");
				params.add(bean);
			}
			if(null != bean.getVname()	){
				sb.append(" and  vname = ? ");
				params.add(bean.getVname()	);
			}
			if(null != bean.getVtype()){
				sb.append(" and vtype = ? ");
				params.add(bean.getVtype());
			}
		}
		sb.append("order by vid desc ");
		return db.findMutiple(sb.toString(), params,VoteBean.class);
	}
	
	
	public List<VoteVO> findByTremVo(VoteVO vo) throws Exception {
		StringBuffer sb  = new StringBuffer();
		String sql="select v.vid,v.vname,v.vtype,to_char(v.startDate,'yyyy-MM-dd')startDate,"
				+ "to_char(v.endDate,'yyyy-MM-dd')endDate,o.opid,o.opname,o.views,o.usid "
				+ "from vote v inner join options o on v.vid = o.vid where 1=1";
		sb.append(sql);
		List<Object> params=null;
		if(null != vo) {
			params = new ArrayList<Object>();
			if(null != vo.getVid()){
				sb.append(" and  v.vid = ? ");
				params.add(vo.getVid());
			}

		}
		return db.findMutiple(sb.toString(), params,VoteVO.class);
	}
}
