package com.ouweihao.service;

import com.ouweihao.po.Blog;
import com.ouweihao.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {

    // 根据id查询博客
    Blog getBlog(Long id);

    // 分页查询
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    // 新增
    Blog saveBlog(Blog blog);

    // 修改
    Blog updateBlog(Long id, Blog blog);

    // 删除
    void deleteBlog(Long id);

}
