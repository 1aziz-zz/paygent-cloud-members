package org.aziz.paygent.memberservice.datasource;

import org.aziz.paygent.memberservice.models.entities.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MemberRepo extends CrudRepository<Member, String> {
}

