package com.ywb.common.listener;

import com.ywb.common.util.BaseMap;

/**
 * 自定义消息监听
 */
public interface RedisListerer {

    void onMessage(BaseMap message);

}
