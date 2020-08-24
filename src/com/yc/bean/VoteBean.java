package com.yc.bean;

/**
 * 投票主题实体类
 * @author 38929
 *
 */
public class VoteBean {

	private String vid;//主题编号
	private String vname;//主题名
	private String vtype;//投票类型 1单选 2多选
	private String startDate;//开始时间
	private String endDate;//结束时间
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
	@Override
	public String toString() {
		return "voteBean [vid=" + vid + ", vname=" + vname + ", vtype=" + vtype + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
}
