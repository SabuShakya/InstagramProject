package com.f1soft.admin.serviceimpl;

import com.f1soft.admin.dto.UserListInfoDto;
import com.f1soft.admin.model.User;
import com.f1soft.admin.repository.UserRepository;
import com.f1soft.admin.service.UserService;
import com.f1soft.admin.utils.TotalUsersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

//    gets all users in ascending order
    @Override
    public List<UserListInfoDto> getTotalUsers(Pageable pageable) {

        final String SQL_QUERY = "SELECT u from User u ORDER BY username";
        return getList(SQL_QUERY,pageable);
    }

//    This method gets active users only
    @Override
    public List<UserListInfoDto> getTotalActiveUsers(Pageable pageable) {
        final String SQL_QUERY = "SELECT u from User u where u.userTokenAuth.status='Y' ORDER BY username";
        return getList(SQL_QUERY,pageable);
    }

//    This method gets list of users for pagination
    public List<UserListInfoDto> getList(final String SQL_QUERY,Pageable pageable){
        Query query = entityManager.createQuery(SQL_QUERY, User.class);
        int totalItems = query.getResultList().size();
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<User> users = query.getResultList();
        List<User> userList = new ArrayList<User>();
        for (User user : users) {
            user.setTotalItems(totalItems);
            userList.add(user);
        }
        return TotalUsersUtil.convertUsersListToUserListInfoDto(userList);
    }
}
