package com.example.umc9th.app.domain.home.service;

import com.example.umc9th.app.domain.home.dto.GetHomeResponse;
import com.example.umc9th.app.domain.home.exception.HomeErrorCode;
import com.example.umc9th.app.domain.home.exception.HomeException;
import com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberHomeResponse;
import com.example.umc9th.app.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//실제 요청의 비즈니스 로직이 들어감
//서비스 계층임을 명시
@Service
@RequiredArgsConstructor
public class HomeService {
    //사용할 데이터를 가져옴
    private final MemberService memberService;

    //getHomeResponse를 반환하는 getHome함수를 생성
    public GetHomeResponse getHome(Long memberId, int page, int size) {
        //미션 목록을 반환하기 위해 List로 받아옴
        List<GetAvailableMemberMissionResponse> missions = memberService.getAvailableMemberMission(memberId, page, size).getContent();

        //홈화면에 필요한 회원 정보를 받아옴
        GetMemberHomeResponse member = memberService.getMemberHome(memberId);
        //위 두 개의 결과가 Response 객체로 반환됨
        return new GetHomeResponse(member, missions);
    }

    public void checkFlag(Long flag){
        if (flag == 1){
            throw new HomeException(HomeErrorCode.HOME_EXCEPTION);
        }
    }
}
