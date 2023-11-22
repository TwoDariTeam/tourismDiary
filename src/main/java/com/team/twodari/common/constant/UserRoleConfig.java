package com.team.twodari.common.constant;

public class UserRoleConfig {
    public enum UserRole {
        GUEST(1), USER(2), ADMIN(3);
        private Integer level;

        UserRole(Integer level) {
            this.level = level;
        }

        public Integer getLevel() {
            return this.level;
        }

    }
}
