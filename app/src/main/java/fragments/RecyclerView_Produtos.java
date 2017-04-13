package fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Entidades.Produto;
import adapters.ProdutoAdapter;
import br.com.farmaciaja.una.tidir.farmaciaja.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerView_Produtos extends Fragment {

    Context context;


    public RecyclerView_Produtos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_farmacia_recyclerview_produtos, container, false);
        context = rootView.getContext();
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.produto_recycler_view);

        //creating sample data
        List<Produto> produtosList = new ArrayList<>();
        int qt_produtos = 50;
        Produto[] lista_produto = new Produto[qt_produtos];
        for (int i = 0; i < qt_produtos; i++) {
            lista_produto[i] = new Produto(i, 1, "Produto " + i, "Nome produto " + i, (double) i);
            produtosList.add(lista_produto[i]);
        }

        //setting produto adapter
        ProdutoAdapter pa = new ProdutoAdapter(produtosList, context);
        rv.setAdapter(pa);

        //Layout manager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        return rootView;
    }

}
