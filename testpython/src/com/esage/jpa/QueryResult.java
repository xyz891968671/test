package com.esage.jpa;
//
//public class QueryResult {
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
 * �޸��ߣ�       �޸����ڣ�
 * �޸ļ�¼��
 ************************* ��   �� *********************************
 *
 * �������дд���룬�п�������ʲô�ġ�
 * 
 ******************************************************************
 */

//package com.posoftframework.dao;

import java.util.List;

/**
 * ��ѯ�������
 * 
 * @author yongtree
 * @date 2009-4-30 ����09:00:12
 * @version 1.0
 */
public class QueryResult<T> {
    private List<T> resultlist;
    private Long totalrecord;

    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    public Long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(Long totalrecord) {
        this.totalrecord = totalrecord;
    }
}
