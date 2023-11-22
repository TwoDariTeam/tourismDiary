package com.team.twodari.point.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointEntity is a Querydsl query type for PointEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointEntity extends EntityPathBase<PointEntity> {

    private static final long serialVersionUID = -1006414280L;

    public static final QPointEntity pointEntity = new QPointEntity("pointEntity");

    public final NumberPath<Long> boardSeq = createNumber("boardSeq", Long.class);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Long> pointSeq = createNumber("pointSeq", Long.class);

    public QPointEntity(String variable) {
        super(PointEntity.class, forVariable(variable));
    }

    public QPointEntity(Path<? extends PointEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointEntity(PathMetadata metadata) {
        super(PointEntity.class, metadata);
    }

}

