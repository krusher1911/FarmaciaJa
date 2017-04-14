package DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;

import Entidades.Farmacia;

/**
 * Created by bravo3465 on 10/10/15.
 * edited by Krusher on 13/04/17
 **/
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

            //TABLE FARMACIA
            //int _id;
            //String descFarmacia;
            //String endereco;
            //float mediaTempoEntrega;
            //float mediaNotaAtendimento;
            //String informacoesFarmacia;

            values.put("descFarmacia", farmacia.getDescFarmacia());
            values.put("endereco", farmacia.getEndereco());
            values.put("mediaTempoEntrega", farmacia.getMediaTempoEntrega());
            values.put("mediaNotaAtendimento", farmacia.getMediaNotaAtendimento());
            values.put("informacoesFarmacia", farmacia.getInformacoesFarmacia());

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

    public LinkedList buscarFarmacias() {
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
                farmacia.setMediaTempoEntrega(Float.parseFloat(cursor.getString(3)));
                farmacia.setMediaNotaAtendimento(Float.parseFloat(cursor.getString(4)));

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

    public Farmacia getObjectById(int id) {

        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM FARMACIAS WHERE _id = " + id, null);

        Farmacia farmacia = new Farmacia();

        try {
            while (cursor.moveToNext()) {
                farmacia = new Farmacia();

                farmacia.set_id(Integer.parseInt(cursor.getString(0)));
                farmacia.setDescFarmacia(cursor.getString(1));
                farmacia.setMediaTempoEntrega(Float.parseFloat(cursor.getString(3)));
                farmacia.setMediaNotaAtendimento(Float.parseFloat(cursor.getString(4)));

            }
        } catch (Exception ex) {
            Log.e("buscarFarmacias", ex.getMessage());
        } finally {
            cursor.close();
        }

        return farmacia;
    }

}
