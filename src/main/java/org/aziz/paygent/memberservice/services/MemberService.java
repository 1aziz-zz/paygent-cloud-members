package org.aziz.paygent.memberservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aziz.paygent.memberservice.datasource.MemberRepo;
import org.aziz.paygent.memberservice.exceptions.MemberNotFoundException;
import org.aziz.paygent.memberservice.models.GroupMemberDTO;
import org.aziz.paygent.memberservice.models.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService extends GenericAbstractService<Member, MemberRepo> {

    @Autowired
    private JmsTemplate jmsTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void addMemberToGroup(final GroupMemberDTO groupMemberDTO) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(groupMemberDTO);
        jmsTemplate.convertAndSend("addMemberToGroup", message);
        System.out.println("MEMBER-SERVICE: sent: <" + message + ">");
    }

    public void deleteMemberFromGroup(GroupMemberDTO message) {
        jmsTemplate.convertAndSend("deleteMemberFromGroup", message);
        System.out.println("MEMBER-SERVICE: send: <" + message + ">");
    }

    public Member validatedMember(String id) {
        Optional<Member> group = get(id);
        group.orElseThrow(
                () -> new MemberNotFoundException(id));
        return group.get();
    }

    public boolean memberExistsByEmail(String email) {
        for (Member member : this.getAll()) {
            if (member.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
