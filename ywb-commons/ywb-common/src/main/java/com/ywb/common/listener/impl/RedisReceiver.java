package com.ywb.common.listener.impl;


import cn.hutool.core.util.ObjectUtil;
import com.ywb.common.constant.ApiConstant;
import com.ywb.common.listener.RedisListerer;
import com.ywb.common.util.BaseMap;
import com.ywb.common.util.SpringUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RedisReceiver implements RedisListerer {


    /**
     * 接受消息并调用业务逻辑处理器
     *
     * @param params
     */
    public void onMessage(BaseMap params) {
        Object handlerName = params.get(ApiConstant.HANDLER_NAME);
        RedisListerer messageListener = SpringUtils.getBean(handlerName.toString(), RedisListerer.class);
        if (ObjectUtil.isNotEmpty(messageListener)) {
            messageListener.onMessage(params);
        }
    }

}
