package com.esage.test20180605;

import java.io.Serializable;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "���ֲ���Ϊ��")
	private String name;

	@Size(min = 6, max = 30, message = "��ַӦ����6-30�ַ�֮��")
	private String address;

	@DecimalMax(value = "100.00", message = " weight  ������Щ����Ŷ")
	@DecimalMin(value = "60.00", message = "weight  ��Ե㷹��")
	private BigDecimal weight;

	private String friendName;

	@AssertTrue
	private Boolean isHaveFriend() {
		return friendName != null ? true : false;
	}

	@Future(message = "birthday ���ձ����ڵ�ǰʵ��֮ǰ")
	private Date birthday;

	@Pattern(regexp = "^(.+)@(.+)$", message = "email  ����ĸ�ʽ���Ϸ�")
	private String email;

	@CheckCase(value=CaseMode.LOWER,message="���ֵ�ƴ��"
			+ "ҪСд")
	private String spellName;
	
	
	
	
	
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + ", weight=" + weight + ", friendName=" + friendName
				+ ", birthday=" + birthday + ", email=" + email + "]";
	}

	
	
	

}
