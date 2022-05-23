package com.txg.www.service.impl;

import com.txg.www.dao.impl.BlogDaoImpl;
import com.txg.www.dao.impl.CollectDaoImpl;
import com.txg.www.dao.intf.BlogDao;
import com.txg.www.dao.intf.CollectDao;
import com.txg.www.model.entity.Collect;
import com.txg.www.service.intf.CollectService;

import java.sql.SQLException;
import java.util.List;

public class CollectServiceImpl implements CollectService {
      CollectDao collectDao=new CollectDaoImpl();
      BlogDao blogDao=new BlogDaoImpl();
    @Override
    public boolean addCollect(int userId, int blogId) throws Exception {
        Collect collect = collectDao.selectCollect(userId, blogId);
        if (collect!=null){
            collectDao.deleteCollect(userId, blogId);
            blogDao.updateCollectNum(blogId,-1);
            return false;
        }
         collectDao.insertCollect(userId, blogId);
        blogDao.updateCollectNum(blogId,1);
        return true;
    }

    @Override
    public boolean removeCollect(int userId, int blogId) throws SQLException {
        return 1==collectDao.deleteCollect(userId, blogId);
    }

    @Override
    public List<Collect> getcollects(int userId) throws Exception {
        return collectDao.selectCollects(userId);
    }
}
