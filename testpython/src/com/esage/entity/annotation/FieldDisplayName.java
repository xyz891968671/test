package com.esage.entity.annotation;

//public @interface FieldDisplayName {
//
//}
/************************* ��Ȩ���� *********************************
 *                                                                  *
 *                     ��Ȩ���У��������                           *
 *          Copyright (c) 2010 by www.po-soft.com                *
 *                                                                  *
 ************************* �����¼ *********************************
 *
 * �����ߣ�slx   �������ڣ� 2010-7-1
 * ��ע��
 * 
 * �޸��ߣ�       �޸����ڣ�
 * ��ע��
 * 
 */    

//package com.posoftframework.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �ֶ���ʾ��(������)ע��
 * @author slx
 * @date 2010-7-1 ����08:56:37
 * @version 1.0
 */
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDisplayName {

    String value();
}
