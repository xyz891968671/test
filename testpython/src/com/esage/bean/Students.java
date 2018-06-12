package com.esage.bean;

//public class Students {
//
//}
import java.util.Date;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

//ѧ��  �־û���
public class Students {
    // ��ӵ���Ҫ���ԭ��
    /*
     * 1.���е��� 
     * 2.�ṩ���еĲ����ε�Ĭ�ϵĹ��췽�� 
     * 3.����˽�� 
     * 4.����setter��getter��װ
     */

    private int sid; // ѧ��
    private String sname; // ����
    private String gender; // �Ա�
    private Date birthday; // ��������
    private String address; // ��ַ

    public Students() {

    }

    public Students(int sid, String sname, String gender, Date birthday, String address) {
        this.sid = sid;
        this.sname = sname;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Students [sid=" + sid + ", sname=" + sname + ", gender=" + gender + ", birthday=" + birthday
                + ", address=" + address + "]";
    }

}
