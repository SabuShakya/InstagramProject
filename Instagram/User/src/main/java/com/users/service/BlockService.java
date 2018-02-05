package com.users.service;

import com.users.dto.BlockUserdto;
import com.users.model.BlockUser;

import java.util.List;

public interface BlockService {
    public void blockUser(BlockUserdto blockdto);
    public boolean checkBlocked(BlockUserdto blockUserdto);
    public void unblockUser(BlockUserdto blockUserdto);
    public List<BlockUserdto> getBlockedUserList(String username);
}
