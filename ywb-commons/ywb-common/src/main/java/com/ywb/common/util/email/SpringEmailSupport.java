package com.ywb.common.util.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Component
public class SpringEmailSupport implements EmailSupport {

	@Value("${spring.mail.username:}")
	private String from;
	
	@Autowired(required=false)
    private JavaMailSender mailSender;
	
	/**
	 * 普通附件
	 * @param toEmail
	 * @return
	 */
	@Async
	@Override
	public Boolean commonEmail(ToEmail toEmail) {
		try {
		    //创建简单邮件消息
		    SimpleMailMessage message = new SimpleMailMessage();
		    //谁发的
		    message.setFrom(from);
		    //谁要接收
		    message.setTo(toEmail.getTos());
		    //邮件标题
		    message.setSubject(toEmail.getSubject());
		    //邮件内容
		    message.setText(toEmail.getContent());
	    
	    	mailSender.send(message);
	    	return Boolean.TRUE;
	    } catch (MailException e) {
	    	log.error("邮件发送失败,错误原因:{}",e.getMessage());
	    }
	    return Boolean.FALSE;
	}
	
	/**
	 * html内容附件
	 * @param toEmail
	 * @return
	 * @throws MessagingException
	 */
	@Async
	@Override
	public Boolean htmlEmail(ToEmail toEmail) {
		try {
		    //创建一个MINE消息
		    MimeMessage message = mailSender.createMimeMessage();
		    MimeMessageHelper minehelper = new MimeMessageHelper(message, true,"utf-8");
		    //谁发
		    minehelper.setFrom(from);
		    //谁要接收
		    minehelper.setTo(toEmail.getTos());
		    //邮件主题
		    minehelper.setSubject(toEmail.getSubject());
		    //邮件内容   true 表示带有附件或html
		    minehelper.setText(toEmail.getContent(), true);
	    	mailSender.send(message);
    	
	    	return Boolean.TRUE;
	    } catch (Exception e) {
	    	log.error("邮件发送失败,错误原因:{}",e.getMessage());
	    }
	    return Boolean.FALSE;
	  }
	
	/**
	 * html带静态文件
	 * @param toEmail
	 * @param toFile
	 * @param resId
	 * @return
	 */
	@Async
	@Override
	public Boolean staticEmail(ToEmail toEmail, File toFile, String resId) {
	    try {
	    	  //创建一个MINE消息
	    	  MimeMessage message = mailSender.createMimeMessage();
		      MimeMessageHelper helper = new MimeMessageHelper(message, true);
		      //谁发
		      helper.setFrom(from);
		      //谁接收
		      helper.setTo(toEmail.getTos());
		      //邮件主题
		      helper.setSubject(toEmail.getSubject());
		      //邮件内容   true 表示带有附件或html
		      //邮件内容拼接
		      String content =
		          "<html><body><img width='250px' src=\'cid:" + resId + "\'>" + toEmail.getContent()
		              + "</body></html>";
		      helper.setText(content, true);
		      //蒋 multpartfile 转为file
		      FileSystemResource res = new FileSystemResource(toFile);
	
		      //添加内联资源，一个id对应一个资源，最终通过id来找到该资源
		      helper.addInline(resId, res);
		      mailSender.send(message);
		      return Boolean.TRUE;
	    } catch (Exception e) {
	    	log.error("邮件发送失败,错误原因:{}",e.getMessage());
	    }
	    return Boolean.FALSE;
	  }
	
	/**
	 * 带附件的邮件
	 * @param toEmail
	 * @param multipartFile
	 * @return
	 */
	@Async
	@Override
	public Boolean enclosureEmail(ToEmail toEmail, File ToFile) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //谁发
            helper.setFrom(from);
            //谁接收
            helper.setTo(toEmail.getTos());
            //邮件主题
            helper.setSubject(toEmail.getSubject());
            //邮件内容   true 表示带有附件或html
            helper.setText(toEmail.getContent(), true);
            FileSystemResource file = new FileSystemResource(ToFile);
            String filename = file.getFilename();
            //添加附件
            helper.addAttachment(filename, file);
            mailSender.send(message);
            return Boolean.TRUE;
        } catch (Exception e) {
        	log.error("邮件发送失败,错误原因:{}",e.getMessage());
        }
        return Boolean.FALSE;
    }
}
