package com.users.serviceImpl;
import com.users.dto.UserPhotodto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.PhotoRepository;
import com.users.service.PhotoService;
import com.users.service.UserService;
import com.users.utils.PhotoUtils;
import com.users.utils.TokenUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

   @Resource
   private PhotoRepository photoRepository;

   @Autowired
   private UserService userService;

   @Autowired
   private EntityManager entityManager;

    public void savePhoto(UserPhotodto userPhotodto){
        File dir = new File(System.getProperty("catalina.home")+ "/uploads");
        if(!dir.exists()){
            dir.mkdir();
        }
        User user = userService.getUser(userPhotodto.getUsername());
        for (String s:userPhotodto.getImageList()){
            byte[] imageDecoded = Base64.getDecoder().decode(s);
            String filename = imageDecoded.toString();
            String pathToImage = dir + "/" + filename;
            try {
                FileOutputStream fout = new FileOutputStream(pathToImage);
                fout.write(imageDecoded);
                fout.close();
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            UserPhotos userPhotos = new UserPhotos();
            userPhotos.setUser(user);
            userPhotos.setCreated_date(new Date());
            userPhotos.setCaption(userPhotodto.getCaption());
            userPhotos.setImage_path(filename);
            photoRepository.save(userPhotos);
        }
    }

    public List<UserPhotos> getListOfPhotos(List<User> listOfFollowedUsers, Pageable pageable) {
        final String SQL_QUERY="SELECT u from UserPhotos u where u.user.id=:id";
        Query query = entityManager.createQuery(SQL_QUERY,UserPhotos.class);
        List<UserPhotos> userPhotosList = new ArrayList<UserPhotos>();
        for (User user:listOfFollowedUsers){
            long id = user.getId();
            System.out.println(id);
            query.setParameter("id",id).getResultList();
            int totalItems =query.getResultList().size();

            query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
            List <UserPhotos> userPhotosList1 = query.getResultList();
            List<UserPhotos> list = photoRepository.getUserPhotosByUser_Id(id);
            for(UserPhotos userPhotos:userPhotosList1) {
                userPhotos.setTotalItems(totalItems);
                userPhotosList.add(userPhotos);
            }
        }
        return userPhotosList;
    }

    public List<UserPhotodto> getAllPhotos(String username) {
        List<UserPhotos> photoList = photoRepository.getUserPhotosByUserUsername(username);
        List<UserPhotodto> userPhotodto = PhotoUtils.convertUserPhotos(photoList);
        return userPhotodto;
    }

    public UserPhotos getPhotos(String image_path) {
       UserPhotos userPhotos= photoRepository.getUserPhotosByImage_path(image_path);
       return userPhotos;
    }

    public long getPhotoCount(String username) {
        List<UserPhotos> userPhotosList=photoRepository.getUserPhotosByUserUsername(username);
        return userPhotosList.size();
    }
}

