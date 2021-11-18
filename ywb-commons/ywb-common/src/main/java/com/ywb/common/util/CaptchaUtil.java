package com.ywb.common.util;

import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import com.ywb.common.domain.LoginCode;
import com.ywb.common.em.LoginCodeEnum;
import com.ywb.common.exception.BusinessException;

import java.awt.*;

public class CaptchaUtil {
    /**
     * 依据配置信息生产验证码
     *
     * @param loginCode 验证码配置信息
     * @return /
     */
    public Captcha switchCaptcha(LoginCode loginCode) {
        Captcha captcha;
        synchronized (this) {
            switch (loginCode.getCodeType()) {
                case arithmetic:
                    // 算术类型 https://gitee.com/whvse/EasyCaptcha
                    captcha = new ArithmeticCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    ArithmeticCaptcha arithmeticCaptcha = (ArithmeticCaptcha) captcha;
                    captcha.setLen(loginCode.getLength());
                    String resultStr = arithmeticCaptcha.getArithmeticString();
                    boolean flag = true;
                    while (flag) {
                        if (resultStr.contains("+")) {
                            // 几位数运算，默认是两位
                            flag = false;
                        } else {
                            captcha = new ArithmeticCaptcha(loginCode.getWidth(), loginCode.getHeight());
                            ArithmeticCaptcha arithmeticCaptchaAny = (ArithmeticCaptcha) captcha;
                            captcha.setLen(loginCode.getLength());
                            resultStr = arithmeticCaptchaAny.getArithmeticString();
                        }
                    }

                    break;
                case chinese:
                    captcha = new ChineseCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case chinese_gif:
                    captcha = new ChineseGifCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case gif:
                    captcha = new GifCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                case spec:
                    captcha = new SpecCaptcha(loginCode.getWidth(), loginCode.getHeight());
                    captcha.setLen(loginCode.getLength());
                    break;
                default:
                    throw new BusinessException("验证码配置信息错误！正确配置查看 LoginCodeEnum");
            }
        }
        if (StringUtils.isNotBlank(loginCode.getFontName())) {
            captcha.setFont(new Font(loginCode.getFontName(), Font.PLAIN, loginCode.getFontSize()));
        }
        return captcha;
    }

    public String result(Captcha captcha) {
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        return captchaValue;
    }
}
