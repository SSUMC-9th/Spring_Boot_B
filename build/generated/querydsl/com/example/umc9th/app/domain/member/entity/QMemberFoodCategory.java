package com.example.umc9th.app.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberFoodCategory is a Querydsl query type for MemberFoodCategory
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberFoodCategory extends EntityPathBase<MemberFoodCategory> {

    private static final long serialVersionUID = 734642543L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberFoodCategory memberFoodCategory = new QMemberFoodCategory("memberFoodCategory");

    public final com.example.umc9th.infra.entity.QBaseTimeEntity _super = new com.example.umc9th.infra.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final com.example.umc9th.infra.entity.QFoodCategory foodCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberFoodCategory(String variable) {
        this(MemberFoodCategory.class, forVariable(variable), INITS);
    }

    public QMemberFoodCategory(Path<? extends MemberFoodCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberFoodCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberFoodCategory(PathMetadata metadata, PathInits inits) {
        this(MemberFoodCategory.class, metadata, inits);
    }

    public QMemberFoodCategory(Class<? extends MemberFoodCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodCategory = inits.isInitialized("foodCategory") ? new com.example.umc9th.infra.entity.QFoodCategory(forProperty("foodCategory")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

