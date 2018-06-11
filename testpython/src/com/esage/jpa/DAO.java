package com.esage.jpa;

//public class Dao {
//
//}
/************************* ��Ȩ���� *********************************
 * 
 * ��Ȩ���У��������
 * Copyright (c) 2009 by Pearl Ocean.
 * 
 ************************* �����¼ *********************************
 *
 * �����ߣ�yongtree   �������ڣ� 2009-4-30
 * ������¼��������ṹ��
 * 
 * �޸��ߣ�������       �޸����ڣ�2010-1-12
 * �޸ļ�¼���޸Ľӿڶ����еķ���T ,����ӽӿڶ���Ų��ÿ������������.
 * 
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

import com.esage.bean.BaseEO;

//import com.posoftframework.entity.base.BaseEO;

/**
 * �ײ����ݲ����Ĺ�����
 * 
 * @author yongtree
 * @date 2009-4-30 ����08:55:32
 * @version 2.0
 * @since 2.0 ����TŲ������������,��������ʱ����Ҫָ������.
 */
public interface DAO {

    /**
     * ���һ�����������
     */
    public void clear();

    /**
     * ����ʵ��
     * 
     * @param entity
     *            ʵ��
     */
    public <T extends BaseEO> void create(T entity);

    /**
     * ��������ʵ��
     * 
     * @param entitys
     *            ʵ���б�
     */
    public <T extends BaseEO> void createBatch(List<T> entitys);

    /**
     * ����ʵ��
     * 
     * @param entity
     *            ʵ��
     */
    public <T extends BaseEO> void update(T entity);

    /**
     * ɾ��ʵ��
     * 
     * @param entityClass
     *            ʵ����
     * @param entityid
     *            ʵ��id
     */
    public <T extends BaseEO> void delete(Class<T> entityClass, Object entityid);

    /**
     * ɾ��ʵ��
     * 
     * @param entityClass
     *            ʵ����
     * @param entityids
     *            ʵ��id����
     */
    public <T extends BaseEO> void delete(Class<T> entityClass,
            Object[] entityids);

    /**
     * ��������ɾ��
     * 
     * @author slx
     * @date 2009-11-24 ����05:52:04
     * @modifyNote
     * @param entityClass
     * @param where
     * @param delParams
     */
    public <T extends BaseEO> void deleteByWhere(Class<T> entityClass,
            String where, Object[] delParams);

    /**
     * ��ȡʵ��
     * 
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @param entityId
     *            ʵ��id
     * @return
     */
    public <T extends BaseEO> T find(Class<T> entityClass, Object entityId);

    /**
     * ����where������ѯ��������
     * @author slx
     * @date 2010-7-19 ����10:33:20
     * @modifyNote
     * @param <T>
     * @param entityClass
     *          ����
     * @param where
     *          ����
     * @param params
     *          ����
     * @return
     */
    public <T extends BaseEO> T findByWhere(Class<T> entityClass, String where ,Object[] params);

    /**
     * ��ȡʵ�壬�����ӳټ��ص����ã���find��ȣ�
     * 
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @param entityId
     *            ʵ��id
     * @return
     */
    public <T extends BaseEO> T load(Class<T> entityClass, Object entityId);

    /**
     * ���������ж�ʵ���Ƿ����
     * 
     * @author slx
     * @date 2009-7-8 ����11:49:13
     * @modifyNote
     * @param entityClass
     *            ʵ����
     * @param whereql
     *            ��ѯ����(�ɿ�,��Ϊ field1=? and field2=? ��ʽ,Ҳ��Ϊfield1='value1' and
     *            field2='value2'����ʽ)
     * @param queryParams
     *            ����(�ɿգ����ǵ�����ʹ����field1=? and field2=? ����ʽ���������Ϊ��)
     * @return �Ƿ����
     */
    public <T extends BaseEO> boolean isExistedByWhere(Class<T> entityClass,
            String whereql, Object[] queryParams);

    /**
     * ��ȡ��¼����
     * 
     * @param entityClass
     *            ʵ����
     * @return
     */
    public <T extends BaseEO> long getCount(Class<T> entityClass);

    /**
     * ���������Ͳ�����ȡ��¼����
     * 
     * @author slx
     * @date 2009-7-8 ����11:34:41
     * @modifyNote
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @param whereql
     *            ��ѯ����(�ɿ�,��Ϊ field1=? and field2=? ��ʽ,Ҳ��Ϊfield1='value1' and
     *            field2='value2'����ʽ)
     * @param queryParams
     *            ����(�ɿգ����ǵ�����ʹ����field1=? and field2=? ����ʽ���������Ϊ��)
     * @return ��¼����
     */
    public <T extends BaseEO> long getCountByWhere(Class<T> entityClass,
            String whereql, Object[] queryParams);

    /**
     * ��ȡ��ҳ����
     * 
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @param firstindex
     *            ��ʼ����
     * @param maxresult
     *            ��Ҫ��ȡ�ļ�¼��
     * @return
     */
    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, Object[] queryParams,
            LinkedHashMap<String, String> orderby);

    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, List<Object> queryParams,
            LinkedHashMap<String, String> orderby);

    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, Map<String, Object> queryParams,
            LinkedHashMap<String, String> orderby);

    /**
     * ��ѯʵ�岿���ֶΣ���ȡ��ҳ����
     * 
     * ���صĽ����������װ��ʵ�������У�û�в�ѯ���ֶ�ΪNULL<br>
     * ע�⣺ʹ�øýӿ�ʱ��Ҫȷ��ʵ�������ж�Ӧ�Ĳ�ѯ�ֶε��в������췽�������Ҳ�����˳��Ҫ�ʹ˴���queryfields�����Ԫ��һ��
     * 
     * @author yongtree
     * @date 2010-4-13 ����12:56:03
     * @modifyNote
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
    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, Object[] queryParams,
            LinkedHashMap<String, String> orderby);

    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, List<Object> queryParams,
            LinkedHashMap<String, String> orderby);

    public <T extends BaseEO> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, Map<String, Object> queryParams,
            LinkedHashMap<String, String> orderby);

    /**
     * ����������ѯʵ���е�ָ�������ֶ� <br>
     * ���ؽ��List<String[]>��ʽ���£� <br>
     * ��1�� �ֶ�1value , �ֶ�2value , �ֶ�3value <br>
     * ��2�� �ֶ�1value , �ֶ�2value , �ֶ�3value
     * 
     * @author slx
     * @date 2009-5-14 ����01:14:23
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param queryfields
     * @param wheresql
     * @param queryParams
     * @return
     */
    public <T extends BaseEO> List<Object[]> queryFieldValues(
            Class<T> entityClass, String[] queryfields, String wheresql,
            Object[] queryParams);

    public <T extends BaseEO> List<Object[]> queryFieldValues(
            Class<T> entityClass, String[] queryfields, String wheresql,
            Object[] queryParams, int startRow, int rows);

    /**
     * ����������ѯʵ���е�ָ�������ֶ� <br>
     * ���صĽ����������װ��ʵ�������У�û�в�ѯ���ֶ�ΪNULL<br>
     * ע�⣺ʹ�øýӿ�ʱ��Ҫȷ��ʵ�������ж�Ӧ�Ĳ�ѯ�ֶε��в������췽�������Ҳ�����˳��Ҫ�ʹ˴���queryfields�����Ԫ��һ��
     * 
     * @author yongtree
     * @date 2010-4-13 ����11:45:27
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param queryfields
     * @param wheresql
     * @param queryParams
     * @return
     */
    public <T extends BaseEO> List<T> queryByWhere(Class<T> entityClass,
            String[] queryfields, String wheresql, Object[] queryParams);

    public <T extends BaseEO> List<T> queryByWhere(Class<T> entityClass,
            String[] queryfields, String wheresql, Object[] queryParams,
            int startRow, int rows);

    /**
     * ����where������ѯʵ��bean�б� <br>
     * where��queryParams�ɿ�
     * 
     * @author slx
     * @date 2009-5-14 ����01:20:19
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param wheresql
     * @param queryParams
     * @return
     */
    public <T extends BaseEO> List<T> queryByWhere(Class<T> entityClass,
            String wheresql, Object[] queryParams);

    /**
     * ����where������ѯʵ��bean�б�,��ָ��ȡ�ڼ��е��ڼ��� <br>
     * where��queryParams�ɿ�
     * 
     * @author slx
     * @date 2009-5-14 ����01:20:19
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param wheresql
     * @param queryParams
     * @param startRow
     *            ��ʼ��
     * @param rows
     *            ��������
     * @return
     */
    public <T extends BaseEO> List<T> queryByWhere(Class<T> entityClass,
            String wheresql, Object[] queryParams, int startRow, int rows);

    /**
     * �õ�EM�������龭��ʹ�ã�����ʹ�ù����������ϵͳ��ά���ѶȼӴ����չ�Ա��
     * 
     * @author yongtree
     * @date 2009-11-5 ����11:32:49
     * @modifyNote
     * @return
     */
    public EntityManager getEntityManager();
}
