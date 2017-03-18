package DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;

import Entidades.Farmacia;

/**
 * Created by bravo3465 on 11/10/15.
 */
public class FarmaciaDal
{
    SQLiteDatabase db;
    DbHelper dbHelper;
    ContentValues values;

    public FarmaciaDal(Context context)
    {
        dbHelper = new DbHelper(context);
    }

    public void salvarNovaFarmacia(Farmacia farmacia)
    {
        try
        {
            db = dbHelper.getWritableDatabase();
            values = new ContentValues();

            values.put("desc_farmacia", farmacia.getDescFarmacia());

            try
            {
                db.insert("FARMACIAS", null, values);
            }
            finally
            {
                db.close();
            }
        }
        catch (Exception ex)
        {
            Log.e("salvarNovaFarmacia: ", ex.getMessage());
        }
    }

    public LinkedList buscarFarmacias()
    {
        db = dbHelper.getReadableDatabase();
        Farmacia farmacia;
        LinkedList listFarmacias = new LinkedList();

        Cursor cursor = db.query("FARMACIAS", null, null, null, null, null, null);

        try
        {
            while (cursor.moveToNext())
            {
                farmacia = new Farmacia();

                farmacia.set_id(Integer.parseInt(cursor.getString(0)));
                farmacia.setDescFarmacia(cursor.getString(1));

                listFarmacias.add(farmacia);
            }
        }
        catch (Exception ex)
        {
            Log.e("buscarFarmacias", ex.getMessage());
        }
        finally
        {
            cursor.close();
        }

        return listFarmacias;
    }
}
