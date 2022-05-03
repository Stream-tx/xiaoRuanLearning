package com.example.backend.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/*
获取user实体类中键值为空的属性的工具类，以便在copyProperties时不会空值覆盖有意义的值
 */
public class MyBeanUtils {

    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for(PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String name = propertyDescriptor.getName();
            if(beanWrapper.getPropertyValue(name) == null)
                nullPropertyNames.add(name);
        }
        return nullPropertyNames.toArray(new String[0]);
    }
}
