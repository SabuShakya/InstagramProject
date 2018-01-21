package com.users.serviceImpl;

import com.users.dto.Commentsdto;
import com.users.model.Likes;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.LikesRepository;
import com.users.repository.PhotoRepository;
import com.users.repository.UserRepository;
import com.users.service.LikesService;
import com.users.utils.LikesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LikesServiceImpl implements LikesService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private LikesRepository likesRepository;
//sabu
    public int saveLike(Commentsdto commentsdto) {
        Likes liked = likesRepository.getByUserPhotos_Image_pathAAndUser_Username(
                            commentsdto.getImage_path(),commentsdto.getImage_path());
        if(liked !=null){
            if(liked.isLiked()){
                liked.setLiked(false);
                likesRepository.save(liked);
            }else {
                liked.setLiked(true);
                likesRepository.save(liked);
            }
            return getCountOfLikes(liked);
        }
        User user = userRepository.getUserByUsername(commentsdto.getUsername());
        UserPhotos userPhotos = photoRepository.getUserPhotosByImage_path(commentsdto.getImage_path());
        Likes likes = LikesUtil.generateLikes(user, userPhotos);
        likesRepository.save(likes);
        return getCountOfLikes(likes);
    }
//sabu
    public int getCountOfLikes(Likes likes){
        List<Likes> likesList = likesRepository.getByUserPhotos_Image_path(likes.getUserPhotos().getImage_path());
        return likesList.size();
    }
}
