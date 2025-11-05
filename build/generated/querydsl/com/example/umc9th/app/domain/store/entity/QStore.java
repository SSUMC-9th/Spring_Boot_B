package com.example.umc9th.app.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -325184795L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final com.example.umc9th.infra.entity.QBaseTimeEntity _super = new com.example.umc9th.infra.entity.QBaseTimeEntity(this);

    public final com.example.umc9th.infra.entity.QAddress address;

    public final StringPath backgroundImg = createString("backgroundImg");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final com.example.umc9th.infra.entity.QFoodCategory foodCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<com.example.umc9th.app.domain.review.entity.Review, com.example.umc9th.app.domain.review.entity.QReview> reviews = this.<com.example.umc9th.app.domain.review.entity.Review, com.example.umc9th.app.domain.review.entity.QReview>createList("reviews", com.example.umc9th.app.domain.review.entity.Review.class, com.example.umc9th.app.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final ListPath<StoreHours, QStoreHours> storeHoursList = this.<StoreHours, QStoreHours>createList("storeHoursList", StoreHours.class, QStoreHours.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.example.umc9th.infra.entity.QAddress(forProperty("address")) : null;
        this.foodCategory = inits.isInitialized("foodCategory") ? new com.example.umc9th.infra.entity.QFoodCategory(forProperty("foodCategory")) : null;
    }

}

