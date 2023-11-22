package com.team.twodari.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -1497518872L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final com.team.twodari.common.entity.QMutableBaseEntity _super = new com.team.twodari.common.entity.QMutableBaseEntity(this);

    //inherited
    public final StringPath createName = _super.createName;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final StringPath deleted = createString("deleted");

    public final StringPath email = createString("email");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    //inherited
    public final StringPath updateName = _super.updateName;

    //inherited
    public final StringPath updateTime = _super.updateTime;

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

