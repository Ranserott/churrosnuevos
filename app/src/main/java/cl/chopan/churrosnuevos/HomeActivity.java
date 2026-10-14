package cl.chopan.churrosnuevos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.views.MapView;

public class HomeActivity extends AppCompatActivity {

    // Declarar el mapa
    private MapView mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Conectar el mapa del XML con el código
        mapa = findViewById(R.id.mapView);

        // Permitir hacer zoom con los dedos
        mapa.setMultiTouchControls(true);

        // Centrar el mapa en una ubicación
        // new GeoPoint(latitud, longitud)
        org.osmdroid.util.GeoPoint ubicacion = new org.osmdroid.util.GeoPoint(-33.4489, -70.6693);
        mapa.getController().setCenter(ubicacion);

        // Zoom inicial (número mayor = más cerca)
        mapa.getController().setZoom(15.0);

        // Botón cerrar sesión
        Button btnCerrar = findViewById(R.id.btn_logout);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Vuelve al login
            }
        });
    }

    // Necesario para que el mapa funcione bien
    @Override
    protected void onResume() {
        super.onResume();
        mapa.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapa.onPause();
    }
}
