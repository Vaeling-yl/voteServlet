package com.yc.bean;

/**
 * 投票选项实体类
 * @author 38929
 *
 */
public class OptionsBean {

	private Integer opid;//选项编号
	private String vid;//主题编号
	private String opname;//选项名
	private Integer views;//投票数
	private String usid;//投票用户ID
	public Integer getOpid() {
		return opid;
	}
	public void setOpid(Integer opid) {
		this.opid = opid;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
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
		return "optionsBean [opid=" + opid + ", vid=" + vid + ", opname=" + opname + ", views=" + views + ", usid="
				+ usid + "]";
	}
}
