package com.myservice.web.members;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    public static MemberForm createMemberForm(String username, String loginId, String password) {
        MemberForm form = new MemberForm();
        form.setUsername(username);
        form.setLoginId(loginId);
        form.setPassword(password);
        return form;
    }
}
