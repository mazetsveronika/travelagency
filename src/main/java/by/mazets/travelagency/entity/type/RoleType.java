package by.mazets.travelagency.entity.type;

public enum  RoleType {

    CLIENT(1), ADMIN(2);

    private int id;

    private RoleType(int id) {
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
            roleType = CLIENT;
        } else if (id == 2) {
            roleType = ADMIN;
        }
        return roleType;
    }

}