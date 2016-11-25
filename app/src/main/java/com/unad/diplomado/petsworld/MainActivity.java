package com.unad.diplomado.petsworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.unad.diplomado.petsworld.domain.Categoria;
import com.unad.diplomado.petsworld.tools.Constantes;
import com.unad.diplomado.petsworld.ui.fragmentos.AcercadeFragment;
import com.unad.diplomado.petsworld.ui.fragmentos.CategoriasFragment;
import com.unad.diplomado.petsworld.ui.fragmentos.ConfiguracionFragment;
import com.unad.diplomado.petsworld.ui.fragmentos.SitiosFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Categoria categoria;

    NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            displaySelectedScreen(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("AAA", String.valueOf(requestCode) + " y " + String.valueOf(resultCode));
        if(requestCode==2)
        {
            getSupportFragmentManager().findFragmentByTag("sitiosFragment");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, new ConfiguracionFragment())
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displaySelectedScreen(item.getItemId());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        String title = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new CategoriasFragment();
                break;
            case R.id.nav_parques:
                fragment = crearFragmentoSitios("1");
                title = getString(R.string.parques_cat);
                break;
            case R.id.nav_comidas:
                fragment = crearFragmentoSitios("2");
                title = getString(R.string.comidas_cat);
                break;
            case R.id.nav_veterinarias:
                fragment = crearFragmentoSitios("3");
                title = getString(R.string.veterinarias_cat);
                break;
            case R.id.nav_tiendas:
                fragment = crearFragmentoSitios("4");
                title = getString(R.string.tiendas_cat);
                break;
            case R.id.nav_servicios:
                fragment = crearFragmentoSitios("5");
                title = getString(R.string.servicios_cat);
                break;
            case R.id.nav_configuracion:
                fragment = new ConfiguracionFragment();
                break;
            case R.id.nav_acercade:
                fragment = new AcercadeFragment();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            mostrarFragment(fragment);
            if(title != null)
            {
                setTitle(title);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private Fragment crearFragmentoSitios(String idCategoria){
        Fragment sitiosFragment = new SitiosFragment();
        Bundle argumentos = new Bundle();
        argumentos.putString(Constantes.EXTRA_ID_CATEGORIA, idCategoria);
        sitiosFragment.setArguments(argumentos);
        return sitiosFragment;
    }

    public void mostrarFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, fragment.toString());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void cambiarItemMenuSelecionado(String idCategoria){
        int id = Integer.parseInt(idCategoria);
        mNavigationView.setCheckedItem(id);
    }
}
