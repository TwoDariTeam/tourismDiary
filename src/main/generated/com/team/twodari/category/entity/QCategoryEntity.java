package com.team.twodari.category.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryEntity is a Querydsl query type for CategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryEntity extends EntityPathBase<CategoryEntity> {

    private static final long serialVersionUID = 2025799374L;

    public static final QCategoryEntity categoryEntity = new QCategoryEntity("categoryEntity");

    public final com.team.twodari.common.entity.QMutableBaseEntity _super = new com.team.twodari.common.entity.QMutableBaseEntity(this);

    public final NumberPath<Integer> accessRole = createNumber("accessRole", Integer.class);

    public final StringPath category = createString("category");

    public final NumberPath<Long> categorySeq = createNumber("categorySeq", Long.class);

    //inherited
    public final StringPath createName = _super.createName;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    //inherited
    public final StringPath updateName = _super.updateName;

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public QCategoryEntity(String variable) {
        super(CategoryEntity.class, forVariable(variable));
    }

    public QCategoryEntity(Path<? extends CategoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryEntity(PathMetadata metadata) {
        super(CategoryEntity.class, metadata);
    }

}

