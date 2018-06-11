package com.esage.jpa;

//public class QueryCondition {
//
//}
/************************* ��Ȩ���� *********************************
 * 
 * ��Ȩ���У��������
 * Copyright (c) 2009 by Pearl Ocean.
 * 
 ************************* �����¼ *********************************
 *
 * �����ߣ�slx   �������ڣ� 2009-6-24
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

import java.io.Serializable;

/**
 * ��������
 * @author slx
 * @date 2009-6-24 ����10:53:46
 * @version 1.0
 */
public class QueryCondition implements Serializable,Cloneable{

    private static final long serialVersionUID = 1L;

    /** �ֶ���������.������ **/
    private String field ;

    /** ������ **/
    private String operator ;

    /** ֵ **/
    private Object value ;

    public QueryCondition() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public QueryCondition(String field , String operator , Object value) {
        this.field = field ;
        this.operator = operator ;
        this.value = value ;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
