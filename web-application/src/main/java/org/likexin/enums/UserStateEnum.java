package org.likexin.enums;

public enum UserStateEnum {

	USER_NOT_EXIT(1, "用户不存在"),
	REQUEST_FAIL(2, "请求失败"),
	REQUEST_SUCCESS(3, "请求成功");
	
	private int state;
	
	private String stateInfo;
	
	private UserStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static UserStateEnum stateOf(int index) {
        for (UserStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
