package com.txg.www.service.impl;

import com.txg.www.dao.impl.RelationDaoImpl;
import com.txg.www.dao.intf.RelationDao;
import com.txg.www.model.entity.Relation;
import com.txg.www.service.intf.RelationService;

import java.sql.SQLException;
import java.util.List;

public class RelationServiceImpl implements RelationService {
    RelationDao relationDao = new RelationDaoImpl();

    @Override
    public boolean addConcern(int userId, int concernId) throws SQLException {
        return 1 == relationDao.insertRelation(userId, concernId);
    }

    @Override
    public List<Relation> concernList(int userId) throws Exception {
        return relationDao.selectConcerns(userId);
    }

    @Override
    public List<Relation> fanList(int userId) throws Exception {
        return relationDao.selectFans(userId);
    }

    @Override
    public boolean removeConcern(int userId, int concernId) throws SQLException {
        return 1 == relationDao.deleteRelation(userId, concernId);
    }

    @Override
    public Relation concern(int userId, int concernId) throws Exception {
        return relationDao.selectConcern(userId, concernId);
    }
}
