package com.jira.r4j;

import java.util.List;

public class R4JFolder {
	private String name;
	private String description;
	private int id;
	private String name_display;
	private int parent;
	private int position;
	private List<R4JFolder> folders;
	private List<R4JIssue>  issues;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_display() {
		return name_display;
	}
	public void setName_display(String name_display) {
		this.name_display = name_display;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public List<R4JFolder> getFolders() {
		return folders;
	}
	public void setFolders(List<R4JFolder> folders) {
		this.folders = folders;
	}
	public List<R4JIssue> getIssues() {
		return issues;
	}
	public void setIssues(List<R4JIssue> issues) {
		this.issues = issues;
	}
	
	
	@Override
	public String toString() {
		return "R4JFolder [name=" + name + ", description=" + description + ", id=" + id + ", name_display="
				+ name_display + ", parent=" + parent + ", position=" + position + ", folders=" + folders + ", issues="
				+ issues + "]";
	}
	
	
}
