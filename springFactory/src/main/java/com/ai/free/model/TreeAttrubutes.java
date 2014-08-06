package com.ai.free.model;

public class TreeAttrubutes {
	private String url;
	private long parentMenuId;
	public TreeAttrubutes() {
		super();
	}
	public TreeAttrubutes(String url, long parentMenuId) {
		super();
		this.url = url;
		this.parentMenuId = parentMenuId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
}
