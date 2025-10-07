package com.example.umc9th.app.domain.member.entity;

import com.example.umc9th.infra.entity.BaseTimeEntity;
import jakarta.persistence.*;

public class MemberTerm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    private Term term;

    @Column(name = "is_agreed", nullable = false)
    private boolean isAgreed;
}
