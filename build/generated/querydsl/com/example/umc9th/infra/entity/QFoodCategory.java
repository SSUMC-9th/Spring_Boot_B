package com.example.umc9th.infra.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodCategory is a Querydsl query type for FoodCategory
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodCategory extends EntityPathBase<FoodCategory> {

    private static final long serialVersionUID = 1074189646L;

    public static final QFoodCategory foodCategory = new QFoodCategory("foodCategory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.umc9th.app.domain.member.entity.MemberFoodCategory, com.example.umc9th.app.domain.member.entity.QMemberFoodCategory> memberFoodCategoryList = this.<com.example.umc9th.app.domain.member.entity.MemberFoodCategory, com.example.umc9th.app.domain.member.entity.QMemberFoodCategory>createList("memberFoodCategoryList", com.example.umc9th.app.domain.member.entity.MemberFoodCategory.class, com.example.umc9th.app.domain.member.entity.QMemberFoodCategory.class, PathInits.DIRECT2);

    public final EnumPath<com.example.umc9th.infra.enums.FoodCategoryName> name = createEnum("name", com.example.umc9th.infra.enums.FoodCategoryName.class);

    public final ListPath<com.example.umc9th.app.domain.store.entity.Store, com.example.umc9th.app.domain.store.entity.QStore> stores = this.<com.example.umc9th.app.domain.store.entity.Store, com.example.umc9th.app.domain.store.entity.QStore>createList("stores", com.example.umc9th.app.domain.store.entity.Store.class, com.example.umc9th.app.domain.store.entity.QStore.class, PathInits.DIRECT2);

    public QFoodCategory(String variable) {
        super(FoodCategory.class, forVariable(variable));
    }

    public QFoodCategory(Path<? extends FoodCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodCategory(PathMetadata metadata) {
        super(FoodCategory.class, metadata);
    }

}

