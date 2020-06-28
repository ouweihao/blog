package com.ouweihao.service;

import com.ouweihao.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    // 保存
    Type saveType(Type type);

    // 根据id查询类型
    Type getType(Long id);

    // 通过名称查询类型
    Type getTypeByName(String name);

    // 分页查询
    Page<Type> listType(Pageable pageable);

    // 返回所有的类型
    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    // 修改
    Type updateType(Long id, Type type);

    // 删除
    void deleteType(Long id);

}
