package org.aziz.paygent.memberservice.models;


import java.io.Serializable;

public class GroupMemberDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String memberId, groupId;
    public GroupMemberDTO() {
    }
    public GroupMemberDTO(String memberId, String groupId) {
        this.memberId = memberId;
        this.groupId = groupId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
