package com.users.service;

import com.users.dto.BlockUserdto;

public interface BlockService {
    public void blockUser(BlockUserdto blockdto);
    public boolean checkBlocked(BlockUserdto blockUserdto);
    public void unblockUser(BlockUserdto blockUserdto);
}
