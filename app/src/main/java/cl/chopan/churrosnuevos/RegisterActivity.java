package cl.chopan.churrosnuevos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etUsuario = findViewById(R.id.et_reg_username);
        EditText etEmail = findViewById(R.id.et_reg_email);
        EditText etContrasena = findViewById(R.id.et_reg_password);
        EditText etConfirmar = findViewById(R.id.et_reg_confirm);
        Button btnRegistrar = findViewById(R.id.btn_register);
        Button btnVolver = findViewById(R.id.btn_back_login);

        // Botón para registrarse
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String contrasena = etContrasena.getText().toString().trim();
                String confirmar = etConfirmar.getText().toString().trim();

                // Validar campos vacíos
                if (usuario.isEmpty() || email.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar contraseñas
                if (!contrasena.equals(confirmar)) {
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Deshabilitar botón mientras se guarda
                btnRegistrar.setEnabled(false);

                // Guardar en Supabase en un hilo separado
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final boolean registroExitoso = SupabaseHelper.registrarUsuario(usuario, email, contrasena);

                        // Volver al hilo principal
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btnRegistrar.setEnabled(true);

                                if (registroExitoso) {
                                    Toast.makeText(RegisterActivity.this, "Registro exitoso. Inicia sesión.", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Error al registrar. Intenta otro usuario.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        // Botón para volver al login
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}