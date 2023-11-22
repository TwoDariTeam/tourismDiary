package com.team.twodari.common.config.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

//@Configuration
public class QuerydslConfig {


    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return jpaQueryFactory(em);
    }

}
