package by.mazets.travelagency.command;

public class Router {
    public enum RouterType {

        FORWARD,

        REDIRECT
    }

    private String page = PagePath.INDEX_PAGE;
    private RouterType type = RouterType.FORWARD;


    public Router(String page, RouterType type) {
        if (page != null) {
            this.page = page;
        }
        if (type != null) {
            this.type = type;
        }
    }


    public String getPage() {
        return page;
    }


    public RouterType getType() {
        return type;
    }
}

