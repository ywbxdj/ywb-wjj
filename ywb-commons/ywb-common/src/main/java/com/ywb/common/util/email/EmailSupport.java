package com.ywb.common.util.email;

import javax.mail.MessagingException;
import java.io.File;

public interface EmailSupport {

	/**
	 * 普通附件
	 * @param toEmail
	 * @return
	 */
	Boolean commonEmail(ToEmail toEmail);
	
	/**
	 * html内容附件
	 * @param toEmail
	 * @return
	 * @throws MessagingException
	 */
	Boolean htmlEmail(ToEmail toEmail);
	
	/**
	 * html带静态文件
	 * @param toEmail
	 * @param toFile
	 * @param resId
	 * @return
	 */
	Boolean staticEmail(ToEmail toEmail, File toFile, String resId);
	
	/**
	 * 带附件的邮件
	 * @param toEmail
	 * @param multipartFile
	 * @return
	 */
	Boolean enclosureEmail(ToEmail toEmail, File ToFile);
}
