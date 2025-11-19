package com.plane.umc9th.domain.member.service.command;

import com.plane.umc9th.domain.catergory.entity.FoodCatergory;
import com.plane.umc9th.domain.catergory.exception.CategoryException;
import com.plane.umc9th.domain.catergory.exception.code.CategoryErrorCode;
import com.plane.umc9th.domain.catergory.repository.CategoryRepository;
import com.plane.umc9th.domain.member.converter.MemberConverter;
import com.plane.umc9th.domain.member.dto.MemberReqDTO;
import com.plane.umc9th.domain.member.dto.MemberResDTO;
import com.plane.umc9th.domain.member.entity.FoodCatergoryLikes;
import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.member.repository.CategoryLikesRepository;
import com.plane.umc9th.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final CategoryLikesRepository categoryLikesRepository;
    private final CategoryRepository categoryRepository;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ){
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            List<FoodCatergoryLikes> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()){

                // 음식 존재 여부 검증
                FoodCatergory category = categoryRepository.findById(id)
                        .orElseThrow(() -> new CategoryException(CategoryErrorCode.NOT_FOUND));

                // MemberFood 엔티티 생성 (컨버터 사용해야 함)
                FoodCatergoryLikes memberFood = FoodCatergoryLikes.builder()
                        .member(member)
                        .foodCatergory(category)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }

            // 모든 선호 음식 추가: DB 적용
            categoryLikesRepository.saveAll(memberFoodList);
        }


        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }
}