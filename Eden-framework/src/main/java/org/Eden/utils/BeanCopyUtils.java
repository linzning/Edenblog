package org.Eden.utils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import javafx.beans.binding.ObjectExpression;
import org.Eden.domain.entity.Article;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    //私有的空参构造方法
    private BeanCopyUtils() {
    }

    //单个单个实体类的拷贝，第一个参数是要拷贝的对象，第二个参数是类的字节码对象
    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性拷贝。也就是把source的属性拷贝到这个目标对象。BeanUtils是spring提供的工具类
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }

    //集合的拷贝
    public static <I,T> List<T> copyBeanList(List<I> source,Class<T> clazz){
        return source.stream()
                .map(o->copyBean(o,clazz))
                .collect(Collectors.toList());
    }
}
