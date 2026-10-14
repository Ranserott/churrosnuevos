package cl.chopan.churrosnuevos;

import org.json.JSONObject;
import java.net.URLEncoder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// Clase simple para conectar con Supabase usando REST API
public class SupabaseHelper {

    // URL y clave de tu proyecto Supabase
    private static final String URL = "https://obdefsofdguflsmmiqbv.supabase.co";
    private static final String KEY = "sb_publishable_-OMR_uDqLOV-TM8ySz3ehQ_LAgV3X3L";

    // Cliente HTTP para hacer peticiones
    private static final OkHttpClient cliente = new OkHttpClient();

    // Tipo de contenido JSON
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // Método para REGISTRAR un usuario en Supabase
    public static boolean registrarUsuario(String usuario, String email, String contrasena) {
        try {
            // Crear el JSON con los datos del usuario
            JSONObject json = new JSONObject();
            json.put("usuario", usuario);
            json.put("email", email);
            json.put("contrasena", contrasena);

            // Crear la petición POST
            RequestBody body = RequestBody.create(json.toString(), JSON);
            Request request = new Request.Builder()
                    .url(URL + "/rest/v1/usuarios")
                    .addHeader("apikey", KEY)
                    .addHeader("Authorization", "Bearer " + KEY)
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();

            // Ejecutar la petición
            Response response = cliente.newCall(request).execute();
            int codigo = response.code();
            return codigo == 201; // 201 = creado correctamente
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para INICIAR SESIÓN verificando en Supabase
    public static boolean loginUsuario(String usuario, String contrasena) {
        try {
            // Codificar los valores para URL (evita problemas con caracteres especiales)
            String usuarioCodificado = URLEncoder.encode(usuario, "UTF-8");
            String contrasenaCodificada = URLEncoder.encode(contrasena, "UTF-8");

            // Buscar usuario por nombre y contraseña
            // Supabase usa filtros: ?usuario=eq.X&contrasena=eq.Y
            String filtro = "?usuario=eq." + usuarioCodificado + "&contrasena=eq." + contrasenaCodificada;

            Request request = new Request.Builder()
                    .url(URL + "/rest/v1/usuarios" + filtro)
                    .addHeader("apikey", KEY)
                    .addHeader("Authorization", "Bearer " + KEY)
                    .addHeader("Content-Type", "application/json")
                    .get()
                    .build();

            // Ejecutar la petición
            Response response = cliente.newCall(request).execute();
            String respuesta = response.body().string();

            // Si la respuesta es [] = no encontrado
            // Si tiene datos = usuario encontrado
            return respuesta.length() > 2 && !respuesta.equals("[]");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para VERIFICAR si un usuario ya existe
    public static boolean usuarioExiste(String usuario) {
        try {
            String usuarioCodificado = URLEncoder.encode(usuario, "UTF-8");
            String filtro = "?usuario=eq." + usuarioCodificado;

            Request request = new Request.Builder()
                    .url(URL + "/rest/v1/usuarios" + filtro)
                    .addHeader("apikey", KEY)
                    .addHeader("Authorization", "Bearer " + KEY)
                    .get()
                    .build();

            Response response = cliente.newCall(request).execute();
            String respuesta = response.body().string();
            return respuesta.length() > 2 && !respuesta.equals("[]");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}