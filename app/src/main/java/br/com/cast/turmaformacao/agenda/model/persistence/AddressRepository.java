package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Address;

public final class AddressRepository {
    private AddressRepository() {
        super();
    }

    public static void save(Address address) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = AddressContract.getContentValues(address);

        if (address.getId() == null) {
            db.insert(AddressContract.TABLE, null, values);
        } else {
            String where = AddressContract.ID + " = ? ";
            String[] params = {address.getId().toString()};
            db.update(AddressContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = AddressContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(AddressContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Address> getAll() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(AddressContract.TABLE, AddressContract.COLUNS, null, null, null, null, AddressContract.ID);
        List<Address> addresses = AddressContract.getAddresses(cursor);

        while (cursor.moveToNext()) {
            Address address = new Address();
            addresses.add(address);
        }

        db.close();
        databaseHelper.close();
        return addresses;
    }
}
