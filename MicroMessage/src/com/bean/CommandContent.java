package com.bean;

/**
 * 指令内容表对应的实体类
 * @author Kexin_Li
 *
 */
public class CommandContent {

	private String id;
	private String content;
	private String command_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommand_id() {
		return command_id;
	}
	public void setCommand_id(String command_id) {
		this.command_id = command_id;
	}

}
