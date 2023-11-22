package com.team.twodari.tag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTagEntity is a Querydsl query type for TagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTagEntity extends EntityPathBase<TagEntity> {

    private static final long serialVersionUID = 487712184L;

    public static final QTagEntity tagEntity = new QTagEntity("tagEntity");

    public final com.team.twodari.common.entity.QMutableBaseEntity _super = new com.team.twodari.common.entity.QMutableBaseEntity(this);

    public final NumberPath<Integer> boardSeq = createNumber("boardSeq", Integer.class);

    //inherited
    public final StringPath createName = _super.createName;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final StringPath tag = createString("tag");

    public final NumberPath<Integer> tagSeq = createNumber("tagSeq", Integer.class);

    //inherited
    public final StringPath updateName = _super.updateName;

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public QTagEntity(String variable) {
        super(TagEntity.class, forVariable(variable));
    }

    public QTagEntity(Path<? extends TagEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTagEntity(PathMetadata metadata) {
        super(TagEntity.class, metadata);
    }

}

