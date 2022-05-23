package com.txg.www.service.impl;

import com.txg.www.dao.impl.DraftDaoImpl;
import com.txg.www.dao.intf.DraftDao;
import com.txg.www.model.entity.Draft;
import com.txg.www.service.intf.DraftService;

import java.sql.SQLException;

public class DraftServiceImpl implements DraftService {
    DraftDao draftDao = new DraftDaoImpl();

    @Override
    public boolean addDraft(String title, String content, String type, int userId) throws SQLException {

        return 1 == draftDao.save(title, userId, content, type);
    }

    @Override
    public Draft getDraft(int userId) throws Exception {
        return draftDao.select(userId);
    }

    @Override
    public boolean modify(String title, String content, String type, int userId) throws SQLException {
        return 1 == draftDao.update(title, userId, content, type);
    }

    @Override
    public boolean removeDraft(int userId) throws SQLException {
        return 1 == draftDao.delete(userId);
    }
}
