package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

public final class ContactRepository {
    private ContactRepository() {
        super();
    }

    public static void save(Contact contact) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = ContactContract.getContentValues(contact);

        if (contact.getId() == null) {
            db.insert(ContactContract.TABLE, null, values);
        } else {
            String where = ContactContract.ID + " = ? ";
            String[] params = {contact.getId().toString()};
            db.update(ContactContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = ContactContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(ContactContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Contact> getAll() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUNS, null, null, null, null, ContactContract.ID);
        List<Contact> contacts = ContactContract.getContacts(cursor);

        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            contacts.add(contact);
        }

        db.close();
        databaseHelper.close();
        return contacts;
    }

    public static Contact getId(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = ContactContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUNS, where, params, null, null, ContactContract.ID);

        Contact contact = ContactContract.getContact(cursor);
        db.close();
        databaseHelper.close();

        return contact;
    }

    public static List<Contact> filter(Contact contact){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = ContactContract.NAME + " = ? ";
        String[] params = {contact.getName()};

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUNS, where, params, null, null, ContactContract.ID);

        List<Contact> contacts = ContactContract.getContacts(cursor);

        db.close();
        databaseHelper.close();
        return contacts;
    }
}
