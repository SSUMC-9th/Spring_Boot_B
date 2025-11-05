package com.example.umc9th.app.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreHours is a Querydsl query type for StoreHours
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreHours extends EntityPathBase<StoreHours> {

    private static final long serialVersionUID = -2008167638L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreHours storeHours = new QStoreHours("storeHours");

    public final com.example.umc9th.infra.entity.QBaseTimeEntity _super = new com.example.umc9th.infra.entity.QBaseTimeEntity(this);

    public final TimePath<java.time.LocalTime> close = createTime("close", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isClosed = createBoolean("isClosed");

    public final BooleanPath isOvernight = createBoolean("isOvernight");

    public final TimePath<java.time.LocalTime> open = createTime("open", java.time.LocalTime.class);

    public final QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Integer> week = createNumber("week", Integer.class);

    public QStoreHours(String variable) {
        this(StoreHours.class, forVariable(variable), INITS);
    }

    public QStoreHours(Path<? extends StoreHours> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreHours(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreHours(PathMetadata metadata, PathInits inits) {
        this(StoreHours.class, metadata, inits);
    }

    public QStoreHours(Class<? extends StoreHours> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

