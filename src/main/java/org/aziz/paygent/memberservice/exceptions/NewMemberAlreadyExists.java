package org.aziz.paygent.memberservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NewMemberAlreadyExists extends RuntimeException {
    public NewMemberAlreadyExists() {
        super("The member already exists in the database.");
    }

}

