package com.ouweihao.service;

import com.ouweihao.po.Tag;
import com.ouweihao.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    // 保存
    Tag saveTag(Tag tag);

    // 根据id查询类型
    Tag getTag(Long id);

    // 通过名称查询类型
    Tag getTagByName(String name);

    // 分页查询
    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    // 修改
    Tag updateTag(Long id, Tag tag);

    // 删除
    void deleteTag(Long id);

}
