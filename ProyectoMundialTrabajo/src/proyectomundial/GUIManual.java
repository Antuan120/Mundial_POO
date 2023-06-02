package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import proyectomundial.DAO.ResulltadosDAO;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Seleccion;
import proyectomundial.model.Resultados;

public class GUIManual extends JFrame {

    SeleccionDAO seleccionDAO = new SeleccionDAO();
    ResulltadosDAO resultadoDAO = new ResulltadosDAO();

    private int home, seleccione, resultado, dashResultados, dashSelecciones;

    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los Resultados de los partidos cargardos
    public String[][] resultados = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;

    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuAuditoria;
    private JLabel btnAuditoria;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    public GUIManual() {

        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuAuditoria = new JPanel();
        btnAuditoria = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los Resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de Resultados
        pintarMenuDashboardRes();

        //pinta auditoria
        //pintarMenuAuditoria();
        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {

        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home++;
                System.out.println("Home");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {

        jLabelTop.setText("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {

        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccione++;
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {

        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultado++;
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        resultados = resultadoDAO.getResultadosMatriz();
        // Si no hay Resultados cargados, muestra el botón de carga de Resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de Resultados
        else {
            pintarTablaResultados();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {

        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);

        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                dashSelecciones++;
                accionDashboardSel();
            }
        });
    }

    private void accionDashboardSel() {
        jLabelTop.setText("Dash seleciones");

        //PANEL CANTIDAD DE SELECCIONES
        JLabel lblTotalPartidos = new JLabel("Total De Selecciones: " + seleccionDAO.totalPartidos());
        JPanel panelTotalPar = new JPanel();
        lblTotalPartidos.setHorizontalAlignment(JLabel.CENTER);
        lblTotalPartidos.setFont(new Font("Verdana", Font.BOLD, 17));
        lblTotalPartidos.setForeground(Color.BLACK);
        panelTotalPar.setBackground(Color.gray);
        panelTotalPar.setPreferredSize(new Dimension(240, 60));
        panelTotalPar.setForeground(Color.BLACK);
        panelTotalPar.add(lblTotalPartidos);

        //EQIPOS POR CONTINENTE
        JTable table23 = new JTable();
        JLabel cantidadNacionali23 = new JLabel("Selecciones Por Continente:");
        cantidadNacionali23.setHorizontalAlignment(JLabel.CENTER);
        cantidadNacionali23.setForeground(Color.BLACK);
        table23.setModel(seleccionDAO.equiposPorContinente());

        JPanel CantNac2 = new JPanel();
        CantNac2.setBackground(Color.gray);
        CantNac2.setPreferredSize(new Dimension(220, 120));
        CantNac2.setFont(new Font("Verdana", Font.BOLD, 13));
        CantNac2.setForeground(Color.BLACK);
        CantNac2.add(cantidadNacionali23);
        CantNac2.add(table23);
        
        //CANTIDAD DE NACIONALIDADES
        JLabel lblNacioDt = new JLabel("Cantidad de nacionalidades: " + seleccionDAO.cantidadNacionalidad());
        JPanel panelNacioDt = new JPanel();
        lblNacioDt.setHorizontalAlignment(JLabel.CENTER);
        lblNacioDt.setFont(new Font("Verdana", Font.BOLD, 17));
        lblNacioDt.setForeground(Color.BLACK);
        panelNacioDt.setBackground(Color.gray);
        panelNacioDt.setPreferredSize(new Dimension(300, 60));
        panelNacioDt.setForeground(Color.BLACK);
        panelNacioDt.add(lblNacioDt);

        //RANKING DE NACIONALIDADES DE DIRECTORES TECNICOS
        JTable table = new JTable();
        JLabel cantidadNacionali = new JLabel("Cantidad de nacionalidades de los directores tecnicos");
        cantidadNacionali.setHorizontalAlignment(JLabel.CENTER);
        cantidadNacionali.setForeground(Color.BLACK);
        table.setModel(seleccionDAO.cantidadSeleccionesPorCont());
        JPanel CantNac = new JPanel();
        CantNac.setBackground(Color.gray);
        CantNac.setPreferredSize(new Dimension(330, 400));
        CantNac.setFont(new Font("Verdana", Font.BOLD, 13));
        CantNac.setForeground(Color.BLACK);
        CantNac.add(cantidadNacionali);
        CantNac.add(table);
        
        
        

        jPanelMain.removeAll();
        jPanelMain.add(panelTotalPar);
        jPanelMain.add(CantNac2);
        jPanelMain.add(panelNacioDt);
        jPanelMain.add(CantNac);

        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {

        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);

        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashResultados++;
                System.out.println("Dashboard Resultados");
                accionDashboardRes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() {
        jLabelTop.setText("Dash Resultados");

        //NUMERO DE PARTIDOS
        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 45;'>"
                + resultadoDAO.totalPartidos() + "</span><br>TOTAL DE PARTIDOS</div></html>");
        panel1.add(label);
        panel1.setBackground(Color.gray);
        panel1.setPreferredSize(new Dimension(185, 80));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 13));
        label.setForeground(Color.BLACK);

        //PROMEDIO DE GOLES POR PARTIDO
        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 45;'>"
                + resultadoDAO.promedioGolePorPatidos() + "</span><br>PROMEDIO DE GOLES</div></html>");
        panel2.add(label2);
        panel2.setBackground(Color.gray);
        panel2.setPreferredSize(new Dimension(190, 80));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setFont(new Font("Verdana", Font.BOLD, 13));
        label2.setForeground(Color.BLACK);

        //PARTIDO CON MENOS GOLES
        JTable tabla = new JTable();
        JLabel label6 = new JLabel("Partidos con menos goles");
        DefaultTableModel modeloTabla = resultadoDAO.partidosMenosGoles();
        tabla.setModel(modeloTabla);
        JPanel ParMasGo3 = new JPanel();
        ParMasGo3.setBackground(Color.gray);
        ParMasGo3.setPreferredSize(new Dimension(315, 60));
        ParMasGo3.setFont(new Font("Verdana", Font.BOLD, 13));
        ParMasGo3.setForeground(Color.BLACK);
        ParMasGo3.add(label6);
        ParMasGo3.add(tabla);

        //PARTIDO CON MAS GOLES
        JTable tabla2 = new JTable();
        JLabel label7 = new JLabel("Partidos con mas goles");
        DefaultTableModel modeloTabla2 = resultadoDAO.partidosMasGoles();
        tabla2.setModel(modeloTabla2);
        JPanel ParMasGo4 = new JPanel();
        ParMasGo4.setBackground(Color.gray);
        ParMasGo4.setPreferredSize(new Dimension(315, 60));
        ParMasGo4.setFont(new Font("Verdana", Font.BOLD, 13));
        ParMasGo4.setForeground(Color.BLACK);
        ParMasGo4.add(label7);
        ParMasGo4.add(tabla2);

        //NUMEROS DE PARTIDOS DONDE HUBO GANADOR Y DONDE HUBO EMPATES
        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel("Partidos ganados: 0, Partidos empatados: 0");
        panel3.setBackground(Color.gray);
        panel3.setPreferredSize(new Dimension(340, 40));
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setFont(new Font("Verdana", Font.BOLD, 13));
        label3.setForeground(Color.BLACK);
        int[] resultados = resultadoDAO.partidoGanEmp();
        int partidosGanados = resultados[0];
        int partidosEmpatados = resultados[1];
        label3.setText("Partidos ganados: " + partidosGanados + ", Partidos empatados: " + partidosEmpatados);
        panel3.add(label3);

        //SELECCIONES CON MAS GOLES
        JTable table2 = new JTable(new DefaultTableModel());
        JLabel label4 = new JLabel("Selecciones con mas goles");
        String[] selecciones4 = resultadoDAO.seleccionesMasGoles();
        DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
        model2.addColumn("Selección");
        for (String seleccion : selecciones4) {
            model2.addRow(new Object[]{seleccion});
        }

        JPanel SelMasGo = new JPanel();
        SelMasGo.setBackground(Color.gray);
        SelMasGo.setPreferredSize(new Dimension(210, 59));
        SelMasGo.setFont(new Font("Verdana", Font.BOLD, 13));
        SelMasGo.setForeground(Color.BLACK);
        SelMasGo.add(label4);
        SelMasGo.add(table2);

        //SELECCIONES CON MENOS GOLES
        JTable table3 = new JTable(new DefaultTableModel());
        JLabel label5 = new JLabel("Selecciones con menos goles");
        String[] selecciones3 = resultadoDAO.seleccionesMenosGoles();
        DefaultTableModel model = (DefaultTableModel) table3.getModel();
        model.addColumn("Selección");
        for (String seleccion : selecciones3) {
            model.addRow(new Object[]{seleccion});
        }
        JPanel SelMasGo2 = new JPanel();
        SelMasGo2.setBackground(Color.gray);
        SelMasGo2.setPreferredSize(new Dimension(170, 250));
        SelMasGo2.setFont(new Font("Verdana", Font.BOLD, 13));
        SelMasGo2.setForeground(Color.BLACK);
        SelMasGo2.add(label5);
        SelMasGo2.add(table3);

        //SELECCION CON MAS PUNTOS Y MENOS PUNTOS
        JTable table4 = new JTable();
        JLabel label8 = new JLabel("Selecciones con mas puntos y menos puntos");
        String[] selecciones5 = resultadoDAO.seleccionesMayorYMenorPuntuacion();
        DefaultTableModel model3 = (DefaultTableModel) table4.getModel();
        model3.addColumn("Equipo");
        model3.addColumn("Puntos");
        for (String seleccion : selecciones5) {
            model3.addRow(new Object[]{seleccion});
        }
        JPanel SelMasMenosPuntos = new JPanel();
        SelMasMenosPuntos.setBackground(Color.gray);
        SelMasMenosPuntos.setPreferredSize(new Dimension(255, 97));
        SelMasMenosPuntos.setFont(new Font("Verdana", Font.BOLD, 13));
        SelMasMenosPuntos.setForeground(Color.BLACK);
        SelMasMenosPuntos.add(label8);
        SelMasMenosPuntos.add(table4);

        //CONTINENTE CON MAS GOLES 
        JTable table5 = new JTable();
        JLabel label9 = new JLabel("Continente con mas goles");
        String[] selecciones6 = resultadoDAO.continentesMayorPuntos();
        DefaultTableModel model4 = (DefaultTableModel) table5.getModel();
        model4.addColumn("Equipo");
        //model4.addColumn("Puntos");
        for (String seleccion : selecciones6) {
            model4.addRow(new Object[]{seleccion});
        }
        JPanel ContinenteMasGoles = new JPanel();
        ContinenteMasGoles.setBackground(Color.gray);
        ContinenteMasGoles.setPreferredSize(new Dimension(240, 50));
        ContinenteMasGoles.setFont(new Font("Verdana", Font.BOLD, 13));
        ContinenteMasGoles.setForeground(Color.BLACK);
        ContinenteMasGoles.add(label9);
        ContinenteMasGoles.add(table5);

        //CONTINENTE CON MENOS GOLES
        JTable table6 = new JTable();
        JLabel label10 = new JLabel("Continente con menos goles");
        String[] selecciones7 = resultadoDAO.continentesMenorPuntos();
        DefaultTableModel model5 = (DefaultTableModel) table6.getModel();
        model5.addColumn("Equipo");
        //model5.addColumn("Puntos");
        for (String seleccion : selecciones7) {
            model5.addRow(new Object[]{seleccion});
        }
        JPanel ContinenteMenosGoles = new JPanel();
        ContinenteMenosGoles.setBackground(Color.gray);
        ContinenteMenosGoles.setPreferredSize(new Dimension(220, 60));
        ContinenteMenosGoles.setFont(new Font("Verdana", Font.BOLD, 13));
        ContinenteMenosGoles.setForeground(Color.BLACK);
        ContinenteMenosGoles.add(label10);
        ContinenteMenosGoles.add(table6);

        jPanelMain.removeAll();
        //NUMERO DE PARTIDOS
        jPanelMain.add(panel1);
        //PROMEDIO DE GOLES POR PARTIDO
        jPanelMain.add(panel2);
        //PARTIDO CON MENOS GOLES
        jPanelMain.add(ParMasGo3);
        //PARTIDO CON MAS GOLES
        jPanelMain.add(ParMasGo4);
        //NUMEROS DE PARTIDOS DONDE HUBO GANADOR Y DONDE HUBO PERDEDOR
        jPanelMain.add(panel3);
        //SELECCIONES CON MAS GOLES
        jPanelMain.add(SelMasGo);
        //SELECCIONES CON MENOS GOLES
        jPanelMain.add(SelMasGo2);
        //SELECCION CON MAS PUNTOS Y MENOS PUNTOS
        jPanelMain.add(SelMasMenosPuntos);
        //CONTINENTE CON MAS GOLES
        jPanelMain.add(ContinenteMasGoles);
        //CONTINENTE CON MENOS GOLES
        jPanelMain.add(ContinenteMenosGoles);

        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if (seleccionDAO.registrarSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                } else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }

            selecciones = seleccionDAO.getSeleccionesMatriz();
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda(field.getText());
                /* List<Resultados>resultadosBusqueda=resultadosDAO.getResultadosBusqueda(field.getText());*/

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

// Agregar las filas correspondientes a la lista de selecciones
                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar'");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field.setText("");
                List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda("");

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

                table.setModel(modeloTabla);
            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Resultados resultados = new Resultados(columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7]);
                if (resultadoDAO.registrarResultados(resultados)) {
                    System.out.println("Resultado " + resultados.getGrupo() + " registrada");
                } else {
                    System.out.println("Error " + resultados.getGrupo());
                }

            }
            resultados = resultadoDAO.getResultadosMatriz();
            pintarTablaResultados();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * Resultados que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                //   List<Resultados> ResultadosBusqueda = ResulltadosDAO.
                List<Resultados> resultadosBusqueda = resultadoDAO.getResultadosBusqueda(field.getText());

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinente_local(), resultado.getContinente_visitante(), resultado.getGoles_locales(), resultado.getGoles_visitante()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                field.setText("");
                List<Resultados> resultadosBusqueda = resultadoDAO.getResultadosBusqueda("");

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinente_local(), resultado.getContinente_visitante(), resultado.getGoles_locales(), resultado.getGoles_visitante()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(670, 570)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());

        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(650, 670)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}
