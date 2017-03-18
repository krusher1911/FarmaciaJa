package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import DAL.UsuarioDal;
import Entidades.Usuario;
import br.com.farmaciaja.una.tidir.farmaciaja.R;

public class Act_Cadastro extends AppCompatActivity implements View.OnClickListener{

    //OBJETOS
    private Toolbar toolbar;

    //ENTIDADES
    Usuario usuario;

    //DAL
    UsuarioDal usuarioDal;

    //VIEWS
    EditText txtNome;
    EditText txtEmail;
    EditText txtDDD;
    EditText txtTelefone;
    EditText txtUsuario;
    EditText txtSenha;
    EditText txtConfirmarSenha;
    CheckBox chkLogar;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        carregarViews();
        listeners();
    }

    private void carregarViews() {

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtDDD = (EditText)findViewById(R.id.txtDDD);
        txtTelefone = (EditText)findViewById(R.id.txtTelefone);
        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtSenha = (EditText)findViewById(R.id.txtSenha);
        txtConfirmarSenha = (EditText)findViewById(R.id.txtConfirmarSenha);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrarUsuario);
        chkLogar = (CheckBox)findViewById(R.id.chkLogarAutomaticamente);
    }

    private void listeners() {

        btnCadastrar.setOnClickListener(this);
    }

    private boolean validarCampos()
    {
        Boolean camposValidos = true;

        if(txtNome.getText().length() == 0)
        {
            txtNome.setError("Campo Obrigatório");
            camposValidos = false;
        }
        if(txtEmail.getText().length() == 0)
        {
            txtEmail.setError("Campo Obrigatório");
            camposValidos = false;
        }

        if(txtDDD.getText().length() == 0)
        {
            txtDDD.setError("Campo Obrigatório");
            camposValidos = false;
        }
        else
        {
            if(txtDDD.getText().length() < 2) {
                txtDDD.setError("Campo DDD inválido");
                camposValidos = false;
            }
        }

        if(txtTelefone.getText().equals(""))
        {
            txtTelefone.setError("Campo Obrigatório");
            camposValidos = false;
        }
        else
        {
            if(txtTelefone.getText().length() < 2) {
                txtTelefone.setError("Campo DDD inválido");
                camposValidos = false;
            }
        }

        if(txtUsuario.getText().length() == 0)
        {
            txtUsuario.setError("Campo Obrigatório");
            camposValidos = false;
        }

        if(txtSenha.getText().length() == 0)
        {
            txtSenha.setError("Campo Obrigatório");
            camposValidos = false;
        }
        if(txtConfirmarSenha.getText().length() == 0)
        {
            txtConfirmarSenha.setError("Campo Obrigatório");
            camposValidos = false;
        }

        if(txtSenha.getText().length() > 0 && txtConfirmarSenha.length() > 0)
        {
            if(!txtSenha.getText().toString().equals(txtConfirmarSenha.getText().toString()))
            {
                camposValidos = false;
                txtConfirmarSenha.setError("Senhas diferentes");
            }
        }

        return camposValidos;
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
    public void onClick(View v) {

        switch (v.getId())
        {
            case(R.id.btnCadastrarUsuario):
            {
                salvar();
            }
            break;
        }
    }

    private void salvar() {

        if(validarCampos())
        {
            usuario = new Usuario();
            usuarioDal = new UsuarioDal(this);

            usuario.setNome(txtNome.getText().toString());
            usuario.setEmail(txtEmail.getText().toString());
            usuario.setTelefone(txtTelefone.getText().toString());
            usuario.setUsuario(txtUsuario.getText().toString());
            usuario.setSenha(txtSenha.getText().toString());
            usuario.setLogado(1);

            if(chkLogar.isChecked())
            {
                usuario.setLogarAuto(1);
            }
            else
            {
                usuario.setLogarAuto(0);
            }

            usuarioDal.salvarNovoUsuario(usuario);

            Toast.makeText(this, "Cadastro realizado com Sucesso!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Act_farmacias.class);
            startActivity(intent);

            finish();
        }
    }
}