package com.f1soft.admin.serviceimpl;


import com.f1soft.admin.dto.Commentsdto;
import com.f1soft.admin.dto.Likesdto;
import com.f1soft.admin.model.Likes;
import com.f1soft.admin.model.User;
import com.f1soft.admin.model.UserPhotos;
import com.f1soft.admin.repository.LikesRepository;
import com.f1soft.admin.repository.PhotoRepository;
import com.f1soft.admin.repository.UserRepository;
import com.f1soft.admin.service.LikesService;

import com.f1soft.admin.utils.LikesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public int saveLike(Commentsdto commentsdto) {
        User user = userRepository.getUserByUsername(commentsdto.getUsername());
        UserPhotos userPhotos = photoRepository.getUserPhotosByImage_path(commentsdto.getImage_path());
        Likes liked = likesRepository.getByUserIdAndPhotoId(user.getId(),userPhotos.getId());
        if(liked !=null){
            if(liked.isLiked()){
                liked.setLiked(false);
                likesRepository.delete(liked);
            }
            return getCountOfLikes(liked);
        }
        Likes likes = LikesUtil.generateLikes(user, userPhotos);
        likesRepository.save(likes);
        return getCountOfLikes(likes);
    }

    public int getCountOfLikes(Likes likes){
        int countofLikes = 0;
        List<Likesdto>  list =getLikesList(likes.getUserPhotos().getImage_path());
        for (Likesdto like: list){
            if(like.getActivationStatus().equals("activated")) {
                countofLikes +=1;
            }
        }
        return countofLikes;
    }

    public List<Likes> getByPhotoId(long id) {
        return likesRepository.getByUserPhotos_Id(id);
    }

    public int getLikesCountForImage(String imageName) {
        List<Likes> likesList= likesRepository.getByUserPhotos_Image_path(imageName);
        List<Likesdto> list=LikesUtil.convertLikesToLikesDto(likesList);
        List<Likesdto> likesResult= new ArrayList<>();
        for(Likesdto likesdto:list){
            if(likesdto.getActivationStatus().equals("activated")){
                likesResult.add(likesdto);
            }
        }
        return likesResult.size();
    }

    public List<Likesdto> getLikesList(String imageName) {
        List<Likes> likesList = likesRepository.getByUserPhotos_Image_path(imageName);
        List<Likesdto> list = LikesUtil.convertLikesToLikesDto(likesList);
        List<Likesdto> resultList = new ArrayList<Likesdto>();
        for (Likesdto like: list){
            if(like.getActivationStatus().equals("activated")) {
               resultList.add(like);
            }
        }
        return resultList;
    }

}
