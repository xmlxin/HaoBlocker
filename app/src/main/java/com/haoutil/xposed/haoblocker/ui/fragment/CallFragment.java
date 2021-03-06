package com.haoutil.xposed.haoblocker.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.haoutil.xposed.haoblocker.AppContext;
import com.haoutil.xposed.haoblocker.R;
import com.haoutil.xposed.haoblocker.presenter.CallPresenter;
import com.haoutil.xposed.haoblocker.presenter.impl.CallPresenterImpl;
import com.haoutil.xposed.haoblocker.ui.CallView;
import com.haoutil.xposed.haoblocker.ui.activity.SettingsActivity;

public class CallFragment extends PromptFragment implements CallView, SettingsActivity.OnMenuItemClickListener {
    private CallPresenter mCallPresenter;
    private RecyclerView rv_call;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mCallPresenter = new CallPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            rv_call = (RecyclerView) view.findViewById(R.id.rv_call);
            Context context = AppContext.getsInstance().getApplicationContext();
            rv_call.setLayoutManager(new LinearLayoutManager(context));
            mCallPresenter.setListItems();
        }
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        mCallPresenter.setMenuItems(menu);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_call;
    }

    @Override
    public void onActionClick(View action) {
        mCallPresenter.restoreCall();
    }

    @Override
    public void onFilter(MenuItem item) {
    }

    @Override
    public void onBackup(MenuItem item) {
        mCallPresenter.exportCalls();
    }

    @Override
    public void onRestore(MenuItem item) {
        mCallPresenter.importCalls();
    }

    @Override
    public void setCallAdapter(RecyclerView.Adapter adapter) {
        rv_call.setAdapter(adapter);
    }

    @Override
    public void setMenuItems(Menu menu) {
        menu.findItem(R.id.backup).setVisible(true);
        menu.findItem(R.id.restore).setVisible(true);
        ((SettingsActivity) getActivity()).setOnMenuItemClickListener(this);
    }
}
