package kr.blogspot.ovsoce.hotkey.fragment;

import java.util.List;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

public interface FragmentPresenter{
    void onActivityCreated(int menuId);
    void onItemAlertDialogOkClick(String itemId);
    interface View {
        void setRecyclerView(List<ContactsItem> contactsItemList);
        void showItemSetDialog(ContactsItem item);
        void makeCall(String phoneNumber);
        void updateRecyclerViewItem(ContactsItem item);
    }
}
