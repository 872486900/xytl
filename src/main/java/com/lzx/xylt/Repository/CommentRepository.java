package com.lzx.xylt.Repository;

import com.lzx.xylt.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 87248 on 2020-04-21 11:27
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByAritcle_ArtIdAndParentCommentNull(Long artId,Sort sort);
}
