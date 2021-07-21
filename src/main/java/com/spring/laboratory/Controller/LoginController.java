package com.spring.laboratory.Controller;


import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Entity.User;
import com.spring.laboratory.Entity.UserInfo;
import com.spring.laboratory.Exception.ErrorEnum;
import com.spring.laboratory.Mapper.LoginMapper;
import com.spring.laboratory.Service.LoginService;
import com.spring.laboratory.Utils.ResultUtils;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: hxy
 * @description: 登录相关Controller
 * @date: 2017/10/24 10:33
 */
@RestController
@ApiIgnore
@RequestMapping("/text")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginMapper loginMapper;
    private Map<String, Object> saveParams = new HashMap<>();
    @Autowired
    private RedisTemplate redisTemplate;

    //登录
//    @PostMapping("/auth")
//    public String doLogin(HttpServletRequest request) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        // 将用户名及密码封装到UsernamePasswordToken
//
//
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        password = String.valueOf(token.getPassword());
//        System.out.println("password:"+password);
//        token.setRememberMe(true);
//        // 创建Subject实例
//        Subject currentUser = SecurityUtils.getSubject();
//        try {
//            currentUser.login(token);
//            System.out.println("token:"+token);
//
//            // 判断当前用户是否登录
//            if (currentUser.isAuthenticated() == true) {
//                System.out.println("是否记住："+currentUser.isRemembered());
//                return "/login";
//            }
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            System.out.println("登录失败");
//           return "密码错误";
//        }
//        //return "失败了";
//        return "/login";
//    }
    @RequestMapping("/login")
    public Result login(UserInfo user) {
        Map<String, String> map = new HashMap();
        ;
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("username", user.getUsername());
        System.out.println(session.getAttributeKeys());
        System.out.println(session.getAttribute("username"));
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {

            subject.login(usernamePasswordToken);
            System.out.println("userToken" + usernamePasswordToken);
            String sessionId = (String) subject.getSession().getId();
            System.out.println("session:" + sessionId);
            System.out.println("会话的ip" + subject.getSession().getHost().toString());
            map.put("sessionId", sessionId);
            System.out.println(map.get(sessionId));
            System.out.println("session:" + map);
            System.out.println("成功");
//         response.setCharacterEncoding("UTF-8");
//         PrintWriter out = response.getWriter();
//         response.setHeader("content-type", "text/html;charset=UTF-8");
//         out.write(sessionId);
            return ResultUtils.success(map);

        } catch (Exception e) {
            System.out.println("失败");
            return ResultUtils.error();
        }

    }
    //注销登录
    @RequestMapping("/logout")
    public Result loginout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("进入了注销界面");
        return ResultUtils.success();
    }
    //忘记密码，密码找回
    @PutMapping("/retrievepassword")
    public Result retrievePassword(@Param("studentId") String studentId, @Param("email") String email, @Param("newpassword") String newpassword,HttpServletRequest request){
         UserInfo userInfo;
        System.out.println("马上就进入到if语句");
        Map<String,String> map = new HashMap<>();
        map.put("studentId",studentId);
        map.put("email",email);
        System.out.println("查询到的数据条数有多少:"+loginService.queryStudentAndEmail(map));
        System.out.println(studentId);
        System.out.println(email);
        System.out.println(map.get("studentId"));
        System.out.println(map.get("email"));

        if (loginService.queryStudentAndEmail(map)>0){
            System.out.println("在获取验证码之前");
            String t = request.getParameter("email");
            String n = request.getParameter("number");
            System.out.println("number："+n);
            String a;
            a = redisTemplate.opsForValue().get("" + t).toString();
            System.out.println("redis:验证码" + redisTemplate.opsForValue().get("" + t));
            System.out.println(n.equals(redisTemplate.opsForValue().get("" + t)));
            if(n.equals(a)) {
                System.out.println("进入到if语句");
                userInfo = loginService.queryUser(map);
                System.out.println("userinfo:::" + userInfo);
                Md5Hash md5Hash = new Md5Hash(newpassword, userInfo.getUsername() + userInfo.getSalt(), 2);
                userInfo.setPassword(md5Hash.toString());
                System.out.println("修改后的userinfo:::" + userInfo);
                System.out.println("快要修改密码了");
                loginService.retrievePassword(userInfo);
                System.out.println("到这里就要成功了");
                return ResultUtils.success();
            }
            else {
                return ResultUtils.error(405,"验证码错误");
            }
        }else {
       return ResultUtils.error(404,"数据库中没有此数据");
        }
    }

//注册用户
    @PostMapping("/register")
    public Result register(HttpServletRequest request, UserInfo userInfo, Integer id) {
        if (loginService.queryUsername(userInfo.getUsername()) == 0) {
            if (loginService.queryEmail(userInfo.getEmail()) == 0) {
                if (loginService.queryStudentid(userInfo.getStudentId()) == 0) {
                    UUID s = UUID.randomUUID();
                    String salt = s.toString();
                    userInfo.setSalt(salt);
                    String password = userInfo.getPassword();
                    Md5Hash md5Hash = new Md5Hash(password, userInfo.getUsername() + salt, 2);
                    userInfo.setPassword(md5Hash.toString());

                    String t = request.getParameter("email");
                    String n = request.getParameter("number");
                    String a;
                    a = redisTemplate.opsForValue().get("" + t).toString();
                    System.out.println("redis:验证码" + redisTemplate.opsForValue().get("" + t));
                    System.out.println(n.equals(redisTemplate.opsForValue().get("" + t)));
                    if (n.equals(a)) {
                        loginMapper.register(userInfo);
                        saveParams.put("sid", userInfo.getStudentId());
                        System.out.println(userInfo.getStudentId());
                        System.out.println(Arrays.asList(id));
                        saveParams.put("roleIds", Arrays.asList(id));
                        loginMapper.saveRole(saveParams);
                        return ResultUtils.success();
                    } else {
                        System.out.println("输入的验证码：" + n + "    " + "邮箱验证码" + a);
                        return ResultUtils.error(403, "验证码错误");

                    }

                } else {
                    return ResultUtils.error(403, "此学号已被注册");
                }
            } else {
                return ResultUtils.error(403, "此邮箱已被注册");
            }
        } else {
            return ResultUtils.error(403, "此用户名已存在");
        }

        //return null;
    }


    //         userInfo.setEmail("yhq1913@sina.com");
//
//         userInfo.setName("杜金川");
//         userInfo.setUsername("ds111fs");
//         userInfo.setPassword("sss");
//         //密码加密
//         userInfo.setSalt("123");
//         userInfo.setTel("15910721339");
//         userInfo.setStudentId(2013108086);
//         loginMapper.register(userInfo);
//         System.out.println("有没有运行在这里");
//        // saveParams.put("userId", userInfo.getuId());
//         //这里指定两个角色的ID进行测试
//         System.out.println("已经快要map");
//         System.out.println(userInfo.getStudentId());
//         saveParams.put("sid",userInfo.getStudentId());
//         saveParams.put("roleIds",Arrays.asList("1","2"));
//         System.out.println(Arrays.asList("1","2"));
//         System.out.println(saveParams.get("roleIds"));
//         System.out.println("已经map了");
//         loginMapper.saveRole(saveParams);
    //查询个人信息
    @RequestMapping("/personalinformation")
    public User getUserInfo(String sessionID, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询到个人信息");

        boolean status = false;
        SessionKey key = new WebSessionKey(sessionID, request, response);
        System.out.println("sessionid:" + sessionID);
//        System.out.println("request:"+request.getRequestURI());
//        System.out.println("response:"+response.getHeaderNames());
        try {
            Session se = SecurityUtils.getSecurityManager().getSession(key);
            System.out.println("se:" + se);
            Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            System.out.println("obj:" + obj);
            //org.apache.shiro.subject.SimplePrincipalCollection cannot be cast to com.hncxhd.bywl.entity.manual.UserInfo
            SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
            UserInfo u = (UserInfo) coll.getPrimaryPrincipal();
            User uu = new User();
            uu.setEmail(u.getEmail());
            uu.setName(u.getName());
            uu.setStudent_id(u.getStudentId());
            uu.setTel(u.getTel());
            uu.setUsername(u.getUsername());
            return uu;
        } catch (Exception e) {
            System.out.println("报错");
            e.printStackTrace();
        } finally {
        }
        return null;
    }
//修改信息
    @PostMapping("/modifyinformation")
    public Result modifyinfomation( String name,String tel,String sessionID, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到修改个人信息");
        boolean status = false;
        SessionKey key = new WebSessionKey(sessionID, request, response);
        System.out.println("sessionid:" + sessionID);
//        System.out.println("request:"+request.getRequestURI());
//        System.out.println("response:"+response.getHeaderNames());
        try {
            Session se = SecurityUtils.getSecurityManager().getSession(key);
            System.out.println("se:" + se);
            Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            System.out.println("obj:" + obj);
            //org.apache.shiro.subject.SimplePrincipalCollection cannot be cast to com.hncxhd.bywl.entity.manual.UserInfo
            SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
            UserInfo u = (UserInfo) coll.getPrimaryPrincipal();
            u.setName(name);
            u.setTel(tel);
            System.out.println(u.toString());
            loginService.modifyInformation(u);
            System.out.println("已经快成功了");
            return  ResultUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultUtils.error(ErrorEnum.ERROR_500);
        }
    }
  //修改密码
    @PostMapping("/changepassword")
    public Result changePassword(String oldpassword,String newpassword,String sessionID, HttpServletRequest request, HttpServletResponse response){
        System.out.println("进入到修改密码");
        boolean status = false;
        SessionKey key = new WebSessionKey(sessionID, request, response);
        System.out.println("sessionid:" + sessionID);
//        System.out.println("request:"+request.getRequestURI());
//        System.out.println("response:"+response.getHeaderNames());
        try {
            Session se = SecurityUtils.getSecurityManager().getSession(key);
            System.out.println("se:" + se);
            Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            System.out.println("obj:" + obj);
            //org.apache.shiro.subject.SimplePrincipalCollection cannot be cast to com.hncxhd.bywl.entity.manual.UserInfo
            SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
            UserInfo u = (UserInfo) coll.getPrimaryPrincipal();
            Md5Hash oldmd5Hash = new Md5Hash(oldpassword,u.getUsername()+u.getSalt(),2);
            System.out.println("是不是正确的："+oldmd5Hash.toString().equals(u.getPassword()));
            if (oldmd5Hash.toString().equals(u.getPassword())==false){
                return ResultUtils.error(422,"原密码输入错误");
            }else {
                System.out.println("下一步是数据加密");
                System.out.println("获得新密码：" + newpassword);
                Md5Hash md5Hash = new Md5Hash(newpassword, u.getUsername() + u.getSalt(), 2);
                u.setPassword(md5Hash.toString());
                System.out.println(md5Hash.toString());
                loginService.changePassword(u);
                System.out.println("已经快成功了");
                System.out.println(u);
                return ResultUtils.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultUtils.error(ErrorEnum.ERROR_500);
        }
    }


    }
