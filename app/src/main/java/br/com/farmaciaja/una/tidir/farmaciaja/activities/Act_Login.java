package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DAL.UsuarioDal;
import Entidades.Usuario;
import br.com.farmaciaja.una.tidir.farmaciaja.R;

public class Act_Login extends AppCompatActivity implements View.OnClickListener {

    //OBJETOS
    private Toolbar toolbar;

    //ENTIDADES
    Usuario usuario;

    //DAL
    UsuarioDal usuarioDal;

    //VIEWS
    Button btnCadastrar;
    Button btnEntrar;
    EditText txtUsuario;
    EditText txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        carregarViews();

        listeners();

        usuarioDal = new UsuarioDal(this);
    }

    private void carregarViews() {

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        txtUsuario = (EditText)findViewById(R.id.txtUsuarioLogin);
        txtSenha = (EditText)findViewById(R.id.txtSenhaLogin);
    }

    private void listeners() {

        btnCadastrar.setOnClickListener(this);
        btnEntrar.setOnClickListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case (R.id.btnCadastrar): {

                intent = new Intent(this, Act_Cadastro.class);
                this.startActivityForResult(intent, 1);
            }
            break;
            case (R.id.btnEntrar):
            {
                usuario = new Usuario();

                if(validarCampos())
                {
                    usuario = usuarioDal.logar(String.valueOf(txtUsuario.getText()), String.valueOf(txtSenha.getText()));

                    if(usuario.get_id() != 0)
                    {
                        Toast.makeText(this, "Login realizado com Sucesso!", Toast.LENGTH_LONG).show();

                        usuario.setLogado(1);

                        usuarioDal.atualizar(usuario);

                        intent = new Intent(this, Act_MinhaConta.class);

                        startActivity(intent);

                        finish();
                    }
                    else
                    {
                        txtUsuario.setText("");
                        txtSenha.setText("");

                        Toast.makeText(this, "Usuario ou Senha Incorretos!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }

    }

    private boolean validarCampos()
    {
        boolean camposValidos = true;

        if(txtSenha.getText().toString().length() == 0)
        {
            camposValidos = false;
            txtSenha.setError("Campo Obrigatório");
        }
        if(txtUsuario.getText().toString().length() == 0)
        {
            camposValidos = false;
            txtUsuario.setError("Campo Obrigatório");
        }

        return camposValidos;
    }
}
