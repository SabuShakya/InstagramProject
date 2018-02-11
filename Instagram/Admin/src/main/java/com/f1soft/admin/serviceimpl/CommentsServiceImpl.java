package com.f1soft.admin.serviceimpl;

import com.f1soft.admin.dto.Commentsdto;
import com.f1soft.admin.dto.UserPostDto;
import com.f1soft.admin.service.CommentsService;
import com.f1soft.admin.utils.CommentsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Commentsdto> getAllComments(String imageName) {
//        getting comments list
        List<Commentsdto> commentsdtoList = new ArrayList<Commentsdto>();
        String commentSql = "SELECT t.comments,u.username FROM comment_table t LEFT JOIN photo_table t2 ON t.pic_id = t2.id LEFT JOIN user_table u ON t.user_id = u.id " +
                "WHERE t2.image_path =:imageName";

        javax.persistence.Query query = entityManager.createNativeQuery(commentSql).setParameter("imageName", imageName);
        List<Object[]> commentList = query.getResultList();
        for (Object[] o : commentList) {
            commentsdtoList.add(CommentsUtil.convertToDto(o));
        }
        return commentsdtoList;

    }
}
