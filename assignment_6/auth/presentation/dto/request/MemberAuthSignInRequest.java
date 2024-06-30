package org.sopt.sopt.auth.presentation.dto.request;

public record MemberAuthSignInRequest(
    String userId,
    String password
) {

}
