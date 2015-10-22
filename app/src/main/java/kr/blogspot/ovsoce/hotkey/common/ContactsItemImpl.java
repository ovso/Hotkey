package kr.blogspot.ovsoce.hotkey.common;

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

    String name, number, color;

    public ContactsItemImpl(String name, String number, String color) {
        this.name = name;
        this.color = color;
        this.number = number;
    }

}
