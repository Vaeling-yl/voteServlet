package com.yl.myservlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.UserBean;
import com.yc.biz.UserBiz;
import com.yc.dao.OptionsDAO;
import com.yc.dao.VoteDAO;
import com.yc.util.WebUtil;
import com.yc.vo.VoteVO;

@WebServlet("/voteUserServlet")
public class voteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码集
		WebUtil.setEncoding(request, response);
		// 获取前端页面操作指令
		String op = request.getParameter("op");

		try {
			if ("register".equals(op)) { // 用户注册
				register(request, response);
			} else if ("login".equals(op)) { // 用户登录
				login(request, response);
			} else if ("CheckName".equals(op)) { // 检查重名问题
				CheckName(request, response);
			} else if ("doAdd".equals(op)) { // 添加主题
				doAdd(request, response);
			} else if ("doAddVote".equals(op)) { // 添加投票
				doAddVote(request, response);
			} else if ("doView".equals(op)) { // 查看投票
				doView(request, response);
			} else if ("doVote".equals(op)) { // 统计投票
				doVote(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 统计投票
	private void doVote(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WebUtil.setEncoding(request, response); // 设置编码集
		String vid = request.getParameter("vid"); // 获取主题编号

		OptionsDAO odao = new OptionsDAO();
		VoteDAO vdao = new VoteDAO();
		VoteVO vo = new VoteVO();

		vo.setVid(vid);

		// 根据主题编号vid进行多表查询
		List<VoteVO> list = vdao.findByTremVo(vo);
		// 统计当前主题投票网友
		int count = odao.getCountUsid(vid);

		request.setAttribute("votes", list);
		request.setAttribute("count", count);

		// 请求转发
		request.getRequestDispatcher("vote.jsp").forward(request, response);
	}

	// 查看投票
	private void doView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WebUtil.setEncoding(request, response); // 设置编码集
		String vid = request.getParameter("vid"); // 获取主题编号

		OptionsDAO odao = new OptionsDAO();
		VoteDAO vdao = new VoteDAO();
		VoteVO vo = new VoteVO();

		vo.setVid(vid);

		// 根据主题编号vid进行多表查询
		List<VoteVO> list = vdao.findByTremVo(vo);
		// 统计当前主题投票网友
		int count = odao.getCountUsid(vid);
		// System.out.println("count:" + count);
		// 获取该主题的总票数
		int total = 0;
		for (VoteVO v : list) {
			total += v.getViews();
		}

		request.setAttribute("votes", list);
		request.setAttribute("count", count);
		request.setAttribute("total", total);

		// 请求转发
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}

	// 添加投票
	private void doAddVote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// 设置编码集
		WebUtil.setEncoding(request, response);
		// 获取投票选项
		String[] options = request.getParameterValues("options");
		// 获取当前登录的用户信息
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");

		OptionsDAO dao = new OptionsDAO();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("usid", user.getUsid()); // 用户编号
		map.put("options", options); // 投票选项

		int i = dao.updateOptions(map);
		// 开始投票
		if (i > 0) {
			request.setAttribute("info", "a_success");
		} else {
			request.setAttribute("info", "a_error");
		}

		// 请求转发到info.jsp
		request.getRequestDispatcher("info.jsp").forward(request, response);
	}

	// 添加主题
	private void doAdd(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// 设置编码集
		WebUtil.setEncoding(request, response);
		// 获取主题名称 主题类型 选项数组
		String vname = request.getParameter("vname");
		String vtype = request.getParameter("vtype");
		String[] options = request.getParameterValues("options");

		// 参数注入map中
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vname", vname);
		map.put("vtype", vtype);
		map.put("options", options);

		// 数据插入
		VoteDAO dao = new VoteDAO();
		int i = dao.addVote(map);
		if (i > 0) {
			request.setAttribute("info", "v_success");
		} else {
			request.setAttribute("info", "v_error");
		}

		// 请求转发到info.jsp(信息提示)界面
		request.getRequestDispatcher("info.jsp").forward(request, response);
	}

	// 检查重名问题
	private void CheckName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 设置编码集
		WebUtil.setEncoding(request, response);
		// 从页面取值
		String uname = request.getParameter("uname");

		UserBiz ubiz = new UserBiz();
		UserBean bean = new UserBean();
		bean.setUname(uname);
		// 连接数据查询用户名是否重名
		UserBean user = ubiz.login(bean);
		System.out.println(user);
		if (null != user) {
			response.getWriter().print("用户名已被注册");
			return;
		} else {
			response.getWriter().print("用户名可用");
			return;
		}
	}

	// 用户注册
	private void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 设置编码集
		WebUtil.setEncoding(request, response);
		// 从页面取值
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		System.out.println(uname + "--" + pwd);

		UserBiz ubiz = new UserBiz();
		UserBean bean = new UserBean();
		bean.setUname(uname);
		bean.setPwd(pwd);

		// 注册
		int i = ubiz.reg(bean);
		if (i > 0) {
			request.setAttribute("info", "r_success");
		} else {
			request.setAttribute("info", "r_error");
		}

		// 请求转发到info.jsp(信息提示)界面
		request.getRequestDispatcher("info.jsp").forward(request, response);
	}

	// 用户登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 设置编码集
		WebUtil.setEncoding(request, response);
		// 从页面取值
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");

		UserBiz ubiz = new UserBiz();
		UserBean bean = new UserBean();
		bean.setUname(uname);
		bean.setPwd(pwd);
		// System.out.println("ubiz:" + ubiz.login(bean));
		// 调用登录方法
		UserBean user = ubiz.login(bean);

		if (null != user) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.getWriter().print("success");
		} else {
			response.getWriter().print("error");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
