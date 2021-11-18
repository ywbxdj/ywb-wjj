package com.ywb.common.util.email;

import lombok.Data;

import java.io.Serializable;

@Data
public class ToEmail implements Serializable {

	private static final long serialVersionUID = 8793991865635657868L;
/**
   * 邮件接收方，可多人
   */
  private String[] tos;
  /**
   * 邮件主题
   */
  private String subject;
  /**
   * 邮件内容
   */
  private String content;
}