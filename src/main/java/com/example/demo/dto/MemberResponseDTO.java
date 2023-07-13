package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberResponseDTO {
    @JsonProperty(value = "member_id")
    private Long memberId;
    private String email;
    private String password;
    private String nickname;
    @JsonProperty(value = "member_type")
    private String memberType;
    @JsonProperty(value = "created_date")
    private LocalDateTime createdDate;
    @JsonProperty(value = "updated_date")
    private LocalDateTime updatedDate;
}
