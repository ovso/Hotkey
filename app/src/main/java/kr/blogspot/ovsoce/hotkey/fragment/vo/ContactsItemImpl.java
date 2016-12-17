package kr.blogspot.ovsoce.hotkey.fragment.vo;

public class ContactsItemImpl implements ContactsItem {

    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getColor() {
        return color;
    }
    public int getTabPosition() {
        return tabPosition;
    }

    @Override
    public String getId() {
        return id;
    }

    String id,name, number, color;
    int tabPosition;
    public void setId(String id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setTabPosition(int tabPosition) {
        this.tabPosition = tabPosition;
    }
}
