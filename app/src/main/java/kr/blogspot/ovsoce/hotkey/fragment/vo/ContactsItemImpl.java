package kr.blogspot.ovsoce.hotkey.fragment.vo;

/**
 * Created by ovso on 2015. 10. 17..
 */
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
    public int getMenuType() {
        return menuType;
    }

    @Override
    public String getId() {
        return id;
    }

    String id,name, number, color;
    int menuType;
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
    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }
//    public ContactsItemImpl(String id, String name, String number, String color, int menuType) {
//        this.name = name;
//        this.color = color;
//        this.number = number;
//        this.menuType = menuType;
//    }

}
