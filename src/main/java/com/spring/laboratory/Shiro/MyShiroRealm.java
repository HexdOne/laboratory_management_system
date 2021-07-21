package com.spring.laboratory.Shiro;

import com.spring.laboratory.Entity.Permission;
import com.spring.laboratory.Entity.Role;
import com.spring.laboratory.Entity.UserInfo;
import com.spring.laboratory.Service.LoginService;
import com.spring.laboratory.Service.PermissionService;
import com.spring.laboratory.Service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Override
    public String getName() {
        return "MyShiroRealm";
    }
    @Override
    //授权操作
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo)principalCollection.getPrimaryPrincipal();//获取输入的用户名
        String username = (String)userInfo.getUsername();
        List<Permission> permissions = new ArrayList<Permission>();
        List<Role> roles = new ArrayList<Role>();
     for (Role role:roleService.queryroleByusername(username)) {
            System.out.println("进入到添加Role");
            roles.add(role);
        for (Permission permission: permissionService.getUserPermission(username)){
            System.out.println("permission："+permission.toString());
            permissions.add(permission);
            System.out.println("循环完了：");

           }
        }
        System.out.println("用户："+userInfo);
        if (userInfo!=null) {
            for (Role role : roles) {
                authorizationInfo.addRole(role.getRole());
                System.out.println("具有的角色有："+role.getRole());
                for (Permission permission : permissions) {
                    System.out.println("具有的权限有：" + permission);
                    authorizationInfo.addStringPermission(permission.getPermission());
                }
            }
       }

        return authorizationInfo;
    }

    @Override
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       // 将token装换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        System.out.println("myrealm的token"+authenticationToken);
        // 获取用户名即可
        String username = upToken.getUsername();
        // 查询数据库，是否查询到用户名和密码的用户
        UserInfo userInfo = loginService.queryInfoByUsername(username);
        System.out.println(userInfo);
        SimpleAuthenticationInfo authenticationInfo;
        if(userInfo != null) {
            // 如果查询到了，封装查询结果，返回给我们的调用
//            Object principal =  userInfo.get("username");
//            Object credentials = userInfo.get("password");
//            // 获取盐值，即用户名
//            System.out.println("盐"+userInfo.get(1));
//            ByteSource salt = ByteSource.Util.bytes(userInfo.get(2));
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            authenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getCredentialsSalt()),realmName);
        }else {
            // 如果没有查询到，抛出一个异常
            throw new AuthenticationException();
        }
        return authenticationInfo;
    }


}
