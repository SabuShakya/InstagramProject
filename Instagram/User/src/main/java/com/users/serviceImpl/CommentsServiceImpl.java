package com.users.serviceImpl;

import com.users.dto.Commentsdto;
import com.users.model.Comments;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.CommentsRepository;
import com.users.service.CommentsService;
import com.users.service.PhotoService;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

   @Resource
    private CommentsRepository commentsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    public void saveComments(Commentsdto commentsdto){
        User user = userService.getUser(commentsdto.getUsername());
        UserPhotos userPhotos =photoService.getPhotos(commentsdto.getImage_path());
        Comments comments = new Comments();
        comments.setUser(user);
        comments.setUserPhotos(userPhotos);
        comments.setComments(commentsdto.getComments());
        commentsRepository.save(comments);
    }
}
