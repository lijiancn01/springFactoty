package com.ai.free.model;

import java.util.ArrayList;
import java.util.List;

public class TreeEasyUI {
	private long id;
	private String text;
	private String state = "closed";
	private boolean checked = false;
	private TreeAttrubutes attributes;
	private String iconCls;
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	private List<TreeEasyUI> children = new ArrayList<TreeEasyUI>();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public TreeAttrubutes getAttributes() {
		return attributes;
	}
	public void setAttributes(TreeAttrubutes attributes) {
		this.attributes = attributes;
	}
	public List<TreeEasyUI> getChildren() {
		return children;
	}
	public void setChildren(List<TreeEasyUI> children) {
		this.children = children;
	}
	
}
