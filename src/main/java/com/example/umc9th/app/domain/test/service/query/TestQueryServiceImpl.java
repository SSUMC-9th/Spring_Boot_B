package com.example.umc9th.app.domain.test.service.query;

import com.example.umc9th.app.domain.test.exception.TestErrorCode;
import com.example.umc9th.app.domain.test.exception.TestException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService {

    //TestQueryService의 checkFlag 메서드를 오버라이드 flag가 1이면 TestException을 던짐
    @Override
    public void checkFlag(Long flag){
        if (flag == 1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}