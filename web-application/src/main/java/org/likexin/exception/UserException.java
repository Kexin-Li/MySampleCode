package org.likexin.exception;

import org.likexin.enums.UserStateEnum;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int state;
	
	private String stateInfo;
	
	public UserException(String message) {
		super(message);
	}
	
	public UserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UserException(UserStateEnum en) {
	    super();
	    this.state = en.getState();
	    this.stateInfo = en.getStateInfo();
    }

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
}
