package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

public final class ContactContract {
    public static final String TABLE = "contact";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ZIPCODE = "zipcode";
    public static final String STREET = "street";
    public static final String STATE = "state";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String[] COLUNS = {TABLE, ID, NAME, ZIPCODE, STREET, STATE, NEIGHBORHOOD, CITY};

    private ContactContract() {
        super();
    }

    public static String createTableContact() {
        final StringBuilder builder = new StringBuilder();

        builder.append(" CREATE TABLE " + TABLE);
        builder.append(" ( ");
        builder.append(ID + " INTEGER PRIMARY KEY, ");
        builder.append(NAME + " TEXT NOT NULL, ");
        builder.append(ZIPCODE + " TEXT NOT NULL, ");
        builder.append(STREET + " TEXT NOT NULL, ");
        builder.append(STATE + " TEXT NOT NULL, ");
        builder.append(NEIGHBORHOOD + " TEXT NOT NULL, ");
        builder.append(CITY + " TEXT NOT NULL ");
        builder.append(" ); ");

        return builder.toString();
    }

    public static ContentValues getContentValues(Contact contact) {
        ContentValues values = new ContentValues();

        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getName());
        values.put(ContactContract.ZIPCODE, contact.getAddress().getZipCode());
        values.put(ContactContract.STREET, contact.getAddress().getStreet());
        values.put(ContactContract.STATE, contact.getAddress().getState());
        values.put(ContactContract.NEIGHBORHOOD, contact.getAddress().getNeighborhood());
        values.put(ContactContract.CITY, contact.getAddress().getCity());

        return values;
    }

    public static Contact getContact(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactContract.ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));
            contact.getAddress().setZipCode(cursor.getString(cursor.getColumnIndex(ContactContract.ZIPCODE)));
            contact.getAddress().setStreet(cursor.getString(cursor.getColumnIndex(ContactContract.STREET)));
            contact.getAddress().setState(cursor.getString(cursor.getColumnIndex(ContactContract.STATE)));
            contact.getAddress().setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactContract.NEIGHBORHOOD)));
            contact.getAddress().setCity(cursor.getString(cursor.getColumnIndex(ContactContract.CITY)));

            return contact;
        }
        return null;
    }

    public static List<Contact> getContacts(Cursor cursor) {
        List<Contact> contacts = new ArrayList<>();

        while (cursor.moveToNext()) {
            contacts.add(getContact(cursor));
        }

        return contacts;
    }
}
