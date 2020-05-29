package departments;

public class Page {
    private String link;
    private String formSelector;
    private String departmentSelector;

    public Page(String link, String formSelector, String departmentSelector) {
        this.link = link;
        this.formSelector = formSelector;
        this.departmentSelector = departmentSelector;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFormSelector() {
        return formSelector;
    }

    public void setFormSelector(String formSelector) {
        this.formSelector = formSelector;
    }

    public String getDepartmentSelector() {
        return departmentSelector;
    }

    public void setDepartmentSelector(String departmentSelector) {
        this.departmentSelector = departmentSelector;
    }
}
