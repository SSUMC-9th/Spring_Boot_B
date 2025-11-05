package com.example.umc9th.app.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;


/**
 * QQna is a Querydsl query type for Qna
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQna extends EntityPathBase<Qna> {

    private static final long serialVersionUID = 321429707L;

    public static final QQna qna = new QQna("qna");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final EnumPath<com.example.umc9th.app.domain.member.enums.QnaStatus> status = createEnum("status", com.example.umc9th.app.domain.member.enums.QnaStatus.class);

    public final StringPath title = createString("title");

    public final EnumPath<com.example.umc9th.app.domain.member.enums.QnaType> type = createEnum("type", com.example.umc9th.app.domain.member.enums.QnaType.class);

    public QQna(String variable) {
        super(Qna.class, forVariable(variable));
    }

    public QQna(Path<? extends Qna> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQna(PathMetadata metadata) {
        super(Qna.class, metadata);
    }

}

