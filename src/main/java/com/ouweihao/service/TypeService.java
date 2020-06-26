package com.ouweihao.service;

import com.ouweihao.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    // 保存
    Type saveType(Type type);

    // 根据id查询类型
    Type getType(Long id);

    // 分页查询
    Page<Type> listType(Pageable pageable);

    // 修改
    Type updateType(Long id, Type type);

    // 删除
    void deleteType(Long id);

}