package com.wonders.wddc.suite.user.entity;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("SUITE_AUTHORITY_TREE")
public class AuthorityBo {

    public AuthorityBo() {
    }

    public AuthorityBo(String nodeId) {
        this.nodeId = nodeId;
    }

    @Name
    @Column("NODE_ID")
    private String nodeId;
    
    @Column("NODE_CODE")
    private String nodeCode;
    
    @Column("NODE_NAME")
    private String nodeName;

    @Column("NODE_TYPE")
    private String nodeType;

    @Column("NODE_URL")
    private String nodeURL;
    
    @Column("NODE_ORDER")
    private int order;

    @Column("NODE_MEMO")
    private String nodeMemo;

    private String checkFlag;
    

    @ManyMany(target = UserInfoBo.class, relation = "SUITE_USER_AUTHORITY_MAP", from = "NODE_ID", to = "USER_ID")
    private List<UserInfoBo> users;

    @ManyMany(target = RoleBo.class, relation = "SUITE_ROLE_AUTHORITY_MAP", from = "NODE_ID", to = "ROLE_ID")
    private List<RoleBo> roles;

    public List<UserInfoBo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfoBo> users) {
        this.users = users;
    }

    public List<RoleBo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleBo> roles) {
        this.roles = roles;
    }


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }


    public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getNodeMemo() {
		return nodeMemo;
	}

	public void setNodeMemo(String nodeMemo) {
		this.nodeMemo = nodeMemo;
	}

	public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeURL() {
        return nodeURL;
    }

    public void setNodeURL(String nodeURL) {
        this.nodeURL = nodeURL;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuthorityBo other = (AuthorityBo) obj;
        if (nodeId == null) {
            if (other.nodeId != null)
                return false;
        } else if (!nodeId.equals(other.nodeId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Authority [nodeId=" + nodeId + ", nodeName=" + nodeName + "]";
    }

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
    

}
