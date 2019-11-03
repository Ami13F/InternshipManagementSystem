package com.intern.Internship.model.validator;

import com.intern.Internship.model.Candidate;

public class CandidateValidator implements Validator<Candidate> {

    @Override
    public void validate(Candidate entity) {
        String msg="";

        if(entity.getID().equals("")) msg+="Email cannot be empty!";
        if(entity.getPassword().equals("")) msg+="Password cannot be empty!";
        if(entity.getFirstName().equals("")) msg+="First name cannot be empty!";
        if(entity.getLastName().equals("")) msg+="Last name cannot be empty!";
        if(entity.getAddress().equals("")) msg+="Address cannot be empty!";
        if(entity.getTelephone().equals("")) msg+="Telephone cannot be empty!";
        if(entity.getBirthDate()==null) msg+="Birth date cannot be empty!";
        //if(entity.getSex()==null) msg+="Sex cannot be empty!"; !!!sunt unii care nu vor sa precizeze
        if(entity.getCandidateStatus()==null) msg+="Status cannot be empty!";
        if(entity.getAvatar()==null) msg+="Insert an avatar!";
        if(entity.getCVPdf()==null) msg+="Insert CV!";

        if(msg!=""){
            throw new ValidationException(msg);
        }
    }
}