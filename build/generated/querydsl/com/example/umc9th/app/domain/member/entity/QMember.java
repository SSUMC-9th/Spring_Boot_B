package com.example.umc9th.app.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 2107925075L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.example.umc9th.infra.entity.QBaseTimeEntity _super = new com.example.umc9th.infra.entity.QBaseTimeEntity(this);

    public final com.example.umc9th.infra.entity.QAddress address;

    public final ListPath<Alarm, QAlarm> alarms = this.<Alarm, QAlarm>createList("alarms", Alarm.class, QAlarm.class, PathInits.DIRECT2);

    public final EnumPath<com.example.umc9th.app.domain.member.enums.AuthProvider> authProvider = createEnum("authProvider", com.example.umc9th.app.domain.member.enums.AuthProvider.class);

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final BooleanPath eventEnabled = createBoolean("eventEnabled");

    public final EnumPath<com.example.umc9th.app.domain.member.enums.Gender> gender = createEnum("gender", com.example.umc9th.app.domain.member.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final BooleanPath isPhoneNumVerified = createBoolean("isPhoneNumVerified");

    public final ListPath<MemberFoodCategory, QMemberFoodCategory> memberFoodCategories = this.<MemberFoodCategory, QMemberFoodCategory>createList("memberFoodCategories", MemberFoodCategory.class, QMemberFoodCategory.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath phoneNum = createString("phoneNum");

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final BooleanPath qnaEnabled = createBoolean("qnaEnabled");

    public final BooleanPath replyEnabled = createBoolean("replyEnabled");

    public final ListPath<com.example.umc9th.app.domain.review.entity.Review, com.example.umc9th.app.domain.review.entity.QReview> reviews = this.<com.example.umc9th.app.domain.review.entity.Review, com.example.umc9th.app.domain.review.entity.QReview>createList("reviews", com.example.umc9th.app.domain.review.entity.Review.class, com.example.umc9th.app.domain.review.entity.QReview.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.example.umc9th.infra.entity.QAddress(forProperty("address")) : null;
    }

}

