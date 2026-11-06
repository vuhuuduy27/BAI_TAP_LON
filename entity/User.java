
package entity;

import java.time.LocalDateTime;

    abstract class User {
        private String id;
        private String userName;
        private String password;
        private String email;
        private boolean status;
        private LocalDateTime createdAt;

        protected User(String userName, String password, String email) {
            this.userName = userName;
            this.password = password;
            this.email = email;
            this.status = false;
            this.createdAt = LocalDateTime.now();
        }

        public String getId() { return this.id;}
        public String getUserName() { return this.userName;}
        public String getPassword() { return this.password;}
        public String getEmail() { return this.email;}
        public String getStatus() { return this.status;}

        public void setEmail(String email) {
            this.email = email;
        }
        public void setStatus(boolean status) {
            this.status = status;
        }

        public boolean checkPassword(String raw) {
            if (raw.equals(this.password)) {
                return true;
            }
            return false;
        }

        public boolean changePassword(String old, String now) {
            if (old.equals(this.password)) {
                this.password = now;
                return true;
            }
            return false;
        }

        public abstract Roles getRole();

        public String getDescription() {
            return this.userName + " | Email: " + this.email;
        }

    }
