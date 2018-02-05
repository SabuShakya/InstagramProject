package com.users.utils;

import com.users.dto.BlockUserdto;
import com.users.model.BlockUser;
import com.users.model.User;

import java.util.ArrayList;
import java.util.List;

public class BlockUtils {
    public static List<BlockUserdto> convertBlocktoBlockdto(List<User> userList){
        List<BlockUserdto> blockUserdtos=new ArrayList<BlockUserdto>();
        for (User user:userList){
            BlockUserdto blockUserdto=new BlockUserdto();
            blockUserdto.setUserName(user.getUsername());
//            blockUserdto.setBlockedUsername(user.getBlockedUser());
            blockUserdtos.add(blockUserdto);
        }
        return blockUserdtos;
    }
}
