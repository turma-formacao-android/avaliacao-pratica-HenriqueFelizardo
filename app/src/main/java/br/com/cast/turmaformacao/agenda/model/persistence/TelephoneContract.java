package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Social;
import br.com.cast.turmaformacao.agenda.model.entities.Telephone;

public final class TelephoneContract {
    public static final String TABLE = "telephone";
    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String CONTACT_ID = "contact_id";
    public static final String[] COLUNS = {ID, NUMBER, CONTACT_ID};

    private TelephoneContract() {
        super();
    }

    public static String createTableTelephone() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" CREATE TABLE " + TABLE);
        builder.append(" ( ");
        builder.append(ID + " INTEGER PRIMARY KEY, ");
        builder.append(NUMBER + " TEXT NOT NULL, ");
        builder.append(CONTACT_ID + " INTEGER ");
        builder.append(" ); ");

        return builder.toString();
    }

    public static ContentValues getContentValues(Telephone telephone) {

        ContentValues values = new ContentValues();
        values.put(TelephoneContract.ID, telephone.getId());
        values.put(TelephoneContract.NUMBER, telephone.getNumber());
        values.put(TelephoneContract.CONTACT_ID, telephone.getContact().getId());

        return values;
    }

    public static Telephone getTelephone(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Telephone telephone = new Telephone();
            telephone.setId(cursor.getLong(cursor.getColumnIndex(TelephoneContract.ID)));
            telephone.setNumber(cursor.getString(cursor.getColumnIndex(TelephoneContract.NUMBER)));

            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(TelephoneContract.CONTACT_ID)));
            telephone.setContact(contact);

            return telephone;
        }
        return null;
    }

    public static List<Telephone> getTelephones(Cursor cursor) {
        ArrayList<Telephone> telephones = new ArrayList<>();
        while (cursor.moveToNext()) {
            telephones.add(getTelephone(cursor));
        }
        return telephones;
    }
}
