package cam.fructuso.matias.cam;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CuestionariosActivity extends AppCompatActivity {
    private Button btnCreate;
    private Button btnDelete;

    private CuestionariosSQLiteHelper cuestionariosHelper;
    private SQLiteDatabase db;

    private ListView listView;
    private MyAdapter adapter;

    private List<Cuestionario> cuestionarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionarios);

        listView = (ListView) findViewById(R.id.listView);
        cuestionarios = new ArrayList<Cuestionario>();

        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnDelete = (Button) findViewById(R.id.buttonDelete);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
                update();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAll();
                update();
            }
        });


        //Abrimos la base de datos 'DBMisCuestionarios' en modo escritura
        cuestionariosHelper = new CuestionariosSQLiteHelper(this, "DBMisCuestionarios", null, 1);
        db = cuestionariosHelper.getWritableDatabase();

        adapter = new MyAdapter(this, cuestionarios, R.layout.itemdb);
        listView.setAdapter(adapter);

        update();
    }


    private List<Cuestionario> getAllCuestionarios() {
        // Seleccionamos todos los registros de la tabla Cuestionarios
        Cursor cursor = db.rawQuery("select * from Cuestionarios", null);
        List<Cuestionario> list = new ArrayList<Cuestionario>();

        if (cursor.moveToFirst()) {
            // iteramos sobre el cursor de resultados,
            // y vamos rellenando el array que posteriormente devolveremos mientras no sea el último
            while (cursor.isAfterLast() == false) {

                int CIN = cursor.getInt(cursor.getColumnIndex("CIN"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String estado = cursor.getString(cursor.getColumnIndex("estado"));

                list.add(new Cuestionario(CIN, name, estado));
                cursor.moveToNext();
            }
        }
        return list;
    }


    private void create() {
        //Si hemos abierto correctamente la base de datos
        if (db != null) {
            //Creamos el registro a insertar como objeto ContentValues
            ContentValues nuevoRegistro = new ContentValues();
            // El ID es auto incrementable como declaramos en nuestro CuestionariosSQLiteHelper
            nuevoRegistro.put("name", "Cuestionario de Precios");
            nuevoRegistro.put("estado", "completado");

            //Insertamos el registro en la base de datos
            db.insert("Cuestionarios", null, nuevoRegistro);
        }
    }

    private void removeAll() {
        db.delete("Cuestionarios", "", null);
    }

    private void update() {
        // borramos todos los elementos
        cuestionarios.clear();
        // cargamos todos los elementos
        cuestionarios.addAll(getAllCuestionarios());
        // refrescamos el adaptador
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        // cerramos conexión base de datos antes de destruir el activity
        db.close();
        super.onDestroy();
    }
}
