package prototipo.Servicio;

public class SesionActual {

    private static String usuarioLogueado;
    private static int tagSesion;

    public static void setUsuario(String usuario) {
        usuarioLogueado = usuario;
    }

    public static String getUsuario() {
        return usuarioLogueado;
    }

    public static void setTagSesion(int tag) {
        tagSesion = tag;
    }

    public static int getTagSesion() {
        return tagSesion;
    }

}
