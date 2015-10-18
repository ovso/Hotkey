package kr.blogspot.ovsoce.hotkey.fragment;

/**
 * Created by ovso on 2015. 10. 17..
 */
public class ContactsItem {

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    String name;
    String color;

    public ContactsItem(String name, String color) {
        this.name = name; this.color = color;
    }



}
