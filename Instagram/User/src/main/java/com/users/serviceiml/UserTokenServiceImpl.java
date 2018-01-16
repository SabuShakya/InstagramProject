package com.users.serviceiml;

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
//@Transactionalo
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
        return userTokenRepository.getUserByTokenNoAndId(tokenNo, id);

    }

    public UserTokenDto authToken(Userdto userdto) {
        UserToken userToken = userTokenRepository.getByUserId(userdto.getId());
        UserTokenDto userTokenDto =null;
        if ((userToken.getTokenNo() != null) && userToken.getStatus() == 'N') {
                userToken.setTokenNo(TokenUtils.generateToken());
                userToken.setStatus('Y');
                userTokenRepository.save(userToken);
                userTokenDto = TokenUtils.setUserToken(userToken);
                return userTokenDto;
            }
            userTokenDto= TokenUtils.setUserToken(userToken);
            return userTokenDto;
    }

    public boolean verifyloginUser(User user,String tokenNo) {
        UserToken userToken =userTokenRepository.getUserByTokenNoAndId(tokenNo, user.getId());
        if ((userToken != null) && (userToken.getStatus().equals('Y'))){
            return true;
        }
        return false;
    }
}
