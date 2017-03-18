package fragments;


import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DAL.UsuarioDal;
import Entidades.Usuario;
import Extras.CustomDialog;
import Extras.RecyclerViewSlideMenuItens;
import adapters.SlideMenuAdapter;
import br.com.farmaciaja.una.tidir.farmaciaja.R;
import br.com.farmaciaja.una.tidir.farmaciaja.activities.Act_Cadastro;
import br.com.farmaciaja.una.tidir.farmaciaja.activities.Act_Login;
import br.com.farmaciaja.una.tidir.farmaciaja.activities.Act_MinhaConta;

/**
 * A simple {@link Fragment} subclass.
 */
public class frag_navigator extends Fragment implements View.OnClickListener {

    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    CustomDialog customDialog;

    //VIEWS
    LinearLayout linearLayoutAcount;
    LinearLayout linearLayoutTop;
    TextView txtUsuario;
    TextView txtEntrar;
    TextView txtAccount;

    //ENTIDADES
    Usuario usuario;
    Usuario usuarioLogado;

    //DAL
    UsuarioDal usuarioDal;

    public frag_navigator() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navigator_drawer, container, false);

        criarRecylerView(view);

        carregarViews(view);

        listeners();

        buscarUsuario();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(usuario == null)
        {
            usuario = new Usuario();
        }

        usuarioDal = new UsuarioDal(getActivity());

        usuario = usuarioDal.buscarUsuario();

        if(usuario.getLogado() == 1)
        {
            txtUsuario.setText(usuarioLogado.getNome().toUpperCase());
            txtEntrar.setText(R.string.acessar_conta_slide_menu);
            txtAccount.setText(R.string.btn_logar_sair);
        }
    }

    private void criarRecylerView(View view)
    {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_menu);

        SlideMenuAdapter adapter = new SlideMenuAdapter(getActivity(), this.carregarItensMenu());

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void carregarViews(View view)
    {
        linearLayoutAcount = (LinearLayout) view.findViewById(R.id.linearLayoutAcount);
        linearLayoutTop = (LinearLayout) view.findViewById(R.id.linearLayoutTop);
        txtUsuario = (TextView)view.findViewById(R.id.txtUsuario);
        txtEntrar = (TextView)view.findViewById(R.id.txtEntrar);
        txtAccount = (TextView)view.findViewById(R.id.txtAccount);
    }

    private void listeners()
    {
        linearLayoutAcount.setOnClickListener(this);
        linearLayoutTop.setOnClickListener(this);
    }

    private void buscarUsuario()
    {
        usuarioDal = new UsuarioDal(getActivity());
        usuarioLogado = new Usuario();

        usuarioLogado = usuarioDal.buscarUsuario();

        if(usuarioLogado.getLogarAuto() == 1)
        {
            txtUsuario.setText(usuarioLogado.getNome().toUpperCase());
            txtEntrar.setText(R.string.acessar_conta_slide_menu);
            txtAccount.setText(R.string.btn_logar_sair);
        }
    }

    public List<RecyclerViewSlideMenuItens> carregarItensMenu()
    {
        List<RecyclerViewSlideMenuItens> slideMenuItens = new ArrayList<>();

        int[] icons = {R.drawable.ic_format_list_numbers_grey600_24dp};
        String[] titulos = {"Lista de Fármacias"};

        for (int i = 0; i < icons.length && i < titulos.length; i++)
        {
            RecyclerViewSlideMenuItens item = new RecyclerViewSlideMenuItens();

            item.setIconId(icons[i]);
            item.setTitulo(titulos[i]);

            slideMenuItens.add(item);
        }
        return slideMenuItens;
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {

        this.drawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu();
            }
        };

        this.drawerLayout.setDrawerListener(drawerToggle);

        this.drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case (R.id.linearLayoutAcount):
            {
                verificarConta();
            }
            break;
            case (R.id.linearLayoutTop):
            {
                if(txtAccount.getText().toString().equals("Entrar"))
                {
                    verificarConta();
                }
                else
                {
                    Intent intent = new Intent(getActivity(), Act_MinhaConta.class);
                    startActivity(intent);
                }
            }
            break;
        }
    }

    private void verificarConta()
    {
        if(txtAccount.getText().toString().equals("Entrar"))
        {
            Intent intent;
            usuario = new Usuario();

            usuario = usuarioDal.buscarUsuario();

            if(usuario.get_id() == 0)
            {
                intent = new Intent(getActivity(), Act_Cadastro.class);
                startActivity(intent);

                Toast.makeText(getActivity(), "Realize seu cadastro para poder prosseguir.", Toast.LENGTH_LONG).show();
            }
            else
            {
                intent = new Intent(getActivity(), Act_Login.class);
                startActivity(intent);
            }
        }
        else
        {
            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.custom_dialog);

            dialog.setCancelable(false);

            TextView txtTitulo = (TextView)dialog.findViewById(R.id.dialogTittle);
            TextView txtContent = (TextView)dialog.findViewById(R.id.dialogContent);
            Button btnSim = (Button)dialog.findViewById(R.id.btnSim);
            Button btnNao =(Button)dialog.findViewById(R.id.btnNo);

            txtTitulo.setText(R.string.titulo_atencao);
            txtContent.setText(R.string.dialog_desconectar);
            btnSim.setText(R.string.btn_desconectar);
            btnNao.setText(R.string.btn_cancelar);

            btnSim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    desconectar();

                    dialog.dismiss();
                }
            });

            btnNao.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    private void desconectar()
    {
        usuarioLogado.setLogado(0);

        usuarioDal.atualizar(usuarioLogado);

        txtUsuario.setText(R.string.saudacao_slide_menu);
        txtEntrar.setText(R.string.acessar_conta_slide_menu);
        txtAccount.setText(R.string.logar_slide_menu);
    }
}
