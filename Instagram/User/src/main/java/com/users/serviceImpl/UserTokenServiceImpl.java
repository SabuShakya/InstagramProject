package com.users.serviceImpl;

import com.users.dto.UserTokenDto;
import com.users.dto.Userdto;
import com.users.model.User;
import com.users.model.UserToken;
import com.users.repository.UserTokenRepository;
import com.users.service.UserTokenService;
import com.users.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    public void saveToken(User user) {
        UserToken userToken = new UserToken();
        userToken.setTokenNo(TokenUtils.generateToken());
        userToken.setStatus('N');
        userToken.setUser(user);
        userTokenRepository.save(userToken);
    }

    public UserToken getUserByTokenNo(String tokenNo, Long id) {
        return userTokenRepository.findByUser_IdAndAndTokenNo(id,tokenNo);

    }

    public UserTokenDto authToken(Userdto userdto) {
        UserToken userToken = userTokenRepository.getByUserId(userdto.getId());
        UserTokenDto userTokenDto =new UserTokenDto();
        if ((userToken.getTokenNo() != null) && userToken.getStatus()=='N') {
                userToken.setTokenNo(TokenUtils.generateToken());
                userToken.setStatus('Y');
                userTokenRepository.save(userToken);
                userTokenDto = TokenUtils.setUserToken(userToken);
                return userTokenDto;
            }
            userTokenDto= TokenUtils.setUserToken(userToken);
            return userTokenDto;
    }

    public void logoutUser(long id, String tokenNo){
       UserToken userToken=userTokenRepository.findByUser_IdAndAndTokenNo(id,tokenNo);
       userToken.setStatus('N');
       userTokenRepository.save(userToken);
   }
}
