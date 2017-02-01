package com.timejh.runtimepermission;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by tokijh on 2017. 2. 1..
 */

public class ContactManager {

    private Context context;
    private ArrayList<ContactData> datas = new ArrayList<>();

    public ContactManager(Context context) {
        this.context = context;
        getUserContactsList();
    }

    public ArrayList<ContactData> getContactDataList() {
        return datas;
    }

    private void getUserContactsList() {
        String[] arrProjection = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
        String[] arrPhoneProjection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor clsCursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, arrProjection, null, null, null);

        while (clsCursor.moveToNext()) {
            String contactId = clsCursor.getString(0);
            String contactName = clsCursor.getString(1);
            ArrayList<String> contactNums = new ArrayList<>();

            Cursor clsPhoneCursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrPhoneProjection,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null, null
            );

            while (clsPhoneCursor.moveToNext()) {
                contactNums.add(clsPhoneCursor.getString(0));
            }
            clsPhoneCursor.close();

            datas.add(new ContactData(Integer.parseInt(contactId), contactName, contactNums));
        }

        clsCursor.close();
    }
}