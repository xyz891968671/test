package com.test;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String alarmMessage="MACHINE:192.168.1.180 's RAMRATE ��ǰֵΪ44.15,�ѳ���Ԥ����ֵ,�뼰ʱ���д���";
		alarmMessage=alarmMessage.replace("VIRTUALMACHINE", "�����").replace("MACHINE", "����").replace("CPURATE", "CPU������").replace("RAMRATE", "�ڴ�������").replace("DISKRATE", "����������").replace(" 's ", "��").trim();
		System.out.println(alarmMessage);
	}

}
