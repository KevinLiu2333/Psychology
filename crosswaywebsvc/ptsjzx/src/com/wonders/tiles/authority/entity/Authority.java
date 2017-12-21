package com.wonders.tiles.authority.entity;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("ua_authority_tree")
public class Authority {

    public Authority() {
    }

    public Authority(String nodeId) {
        this.nodeId = nodeId;
    }

    @Name
    @Column("NODE_ID")
    private String nodeId;

    @Column("NODE_DESC")
    private String nodeDesc;

    @Column("NODE_LEVEL")
    private String nodeLevel;

    @Column("NODE_PID")
    private String nodePid;

    @Column("NODE_PDesc")
    private String nodePDesc;

    @Column("NODE_ORDER")
    private String order;

    @Many(target = Authority.class, field = "nodePid")
    private List<Authority> children;

    @ManyMany(target = User.class, relation = "ua_user_authority_map", from = "node_id", to = "user_id")
    private List<User> users;

    @ManyMany(target = Role.class, relation = "ua_role_authority_map", from = "node_id", to = "role_id")
    private List<Role> roles;

    private String checked;

    @Column("NODE_CODE")
    private String nodeCode;

    @Column("NODE_TYPE")
    private String nodeType;

    @Column("NODE_URL")
    private String nodeURL;

    @Column("NODE_REL")
    private String nodeRel;

    @Column("NODE_EXTERNAL")
    private String nodeExternal;

    public String getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getNodePid() {
        return nodePid;
    }

    public void setNodePid(String nodePid) {
        this.nodePid = nodePid;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public void check() {
        setChecked("checked");
    }

    public List<Authority> getChildren() {
        return children;
    }

    public void setChildren(List<Authority> children) {
        this.children = children;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
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

    public String getNodeRel() {
        return nodeRel;
    }

    public void setNodeRel(String nodeRel) {
        this.nodeRel = nodeRel;
    }

    public String getNodePDesc() {
        return nodePDesc;
    }

    public void setNodePDesc(String nodePDesc) {
        this.nodePDesc = nodePDesc;
    }

    public String getNodeExternal() {
        return nodeExternal;
    }

    public void setNodeExternal(String nodeExternal) {
        this.nodeExternal = nodeExternal;
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
        Authority other = (Authority) obj;
        if (nodeId == null) {
            if (other.nodeId != null)
                return false;
        } else if (!nodeId.equals(other.nodeId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Authority [nodeId=" + nodeId + ", nodeDesc=" + nodeDesc + "]";
    }

}
