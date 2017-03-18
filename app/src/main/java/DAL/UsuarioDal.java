package DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;

import Entidades.Farmacia;
import Entidades.Usuario;

/**
 * Created by bravo3465 on 25/10/15.
 */
public class UsuarioDal {

    SQLiteDatabase db;
    DbHelper dbHelper;
    ContentValues values;

    public UsuarioDal(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void salvarNovoUsuario(Usuario usuario) {
        try {
            db = dbHelper.getWritableDatabase();
            values = new ContentValues();

            values.put("nome_usuario", usuario.getNome());
            values.put("email_usuario", usuario.getEmail());
            values.put("telefone_usuario", usuario.getTelefone());
            values.put("usuario", usuario.getUsuario());
            values.put("senha", usuario.getSenha());
            values.put("logado", usuario.getLogado());
            values.put("logar_auto", usuario.getLogarAuto());

            try {
                db.insert("USUARIO", null, values);
            } finally {
                db.close();
            }
        } catch (Exception ex) {
            Log.e("salvarNovoUsuario: ", ex.getMessage());
        }
    }

    public Usuario buscarUsuario() {
        db = dbHelper.getReadableDatabase();
        Usuario usuario = new Usuario();

        Cursor cursor = db.query("USUARIO", null, null, null, null, null, null);

        try {
            while (cursor.moveToNext()) {

                if (cursor.getColumnCount() > 0) {
                    usuario.set_id(Integer.parseInt(cursor.getString(0)));
                    usuario.setNome(cursor.getString(1));
                    usuario.setEmail(cursor.getString(2));
                    usuario.setTelefone(cursor.getString(3));
                    usuario.setUsuario(cursor.getString(4));
                    usuario.setSenha(cursor.getString(5));
                    usuario.setLogado(Integer.parseInt(cursor.getString(6)));
                    usuario.setLogarAuto(Integer.parseInt(cursor.getString(7)));
                }
            }
        } catch (Exception ex) {
            Log.e("buscarFarmacias", ex.getMessage());
        } finally {
            cursor.close();
        }

        return usuario;

    }

    public void alterarLogarAutomaticamente() {

    }

    public void atualizar(Usuario usuario)
    {
        try
        {
            db = dbHelper.getWritableDatabase();
            values = new ContentValues();

            values.put("nome_usuario", usuario.getNome());
            values.put("email_usuario", usuario.getEmail());
            values.put("telefone_usuario", usuario.getTelefone());
            values.put("usuario", usuario.getUsuario());
            values.put("senha", usuario.getSenha());
            values.put("logado", usuario.getLogado());

            try
            {
                db.update("USUARIO", values, "usuario = '" + usuario.get_id() + "'", null);
            }
            finally
            {
                db.close();
            }
        }
        catch (Exception ex)
        {
            Log.e("atualizar: ", ex.getMessage());
        }
    }

    public Usuario logar(String usuario, String senha)
    {
        db = dbHelper.getReadableDatabase();

        Usuario usuarioLogin = new Usuario();

        Cursor cursor = db.query("USUARIO", null, "usuario = '" + usuario + "' AND senha = '" + senha + "'", null, null, null, null);

        try {
            while (cursor.moveToNext())
            {
                if (cursor.getCount() > 0)
                {
                    usuarioLogin.set_id(Integer.parseInt(cursor.getString(0)));
                    usuarioLogin.setNome(cursor.getString(1));
                    usuarioLogin.setEmail(cursor.getString(2));
                    usuarioLogin.setTelefone(cursor.getString(3));
                    usuarioLogin.setUsuario(cursor.getString(4));
                    usuarioLogin.setSenha(cursor.getString(5));
                    usuarioLogin.setLogado(Integer.parseInt(cursor.getString(6)));
                    usuarioLogin.setLogarAuto(Integer.parseInt(cursor.getString(7)));
                }
            }
        }
        catch (Exception ex)
        {
            Log.e("logar", ex.getMessage());
        }
        finally
        {
            cursor.close();
        }

        return usuarioLogin;
    }
}
