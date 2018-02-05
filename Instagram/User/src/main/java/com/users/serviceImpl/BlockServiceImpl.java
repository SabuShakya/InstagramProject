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
import jdk.nashorn.internal.ir.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void blockUser(BlockUserdto blockdto){
        User user = userRepository.getUserByUsername(blockdto.getUserName());
        User blockedUser=userRepository.getUserByUsername(blockdto.getBlockedUsername());
        BlockUser block= new BlockUser();
        block.setUser(user);
        block.setBlockedUser(blockedUser);
        block.setBlockStatus(true);
        Follow follow=followRepository.checkFollow(blockdto.getUserName(),
                blockdto.getBlockedUsername());
        if (follow !=null) {
            followRepository.delete(follow);
        }
        blockRepository.save(block);
    }

    public boolean checkBlocked(BlockUserdto blockUserdto){
        BlockUser blockUser=blockRepository.checkBlock(blockUserdto.getUserName(),blockUserdto.getBlockedUsername());
        if(blockUser!=null){
            return true;
        }else {
            return false;
        }
    }

    public void unblockUser(BlockUserdto blockUserdto){
        BlockUser blockUser=blockRepository.checkBlock(blockUserdto.getUserName(),blockUserdto.getBlockedUsername());
        if(blockUser !=null){
            blockRepository.delete(blockUser);
        }
    }

    public List<BlockUserdto> getBlockedUserList(String username){
        User user=userRepository.getUserByUsername(username);
        List<User> blockUsers = blockRepository.getByBlockedUser(user.getId());
        return BlockUtils.convertBlocktoBlockdto(blockUsers);
    }
//    SELECT b.blocked_userId,b.userId,b.blockStatus From blockedUsers_table b JOIN user_table u ON b.blocked_userId = u.id
}
