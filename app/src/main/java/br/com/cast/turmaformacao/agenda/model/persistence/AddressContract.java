package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Address;

public final class AddressContract {
    public static final String TABLE = "addres";
    public static final String ID = "id";
    public static final String ZIPCODE = "zipcode";
    public static final String STREET = "street";
    public static final String STATE = "state";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String[] COLUNS = {TABLE, ID, ZIPCODE, STREET, STATE, NEIGHBORHOOD, CITY};

    private AddressContract() {
        super();
    }

    public static String createTableAddres() {
        final StringBuilder builder = new StringBuilder();

        builder.append(" CREATE TABLE " + TABLE);
        builder.append(" ( ");
        builder.append(ID + " INTEGER PRIMARY KEY, ");
        builder.append(ZIPCODE + " TEXT NOT NULL, ");
        builder.append(STREET + " TEXT NOT NULL, ");
        builder.append(STATE + " TEXT NOT NULL, ");
        builder.append(NEIGHBORHOOD + " TEXT NOT NULL, ");
        builder.append(CITY + " TEXT NOT NULL ");
        builder.append(" ); ");

        return builder.toString();
    }

    public static ContentValues getContentValues(Address address) {
        ContentValues values = new ContentValues();

        values.put(AddressContract.ID, address.getId());
        values.put(AddressContract.ZIPCODE, address.getZipCode());
        values.put(AddressContract.STREET, address.getStreet());
        values.put(AddressContract.STATE, address.getState());
        values.put(AddressContract.NEIGHBORHOOD, address.getNeighborhood());
        values.put(AddressContract.CITY, address.getCity());

        return values;
    }

    public static Address getAddress(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Address address = new Address();
            address.setId(cursor.getLong(cursor.getColumnIndex(AddressContract.ID)));
            address.setZipCode(cursor.getString(cursor.getColumnIndex(AddressContract.ZIPCODE)));
            address.setStreet(cursor.getString(cursor.getColumnIndex(AddressContract.STREET)));
            address.setState(cursor.getString(cursor.getColumnIndex(AddressContract.STATE)));
            address.setNeighborhood(cursor.getString(cursor.getColumnIndex(AddressContract.NEIGHBORHOOD)));
            address.setCity(cursor.getString(cursor.getColumnIndex(AddressContract.CITY)));

            return address;
        }
        return null;
    }

    public static List<Address> getAddresses(Cursor cursor) {
        List<Address> addresses = new ArrayList<>();

        while (cursor.moveToNext()) {
            addresses.add(getAddress(cursor));
        }

        return addresses;
    }
}
