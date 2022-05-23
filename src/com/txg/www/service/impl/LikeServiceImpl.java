package com.txg.www.service.impl;

import com.txg.www.dao.impl.LikeDaoImpl;
import com.txg.www.dao.intf.LikeDao;
import com.txg.www.model.entity.Like;
import com.txg.www.service.intf.LikeService;

import java.sql.SQLException;

public class LikeServiceImpl implements LikeService {
    LikeDao likeDao = new LikeDaoImpl();

    @Override
    public boolean addLike(int userId, int blogId) throws SQLException {
        return 1 == likeDao.insertLike(userId, blogId);
    }

    @Override
    public boolean cancelLike(int userId, int blogId) throws SQLException {
        return 1 == likeDao.deleteLike(userId, blogId);
    }

    @Override
    public Like getLike(int userId, int blogId) throws Exception {
        return likeDao.selectLike(userId, blogId);
    }

    @Override
    public int getLikeNum(int userId) throws Exception {
        return likeDao.slectLikeNum(userId);
    }
}
