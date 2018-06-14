package org.aziz.paygent.memberservice.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.aziz.paygent.memberservice.exceptions.MemberNotFoundException;
import org.aziz.paygent.memberservice.exceptions.NewMemberAlreadyExists;
import org.aziz.paygent.memberservice.models.GroupMemberDTO;
import org.aziz.paygent.memberservice.models.entities.Member;
import org.aziz.paygent.memberservice.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public Iterable<Member> getAll() {
        return memberService.getAll();
    }

    @GetMapping(path = "{id}")
    public Member get(@PathVariable(value = "id") String id) {
        return memberService.get(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    @PostMapping
    public Member create(@RequestBody @Valid Member member) throws JsonProcessingException {
        if (!memberService.memberExistsByEmail(member.getEmail())) {
            return memberService.save(member);
        } else {
            throw new NewMemberAlreadyExists();
        }
    }

    @PostMapping("{memberId}/group/{groupId}")
    public Member addGroupToMember(@PathVariable(value = "memberId") String memberId, @PathVariable(value = "groupId") String groupId) throws JsonProcessingException {
        Member member = memberService.validatedMember(memberId);
        member.getGroups().add(groupId);
        memberService.addMemberToGroup(new GroupMemberDTO(memberId, groupId));
        return memberService.update(member);
    }

    @PutMapping("{id}")
    public Member update(@PathVariable(value = "id") String id, @RequestBody Member memberDetails) {
        Member member = memberService.validatedMember(id);
        member.setEmail(memberDetails.getEmail());
        member.setPassword(memberDetails.getPassword());
        return memberService.update(member);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") String id) {
        memberService.delete(id);
    }

}
