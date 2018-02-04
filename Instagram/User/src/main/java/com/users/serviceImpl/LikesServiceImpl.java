package com.users.serviceImpl;

import com.users.dto.Commentsdto;
import com.users.dto.Likesdto;
import com.users.model.Likes;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.LikesRepository;
import com.users.repository.PhotoRepository;
import com.users.repository.ProfilePhotoRepository;
import com.users.repository.UserRepository;
import com.users.service.LikesService;
import com.users.utils.LikesUtil;
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
//        List<Likes> likesList = likesRepository.getByUserPhotos_Image_path(likes.getUserPhotos().getImage_path());
        List<Likesdto>  list =getLikesList(likes.getUserPhotos().getImage_path());
        for (Likesdto like: list){
            if(like.getActivationStatus().equals("activated")) {
                countofLikes +=1;
            }
        }
//        return likesList.size();
        return countofLikes;
    }

    public List<Likes> getByPhotoId(long id) {
        return likesRepository.getByUserPhotos_Id(id);
    }

    public int getLikesCountForImage(String imageName) {
        List<Likes> likesList = likesRepository.getByUserPhotos_Image_path(imageName);
        return likesList.size();
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
//       return LikesUtil.convertLikesToLikesDto(likesList);
        return resultList;
    }
}
