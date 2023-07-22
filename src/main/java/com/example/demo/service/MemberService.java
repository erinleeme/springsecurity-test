package com.example.demo.service;


import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.request.MemberDetail;
import com.example.demo.dto.request.MemberLoginDTO;
import com.example.demo.dto.request.MemberRequestDTO;
import com.example.demo.dto.response.JwtResponseDTO;
import com.example.demo.exception.DuplicateCheckException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.IsExistCheckException;
import com.example.demo.jwt.JwtProvider;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createMember(MemberRequestDTO memberRequestDTO) {
        MemberDAO isMember = memberMapper.selectMemberByEmail(memberRequestDTO.getEmail());
        if (isMember != null) { throw new DuplicateCheckException(ErrorCode.IS_EXIST_USER_BY_EMAIL); }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        MemberDAO memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()))
                .nickname(memberRequestDTO.getNickname())
                .memberType(memberRequestDTO.getMemberType())
                .build();

        log.info("MemberDAO : " + memberDAO);

        memberMapper.createMember(memberDAO);
    }

    /*로그인 함수*/
    public JwtResponseDTO login(MemberLoginDTO memberLoginDTO) {
        /*비밀번호 확인*/
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(memberLoginDTO.getEmail());
        if(!bCryptPasswordEncoder.matches(memberLoginDTO.getPassword(), memberDAO.getPassword())) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }
        
        /*JWT 생성*/
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(memberLoginDTO.getEmail(),
                        memberLoginDTO.getPassword())
        );
        String accessToken = jwtProvider.generateAccessToken(authentication);
        
        /*반환 객체*/
        JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                .result("success")
                .accessToken(accessToken)
                .build();
        return jwtResponseDTO;
    }
}
