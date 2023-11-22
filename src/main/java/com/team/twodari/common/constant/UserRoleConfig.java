package com.team.twodari.common.constant;

public class UserRoleConfig {
    public enum UserRole {
        BLOCK(1),GUEST(2), USER(7), ADMIN(9);
        private Integer level;

        UserRole(Integer level) {
            this.level = level;
        }

        public Integer getLevel() {
            return this.level;
        }

    }
}
