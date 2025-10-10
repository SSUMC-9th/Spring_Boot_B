package com.example.umc9th.app.domain.member.entity;


import com.example.umc9th.app.domain.member.enums.QnaStatus;
import com.example.umc9th.app.domain.member.enums.QnaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "qna")
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(length = 50)
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QnaStatus status  = QnaStatus.UPLOADED;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QnaType type;


}
