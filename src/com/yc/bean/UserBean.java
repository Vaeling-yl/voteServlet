package com.yc.bean;

/**
 * 用户实体类
 * @author 38929
 *
 */
public class UserBean {
	
	private Integer usid;//用户编号
	private String uname;//用户名
	private String pwd;//密码
	
	public Integer getUsid() {
		return usid;
	}
	public void setUsid(Integer usid) {
		this.usid = usid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "usersBean [usid=" + usid + ", uname=" + uname + ", pwd=" + pwd + "]";
	}
}
