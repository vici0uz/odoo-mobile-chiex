package com.odoo.addons.caja_chica;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.odoo.R;
import com.odoo.addons.caja_chica.models.AccountBankStatement;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.utils.OControls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 14/03/17.
 */
// TODO: 15/03/17 Agregar formulario caja details
// TODO: 15/03/17 Agregar distinto color y un icono caja abierta
// TODO: 15/03/17 Agregar funcion al mantener apretado mucho tiempo longclick
// TODO: 15/03/17 Agregar el menu estado cuentas bancos
// TODO: 15/03/17 Mover la funcionalidad a framework taller777 
// TODO: 15/03/17 Ocultar de la misma forma que aqui el fabButton en framework taller777 

public class CajaChica extends BaseFragment implements
        OCursorListAdapter.OnViewBindListener, SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = CajaChica.class.getSimpleName();
    private OCursorListAdapter mAdapter = null;
    private String mCurFilter = null;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.common_listview, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        ListView mCajaList = (ListView) view.findViewById(R.id.listview);
        mAdapter = new OCursorListAdapter(getActivity(), null, R.layout.caja_row_item);
        mAdapter.setOnViewBindListener(this);
        mCajaList.setAdapter(mAdapter);
        getLoaderManager().initLoader(0,null,this);

    }
    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> items = new ArrayList<>();
        items.add(new ODrawerItem(TAG).setTitle("Registros de Caja")
        .setIcon(R.drawable.ic_monetization_on_black_24dp).setInstance(new CajaChica()));
        return items;
    }

    @Override
    public Class<AccountBankStatement> database() {
        return AccountBankStatement.class;
    }

    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {
        OControls.setText(view, R.id.name, row.getString("name"));
        mView.findViewById(R.id.fabButton).setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle data) {
        String where = "";
        List<String> args = new ArrayList<>();
        if (mCurFilter != null){
            where += "name like ?";
            args.add("%"+ mCurFilter+ "%");

        }
        String selection= (args.size()>0) ? where : null;
        String[] selectionArgs = (args.size()>0) ? args.toArray(new String[args.size()]) : null;
        return new CursorLoader(getActivity(), db().uri(),
                null, selection, selectionArgs, "name");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.changeCursor(data);
        System.out.println(data.getCount());
        if(data.getCount()>0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(mView, R.id.loadingProgress);
                    OControls.setVisible(mView, R.id.swipe_container);
                }
            },500);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);

    }


}
