package cl.chopan.churrosnuevos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsuario = findViewById(R.id.et_username);
        EditText etContrasena = findViewById(R.id.et_password);
        Button btnEntrar = findViewById(R.id.btn_login);
        Button btnRegistrar = findViewById(R.id.btn_register);

        // Botón para iniciar sesión
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString().trim();
                String contrasena = etContrasena.getText().toString().trim();

                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Deshabilitar botón mientras se valida
                btnEntrar.setEnabled(false);

                // Validar en Supabase en un hilo separado
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final boolean loginExitoso = SupabaseHelper.loginUsuario(usuario, contrasena);

                        // Volver al hilo principal para mostrar el resultado
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btnEntrar.setEnabled(true);

                                if (loginExitoso) {
                                    Toast.makeText(LoginActivity.this, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        // Botón para ir a registro
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}