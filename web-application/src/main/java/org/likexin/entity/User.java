package org.likexin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user")
public class User extends jef.database.DataObject {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int userId;
	
	@Column(name = "username", precision = 32, columnDefinition = "varchar", nullable = false)
	private String username;
	
	@Column(name = "password", precision = 255, columnDefinition = "varchar", nullable = false)
	private String password;
	
	@Column(name = "userEmail", precision = 255, columnDefinition = "varchar", nullable = false)
	private String userEmail;
	
	@Column(name = "userPhone", precision = 255, columnDefinition = "varchar", nullable = false)
	private String userPhone;
	
	@Column(name = "createTime", columnDefinition = "timestamp", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public enum Field implements jef.database.Field {
		userId, username, password, userEmail, userPhone, createTime
    }
	
	@Override
	 public String toString() {
		 return "User {" + 
				 "id = " + userId + 
				 ", username = " + username + 
				 ", password = " + password +
				 ", userEmail = " + userEmail + 
				 ", userPhone = " + userPhone +
				 ", createTime = " + createTime + 
				 "}";
	 }

}
