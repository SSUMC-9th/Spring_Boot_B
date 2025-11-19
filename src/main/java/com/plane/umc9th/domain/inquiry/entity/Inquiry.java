package com.plane.umc9th.domain.inquiry.entity;

import com.plane.umc9th.domain.inquiry.enums.InquiryType;
import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Table(name="inquiry")
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "inquiry", fetch = FetchType.LAZY)
    private List<InquiryImage> images;

    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private InquiryType type;
    private String reply;
}
