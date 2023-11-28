package com.team.twodari.common.constant;

public class UserRoleConfig {
    public enum UserRole {
        BLOCK(1L),GUEST(2L), USER(7L), ADMIN(9L);
        private Long level;

        UserRole(Long level) {
            this.level = level;
        }

        public Long getLevel() {
            return this.level;
        }

    }
}
