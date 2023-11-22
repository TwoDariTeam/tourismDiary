package com.team.twodari.image.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageEntity is a Querydsl query type for ImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageEntity extends EntityPathBase<ImageEntity> {

    private static final long serialVersionUID = -1625280488L;

    public static final QImageEntity imageEntity = new QImageEntity("imageEntity");

    public final com.team.twodari.common.entity.QMutableBaseEntity _super = new com.team.twodari.common.entity.QMutableBaseEntity(this);

    //inherited
    public final StringPath createName = _super.createName;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final StringPath deleted = createString("deleted");

    public final NumberPath<Integer> imageSeq = createNumber("imageSeq", Integer.class);

    public final StringPath path = createString("path");

    public final NumberPath<Integer> subBoardSeq = createNumber("subBoardSeq", Integer.class);

    //inherited
    public final StringPath updateName = _super.updateName;

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public QImageEntity(String variable) {
        super(ImageEntity.class, forVariable(variable));
    }

    public QImageEntity(Path<? extends ImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageEntity(PathMetadata metadata) {
        super(ImageEntity.class, metadata);
    }

}

