package com.esage.jpa;
//
//public class BaseJpaDao {
//
//}
/************************* ��Ȩ���� *********************************
 * 
 * ��Ȩ���У��������
 * Copyright (c) 2009 by Pearl Ocean.
 * 
 ************************* �����¼ *********************************
 *
 * �����ߣ�yongtree   �������ڣ� 2009-5-31
 * ������¼��������ṹ��
 * 
 * �޸��ߣ�������       �޸����ڣ�2010-1-12
 * �޸ļ�¼���޸���DAO�ӿ�,ͬʱ�޸��˴�ʵ����.
 ************************* ��   �� *********************************
 *
 * �������дд���룬�п�������ʲô�ġ�
 * 
 ******************************************************************
 */

//package com.posoftframework.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.esage.bean.BaseEO;

//import com.posoftframework.entity.base.BaseEO;

/**
 * ��װ������ɾ�Ĳ����
 * 
 * @author yongtree
 * @date 2009-5-31 ����11:09:01
 * @version 2.0
 * @since 2.0 ����TŲ������������,��������ʱ����Ҫָ������.
 */
@SuppressWarnings("unchecked")
public abstract class BaseJpaDao implements DAO {

    private QLBuilder sqlBuilder = new QLBuilder();

    public void clear() {
        getEntityManager().clear();
    }

    @Transactional
    public <T extends BaseEO> void create(T entity) {
        getEntityManager().persist(entity);
    }

    public <T extends BaseEO> void createBatch(List<T> entitys) {
        for (T entity : entitys) {
            create(entity);
        }
    }

    @Transactional
    public <T extends BaseEO> void update(T entity) {
        getEntityManager().merge(entity);
    }

    @Transactional
    public <T extends BaseEO> void saveAll(List<T> entitys) {
        for (int i = 0; i < entitys.size(); i++) {
            T entity = entitys.get(i);
            save(entity);
        }
    }

    @Transactional
    public <T extends BaseEO> void save(T entity) {
        if (entity.getPrimaryKey() == null) {
            this.create(entity);
        } else {
            this.update(entity);
        }
    }

    @Transactional
    public <T extends BaseEO> void delete(Class<T> entityClass, Object entityid) {
        delete(entityClass, new Object[] { entityid });
    }

    @Transactional
    public <T extends BaseEO> void delete(Class<T> entityClass,
            Object[] entityids) {
        // StringBuffer sf_QL = new StringBuffer(" DELETE FROM ").append(
        // sqlBuilder.getEntityName(entityClass)).append(" o WHERE ")
        // .append(sqlBuilder.getPkField(entityClass, "o")).append("=? ");
        // Query query = getEntityManager().createQuery(sf_QL.toString());
        for (Object id : entityids) {
            getEntityManager().remove(getEntityManager().find(entityClass, id));
            // query.setParameter(1, id).executeUpdate();
        }
    }

    @Transactional
    public <T extends BaseEO> void deleteByWhere(Class<T> entityClass,
            String where, Object[] delParams) {
        StringBuffer sf_QL = new StringBuffer("DELETE FROM ").append(
                sqlBuilder.getEntityName(entityClass)).append(" o WHERE 1=1 ");
        if (where != null && where.length() != 0) {
            sf_QL.append(" AND ").append(where);
        }
        Query query = getEntityManager().createQuery(sf_QL.toString());
        this.setQueryParams(query, delParams);

        query.executeUpdate();
    }

    public <T extends BaseEO> T find(Class<T> entityClass, Object entityId) {
        return getEntityManager().find(entityClass, entityId);
    }

    public <T extends BaseEO> long getCount(Class<T> entityClass) {
        return getCountByWhere(entityClass, null, null);
    }

    public <T extends BaseEO> long getCountByWhere(Class<T> entityClass,
            String whereql, Object[] queryParams) {
        StringBuffer sf_QL = new StringBuffer("SELECT COUNT(").append(
                sqlBuilder.getPkField(entityClass, "o")).append(") FROM ")
                .append(sqlBuilder.getEntityName(entityClass)).append(
                        " o WHERE 1=1 ");
        if (whereql != null && whereql.length() != 0) {
            sf_QL.append(" AND ").append(whereql);
        }
        Query query = getEntityManager().createQuery(sf_QL.toString());
        this.setQueryParams(query, queryParams);
        return (Long) query.getSingleResult();
    }

    public <T extends BaseEO> boolean isExistedByWhere(Class<T> entityClass,
            String whereql, Object[] queryParams) {
        long count = getCountByWhere(entityClass, whereql, queryParams);
        return count > 0 ? true : false;
    }

    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, Object[] queryParams,
            LinkedHashMap<String, String> orderby) {
        return scroll(entityClass, firstindex, maxresult, wherejpql,
                queryParams, orderby);
    }

    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, List<Object> queryParams,
            LinkedHashMap<String, String> orderby) {
        Object[] ps = null;
        if (queryParams != null) {
            ps = queryParams.toArray();
        }
        return getScrollData(entityClass, firstindex, maxresult, wherejpql, ps,
                orderby);
    }

    @Override
    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, Map<String, Object> queryParams,
            LinkedHashMap<String, String> orderby) {
        return scroll(entityClass, firstindex, maxresult, wherejpql,
                queryParams, orderby);
    }

    /**
     * ����������ѯĳ��ʵ����б�
     * 
     * @author slx
     * @param <T>
     * @param entityClass
     *            ʵ������
     * @param firstindex
     *            ��ʼ��
     * @param maxresult
     *            ������
     * @param wherejpql
     *            where����
     * @param queryParams
     *            ����
     * @param orderby
     *            ��������
     * @return
     */
    private <T extends BaseEO> QueryResult<T> scroll(Class<T> entityClass,
            int firstindex, int maxresult, String wherejpql,
            Object queryParams, LinkedHashMap<String, String> orderby) {
        QueryResult<T> qr = new QueryResult<T>();
        String entityname = sqlBuilder.getEntityName(entityClass);
        Query query = getEntityManager()
                .createQuery(
                        "SELECT o FROM "
                                + entityname
                                + " o "
                                + (StringUtils.isEmpty(wherejpql) ? ""
                                        : "WHERE " + wherejpql)
                                + sqlBuilder.buildOrderby(orderby));
        setQueryParams(query, queryParams);
        if (firstindex != -1 && maxresult != -1)
            query.setFirstResult(firstindex).setMaxResults(maxresult).setHint(
                    "org.hibernate.cacheable", true);
        qr.setResultlist(query.getResultList());
        query = getEntityManager().createQuery(
                "SELECT COUNT("
                        + sqlBuilder.getPkField(entityClass, "o")
                        + ") FROM "
                        + entityname
                        + " o "
                        + (StringUtils.isEmpty(wherejpql) ? "" : "WHERE "
                                + wherejpql));
        setQueryParams(query, queryParams);
        qr.setTotalrecord((Long) query.getSingleResult());
        return qr;
    }

    /**
     * ����������ѯʵ��ָ���ֶε�ֵ�����ʵ����. <br/>
     * <b>ע��:</b> <br/>
     * ʵ������а���Ҫ��ѯ���ֶ�Ϊ�����Ĺ��캯��.
     * 
     * @param <T>
     * @param entityClass
     * @param queryfields
     * @param firstindex
     * @param maxresult
     * @param wherejpql
     * @param queryParams
     * @param orderby
     * @return
     */
    private <T extends BaseEO> QueryResult<T> scroll(Class<T> entityClass,
            String[] queryfields, int firstindex, int maxresult,
            String wherejpql, Object queryParams,
            LinkedHashMap<String, String> orderby) {
        QueryResult<T> qr = new QueryResult<T>();
        String entityname = sqlBuilder.getEntityName(entityClass);
        Query query = getEntityManager()
                .createQuery(
                        (sqlBuilder.buildSelect(entityname, queryfields, "o")
                                + "FROM "
                                + entityname
                                + " o "
                                + (StringUtils.isEmpty(wherejpql) ? ""
                                        : "WHERE " + wherejpql) + sqlBuilder
                                .buildOrderby(orderby)));
        setQueryParams(query, queryParams);
        if (firstindex != -1 && maxresult != -1)
            query.setFirstResult(firstindex).setMaxResults(maxresult).setHint(
                    "org.hibernate.cacheable", true);
        qr.setResultlist(query.getResultList());
        query = getEntityManager().createQuery(
                "SELECT COUNT("
                        + sqlBuilder.getPkField(entityClass, "o")
                        + ") FROM "
                        + entityname
                        + " o "
                        + (StringUtils.isEmpty(wherejpql) ? "" : "WHERE "
                                + wherejpql));
        setQueryParams(query, queryParams);
        qr.setTotalrecord((Long) query.getSingleResult());
        return qr;
    }

    @Override
    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, List<Object> queryParams,
            LinkedHashMap<String, String> orderby) {
        return this.scroll(entityClass, queryfields, firstindex, maxresult,
                wherejpql, queryParams, orderby);
    }

    @Override
    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, Map<String, Object> queryParams,
            LinkedHashMap<String, String> orderby) {
        return this.scroll(entityClass, queryfields, firstindex, maxresult,
                wherejpql, queryParams, orderby);
    }

    @Override
    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, Object[] queryParams,
            LinkedHashMap<String, String> orderby) {
        return this.scroll(entityClass, queryfields, firstindex, maxresult,
                wherejpql, queryParams, orderby);
    }

    protected void setQueryParams(Query query, Object queryParams) {
        sqlBuilder.setQueryParams(query, queryParams);
    }

    public <T extends BaseEO> List<T> queryByWhere(Class<T> entityClass,
            String wheresql, Object[] queryParams) {
        String entityname = sqlBuilder.getEntityName(entityClass);
        Query query = getEntityManager().createQuery(
                "SELECT o FROM "
                        + entityname
                        + " o "
                        + ((wheresql == null || wheresql.length() == 0) ? ""
                                : "WHERE " + wheresql));
        setQueryParams(query, queryParams);
        query.setHint("org.hibernate.cacheable", true);
        return query.getResultList();
    }

    public <T extends BaseEO> List<T> queryByWhere(Class<T> entityClass,
            String wheresql, Object[] queryParams, int startRow, int rows) {
        String entityname = sqlBuilder.getEntityName(entityClass);
        Query query = getEntityManager().createQuery(
                "SELECT o FROM "
                        + entityname
                        + " o "
                        + ((wheresql == null || wheresql.length() == 0) ? ""
                                : "WHERE " + wheresql));
        setQueryParams(query, queryParams);
        if (startRow >= 0) {
            query.setFirstResult(startRow);
        }
        if (rows > 0) {
            query.setMaxResults(rows);
        }
        query.setHint("org.hibernate.cacheable", true);
        return query.getResultList();
    }

    @Override
    public <T extends BaseEO> List<T> queryByWhere(Class<T> entityClass,
            String[] queryfields, String wheresql, Object[] queryParams) {
        return queryByWhere(entityClass, queryfields, wheresql, queryParams,
                -1, -1);
    }

    @Override
    public <T extends BaseEO> List<T> queryByWhere(Class<T> entityClass,
            String[] queryfields, String wheresql, Object[] queryParams,
            int startRow, int rows) {
        String entityname = sqlBuilder.getEntityName(entityClass);
        Query query = getEntityManager().createQuery(
                sqlBuilder.buildSelect(entityname, queryfields, "o") + " FROM "
                        + entityname + " o "
                        + (wheresql == null ? "" : "WHERE " + wheresql));
        setQueryParams(query, queryParams);
        if (startRow >= 0) {
            query.setFirstResult(startRow);
        }
        if (rows > 0) {
            query.setMaxResults(rows);
        }
        return query.getResultList();
    }

    public <T extends BaseEO> List<Object[]> queryFieldValues(
            Class<T> entityClass, String[] queryfields, String wheresql,
            Object[] queryParams) {
        return queryFieldValues(entityClass, queryfields, wheresql,
                queryParams, -1, -1);
    }

    @Override
    public <T extends BaseEO> List<Object[]> queryFieldValues(
            Class<T> entityClass, String[] queryfields, String wheresql,
            Object[] queryParams, int startRow, int rows) {
        String entityname = sqlBuilder.getEntityName(entityClass);
        Query query = getEntityManager().createQuery(
                sqlBuilder.buildSelect(queryfields, "o") + " FROM "
                        + entityname + " o "
                        + (wheresql == null ? "" : "WHERE " + wheresql));
        setQueryParams(query, queryParams);
        if (startRow >= 0) {
            query.setFirstResult(startRow);
        }
        if (rows > 0) {
            query.setMaxResults(rows);
        }
        return query.getResultList();
    }

    /**
     * ���ò�ѯ����
     * 
     * @author slx
     * @date 2009-7-8 ����10:02:55
     * @modifyNote
     * @param query
     *            ��ѯ
     * @param queryParams
     *            ��ѯ����
     */
    protected void setQueryParams(Query query, Object[] queryParams) {
        sqlBuilder.setQueryParams(query, queryParams);
    }

    /**
     * ����ʵ�������
     * 
     * @desc ��slx��2010-6-8����04:06:55��д���෽��
     */
    public abstract EntityManager getEntityManager();

    @Override
    public <T extends BaseEO> T load(Class<T> entityClass, Object entityId) {
        try {
            return getEntityManager().getReference(entityClass, entityId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public <T extends BaseEO> T findByWhere(Class<T> entityClass, String where,
            Object[] params) {
         List<T> l = queryByWhere(entityClass, where, params);
         if(l != null && l.size() == 1){
             return l.get(0);
         }else if(l.size() > 1){
             throw new RuntimeException("��Ѱ���Ľ����ֹһ��.");
         }else{
             return null;
         }
    }

}
