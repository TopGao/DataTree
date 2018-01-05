package com.jira.r4j;

import java.util.List;

public class R4JChildReqs {

	private List<R4JChildReq> childReq;

	public List<R4JChildReq> getChildReq() {
		return childReq;
	}

	public void setChildReq(List<R4JChildReq> childReq) {
		this.childReq = childReq;
	}

	@Override
	public String toString() {
		return "R4JChildReqs [childReq=" + childReq + "]";
	}
	
}
