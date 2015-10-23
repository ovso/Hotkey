package kr.blogspot.ovsoce.hotkey.fragment;

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

    @Override
    public String getId() {
        return id;
    }

    String id,name, number, color;

    public ContactsItemImpl(String id, String name, String number, String color) {
        this.name = name;
        this.color = color;
        this.number = number;
    }

}