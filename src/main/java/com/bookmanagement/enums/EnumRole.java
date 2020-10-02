package com.bookmanagement.enums;

    public enum EnumRole {
        ADMIN("ROLE_ADMIN"),
        COSTUMER("ROLE_COSTUMER");

        private String value;

        private EnumRole(String role) {
            value = role;
        }

        public String getValue() {
            return value;
        }
    }
