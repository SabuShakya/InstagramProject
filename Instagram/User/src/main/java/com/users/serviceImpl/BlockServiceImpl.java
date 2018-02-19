package com.users.serviceImpl;

import com.users.dto.BlockUserdto;
import com.users.dto.FollowDto;
import com.users.model.BlockUser;
import com.users.model.Follow;
import com.users.model.User;
import com.users.repository.BlockRepository;
import com.users.repository.FollowRepository;
import com.users.repository.UserRepository;
import com.users.service.BlockService;
import com.users.service.FollowService;
import com.users.utils.BlockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BlockServiceImpl implements BlockService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private FollowService followService;

    @Autowired
    private EntityManager entityManager;

    public void blockUser(BlockUserdto blockdto){
        User user = userRepository.getUserByUsername(blockdto.getUserName());
        User blockedUser=userRepository.getUserByUsername(blockdto.getBlockedUsername());
        BlockUser block = blockRepository.findByUser_IdAndAndBlockedUser_Id(user.getId(),blockedUser.getId());
        if (block == null){
        block= new BlockUser();
        block.setUser(user);
        block.setBlockedUser(blockedUser);
        block.setBlockStatus(true);
        Follow follow=followRepository.checkFollow(blockdto.getUserName(),
                blockdto.getBlockedUsername());
        if (follow !=null) {
            Follow follow1 = followRepository.findByUser_IdAndAndFollowedUser_Id(user.getId(),blockedUser.getId());
            if (follow1 != null){
            follow1.setIsFollowing(false);
            followRepository.save(follow1);
            }
            Follow follow2 = followRepository.findByUser_IdAndAndFollowedUser_Id(blockedUser.getId(),user.getId());
            if (follow2!= null) {
                follow2.setIsFollowing(false);
                followRepository.save(follow2);
            }
        }
        blockRepository.save(block);
        }else {
            block.setBlockStatus(true);
            blockRepository.save(block);
        }

    }

    public boolean checkBlocked(BlockUserdto blockUserdto){
        BlockUser blockUser=blockRepository.checkBlock(blockUserdto.getUserName(),blockUserdto.getBlockedUsername());
        if (blockUser!=null) {
            if (blockUser.isBlockStatus()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void unblockUser(BlockUserdto blockUserdto){
        BlockUser blockUser=blockRepository.checkBlock(blockUserdto.getUserName(),blockUserdto.getBlockedUsername());
        if(blockUser !=null){
            blockUser.setBlockStatus(false);
            blockRepository.save(blockUser);
        }
//        Follow follow=followRepository.checkFollow(blockUserdto.getUserName(),
//                blockUserdto.getBlockedUsername());
//        if (follow !=null) {
//
//            followRepository.delete(follow);
//        }
    }

    public List<BlockUserdto> getBlockedUserList(String username){
        User user=userRepository.getUserByUsername(username);
        final String SQL_QUERY="SELECT u.username, p.profile_pic, b.userId, b.blocked_userId FROM blockedUsers_table b LEFT JOIN user_table u ON b.blocked_userId = u.id LEFT JOIN profile_pic_table p ON u.id = p.user_id WHERE b.userId=:id AND b.blockStatus=true";
        Query query = entityManager.createNativeQuery(SQL_QUERY).setParameter("id",user.getId());
        List<Object[]> blockUsers = query.getResultList();
        List<BlockUserdto> blockUserdtoList= new ArrayList<BlockUserdto>();
        for(Object[] o:blockUsers ){
            BlockUserdto blockUserdto=BlockUtils.convertBlocktoBlockdto(o);
            blockUserdtoList.add(blockUserdto);
        }
        return blockUserdtoList;
    }
}
