package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import proyectomundial.model.Resultados;
import proyectomundial.util.BasedeDatos;

/**
 *
 * @author juand
 */
public class ResulltadosDAO {

    public ResulltadosDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarResultados(Resultados resultado) {
        System.out.println("Error 3");
        String sql = "INSERT INTO a_penaranda12.partidos (grupo, local, visitante, continente_local,continente_visitante,goles_local,goles_visitante) values("
                + "'" + resultado.getGrupo() + "', "
                + "'" + resultado.getLocal() + "', "
                + "'" + resultado.getVisitante() + "', "
                + "'" + resultado.getContinente_local() + "', "
                + "'" + resultado.getContinente_visitante() + "', "
                + "'" + resultado.getGoles_locales() + "', "
                + "'" + resultado.getGoles_visitante() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Resultados> getSelecciones() {
        System.out.println("ERROR 2");
        String sql = "SELECT grupo,local, visitante, continente_local,continente_visitante,goles_Local,goles_visitante FROM a_penaranda12.partidos";
        List<Resultados> resultados = new ArrayList<Resultados>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return resultados;
    }

    public int totalPartidos() {
        int totalPartidos = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT COUNT(*) AS total_partidos FROM a_penaranda12.partidos");
            if (result.next()) {
                totalPartidos = result.getInt("total_partidos");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return totalPartidos;
    }

    public double promedioGolePorPatidos() {
        double promediGoles = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT AVG(goles_local + goles_visitante) AS promedio_goles_por_partido FROM a_penaranda12.partidos");
            if (result.next()) {
                promediGoles = result.getInt("promedio_goles_por_partido");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return promediGoles;
    }

    public int[] partidoGanEmp() {
        int partidosEmpatados = 0;
        int partidosGanados = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT SUM(CASE WHEN goles_local > goles_visitante THEN 1 ELSE 0 END) + "
                    + "SUM(CASE WHEN goles_local < goles_visitante THEN 1 ELSE 0 END) AS partidos_con_ganador, SUM(CASE WHEN goles_local "
                    + "= goles_visitante THEN 1 ELSE 0 END) AS partidos_con_empate FROM a_penaranda12.partidos");
            if (result.next()) {
                partidosGanados = result.getInt("partidos_con_ganador");
                partidosEmpatados = result.getInt("partidos_con_empate");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return new int[]{partidosGanados, partidosEmpatados};
    }

    public DefaultTableModel partidosMasGoles() {
        String[] columnas = {"Equipo 1", "Equipo 2", "Goles Equipo 1", "Goles Equipo 2"};
        Object[][] matrizResultados = new Object[0][columnas.length];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT local as EQUIPO1, visitante as EQUIPO2, goles_local as GOLESEQUIPO1, goles_visitante as GOLESEQUIPO2 FROM a_penaranda12.partidos WHERE goles_local + goles_visitante = (SELECT MAX(goles_local + goles_visitante) FROM a_penaranda12.partidos)");
            while (result.next()) {
                String equipo1 = result.getString("EQUIPO1");
                String equipo2 = result.getString("EQUIPO2");
                int golesEquipo1 = result.getInt("GOLESEQUIPO1");
                int golesEquipo2 = result.getInt("GOLESEQUIPO2");
                Object[] fila = {equipo1, equipo2, golesEquipo1, golesEquipo2};
                matrizResultados = Arrays.copyOf(matrizResultados, matrizResultados.length + 1);
                matrizResultados[matrizResultados.length - 1] = fila;
            }
        } catch (Exception e) {
            // Manejar la excepción cerrando la conexión a la base de datos
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
        return modeloTabla;
    }
    
    public DefaultTableModel partidosMenosGoles() {
        String[] columnas = {"Equipo 1", "Equipo 2", "Goles Equipo 1", "Goles Equipo 2"};
        Object[][] matrizResultados = new Object[0][columnas.length];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT local as EQUIPO1, visitante as EQUIPO2, goles_local as GOLESEQUIPO1, goles_visitante as GOLESEQUIPO2 FROM a_penaranda12.partidos WHERE goles_local + goles_visitante = (SELECT MIN(goles_local + goles_visitante) FROM a_penaranda12.partidos)");
            while (result.next()) {
                String equipo1 = result.getString("EQUIPO1");
                String equipo2 = result.getString("EQUIPO2");
                int golesEquipo1 = result.getInt("GOLESEQUIPO1");
                int golesEquipo2 = result.getInt("GOLESEQUIPO2");
                Object[] fila = {equipo1, equipo2, golesEquipo1, golesEquipo2};
                matrizResultados = Arrays.copyOf(matrizResultados, matrizResultados.length + 1);
                matrizResultados[matrizResultados.length - 1] = fila;
            }
        } catch (Exception e) {
        }

        DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
        return modeloTabla;
    }

    public String[] seleccionesMasGoles() {
        ArrayList<String> selecciones = new ArrayList<>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT seleccion FROM (SELECT local AS seleccion, SUM(goles_local) AS goles FROM a_penaranda12.partidos "
                    + "GROUP BY local union SELECT visitante AS seleccion, SUM(goles_visitante) AS goles FROM a_penaranda12.partidos GROUP BY visitante) t WHERE goles "
                    + "= (SELECT MAX(goles) FROM ( SELECT SUM(goles_local) AS goles FROM a_penaranda12.partidos GROUP BY local union SELECT SUM(goles_visitante) AS goles"
                    + " FROM a_penaranda12.partidos GROUP BY visitante) s)");
            while (result.next()) {
                selecciones.add(result.getString("seleccion"));
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return selecciones.toArray(new String[0]);
    }

    public String[] seleccionesMenosGoles() {
        ArrayList<String> selecciones = new ArrayList<>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT seleccion, goles FROM (SELECT local AS seleccion, SUM(goles_local) AS goles FROM a_penaranda12.partidos GROUP BY "
                    + "local union SELECT visitante AS seleccion, SUM(goles_visitante) AS goles FROM a_penaranda12.partidos GROUP BY visitante) t WHERE goles = (SELECT MIN(goles) FROM "
                    + "( SELECT SUM(goles_local) AS goles FROM a_penaranda12.partidos GROUP BY local union SELECT SUM(goles_visitante) AS goles FROM a_penaranda12.partidos GROUP BY visitante) s)");
            while (result.next()) {
                selecciones.add(result.getString("seleccion"));
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return selecciones.toArray(new String[0]);
    }
    

    public String[] seleccionesMayorYMenorPuntuacion() {
        ArrayList<String> selecciones = new ArrayList<>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT equipo, puntos\n"
                    + "FROM (\n"
                    + "    SELECT equipo, puntos,\n"
                    + "           ROW_NUMBER() OVER (ORDER BY puntos DESC) AS posicion_desc,\n"
                    + "           ROW_NUMBER() OVER (ORDER BY puntos ASC) AS posicion_asc\n"
                    + "    FROM (\n"
                    + "        SELECT equipo, SUM(puntos) AS puntos\n"
                    + "        FROM (\n"
                    + "            SELECT local AS equipo, \n"
                    + "                   CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n"
                    + "            FROM a_penaranda12.partidos p2  \n"
                    + "            GROUP BY local, goles_local, goles_visitante\n"
                    + "            UNION ALL\n"
                    + "            SELECT visitante AS equipo, \n"
                    + "                   CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END AS puntos\n"
                    + "            FROM a_penaranda12.partidos p2  \n"
                    + "            GROUP BY visitante, goles_local, goles_visitante\n"
                    + "        ) AS subquery\n"
                    + "        GROUP BY equipo\n"
                    + "    ) AS subquery2\n"
                    + ") AS subquery3\n"
                    + "WHERE posicion_desc = 1 OR posicion_asc = 1\n"
                    + "ORDER BY puntos DESC;");
            while (result.next()) {
                selecciones.add(result.getString("equipo"));
                selecciones.add(result.getString("puntos"));
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return selecciones.toArray(new String[0]);
    }

    
    public String[] continentesMayorPuntos() {
        ArrayList<String> selecciones = new ArrayList<>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT continente FROM ( SELECT continente_local AS continente, SUM(CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END)+ SUM"
                    + "(CASE WHEN goles_local < goles_visitante THEN 0 ELSE 1 END) AS puntos FROM a_penaranda12.partidos GROUP BY continente_local  UNION SELECT continente_visitante AS continente,SUM(CASE WHEN goles_local < goles_visitante "
                    + "THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) + SUM(CASE WHEN goles_local >goles_visitante THEN 0 ELSE 1 END) AS puntos FROM a_penaranda12.partidos GROUP BY continente_visitante) t WHERE puntos = ( SELECT MAX("
                    + "puntos) FROM ( SELECT continente_local  AS continente, SUM(CASE when goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) + SUM(CASE WHEN goles_local < goles_visitante THEN 0 ELSE 1 END) as puntos FROM a_penaranda12.partidos"
                    + " GROUP BY continente_local UNION SELECT continente_visitante AS continente, SUM(CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) + SUM(CASE WHEN goles_local > goles_visitante THEN 0 ELSE 1 END) AS puntos from a_penaranda12.partidos GROUP BY continente_visitante ) t ) ORDER BY puntos DESC;\n");
            while (result.next()) {
                selecciones.add(result.getString("continente"));
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return selecciones.toArray(new String[0]);
    }

    public String[] continentesMenorPuntos() {
        ArrayList<String> selecciones = new ArrayList<>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT continente FROM ( SELECT continente_local AS continente, SUM(CASE WHEN goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END)+ SUM(CASE WHEN goles_local < goles_visitante THEN 0 ELSE 1 END) AS puntos FROM a_penaranda12.partidos GROUP BY "
                    + "continente_local  UNION SELECT continente_visitante AS continente,SUM(CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) + SUM(CASE WHEN goles_local >goles_visitante THEN 0 ELSE 1 END) AS puntos FROM a_penaranda12.partidos GROUP BY continente_visitante) t WHERE puntos = ( SELECT MIN(puntos) FROM"
                    + " ( SELECT continente_local  AS continente, SUM(CASE when goles_local > goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) + SUM(CASE WHEN goles_local < goles_visitante THEN 0 ELSE 1 END) as puntos FROM a_penaranda12.partidos GROUP BY continente_local UNION SELECT continente_visitante AS continente, SUM(CASE WHEN goles_local < goles_visitante THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) + SUM"
                    + "(CASE WHEN goles_local > goles_visitante THEN 0 ELSE 1 END) AS puntos from a_penaranda12.partidos GROUP BY continente_visitante ) t ) ORDER BY puntos ASC;\n");
            while (result.next()) {
                selecciones.add(result.getString("continente"));
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return selecciones.toArray(new String[0]);
    }


    public List<Resultados> getResultadosBusqueda(String nombreSeleccion) {
        String sql = "SELECT grupo, local, visitante, continente_local, continente_visitante, goles_local, goles_visitante FROM a_penaranda12.partidos WHERE local LIKE ? OR grupo LIKE ? OR visitante LIKE ? OR continente_local LIKE ? OR continente_visitante LIKE ?";
        List<Resultados> resultados = new ArrayList<Resultados>();
        try {
            if (BasedeDatos.conexion == null) {
                System.out.println("No hay conexión a la base de datos");
                return resultados;
            }
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            stmt.setString(1, "%" + nombreSeleccion + "%");
            stmt.setString(2, "%" + nombreSeleccion + "%");
            stmt.setString(3, "%" + nombreSeleccion + "%");
            stmt.setString(4, "%" + nombreSeleccion + "%");
            stmt.setString(5, "%" + nombreSeleccion + "%");

            ResultSet result = stmt.executeQuery();
            if (result != null) {
                while (result.next()) {
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }

        return resultados;
    }

    public String[][] getResultadosMatriz() {
        System.out.println("ERROR 4");
        String[][] matrizResultados = null;
        List<Resultados> resultados = getSelecciones();

        if (resultados != null && resultados.size() > 0) {

            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultados resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getContinente_local();
                matrizResultados[x][4] = resultado.getContinente_visitante();
                matrizResultados[x][5] = resultado.getGoles_locales();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }

        return matrizResultados;
    }
}
