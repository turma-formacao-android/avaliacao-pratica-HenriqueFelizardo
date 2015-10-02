package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Email;

public final class EmailContract {
    public static final String TABLE = "email";
    public static final String ID = "id";
    public static final String ENDERECO_EMAIL = "endereco_email";
    public static final String CONTACT_ID = "contact_id";
    public static final String[] COLUNS = {ID, ENDERECO_EMAIL, CONTACT_ID};

    private EmailContract() {
        super();
    }

    public static String createTableEmail() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" CREATE TABLE " + TABLE);
        builder.append(" ( ");
        builder.append(ID + " INTEGER PRIMARY KEY, ");
        builder.append(ENDERECO_EMAIL + " TEXT NOT NULL, ");
        builder.append(CONTACT_ID + " INTEGER ");
        builder.append(" ); ");

        return builder.toString();
    }

    public static ContentValues getContentValues(Email email) {

        ContentValues values = new ContentValues();
        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.ENDERECO_EMAIL, email.getEndereco_email());
        values.put(EmailContract.CONTACT_ID, email.getContact().getId());

        return values;
    }

    public static Email getEmail(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Email email = new Email();
            email.setId(cursor.getLong(cursor.getColumnIndex(EmailContract.ID)));
            email.setEndereco_email(cursor.getString(cursor.getColumnIndex(EmailContract.ENDERECO_EMAIL)));

            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex(EmailContract.CONTACT_ID)));
            email.setContact(contact);

            return email;
        }
        return null;
    }

    public static List<Email> getEmails(Cursor cursor) {
        ArrayList<Email> emails = new ArrayList<>();
        while (cursor.moveToNext()) {
            emails.add(getEmail(cursor));
        }
        return emails;
    }

}
