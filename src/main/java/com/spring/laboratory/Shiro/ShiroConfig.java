package com.spring.laboratory.Shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        System.out.println("ShiroConfiguration.shirFilter()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //拦截器
        filterMap.put("authc", new ShiroLoginFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //过滤链
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置不会被拦截的链接 顺序判断
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        // 配置退出过滤器，其中的具体代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/static/**","anon");
//        filterChainDefinitionMap.put("/text/login","anon");
//        filterChainDefinitionMap.put("/text/register","anon");
//        filterChainDefinitionMap.put("/text/repairequipment/*","anon");
//        filterChainDefinitionMap.put("/text/repairlab/*","anon");
//        filterChainDefinitionMap.put("/text/application/*","anon");
//        filterChainDefinitionMap.put("/text/queryemail","anon");
//        filterChainDefinitionMap.put("/text/retrievepassword","anon");
//        filterChainDefinitionMap.put("/403","anon");
//        filterChainDefinitionMap.put("/text/logout","authc");
//        filterChainDefinitionMap.put("/text/equipment","authc");
//        filterChainDefinitionMap.put("/**","authc");
        filterChainDefinitionMap.put("/**","anon");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //shiroFilterFactoryBean.setLoginUrl("/text/login");

        shiroFilterFactoryBean.setSuccessUrl("/text/equipment/query");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     *  凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
       System.out.println("md5这里");
        return hashedCredentialsMatcher;
    }
    @Bean
//    @Bean(name = "myShiroRealm")
//    @DependsOn(value = {"lifecycleBeanPostProcessor", "ShiroRedisCacheManager"})
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //设置缓存管理器

        //设置认证密码算法以及迭代复杂度
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        //认证
        myShiroRealm.setAuthenticationCachingEnabled(false);
        myShiroRealm.setAuthorizationCachingEnabled(false);
        return myShiroRealm;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //自定义realm
        securityManager.setRealm(myShiroRealm());
        //自定义session管理
        securityManager.setSessionManager(sessionManager());
        //自定义缓存实现。管理redis

        //注入记住我管理器

        return securityManager;
    }

    /**
     * 会话管理器
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        //全局会话超时时间
        sessionManager.setGlobalSessionTimeout(900000);//ms
        //删除失效的session
        sessionManager.setDeleteInvalidSessions(true);
        //是否开启会话验证器，默认是开启的
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler());
        //开启Cookie
//        sessionManager.setSessionIdCookieEnabled(true);
//        sessionManager.setSessionIdCookie(sessionIdCookie());
        return sessionManager;
    }

    @Bean
    public EnterpriseCacheSessionDAO sessionDAO(){
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        //会话Id生成器 默认为JavaUuidSessionIdGenerator  通过java.util.UUID生成
        sessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        return sessionDAO;
    }

//    @Bean
//    public QuartzSessionValidationScheduler quartzSessionValidationScheduler(){
//        QuartzSessionValidationScheduler quartzsessionValidationScheduler = new QuartzSessionValidationScheduler();
//        quartzsessionValidationScheduler.setSessionValidationInterval(1800000);
//        return quartzsessionValidationScheduler;
//    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createMappingExceptionResolver(){
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException","databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","403");
        r.setExceptionMappings(mappings);
        r.setDefaultErrorView("error");
        r.setExceptionAttribute("ex");
        return r;
    }
    /**
     * 可以直接在pom文件添加依赖，这样的话比较简单
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    /** * Shiro生命周期处理器 * @return */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /*设置缓存管理器：SessionManager*/

}
