package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Social;

public final class SocialRepository {
    private SocialRepository() {
        super();
    }

    public static void save(Social social) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = SocialContract.getContentValues(social);

        if (social.getId() == null) {
            db.insert(SocialContract.TABLE, null, values);
        } else {
            String where = SocialContract.ID + " = ? ";
            String[] params = {social.getId().toString()};
            db.update(SocialContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = SocialContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(SocialContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Social> getAll() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(SocialContract.TABLE, SocialContract.COLUNS, null, null, null, null, SocialContract.ID);
        List<Social> values = SocialContract.getSocials(cursor);

        while (cursor.moveToNext()) {
            Social social = new Social();
            values.add(social);
        }

        db.close();
        databaseHelper.close();
        return values;
    }
}
