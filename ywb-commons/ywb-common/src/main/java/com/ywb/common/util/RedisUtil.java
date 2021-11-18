package com.ywb.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 判断缓存中是否含有key
     * @param key key
     * @return 是否包含
     */
    public boolean hasKey(String key){
        try{
            return this.redisTemplate.hasKey(key);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 批量删除缓存
     */
    public void del(String...key){
        if (key != null && key.length > 0){
            if (key.length == 1){
                if (null == key[0]) {
                    return;
                }
                this.redisTemplate.delete(key[0]);
            }else {
                this.redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 根据key获取缓存
     * @param key key
     * @return opsForValue类型的值
     */
    public <T> T get(String key){
        if (null == key) {
            return null;
        }
        Object obj = this.redisTemplate.opsForValue().get(key);
        if (null == obj) {
            return null;
        }
        return (T) obj;
    }

    /**
     * 普通存入缓存数据
     */
    public boolean set(String key, Object value){
        try{
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }


    /**
     * 普通存入缓存，并设置时间
     * @param key key
     * @param value 对象
     * @param time 过期时间
     * @return 是否成功
     */
    public boolean set(String key, Object value, long time){
        try{
            if (time > 0L) {
                this.redisTemplate.opsForValue().set(key,value, time, TimeUnit.SECONDS);
            } else {
                this.set(key,value);
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 递增或递减， 可用于生成唯一的规范的uuid
     * @param key key
     * @param step 步长
     * @return 下一个值
     */
    public long incr(String key, long step){
        return this.redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * 用户存放map
     */
    public Object hget(String key, String item){
        try {
            return this.redisTemplate.opsForHash().get(key, item);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取hashkey对应的所有键值,map操作
     */
    public Map<Object, Object> hmget(String key){
        try {
            return this.redisTemplate.opsForHash().entries(key);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * hashset
     */
    public boolean hmset(String key, Map<String, Object> map){
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * hashset 并设置时间
     */
    public boolean hmset(String key, Map<String, Object> map, long time){
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (time > 0L){
                this.expire(key, time);
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 向一张表中放入数据，如果不存在就创建
     */
    public boolean hset(String key, String item, Object value){
        try{
            this.redisTemplate.opsForHash().put(key, item, value);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */
    public boolean hset(String key, String item, Object value, long time){
        try{
            this.redisTemplate.opsForHash().put(key, item, value);
            if (time > 0L)
                this.expire(key, time);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     */
    public void hdel(String key, Object...item){
        this.redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * 判断hash表里是否有改key值
     */
    public boolean hHasKey(String key, String item){
        return this.redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增，如果不存在就会创建一个，并把新增的值返回
     */
    public double hincr(String key, String item, double by){
        return this.redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减，如果不存在就会创建一个，并把减少的值返回
     */
    public double hdecr(String key, String item, double by){
        return this.redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 根据key获取set中的所有值
     */
    public Set<Object> sGet(String key){
        try {
            return this.redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询是否存在
     */
    public boolean sHasKey(String key, Object value){
        try{
            return this.redisTemplate.opsForSet().isMember(key, value);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将set数据放入缓存
     */
    public long sSet(String key, Object... values){
        try {
            return this.redisTemplate.opsForSet().add(key,values);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return -1L;
        }
    }

    /**
     * 将set数据放入缓存
     */
    public void sSetAndTime(String key, long time, Object... values){
        try{
            this.redisTemplate.opsForSet().add(key, values);
            if (time > 0L){
                this.expire(key, time);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取set缓存数据的长度
     */
    public long sGetSetSize(String key){
        try {
            return this.redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return -1L;
        }
    }

    /**
     * 获取list缓存的内容
     */
    public List<Object> lGet(String key, long start, long end){
        try{
            return this.redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     */
    public long lGetListSize(String key){
        try {
            return this.redisTemplate.opsForList().size(key);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return -1L;
        }
    }

    /**
     * 通过索引 获取list缓存中的值
     */
    public Object lGetIndex(String key, long index){
        try {
            return this.redisTemplate.opsForList().index(key, index);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return -1L;
        }
    }

    /**
     * 将list放入缓存
     */
    public boolean lSet(String key, Object value){
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     */
    public boolean lSet(String key, Object value, long time){
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            if (time > 0L){
                this.expire(key, time);
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     */
    public boolean lSet(String key,List<Object> value){
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     */
    public boolean lSet(String key, List<Object> value, long time){
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0L){
                this.expire(key, time);
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据索引 修改list缓存中的某个数据
     */
    public boolean lUpdateIndex(String key, long index, Object value){
        try {
            this.redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 移除n个值为value
     */
    public long lRemove(String key, long count, Object value){
        try {
            Long remove = this.redisTemplate.opsForList().remove(key, count, value);
            return remove;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return -1L;
        }
    }

    /**
     * 指定缓存失效时间
     */
    public boolean expire(String key, long time){
        try{
            if (time > 0L){
                this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     */
    public long getExpire(String key){
        return this.redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }
}
