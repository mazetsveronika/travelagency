package by.mazets.travelagency.entity.type;


    public enum RoleType {

         ADMIN(1), CLIENT(2) ;

        private int id;

        RoleType(int id) {
            this.id = id;
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static RoleType getValue(int id) {
            RoleType roleType = null;
            if (id == 1) {
                roleType = ADMIN;
            } else if (id == 2) {
                roleType = CLIENT;
            }
            return roleType;
        }

    }
