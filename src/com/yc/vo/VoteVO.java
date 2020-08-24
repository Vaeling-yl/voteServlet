package com.yc.vo;

import java.io.Serializable;

/**
 * View Object：视图对象  VO
 * @author 38929
 *
 */
public class VoteVO implements Serializable{
	
	/**
	 * 压制警告
	 */
	private static final long serialVersionUID = 6277610403036326362L;
	
	private String vid;//主题编号
	private String vname;//主题名
	private String vtype;//投票类型 1单选 2多选
	private String startDate;//开始时间
	private String endDate;//结束时间
	private Integer opid;//选项编号
	private String opname;//选项名
	private Integer views;//投票数
	private String usid;//投票用户ID
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getOpid() {
		return opid;
	}
	public void setOpid(Integer opid) {
		this.opid = opid;
	}
	public String getOpname() {
		return opname;
	}
	public void setOpname(String opname) {
		this.opname = opname;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public String getUsid() {
		return usid;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	@Override
	public String toString() {
		return "VoteVO [vid=" + vid + ", vname=" + vname + ", vtype=" + vtype + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", opid=" + opid + ", opname=" + opname + ", views=" + views + ", usid="
				+ usid + "]";
	}
	
	
}
