package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Social;

public final class SocialContract {
    public static final String TABLE = "social";
    public static final String ID = "id";
    public static final String USER = "USER";
    public static final String CONTACT_ID = "contact_id";
    public static final String[] COLUNS = {ID, USER, CONTACT_ID};

    private SocialContract() {
        super();
    }

    public static String createTableSocial() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" CREATE TABLE " + TABLE);
        builder.append(" ( ");
        builder.append(ID + " INTEGER PRIMARY KEY, ");
        builder.append(USER + " TEXT NOT NULL, ");
        builder.append(CONTACT_ID + " INTEGER ");
        builder.append(" ); ");

        return builder.toString();
    }

    public static ContentValues getContentValues(Social social) {

        ContentValues values = new ContentValues();
        values.put(SocialContract.ID, social.getId());
        values.put(SocialContract.USER, social.getUser());
        values.put(SocialContract.CONTACT_ID, social.getContact().getId());

        return values;
    }

    public static Social getSocial(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Social social = new Social();
            social.setId(cursor.getLong(cursor.getColumnIndex(SocialContract.ID)));
            social.setUser(cursor.getString(cursor.getColumnIndex(SocialContract.USER)));

            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(SocialContract.CONTACT_ID)));
            social.setContact(contact);

            return social;
        }
        return null;
    }

    public static List<Social> getSocials(Cursor cursor) {
        ArrayList<Social> socials = new ArrayList<>();
        while (cursor.moveToNext()) {
            socials.add(getSocial(cursor));
        }
        return socials;
    }
}
