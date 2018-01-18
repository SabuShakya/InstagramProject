package com.users.serviceImpl;

import com.users.dto.Likesdto;
import com.users.model.Likes;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.LikesRepository;
import com.users.service.LikesService;
import com.users.service.PhotoService;
import com.users.service.UserService;
import com.users.utils.LikesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LikesServiceImpl implements LikesService{
    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;
    @Autowired
    private LikesRepository likesRepository;

    public void saveLikes(Likesdto likesdto){
        User user = userService.getUser(likesdto.getUsername());
        UserPhotos userPhotos = photoService.getPhotos(likesdto.getImage_path());
        Likes likes = new Likes();
        likes.setUser(user);
        likes.setUserPhotos(userPhotos);
//        likes.setLikes(likesdto.getLikes());
        likesRepository.save(likes);
    }

    public List<Likesdto> getAllLikes(String image_path) {
        List<Likes> likesList = likesRepository.getLikesByUserPhotosImage_path(image_path);
        List<Likesdto> likesdtos = LikesUtils.convertLikestoLikesdto(likesList);
        return likesdtos;
    }
}
