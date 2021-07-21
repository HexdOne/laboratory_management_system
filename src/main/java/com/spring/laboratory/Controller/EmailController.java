package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.Email;
import com.spring.laboratory.Service.EmailService;
import com.spring.laboratory.Service.UserManagementService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@Api(value = "邮件", description = "邮件发送验证码")
@RequestMapping("/text")

public class EmailController {

    @Autowired
    private EmailService emailService;
    private Email email;
    @Autowired
    private RedisTemplate redisTemplate;

//    @PostMapping("/add")
//    public Result savaEmail(Email email) {
//        emailService.saveEmail(email);
//        return Result.success();
//    }

    @Autowired
    private JavaMailSender mailSender;


//    @Scheduled(cron = "0/10 * * * * *")
//    public void timer() {
//        //获取当前时间
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println("当前时间为:" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//    }
    @GetMapping("/queryemail")
    public void queryEmail(HttpServletRequest request,Email email) throws javax.mail.MessagingException {
        emailService.saveEmail(email);
        emailService.queryEmail(email);        //获取要验证码的邮箱地址
        //随机数产生
        int max = 200000;
        int min = 100000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        System.out.println("随机数为" + s);
        /*邮箱发送原理：
        * 1、读取发送者邮箱
        * 2、获取请求验证码的邮箱地址
        * 3、将产生的随机数验证码，通过发送到请求者邮箱*/
        //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true );
        //基本设置.
        helper.setFrom("1045224376@qq.com");//发送者
        helper.setTo("" + emailService.queryEmail(email));  //接收者
        helper.setSubject("实验室管理系统");//邮件主题
        helper.setText("【QQ邮箱】" + s + "（登录验证码，请完成验证），如非本人操作，请忽略本消息");//邮件内容
        /*放送邮箱附件的代码*/
        //org.springframework.core.io.FileSystemResource下的:
//        //附件1,获取文件对象
//        FileSystemResource file1 = new FileSystemResource(new File("C:/Users/Administrator/Desktop/201291893228996.jpg"));
//        //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了
//        helper.addAttachment("头像1.jpg", file1);
//        //附件2
//        FileSystemResource file2 = new FileSystemResource(new File("C:/Users/Administrator/Desktop/ceshi.txt"));
//        helper.addAttachment("文档1.txt", file2);
        mailSender.send(mimeMessage);
       /*redis缓存数据
         * */
        String n;
        n=emailService.queryEmail(email);
        redisTemplate.opsForValue().set(""+n, +s,5, TimeUnit.MINUTES);  //设置redis有效缓存时间
        redisTemplate.opsForValue().get(""+s);
        /*
        * 预防数据库中数据量太大，没添加一次信息，清空数据库中的数据*/
        emailService.deleteall(email);
        //返回验证码到前端
//        return ""+redisTemplate.opsForValue().get(""+n);


    }
//    @PostMapping("/adduser")
//    public Result saveuser(UserManagement userManagement) {
//        System.out.println("验证码："+emailService.querynumber(email));
//            userManagementService.saveuser(userManagement);
//            return ResultUtils.success();
//    }



}
