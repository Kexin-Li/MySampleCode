package org.likexin.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.likexin.entity.User;
import org.likexin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jef.common.wrapper.Page;

@Controller
@EnableAutoConfiguration
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 显示登录页面
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	/**
	 * 显示管理页面
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	/**
	 * 验证登录
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "userLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userLogin(User user, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean res = userService.checkUserInfo(user.getUsername(), user.getPassword());
		if (res) {
			map.put("success", true);
			map.put("message", "登录成功");
			User ur = userService.getUserByUsername(user.getUsername()).get(0);
			session.setAttribute("user_name", ur);
		} else {
			map.put("success", false);
			map.put("message", "密码错误");
		}
		return map;
	}
	
	/**
	 * 显示所有用户
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "users", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> userList(@RequestParam(value = "pageNow", required = false, defaultValue = "1") Integer pageNow, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "18") Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<User> list = userService.findAndPageAll((pageNow - 1) * pageSize, pageSize);
		map.put("totalPage", list.getTotalPage());
		map.put("pageNow", pageNow);
		map.put("pageList", list);
		return map;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "users", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUsers(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		user.setCreateTime(new Date());
		userService.addUser(user);
		return map;
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateUsers(@PathVariable(value = "id") Integer id, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		userService.updateUser(user);
		return map;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "users", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteUsers(@RequestParam(value = "idStr") String idStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (idStr.length() > 1) { // 批量删除
			String[] str = idStr.split(",");
			List<Integer> idList = new ArrayList<>();
			for (int i = 0; i < str.length; i++) {
				idList.add(Integer.valueOf(str[i]));
			}
			userService.deleteBatch(idList);
		} else { // 单个删除
			userService.deleteUser(Integer.valueOf(idStr));
		}
		map.put("message", "删除成功");
		return map; 
	}
	
	/**
	 * 根据用户名查找用户
	 * @return
	 */
	@RequestMapping(value = "userSearch", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchUsers(@RequestParam(value = "search") String search, @RequestParam(value = "pageNow", required = false) Integer pageNow, 
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<User> list = userService.findAndPageByLike(search, (pageNow - 1) * pageSize, pageSize);
		map.put("totalPage", list.getTotalPage());
		map.put("pageNow", pageNow);
		map.put("pageList", list);
		return map;
	}
	
	/**
	 * 修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "users/password/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updatePassword(@PathVariable(value = "id") Integer id, @RequestParam(value = "oldPassword") String oldPassword, 
			@RequestParam(value = "newPassword") String newPassword, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user_name");
		boolean result = userService.checkUserInfo(user.getUsername(), oldPassword);
		if (result == false) {
			map.put("message", "原密码错误");
			return map;
		} else {
			user.setUserId(id);
			user.setPassword(newPassword);
			userService.updateUser(user);
			map.put("message", "修改成功");
			return map;
		}
	}
}