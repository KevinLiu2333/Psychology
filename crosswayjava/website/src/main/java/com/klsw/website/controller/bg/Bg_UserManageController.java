package com.klsw.website.controller.bg;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.common.mall.model.BgAuthority;
import com.klsw.common.mall.model.BgManager;
import com.klsw.common.mall.model.BgRole;
import com.klsw.website.controller._BaseController;
import com.klsw.website.service.background.Bg_AuthorityService;
import com.klsw.website.service.background.Bg_ManagerService;
import com.klsw.website.service.background.Bg_RoleService;
import com.klsw.website.util.Bg_Constants;
import com.klsw.website.util.Constants;
import com.klsw.website.util.PasswdEncryption;
import com.klsw.website.vo.Actor;
import com.klsw.website.vo.Role;
import com.klsw.website.vo.RoleAuthority;

@Controller
@RequestMapping("/manage")
public class Bg_UserManageController extends _BaseController {
	
	@Autowired
	private Bg_ManagerService bgManagerService ;
	
	@Autowired
	private Bg_RoleService bgRoleService ;
	
	@Autowired
	private Bg_AuthorityService bgAuthorityService;	
	
	public static final int PAGESIZE = 1;
	
	/**
	 * 该请求的功能是：进入添加管理员界面     （超级管理员权限（00））
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="order/toAddManager")
 	public String toAddManager(HttpServletRequest request, Model model) {
		String msg = "";
		
		List<BgAuthority> authorityList=Lists.newArrayList();	
		//获取管理员的角色id
		BgManager bgManagers = (BgManager) request.getSession().getAttribute(Bg_Constants.MANAGER);
		Integer roleId = bgManagers.getRollid();
		//创建角色对象
		BgRole bgRoles = new BgRole();
		//设置角色对象的角色id
		bgRoles.setRoleid(roleId);
		try {
			//根据角色id从数据库中查出一条角色记录
			bgRoles = bgRoleService.selectOne(bgRoles);
			//获取该角色记录中的角色权限id，并根据”，“分割成权限id数组
			String[] manageAuthority = bgRoles.getRoleauthority().split(",");
			
			for(int i=0;i<manageAuthority.length;i++) {
				BgAuthority bgAuthoritys=new BgAuthority();
				bgAuthoritys.setAuthorityid(manageAuthority[i]);
				bgAuthoritys=bgAuthorityService.selectOne(bgAuthoritys);
				authorityList.add(bgAuthoritys);
				
				String str = manageAuthority[i];		
				if(str.equals("00")) {
					String managerId = request.getParameter("managerId"); 
					
					//创建管理员对象和角色对象类型的引用
					BgManager bgManager = null;
					BgRole role = null;
					//创建自定义实体Actor集合，该实体包含角色id，角色名称、角色权限
					List<Actor> actorList = Lists.newArrayList();
					//创建角色集合
					List<BgRole> bgRoleList = null;
					try {
						//查询出所有的角色对象，并加入到角色集合中
						bgRoleList = bgRoleService.selectAll();
						
						//遍历角色集合，找出大权限及对应的子权限
						for(BgRole bgRole:bgRoleList) {
							//创建角色权限的集合
							List<RoleAuthority> roleAuthorityList = Lists.newArrayList();
							//创建Actor实体对象
							Actor actor = new Actor();
							//设置actor的属性值
							actor.setRoleId(bgRole.getRoleid());//设置角色id
							actor.setRoleName(bgRole.getRolename());//设置角色名称
							
							//创建大权限构成的集合
							List<String> bigAuthorityId = Lists.newArrayList();
							
							//获取每个角色对象的角色权限并根据”，“进行分割，得到权限id数组
							String role_Authority = bgRole.getRoleauthority();
							String[] authorityIds;
							if(!role_Authority.contains(",")) {
								authorityIds =new String[]{role_Authority};
							}
							authorityIds = role_Authority.split(",");
							
							//根据得到的权限id数组进行遍历，找到大权限构成的集合
							for(String s : authorityIds) {
								if(s.length()==2) {
									bigAuthorityId.add(s);
								} else if(s.length() == 4){
									String sub = s.substring(0, 2);
									if(!bigAuthorityId.contains(sub)) {
										bigAuthorityId.add(sub);
									}
								}
							}
							
							//根据大权限集合，将大权限和对应的小权限集合加入到RoleAuthority实体对象中
							for(String big : bigAuthorityId) {
								//创建小权限集合
								List<BgAuthority> bgAuthorityList = Lists.newArrayList();
								//创建自定义的角色权限实体对象，包含两个属性：bgAuthority和bgAuthorityList
								RoleAuthority roleAuthority = new RoleAuthority();
								
								//根据大权限id查出对应的权限对象，并将其设置为RoleAuthority实体的bgAuthority属性
								BgAuthority entity = new BgAuthority();
								entity.setAuthorityid(big);
								BgAuthority bgAuthority = bgAuthorityService.selectOne(entity);
								roleAuthority.setBgAuthority(bgAuthority);
								
								//根据分割得到的权限id数组进行遍历，找出某个大权限对应的子权限								
								for(String s : authorityIds) {
									if(s.length() == 4) {
										if(s.substring(0, 2).equals(big)) {
											entity.setAuthorityid(s);
											bgAuthority = bgAuthorityService.selectOne(entity);
											bgAuthorityList.add(bgAuthority);
										}
									}
								}
								
								//如果该角色对象只有某个大权限，没有对应的子权限，则将所有的子权限加入
								if(bgAuthorityList.isEmpty()) {
									bgAuthorityList = bgAuthorityService.selectByLikeName(big);
								}
								
								//设置RoleAuthority实体对象的bgAuthorityList属性
								roleAuthority.setBgAuthorityList(bgAuthorityList);
								//将RoleAuthority实体对象加入到对应的实体集合中
								roleAuthorityList.add(roleAuthority);
							}
							
							//设置Actor实体对象的roleAuthorityList属性，并添加到actorList集合中
							actor.setRoleAuthorityList(roleAuthorityList);
							actorList.add(actor);
						}
						
						//为修改管理员准备传到页面上的数据
						if(managerId != null) {
							BgManager entity = new BgManager();
							entity.setUserid(new Integer(managerId));
							bgManager = bgManagerService.selectOne(entity);
							
							BgRole bgRole = new BgRole();
							bgRole.setRoleid(bgManager.getRollid());
							role = bgRoleService.selectOne(bgRole);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					model.addAttribute("role", role);
					model.addAttribute("bgManager", bgManager);
					model.addAttribute("bgRoleList", bgRoleList);
					model.addAttribute("actorList", actorList);
			 		return "background/bg_manage/add_user";
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		msg = "很抱歉，您无此权限！";
		model.addAttribute("authorityList",authorityList);
		model.addAttribute("bgRole",bgRoles);
		model.addAttribute("msg", msg);
		return "background/bg_index";
 	}
	
	
	/**
	 * 该请求的功能是：进行管理员的添加操作
	 * @param request
	 * @return
	 */
	//超级管理员权限（00）
	@RequestMapping(value="order/addManager")
 	public String addManager(HttpServletRequest request){
		//获取页面传过来的值：用户名、输入密码、确认密码、角色名称
		String managerId = request.getParameter("managerid");
		String userName = request.getParameter("userName");
		String inputPasswd = request.getParameter("inputPasswd");
		String enterPasswd = request.getParameter("enterPasswd");
		String roleName = request.getParameter("roleName");
		try {
			BgRole entity = new BgRole();//创建一个角色实体对象
			if(managerId == null) {
				BgManager bgManager = new BgManager();//创建管理员对象
				if(enterPasswd.equals(inputPasswd)) {
					entity.setRolename(roleName);//给实体对象设置角色名称
					BgRole bgRole = bgRoleService.selectOne(entity);//通过角色名称从数据库中查出对应的一条角色记录
					bgManager.setUsername(userName);//设置管理员的用户名
					bgManager.setRollid(bgRole.getRoleid());//设置管理员的角色id
					String storePasswd = PasswdEncryption.toPasswd(inputPasswd);//将密码加密
					bgManager.setUserpassword(storePasswd);//设置管理员的密码
					bgManagerService.insert(bgManager);//向管理员列表中插入一条管理员记录
				}
			} else {
				BgManager bgManager = new BgManager();
				bgManager.setUserid(new Integer(managerId));
				bgManager = bgManagerService.selectByPrimaryKey(bgManager);
				entity.setRolename(roleName);//给实体对象设置角色名称
				BgRole bgRole = bgRoleService.selectOne(entity);//通过角色名称从数据库中查出对应的一条角色记录
				bgManager.setRollid(bgRole.getRoleid());
				bgManagerService.updateByPrimaryKey(bgManager);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
 		return "redirect:userList";
 	}
 	
	/**
	 * 该请求的功能是：查看管理员列表（需要权限（00））
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="order/userList") 
 	public ModelAndView userList(Model model,HttpServletRequest request) {
		
		String msg = "";
		List<BgAuthority> authorityList=Lists.newArrayList();	
		/* 以下部分是获取管理员的权限  */
		BgManager bgManagerss = (BgManager) request.getSession().getAttribute(Bg_Constants.MANAGER);
		Integer roleId = bgManagerss.getRollid();
		BgRole bgRole = new BgRole();
		bgRole.setRoleid(roleId);
		try {
			bgRole = bgRoleService.selectOne(bgRole);
			String[] manageAuthority = bgRole.getRoleauthority().split(",");
			for(int i=0;i<manageAuthority.length;i++) {
				BgAuthority bgAuthoritys=new BgAuthority();
				bgAuthoritys.setAuthorityid(manageAuthority[i]);
				bgAuthoritys=bgAuthorityService.selectOne(bgAuthoritys);
				authorityList.add(bgAuthoritys);
				
				String str = manageAuthority[i];
				if(str.equals("00")) {
 
					ModelAndView mav = new ModelAndView("background/bg_manage/bg_user_list");
					List<BgManager> managerList=null;	
					List<BgManager> bgManagers = Lists.newArrayList();
					String pageNum = request.getParameter("pageNum");
					BgRole role=new BgRole();
					if(pageNum==null){
						 PageHelper.startPage(1, PAGESIZE);
					}
					else{
						PageHelper.startPage(Integer.parseInt(pageNum), PAGESIZE);
					}
					try {
						managerList=bgManagerService.selectAll();
						for(BgManager bgManager :managerList){
							
							role=bgRoleService.selectBySerial(bgManager.getRollid());
							if(role == null) {
								bgManager.setRolename("该角色被删除");
							} else {
								bgManager.setRolename(role.getRolename());
							}
							bgManagers.add(bgManager);
			
						} 
						PageInfo<BgManager> PageInfo = new PageInfo<>(managerList);  
						mav.addObject("bgManagers", PageInfo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			 		return mav;


	 
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		msg = "很抱歉，您无此权限！";
		model.addAttribute("authorityList",authorityList);
		model.addAttribute("bgRole",bgRole);
		return new ModelAndView("background/bg_index" ,"msg",msg);
 	}
	
	//删除管理员//超级管理员权限（00）
	@RequestMapping(value="order/manageDelete")
	public String manageDelete(Model model ,HttpServletRequest request) {
		Integer manageId=Integer.parseInt(request.getParameter("managerId")) ;
		BgManager bgManager =new BgManager();
		bgManager.setUserid(manageId);
		try {
			
 			/*bgManager=bgManagerService.selectOne(bgManager); */
			
			bgManagerService.deleteByPrimaryKey(manageId);
			  
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:userList";
	}
	
	//角色列表
	@RequestMapping(value="order/roleList")
	public String roleList(HttpServletRequest request,Model model) {
		//定义需要返回的消息名称
		String msg = "";
		
		List<BgAuthority> authorityList=Lists.newArrayList();	
		
		//获取管理员的角色id
		BgManager bgManager = (BgManager) request.getSession().getAttribute(Bg_Constants.MANAGER);
		Integer roleId = bgManager.getRollid();
		BgRole bgRoles = new BgRole();
		bgRoles.setRoleid(roleId);
		try {
			//通过角色id查询出对应的角色记录
			bgRoles = bgRoleService.selectOne(bgRoles);
			//获取该角色对象的权限id数组
			String[] manageAuthority = bgRoles.getRoleauthority().split(",");
			//设置角色列表的权限入口
			for(int j=0;j<manageAuthority.length;j++) {
				BgAuthority bgAuthoritys=new BgAuthority();
				bgAuthoritys.setAuthorityid(manageAuthority[j]);
				bgAuthoritys=bgAuthorityService.selectOne(bgAuthoritys);
				authorityList.add(bgAuthoritys);
				
				String str = manageAuthority[j];
				if(str.equals("00")) {
					//创建角色集合
					List<BgRole> bgRoleList = Lists.newArrayList();
					//获取角色大权限数组
					String[] roles = Constants.ROLE_AUTHORITY;
					//创建自定义实体Role（包含角色id、角色名称和由”每个大权限及对应子权限构成的集合“作为元素的集合）集合
					List<Role> roleList = Lists.newArrayList();
					//获取从页面传过来的当前页数，并加入分页插件进行管理
					String pageNum = request.getParameter("pageNum");
					if(pageNum==null) {
						PageHelper.startPage(1, 1);
					} else {
						Integer currentPage = new Integer(pageNum);
						PageHelper.startPage(currentPage, 1);
					}
					try {
						//从数据库中查询出所有角色对象并加入角色集合中
						bgRoleList = bgRoleService.selectAll();
						
						//获取每个Role实体对象
						for(BgRole bgRole : bgRoleList) {
							List<List<BgAuthority>> finalAuthorityList = Lists.newArrayList();
							List<String> bigAuthorityId = Lists.newArrayList();//创建每个角色对象的大权限id集合
							String roleAuthority = bgRole.getRoleauthority();//获取每个角色对象的角色权限（是字符串）
							String[] authorityIds = roleAuthority.split(",");//对获取的角色权限进行分割
							
							//获取每个角色对象所包含的大权限
							for(int i=0;i<roles.length;i++) {
								for(String s : authorityIds) {
									if(s.equals(roles[i])) {//如果角色权限id长度为2，通过和大权限数组中的元素进行相等判断
										if(!bigAuthorityId.contains(roles[i])) {
											bigAuthorityId.add(roles[i]);
										}
									} else if(s.length() == 4){//如果角色权限id长度为4，则截取前两位进行相等判断
										if(s.substring(0, 2).equals(roles[i])) {
											if(!bigAuthorityId.contains(roles[i])) {
												bigAuthorityId.add(roles[i]);
											}
										}
									}
								}
							}
							
							//遍历大权限id集合，将对应的子权限加入
							for(String big : bigAuthorityId) {
								List<BgAuthority> bgAuthorityList = Lists.newArrayList();
								BgAuthority entity = new BgAuthority();
								entity.setAuthorityid(big);
								BgAuthority bgAuthority = bgAuthorityService.selectOne(entity);
								bgAuthorityList.add(bgAuthority);
								for(String s : authorityIds) {
									if(s.length() == 4) {
										if(s.substring(0, 2).equals(big)) {
											entity.setAuthorityid(s);
											bgAuthority = bgAuthorityService.selectOne(entity);
											bgAuthorityList.add(bgAuthority);
										}
									}
								}
								if(bgAuthorityList.size() == 1) {
									bgAuthorityList.addAll(bgAuthorityService.selectByLikeName(big));
								}
								finalAuthorityList.add(bgAuthorityList);
							}
							
							//设置Role对象属性
							Role role = new Role();
							role.setRoleId(bgRole.getRoleid());
							role.setRoleName(bgRole.getRolename());
							role.setAuthorityIdList(finalAuthorityList);
							
							//添加到Role集合中
							roleList.add(role);
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
					PageInfo<BgRole> pageInfo = new PageInfo<>(bgRoleList);
					model.addAttribute("roleList", roleList);
					model.addAttribute("pageInfo", pageInfo);
				 	return "background/bg_manage/bg_role_list";
 	 
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		msg = "很抱歉，您无此权限！";
		model.addAttribute("authorityList",authorityList);
		model.addAttribute("bgRole",bgRoles);
		model.addAttribute("msg", msg);
		return "background/bg_index";
	}
		
	//进入添加角色(修改角色)界面
	@RequestMapping(value="order/toAddRole")
	public String toAddRole(HttpServletRequest request,Model model) {
		String msg = "";
		
		List<BgAuthority> authorityList=Lists.newArrayList();	
		/* 以下部分是获取管理员的权限  */

		BgManager bgManager = (BgManager) request.getSession().getAttribute(Bg_Constants.MANAGER);
		Integer roleIds = bgManager.getRollid();
		BgRole bgRoles = new BgRole();
		bgRoles.setRoleid(roleIds);
		try {
			bgRoles = bgRoleService.selectOne(bgRoles);
			String[] manageAuthority = bgRoles.getRoleauthority().split(",");
			for(int j=0;j<manageAuthority.length;j++) {
				BgAuthority bgAuthoritys=new BgAuthority();
				bgAuthoritys.setAuthorityid(manageAuthority[j]);
				bgAuthoritys=bgAuthorityService.selectOne(bgAuthoritys);
				authorityList.add(bgAuthoritys);
				
				String str = manageAuthority[j];
				if(str.equals("00")) {
					String roleId = request.getParameter("roleId");
					BgRole bgRole = null;
					if(roleId != null) {
						BgRole entity = new BgRole();
						entity.setRoleid(Integer.parseInt(roleId));
						try {
							bgRole = bgRoleService.selectOne(entity);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					List<RoleAuthority> roleAuthorityList = Lists.newArrayList();
					BgAuthority entity = new BgAuthority();
					BgAuthority bgAuthority = new BgAuthority();
					List<BgAuthority> bgAuthorityList = Lists.newArrayList();
					String[] roles = Constants.ROLE_AUTHORITY;
					for(int i=0;i<roles.length;i++) {
						entity.setAuthorityid(roles[i]);
						try {
							RoleAuthority roleAuthority = new RoleAuthority();
							bgAuthority = bgAuthorityService.selectOne(entity);
							roleAuthority.setBgAuthority(bgAuthority);
							bgAuthorityList = bgAuthorityService.selectByLikeName(roles[i]);
							roleAuthority.setBgAuthorityList(bgAuthorityList);
							roleAuthorityList.add(roleAuthority);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					model.addAttribute("bgRole", bgRole);
					model.addAttribute("roleAuthorityList", roleAuthorityList);
					return "background/bg_manage/bg_add_role";
 	 
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		msg = "很抱歉，您无此权限！";
		model.addAttribute("authorityList",authorityList);
		model.addAttribute("bgRole",bgRoles);
		model.addAttribute("msg", msg);
 
		return "background/bg_index";
	}
	
	//添加角色(修改角色)请求
	@RequestMapping(value="order/addRole")
	public String addRole(HttpServletRequest request,HttpServletResponse response) {
			//获取从页面传过来的角色id和角色名称
			String roleId = request.getParameter("original_roleId");
		 	String roleName=request.getParameter("roleName");
		 	
		 	//创建StringBuilder，以便将权限添加到一起
		 	StringBuilder builder= new StringBuilder(""); 
		 	
		 	//获取页面传过来的权限名称
			String authorityNameOne=request.getParameter("authorityNameOne");
			//对获取的权限名称进行分割，得到权限名称数组
			String[] authorityNames=authorityNameOne.split(",");
			//创建角色对象
			BgRole bgRole=new BgRole();
			//创建权限id数组
			String[] authorityIds = new String[authorityNames.length];
			//创建权限id长度为2的集合
			List<String> authorityTwo = Lists.newArrayList();
			try {
				//根据权限名称数组获取对应的权限id数组
				for(int i=0;i<authorityNames.length;i++) {
					String s = authorityNames[i].trim();
					BgAuthority entity = new BgAuthority();
					entity.setAuthorityname(s);
					BgAuthority authority = bgAuthorityService.selectOne(entity);
					String authorityId = authority.getAuthorityid();
					authorityIds[i] = authorityId;
				}
				
				//根据得到的权限id数组找出权限id长度为2的元素，并加入到长度为2的权限集合中
				for(int i=0;i<authorityIds.length;i++) {
					if(authorityIds[i].length() ==2) {
						authorityTwo.add(authorityIds[i]);
					}
				}
				
				//根据权限id数组设置角色的权限id（在有大权限时去掉所有对应的子权限）
				for(int i=0;i<authorityIds.length;i++) {
					String str = authorityIds[i];
					if(str.length() ==2) {
						builder.append(str).append(",");
					} else if(!authorityTwo.isEmpty()){
						if(!authorityTwo.contains(str.substring(0, 2))) {
							builder.append(str).append(",");
						}
					} else {
						builder.append(str).append(",");
					}
				}
				
				//去掉builder中最后一个”，“
				if(builder.toString().endsWith(",")) {
					builder.deleteCharAt(builder.length()-1);
				}
				
				//判断是添加角色还是修改角色，roleId为空时是添加，roleId不为空时是修改
				if(roleId == null) {
					BgRole entity = new BgRole();
					entity.setRolename(roleName);
					List<BgRole> roleList = bgRoleService.select(entity);
					//当数据库中没有该角色名称时，才可以添加到数据库中
					if(roleList.isEmpty()) {
						bgRole.setRoleauthority(builder.toString());
						bgRole.setRolename(roleName);
						bgRoleService.insert(bgRole);//添加到数据库
					}
				} else {
					bgRole.setRoleid(new Integer(roleId));
					bgRole.setRoleauthority(builder.toString());
					bgRole.setRolename(roleName);
					bgRoleService.updateByPrimaryKey(bgRole);//更新数据库中原有记录
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:roleList";//回到角色列表页面
 
	}
	
	//删除角色
	@RequestMapping(value="order/deleteRole")
	public String deleteRole(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		try {
			if(roleId != null) {
				BgRole entity = new BgRole();
				entity.setRoleid(Integer.parseInt(roleId));
				bgRoleService.delete(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:roleList";
	}
}
