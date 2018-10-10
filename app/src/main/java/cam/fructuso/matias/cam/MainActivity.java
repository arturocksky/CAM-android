package cam.fructuso.matias.cam;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout mainDrawerLayout = null;
    private NavigationView menuNavigationView = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainDrawerLayout = findViewById(R.id.drawer_layout);
        menuNavigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(getParent(),mainDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mainDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        menuNavigationView.setNavigationItemSelectedListener(this);

         getSupportFragmentManager().beginTransaction().replace(R.id.containerMain,HomeFragment.newInstance("","")).commit(); // para cargar el fragment antes de mandarlo a la vista
    }

    @Override
    public void onBackPressed() {
        if(mainDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mainDrawerLayout.closeDrawer(GravityCompat.START);

        }else{

            super.onBackPressed();

        }


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch(item.getItemId()){

            case R.id.nav_home:
                getSupportFragmentManager().popBackStack();
                 mainDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_registro:

                swithFragment(R.id.containerMain,RegistroFragment.newInstance("Registro","FragmentRegistro"),"TAG-REGISTRO");
                mainDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_perfil:
                swithFragment(R.id.containerMain,PerfilFragment.newInstance("Perfil","FragmentPerfil"),"TAG-PERFIL");
                mainDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_cuestionarios:
                Intent cuestionariosIntent = new Intent(getBaseContext(),CuestionariosActivity.class);
                startActivity(cuestionariosIntent);
                //swithFragment(R.id.containerMain,CuestionariosFragment.newInstance("Cuestionarios","FragmentCuestionarios"),"TAG-CUESTIONARIOS");
                //mainDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_politica:
                swithFragment(R.id.containerMain,PoliticaFragment.newInstance("Politica de privacidad","FragmentPolitica"),"TAG-POLITICA");
                mainDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_resultados:
                swithFragment(R.id.containerMain,ResultadosFragment.newInstance("Resultados","FragmentResultados"),"TAG-RESULTADOS");
                mainDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            default:
                return true;
        }
        // Handle navigation view item clicks here.

    }

    public void swithFragment (int idContainer, Fragment fragment, String tag ){

        FragmentManager fragmentManager = getSupportFragmentManager();



        if (fragment !=null){

            FragmentTransaction transaction = null;

            while (fragmentManager.popBackStackImmediate());


            transaction = fragmentManager.beginTransaction().replace(idContainer,fragment,tag);

            if (!(fragment instanceof HomeFragment)) // dejar el home por default hasta abajo
                transaction.addToBackStack(tag);
              //transaction.addToBackStack(null);
            transaction.commit();

        }


    }


}
