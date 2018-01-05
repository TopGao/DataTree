package com.jira.r4j;

import java.util.List;

public class R4JIssue {
		
	private	int id;
	private	int issueId;
	private	String key;
	private	String url;
	private	String summary;
	private	String description;
	private	String rootfolderdescription;
	private	String description_html;
	private	String icon_url;
	private	String issueType;
	private	int position;
	private	R4JChildReqs childReqs;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getIssueId() {
			return issueId;
		}
		public void setIssueId(int issueId) {
			this.issueId = issueId;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getRootfolderdescription() {
			return rootfolderdescription;
		}
		public void setRootfolderdescription(String rootfolderdescription) {
			this.rootfolderdescription = rootfolderdescription;
		}
		public String getIcon_url() {
			return icon_url;
		}
		public void setIcon_url(String icon_url) {
			this.icon_url = icon_url;
		}
		public String getIssueType() {
			return issueType;
		}
		public void setIssueType(String issueType) {
			this.issueType = issueType;
		}
		public int getPosition() {
			return position;
		}
		public void setPosition(int position) {
			this.position = position;
		}
		public String getDescription_html() {
			return description_html;
		}
		public void setDescription_html(String description_html) {
			this.description_html = description_html;
		}
		public R4JChildReqs getChildReqs() {
			return childReqs;
		}
		public void setChildReqs(R4JChildReqs childReqs) {
			this.childReqs = childReqs;
		}
		@Override
		public String toString() {
			return "R4JIssue [id=" + id + ", issueId=" + issueId + ", key=" + key + ", url=" + url + ", summary="
					+ summary + ", description=" + description + ", rootfolderdescription=" + rootfolderdescription
					+ ", description_html=" + description_html + ", icon_url=" + icon_url + ", issueType=" + issueType
					+ ", position=" + position + ", childReqs=" + childReqs + "]";
		}
		
}
