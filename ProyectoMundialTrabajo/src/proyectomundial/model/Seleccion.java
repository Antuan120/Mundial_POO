
package proyectomundial.model;


public class Seleccion {

    String nombre;
    String continente;
    String dt;
    String nacionalidad;
    int total_equipos;
    int conteo;
    int cantidad_directores_tecnicos;

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    public void setCantidad_directores_tecnicos(int cantidad_directores_tecnicos) {
        this.cantidad_directores_tecnicos = cantidad_directores_tecnicos;
    }

    public int getConteo() {
        return conteo;
    }

    public int getCantidad_directores_tecnicos() {
        return cantidad_directores_tecnicos;
    }

    public Seleccion(String continente, int total_equipos) {
        this.continente = continente;
        this.total_equipos = total_equipos;
    }

    public int getTotal_equipos() {
        return total_equipos;
    }

    public void setTotal_equipos(int total_equipos) {
        this.total_equipos = total_equipos;
    }
    

    public Seleccion() {
    }

    public Seleccion(String nombre, String continente, String dt, String nacionalidad) {
        this.nombre = nombre;
        this.continente = continente;
        this.dt = dt;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
