package com.users.utils;

import com.users.dto.BlockUserdto;

public class BlockUtils {
    public static BlockUserdto convertBlocktoBlockdto(Object[] objects) {
        BlockUserdto blockUserdto = new BlockUserdto();
        blockUserdto.setBlockedUsername(objects[0].toString());
        return blockUserdto;
    }
}
