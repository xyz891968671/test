package com.esage.dao;

import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.classic.Session;

import com.esage.util.HibernateUtils;
import com.esage.util.Pager;

public class UserDaoImpl {
	public Pager pagerff(Pager p, Map<String, Object> pram) throws IllegalStateException, SystemException {
        Session session = null;
        Transaction tx = null;
        try {
            session=HibernateUtils.getSessionFactory().openSession();
            tx=(Transaction) session.beginTransaction();
            String hql=p.getHql();//��ȡ��ѯ���
            Query query= (Query) session.createQuery(hql).setCacheable(true);
            //���ò���
            ((org.hibernate.Query) query).setProperties(pram);
            //��ѯ��������
            int count=((org.hibernate.Query) query).list().size();
            p.setRowsTotal(count);
            int nowPage=1;
            if(p.getPage()>0){
                nowPage=p.getPage();
            }
            //ָ�����Ǹ�����ʼ��ѯ������������λ���Ǵ�0��ʼ�ģ�
            ((org.hibernate.Query) query).setFirstResult((p.getPage()-1)*p.getRows());
            //��ҳʱ��һ������Ѱ�Ķ�����
            ((org.hibernate.Query) query).setMaxResults(p.getRows());
            List<?> list1=((org.hibernate.Query) query).list();        
            p.setList(list1);
            tx.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.close();
        }
        return  p;
    }
}
