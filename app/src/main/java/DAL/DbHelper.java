package DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Entidades.Farmacia;
import Entidades.Produto;
import Entidades.Usuario;

/**
 * Created by bravo3465 on 10/10/15.
 * edited by Krusher on 13/04/17
 **/
public class DbHelper extends SQLiteOpenHelper {

    //VARIAVEIS
    static final String DBNAME = "FarmaciaJa_DB";
    static final int DB_VEESION = 1;
    //ENTIDADES
    Farmacia farmacia;
    Produto produto;
    Usuario usuario;

    public DbHelper(Context context)
    {
        super(context, DBNAME, null, DB_VEESION);

        farmacia = new Farmacia();
        produto = new Produto();
        usuario = new Usuario();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //METODO CHAMADO NA PRIMEIRA VEZ QUE O APP É EXECUTADO, CRIA TODOAS AS TABELAS NO BANCO.
        criarTabelaFarmacia(db);
        criarTabelaUsuario(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try
        {
            db.execSQL("drop table if exists " + "FARMACIAS");
            db.execSQL("drop table if exists " + "USUARIO");
            onCreate(db);
        }
        catch (Exception ex)
        {
            Log.e("onUpgrade", ex.getMessage());
        }
    }

    private void criarTabelaFarmacia(SQLiteDatabase db)
    {
        try
        {

            //TABLE FARMACIA
            //int _id;
            //String descFarmacia;
            //String endereco;
            //float mediaTempoEntrega;
            //float mediaNotaAtendimento;
            //String informacoesFarmacia;
            //Time horaDeAbertura;
            //Time horaDeFechamento;

            String query = "CREATE TABLE FARMACIAS ( _id integer primary key autoincrement" + //0
                    ", descFarmacia text NOT NULL" + //1
                    ", endereco text" + //2
                    ", mediaTempoEntrega float(10,2)" + //3
                    ", mediaNotaAtendimento float(10,2)" + //4
                    ", informacoesFarmacia TEXT" + //5
                    ", horaDeAbertura TIME" + //6
                    ", horaDeFechamento TIME)"; //7

            db.execSQL(query);
        }
        catch (Exception ex)
        {
            Log.e("criarTabelaFarmacia", ex.getMessage());
        }
    }

    private void criarTabelaUsuario(SQLiteDatabase db) {

        try
        {
            String query = "CREATE TABLE USUARIO ( _id integer primary key autoincrement, nome_usuario text, email_usuario text," +
                    "telefone_usuario text, usuario text, senha text, logado integer, logar_auto integer)";

            db.execSQL(query);
        }
        catch (Exception ex)
        {
            Log.e("criarTabelaUsuario", ex.getMessage());
        }
    }
}
