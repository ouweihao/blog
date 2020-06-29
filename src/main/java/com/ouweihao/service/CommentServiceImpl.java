package com.ouweihao.service;

import com.ouweihao.dao.CommentRepository;
import com.ouweihao.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            // 父评论
            comment.setParentComment(commentRepository.findOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * 循环每个顶层评论
     * @param comments
     * @return 只有两层深度（顶层和下一层）的评论集合
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsViews = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsViews.add(c);
        }
        // 合并评论中的各子层级到第一层级中
        combineChildren(commentsViews);
        return commentsViews;
    }

    /**
     * 修改顶层评论的reply集合
     * @param comments 顶层评论
     */
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replyComments = comment.getReplyComments();
            for (Comment replyComment : replyComments) {
                // 循环迭代，找出子代，存放到tempReplys中
                recursively(replyComment);
            }
            // 修改顶层评论的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            // 清空临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    // 存放迭代找出的所有的子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 递归迭代查找回复评论
     * @param comment 顶级评论
     */
    private void recursively(Comment comment) {
        // 顶层节点添加到临时存放集合中
        tempReplys.add(comment);
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                // 递归查找回复评论
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }
}
