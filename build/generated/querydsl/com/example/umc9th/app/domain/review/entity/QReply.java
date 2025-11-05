package com.example.umc9th.app.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;


/**
 * QReply is a Querydsl query type for Reply
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReply extends EntityPathBase<Reply> {

    private static final long serialVersionUID = 1512871955L;

    public static final QReply reply = new QReply("reply");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QReply(String variable) {
        super(Reply.class, forVariable(variable));
    }

    public QReply(Path<? extends Reply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReply(PathMetadata metadata) {
        super(Reply.class, metadata);
    }

}

