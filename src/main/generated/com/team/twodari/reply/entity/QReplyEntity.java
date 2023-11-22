package com.team.twodari.reply.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReplyEntity is a Querydsl query type for ReplyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReplyEntity extends EntityPathBase<ReplyEntity> {

    private static final long serialVersionUID = -700667784L;

    public static final QReplyEntity replyEntity = new QReplyEntity("replyEntity");

    public final com.team.twodari.common.entity.QMutableBaseEntity _super = new com.team.twodari.common.entity.QMutableBaseEntity(this);

    public final StringPath author = createString("author");

    public final NumberPath<Long> boardSeq = createNumber("boardSeq", Long.class);

    public final StringPath contents = createString("contents");

    //inherited
    public final StringPath createName = _super.createName;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final StringPath deleted = createString("deleted");

    public final NumberPath<Long> replySeq = createNumber("replySeq", Long.class);

    //inherited
    public final StringPath updateName = _super.updateName;

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public QReplyEntity(String variable) {
        super(ReplyEntity.class, forVariable(variable));
    }

    public QReplyEntity(Path<? extends ReplyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReplyEntity(PathMetadata metadata) {
        super(ReplyEntity.class, metadata);
    }

}

