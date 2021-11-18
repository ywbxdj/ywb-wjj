package com.ywb.common.util;

import com.ywb.common.exception.BusinessException;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

public class BeanUtils extends org.springframework.beans.BeanUtils{

	/**
	 * 不拷贝null值
	 * @param input
	 * @param entity
	 */
   public static void copyNotNullProperties(Object source, Object target) {
       BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
   }

   /**
    * 忽略null字段
    * @param object
    * @return
    */
   private static String[] getNullPropertyNames(Object object) {
       final BeanWrapperImpl wrapper = new BeanWrapperImpl(object);
       return Stream.of(wrapper.getPropertyDescriptors()).map(PropertyDescriptor::getName)
           .filter(propertyName -> wrapper.getPropertyValue(propertyName) == null)
           .toArray(String[]::new);
   }
    /**
     * 将列表数据复制到指定类型的列表数据中
     *
     * @param source           数据源
     * @param targetClazz      目标类型
     * @param ignoreProperties 排除掉不复制的数据
     * @param <T>              目标类型
     * @return entity
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T copyObject(Object source, Class<T> targetClazz, String... ignoreProperties) {
        T target;
        try {
            target = targetClazz.newInstance();
        } catch (Throwable ex) {
            throw new BusinessException(targetClazz.getName() + " instance error: " + ex.getMessage());
        }
        copyProperties(source, target, ignoreProperties);

        return target;
    }
}
