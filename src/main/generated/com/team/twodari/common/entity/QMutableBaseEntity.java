package com.team.twodari.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMutableBaseEntity is a Querydsl query type for MutableBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QMutableBaseEntity extends EntityPathBase<MutableBaseEntity> {

    private static final long serialVersionUID = -1651286624L;

    public static final QMutableBaseEntity mutableBaseEntity = new QMutableBaseEntity("mutableBaseEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createName = _super.createName;

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    //inherited
    public final StringPath updateName = _super.updateName;

    public final StringPath updateTime = createString("updateTime");

    public QMutableBaseEntity(String variable) {
        super(MutableBaseEntity.class, forVariable(variable));
    }

    public QMutableBaseEntity(Path<? extends MutableBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMutableBaseEntity(PathMetadata metadata) {
        super(MutableBaseEntity.class, metadata);
    }

}

