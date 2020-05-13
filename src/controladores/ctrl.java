package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vista.view;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisangel, mariameza, romanmontgomery
 */
public class ctrl implements ActionListener{

    private final view view;
    String texto;
    private String[] lineas;
    Queue<String> tabla_lexica=new LinkedList();
    Stack<String> pila = new Stack<>(); 
    Stack<String> pila2 = new Stack<>();
    Stack<String> pila3 = new Stack<>();
    String tabla_sintactica[][] = new String[26][37];
    String primeros[][]= new String[22][2];
    String X = "";
    String K = "";
    String auxLinea = "";
    int lineaError = 0;
    String APUN = "";
    boolean f = true;
    private boolean comillas = false;
    public String constant = "";
    private String sentencia = ""; 
    private int aux_sem = 0;
    private DefaultTableModel modelo = new DefaultTableModel();
    
    public ctrl (view view) {
        this.view = view;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.escanear.addActionListener(this);
        //--------COLUMNAS-----------
        tabla_sintactica[0][1] = "Ć";
        tabla_sintactica[0][2] = "Ä";
        tabla_sintactica[0][3] = "Á";
        tabla_sintactica[0][4] = "Đ";
        tabla_sintactica[0][5] = "É";
        tabla_sintactica[0][6] = "Ë";
        tabla_sintactica[0][7] = "Ï";
        tabla_sintactica[0][8] = "Ö";
        tabla_sintactica[0][9] = "Ü";
        tabla_sintactica[0][10] = "U";
        tabla_sintactica[0][11] = "Ô";
        tabla_sintactica[0][12] = "Í";
        tabla_sintactica[0][13] = "Î";
        tabla_sintactica[0][14] = "Ó";
        tabla_sintactica[0][15] = "Ê";
        tabla_sintactica[0][16] = "Ń";
        tabla_sintactica[0][17] = "Q";
        tabla_sintactica[0][18] = "A";
        tabla_sintactica[0][19] = "B";
        tabla_sintactica[0][20] = "D";
        tabla_sintactica[0][21] = "C";
        tabla_sintactica[0][22] = "E";
        tabla_sintactica[0][23] = "F";
        tabla_sintactica[0][24] = "H";
        tabla_sintactica[0][25] = "G";
        tabla_sintactica[0][26] = "I";
        tabla_sintactica[0][27] = "J";
        tabla_sintactica[0][28] = "K";
        tabla_sintactica[0][29] = "V";
        tabla_sintactica[0][30] = "L";
        tabla_sintactica[0][31] = "M";
        tabla_sintactica[0][32] = "N";
        tabla_sintactica[0][33] = "O";
        tabla_sintactica[0][34] = "P";
        tabla_sintactica[0][35] = "R";
        tabla_sintactica[0][36] = "T";
        
        //--------FILAS---------------
        tabla_sintactica[1][0] = "s";
        tabla_sintactica[2][0] = "f";
        tabla_sintactica[3][0] = "w";
        tabla_sintactica[4][0] = "n";
        tabla_sintactica[5][0] = "y";
        tabla_sintactica[6][0] = "o";
        tabla_sintactica[7][0] = "c";
        tabla_sintactica[8][0] = "h";
        tabla_sintactica[9][0] = "u";
        tabla_sintactica[10][0] = "e";
        tabla_sintactica[11][0] = "b";
        tabla_sintactica[12][0] = "p";
        tabla_sintactica[13][0] = "j";
        tabla_sintactica[14][0] = "l";
        tabla_sintactica[15][0] = "m";
        tabla_sintactica[16][0] = ",";
        tabla_sintactica[17][0] = ".";
        tabla_sintactica[18][0] = ")";
        tabla_sintactica[19][0] = "'";
        tabla_sintactica[20][0] = "r";
        tabla_sintactica[21][0] = "d";
        tabla_sintactica[22][0] = "a";
        tabla_sintactica[23][0] = "*";
        tabla_sintactica[24][0] = "i";
        tabla_sintactica[25][0] = "$";
        
        
        
        //-----------Intersecciones por columna----------
        tabla_sintactica[1][17] = "sAfFJ";
        
        tabla_sintactica[2][20] = "z";
        tabla_sintactica[2][22] = "z";
        
        tabla_sintactica[3][24] = "z";
        tabla_sintactica[3][26] = "z";
        tabla_sintactica[3][27] = "wK";
        
        tabla_sintactica[4][22] = "z";
        tabla_sintactica[4][31] = "n(Q)";
        
        tabla_sintactica[5][22] = "z";
        tabla_sintactica[5][29] = "PK";
        tabla_sintactica[5][34] = "y";
        
        tabla_sintactica[6][22] = "z";
        tabla_sintactica[6][29] = "PK";
        tabla_sintactica[6][34] = "O";
        
        tabla_sintactica[7][1] = "cti(Á);Ä";
        tabla_sintactica[7][2] = "Ć";
        tabla_sintactica[7][16] = "Ć";
        
        tabla_sintactica[8][4] = "h";
        
        tabla_sintactica[9][4] = "u";
        
        tabla_sintactica[10][5] = "eg";
        
        tabla_sintactica[11][7] = "Ö";
        tabla_sintactica[11][8] = "biÜ(i)U";
        
        tabla_sintactica[12][9] = "pk";
        
        tabla_sintactica[13][9] = "jk";
        
        tabla_sintactica[14][10] = "li(i)Ô";
        
        tabla_sintactica[15][2] = "Í";
        tabla_sintactica[15][12] = "mqiv(Î);Ń"; //
        tabla_sintactica[15][16] = "Í";
        
        tabla_sintactica[16][5] = "z";
        tabla_sintactica[16][6] = ",Ï";
        tabla_sintactica[16][10] = ",Ö";
        tabla_sintactica[16][11] = ",Ö";
        tabla_sintactica[16][15] = ",Î";
        tabla_sintactica[16][20] = ",B";
        tabla_sintactica[16][22] = "z";
        tabla_sintactica[16][24] = ",F";
        tabla_sintactica[16][26] = "z";
        
        tabla_sintactica[17][22] = ".i";
        
        tabla_sintactica[18][6] = "z";
        tabla_sintactica[18][10] = "z";
        tabla_sintactica[18][11] = "z";
        tabla_sintactica[18][15] = "z";
        tabla_sintactica[18][22] = "z";
        tabla_sintactica[18][24] = "z";
        tabla_sintactica[18][26] = "z";
        tabla_sintactica[18][27] = "z";
        tabla_sintactica[18][29] = "z";
        
        tabla_sintactica[19][13] = "ÓÊ";
        tabla_sintactica[19][14] = "'a'";
        tabla_sintactica[19][33] = "'R'";
        
        tabla_sintactica[20][22] = "z";
        tabla_sintactica[20][31] = "NO";
        tabla_sintactica[20][32] = "r";
        
        tabla_sintactica[21][13] = "ÓÊ";
        tabla_sintactica[21][14] = "D";
        tabla_sintactica[21][33] = "T";
        tabla_sintactica[21][36] = "d";
        
        tabla_sintactica[22][35] = "a";
        
        tabla_sintactica[23][18] = "*";
        
        tabla_sintactica[24][3] = "iĐ(d)ÉË";
        tabla_sintactica[24][7] = "Á";
        tabla_sintactica[24][18] = "B";
        tabla_sintactica[24][19] = "CD";
        tabla_sintactica[24][21] = "iE";
        tabla_sintactica[24][23] = "GH";
        tabla_sintactica[24][25] = "iI";
        tabla_sintactica[24][26] = "i";
        tabla_sintactica[24][28] = "LV";
        tabla_sintactica[24][30] = "CM";
        tabla_sintactica[24][33] = "C";
        
        tabla_sintactica[25][2] = "z";
        tabla_sintactica[25][16] = "z";
        tabla_sintactica[25][20] = "z";
        tabla_sintactica[25][22] = "z";
        tabla_sintactica[25][24] = "z";
        tabla_sintactica[25][26] = "z";
        tabla_sintactica[25][27] = "z";
        tabla_sintactica[25][29] = "z";
        
        
        
        
        primeros[0][0] = "Q";
        primeros[1][0] = "A";
        primeros[2][0] = "B";
        primeros[3][0] = "D";
        primeros[4][0] = "C";
        primeros[5][0] = "E";
        primeros[6][0] = "F";
        primeros[7][0] = "H";
        primeros[8][0] = "G";
        primeros[9][0] = "I";
        primeros[10][0] = "J";
        primeros[11][0] = "K";
        primeros[12][0] = "V";
        primeros[13][0] = "L";
        primeros[14][0] = "M";
        primeros[15][0] = "N";
        primeros[16][0] = "O";
        primeros[17][0] = "P";
        primeros[18][0] = "R";
        primeros[19][0] = "T";
        primeros[20][0] = "f";
        primeros[21][0] = "i";
        
        primeros[0][1] = "s";
        primeros[1][1] = "i";
        primeros[2][1] = "i";
        primeros[3][1] = "a";
        primeros[4][1] = "i";
        primeros[5][1] = "s";
        primeros[6][1] = "i";
        primeros[7][1] = ",";
        primeros[8][1] = "i";
        primeros[9][1] = "i";
        primeros[10][1] = "w";
        primeros[11][1] = "i";
        primeros[12][1] = "y";
        primeros[13][1] = "i";
        primeros[14][1] = "r";
        primeros[15][1] = "r";
        primeros[16][1] = "i";
        primeros[17][1] = "y";
        primeros[18][1] = ".";
        primeros[19][1] = "d";
        primeros[20][1] = "f";
        primeros[21][1] = "i";
        
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
     
        this.view.errores.setText("3:300 Sin error.");
        
        texto = this.view.entrada.getText();
        sentencia = this.view.entrada.getText().replaceAll("\n", " ");
        lineas = new String [50];
        
        for (int i = 0; i < lineas.length; i++) {
            lineas [i] = "";
        }
        
        pila.clear();
        pila2.clear();
        pila3.clear();
        tabla_lexica.clear();
        X = "";
        K = "";
        APUN = "";
        f = true;
        comillas = false;
        constant = "";
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        this.view.repaint();
        
        tabla_lexica();
        analisis();
    }
    
    /*Este metodo analiza toda la entrada con REGEX sepandando por lineas e insertando en la TABLA LEXICA
    cada elemento que se encuentra, si hay algún elemento invalido, lo mostrará y terminara la ejecución*/
    public void tabla_lexica () {
        int cont = 0;
        String lineas [] = texto.split("\n");
        
        int linea = 1;
        
        Pattern p1 = Pattern.compile("(([>\\<])+([=])|[(\\)\\;\\+\\-\\–\\*\\/\\'\\’\\‘\\,\\.\\>\\<\\=]|([@0-9A-Za-z]*)+([#\\.\\%\\_\\-]*)?[0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)"
                + "|([@A-Za-z]*)+([#\\%\\_\\-]*)?[A-Za-z0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)|[!\\$\\%\\&\\?\\¿\\¡\\_]|[a-zA-Z])");
        
        for (int i = 0;i<lineas.length;i++) {
            Matcher m2 = p1.matcher(lineas[i]);
            while (m2.find()){
                cont++;
                if(m2.group().matches("[(\\)\\;\\'\\’\\‘\\,\\.]")){
                    
                    if (m2.group().matches("['\\’\\‘]")) {              //Delimitadores
                        if (comillas) {
                            comillas = false;
                            if (!constant.equals("") && !comillas) {
                           constant = "";
                           tabla_lexica.add("a");                       //Cosntante alfanumerica
                           cont++;
                            } 
                        } else {
                            comillas = true;
                        }
                    }
                    tabla_lexica.add(m2.group());
                    
                } else if(m2.group().matches("[+\\-\\–\\*\\/]")){            //Operadores
                    tabla_lexica.add(m2.group());
                } else if(m2.group().matches("[>\\<\\=]|([>\\<])+([=])")){   //Relacionales
                    tabla_lexica.add("r");
                } else if(m2.group().matches("SELECT|FROM|WHERE|IN|AND|OR|CREATE|TABLE|CHAR|NUMERIC|NOT|NULL|CONSTRAINT|KEY|PRIMARY|FOREIGN|REFERENCES|INSERT|INTO|VALUES|GO|CREATE|PROCEDURE|VARCHAR"
                        + "|AS|IF|EXISTS|BEGIN|PRINT|END|ELSE"))             //Palabras reservadas
                {    
                    tabla_lexica.add(m2.group());
                    
                } else if(m2.group().matches("(^[@A-Za-z0-9]*)+([s\\,\\%\\-\\#]*)?[0-9A-Za-z]+([s\\,\\#\\%\\-\\_\\[0-9A-Za-z]*)?([s\\,\\#\\%\\-\\_\\[0-9A-Za-z]*)")){   
                    if (comillas | m2.group().matches("([0-9]*)")) {         //Identificadores y constantes
                       if (comillas) {
                           constant += " " + m2.group();                    
                           cont--;    
                       } else {
                           tabla_lexica.add("d");                            //Constante decimal
                       }
                    } else {
                       tabla_lexica.add("i");                                //Identificador
                    }
                } else if(m2.group().matches("[!\\#\\%\\&\\?\\¿\\¡]")){
                     this.view.errores.setText("1:101 Error en Línea " + linea + ": Símbolo desconocido. ( " + m2.group() + ")");
                     i = lineas.length;
                     break;
                } else {
                    this.view.errores.setText("1:102 Error en Línea " + linea + ": Elemento inválido. ( " + m2.group() + " )");
                    i = lineas.length;
                    break;
                }
            }
            linea++;
        }
    }
    
    
    
    //ALGORITMO LL
    public void analisis(){
        pila.add("$");
        if (corrector().equals("s")) {       //Select
            pila.add("Q");
            aux_sem = 1;
        } else if (corrector().equals("m")){ //Insert
            pila.add("Ä");
            aux_sem = 2;
        } else {                             //Create
            pila.add("Ć");
            aux_sem = 3;
        }
        
        tabla_lexica.add("$");
        APUN = tabla_lexica.peek();
        
        do {
            X = pila.pop();
            //System.out.print("X = " + X + "  ----  ");
            K = corrector();
            //System.out.println("K = " + K);
            if (terminal(X) || X.equals("$")) {
                if ( X.equals(K)) {
                    //System.out.print("X = " + X + "  ----  ");
                    //System.out.println("K = " + K);
                    tabla_lexica.remove();
                } else {
                    //System.out.println(X + " --" + K);
                    error(X);
                    System.out.println("Error 1");
                    break;
                }
            } else {
                String produccion = Produc(X, K);
                if (produccion != null) {
                    if (produccion != "z") {
                        insertar(produccion);
                    }
                } else {
                    error(X);
                    System.out.println("Error 2");
                    break;
                }
            }
            
        } while (X != "$");
        
        if (aux_sem == 1) {
            ConectionSelect();
        } else if (aux_sem == 2) {
            ConectionInsert();
        } else if (aux_sem == 3) {
            ConectionCreate();
        }
    }
    
    
    
    //Conexiones a la BD segun el tipo de sentencia
    public void ConectionSelect() {
        Connection conn = null;
        System.out.println(sentencia);
        try {
            conn = coonn.getConnection();
            String query = sentencia;
            PreparedStatement  pstm = conn.prepareStatement(query);
            
            ResultSet rs = pstm.executeQuery();
            
            this.view.tabla.setModel(modelo);
            int auxSize = 0;
            
            Pattern p1 = Pattern.compile("(([>\\<])+([=])|[(\\)\\;\\+\\-\\–\\*\\/\\'\\’\\‘\\,\\.\\>\\<\\=]|([@0-9A-Za-z]*)+([#\\.\\%\\_\\-]*)?[0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)"
                + "|([@A-Za-z]*)+([#\\%\\_\\-]*)?[A-Za-z0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)|[!\\$\\%\\&\\?\\¿\\¡\\_]|[a-zA-Z])");
        
            Matcher m2 = p1.matcher(query);
            while (m2.find()) {
                if(m2.group().matches("ALUMNOS|CARRERAS|DEPARTAMENTOS|INSCRITOS|MATERIAS|PROFESORES")){
                    String key = m2.group();
                    switch (key) {
                        case "ALUMNOS": 
                            auxSize = 5;
                            modelo.setColumnCount(0);
                            modelo.addColumn("A");
                            modelo.addColumn("ANOMBRE");
                            modelo.addColumn("GENERACION");
                            modelo.addColumn("SEXO");
                            modelo.addColumn("FK_AC");
                        break;
                        case "CARRERAS": 
                            auxSize = 5;
                            modelo.setColumnCount(0);
                            modelo.addColumn("C");
                            modelo.addColumn("CNOMBRE");
                            modelo.addColumn("VIGENCIA");
                            modelo.addColumn("SEMESTRES");
                            modelo.addColumn("FK_CD");
                        break;
                        case "DEPARTAMENTOS": 
                            auxSize = 2;
                            modelo.setColumnCount(0);
                            modelo.addColumn("D");
                            modelo.addColumn("DNOMBRE");
                        break;
                        case "INSCRITOS": 
                            auxSize = 7;
                            modelo.setColumnCount(0);
                            modelo.addColumn("R");
                            modelo.addColumn("FK_RA");
                            modelo.addColumn("FK_RM");
                            modelo.addColumn("FK_RP");
                            modelo.addColumn("TURNO");
                            modelo.addColumn("SEMESTRE");
                            modelo.addColumn("CALIFICACION");
                        break;
                        case "MATERIAS": 
                            auxSize = 4;
                            modelo.setColumnCount(0);
                            modelo.addColumn("M");
                            modelo.addColumn("MNOMBRE");
                            modelo.addColumn("CREDITOS");
                            modelo.addColumn("FK_MC");
                        break;
                        case "PROFESORES": 
                            auxSize = 7;
                            modelo.setColumnCount(0);
                            modelo.addColumn("P");
                            modelo.addColumn("PNOMBRE");
                            modelo.addColumn("EDAD");
                            modelo.addColumn("SEXO");
                            modelo.addColumn("ESP");
                            modelo.addColumn("GRADO");
                            modelo.addColumn("FK_PD");
                        break;
                        default: 
                            break;
                    }
                }
            }
            
            
            while(rs.next() ) {
                Object [] row = new Object[auxSize];
                for (int i = 0; i<auxSize; i ++) {
                        System.out.println(rs.getRow());
                        //row[i] = rs.getObject(i+1);
                    
                }
                modelo.addRow(row);
            }
            System.out.println(sentencia);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found in SELECT sentence");
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION in SELECT sentence");
            System.out.println(ex.toString());
        }
    }
    
    public void ConectionInsert() {
        Connection conn = null;
        
        try {
            conn = coonn.getConnection();
            String query = sentencia;
            PreparedStatement  pstm = conn.prepareStatement(query);
            
            ResultSet rs = pstm.executeQuery();
            
            System.out.println(sentencia);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found on INSERT sentence");
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION on INSERT sentence");
            System.out.println(ex.toString());
        }
    }
    
    public void ConectionCreate() {
        Connection conn = null;
        
        try {
            conn = coonn.getConnection();
            String query = sentencia;
            CallableStatement  cb = conn.prepareCall(query);
            
            cb.execute();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found on CREATE sentence");
            System.out.println(ex.toString());
        } catch (SQLException ex) {
            System.out.println("SQLException on CREATE sentence");
            System.out.println(ex.toString());
        }
    }
    
    
    
    //ESTE METODO BUSCA Y DEVUELVE LA INTERSECCION DE X,K EN LA TABLA SINTACTICA
    public String Produc (String x, String k) {
        
        int ka = 0;
        for (int i = 0; i < 26; i ++) {
            if (k.equals(tabla_sintactica[i][0])) {
                ka = i;
            }
        }
        
        int equis = 0;
        for (int i = 0; i < 37; i ++) {
            if (x.equals(tabla_sintactica[0][i])) {
                equis = i;
            }
        }
        //System.out.println("Producto de x: " + equis + " k: " + ka);
        String result = tabla_sintactica[ka][equis];
        //System.out.println("Encontro :  " + result);
        return result;
    }
    
    //ESTE METODO INSERTA EN LA PILA LAS PRODUCCIONES DE MANERA INVERSA
    public void insertar (String p) {
            int x = p.length();
            for (int i = x-1; i >=0; i--) {
                //System.out.println(p.charAt(i));
                pila.add(p.charAt(i)+"");
            }
    }
    
    //DETERMINA SI EL VALOR EXTRAIDO ES UN TERMINAL, SI EL VALOR QUE RECIBE EL METODO ES IGUAL A UNA REGLA, 
    //DEVUELVE FALSE, SI NO DEVUELVE TRUE.
    public boolean terminal (String x) {
        if (x.equals("Ć") || x.equals("Ä") || x.equals("Á") || x.equals("Đ") || x.equals("É") || x.equals("Ë") || x.equals("Ï") || x.equals("Ö") || x.equals("Ü") || x.equals("U") || x.equals("Ô") || x.equals("Í") || x.equals("Î") || x.equals("Ó") || x.equals("Ê") || x.equals("Ń") || x.equals("Q") || x.equals("A") || x.equals("B") || x.equals("D") || x.equals("C") || x.equals("E") || x.equals("F") || x.equals("H") || x.equals("G") || x.equals("I") || x.equals("J") || x.equals("K") || x.equals("V") || x.equals("L") || x.equals("M") || x.equals("N") || x.equals("O") || x.equals("P") || x.equals("R") || x.equals("T")) {
            return false;
        } else {
            return true;
        }
    }
    
    //ESTE METODO SEPARA LAS PALABRAS RESERVADAS DE TODOS LOS DEMAS ELEMENTOS, SI NO ES PALABRA RESERVADA DEVUELVE AUTOMATICAMENTE EL TOKEN, 
    //SI ES PALABRA RESERVADA, ANALIZA QUE PALABRA ES Y DEVUELVE EL TOKEN CORRESPONDIENTE
    public String corrector () {
        String aux = tabla_lexica.peek();
        
        if (aux.equals("$") || aux.equals(".") || aux.equals("(") || aux.equals(")") || aux.equals(",") || aux.equals("'") || aux.equals("+") || aux.equals("-") || aux.equals("*") || aux.equals("/") || aux.equals("r") || aux.equals("i") || aux.equals("d") || aux.equals("a")) {
            return aux;
        } 
        
        Pattern p1 = Pattern.compile("(([>\\<])+([=])|[(\\)\\;\\+\\-\\–\\*\\/\\'\\’\\‘\\,\\.\\>\\<\\=]|([@0-9A-Za-z]*)+([#\\.\\%\\_\\-]*)?[0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)"
                + "|([@A-Za-z]*)+([#\\%\\_\\-]*)?[A-Za-z0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)|[!\\$\\%\\&\\?\\¿\\¡\\_]|[a-zA-Z])");
        
        Matcher m2 = p1.matcher(aux);
        while (m2.find()) {
            if(m2.group().matches("SELECT|FROM|WHERE|IN|AND|OR|CREATE|TABLE|CHAR|NUMERIC|NOT|NULL|CONSTRAINT|KEY|PRIMARY|FOREIGN|REFERENCES|INSERT|INTO|VALUES|GO|CREATE|PROCEDURE|VARCHAR"
                        + "|AS|IF|EXISTS|BEGIN|PRINT|END|ELSE"))            //Palabras reservadas
                {
                String pal = m2.group();
                switch (pal) {
                    case "SELECT": aux = "s";
                                    auxLinea = m2.group();
                        break;
                    case "FROM": aux = "f";
                                    auxLinea = m2.group();
                        break;
                    case "WHERE": aux = "w";
                                    auxLinea = m2.group();
                        break;
                    case "IN": aux = "n";
                                    auxLinea = m2.group();
                        break;
                    case "AND": aux = "y";
                                    auxLinea = m2.group();
                        break;
                    case "OR": aux = "o";
                                    auxLinea = m2.group();
                        break;
                    case "CREATE": aux = "c";
                                    auxLinea = m2.group();
                        break;
                    case "TABLE": aux = "t";
                                    auxLinea = m2.group();
                        break;
                    case "CHAR": aux = "h";
                                    auxLinea = m2.group();
                        break;
                    case "NUMERIC": aux = "u";
                                    auxLinea = m2.group();
                        break;
                    case "NOT": aux = "e";
                                    auxLinea = m2.group();
                        break;
                    case "NULL": aux = "g";
                                    auxLinea = m2.group();
                        break;
                    case "CONSTRAINT": aux = "b";
                                    auxLinea = m2.group();
                        break;
                    case "KEY": aux = "k";
                                    auxLinea = m2.group();
                        break;
                    case "PRIMARY": aux = "p";
                                    auxLinea = m2.group();
                        break;
                    case "FOREIGN": aux = "j";
                                    auxLinea = m2.group();
                        break;
                    case "REFERENCES": aux = "l";
                                    auxLinea = m2.group();
                        break;
                    case "INSERT": aux = "m";
                                    auxLinea = m2.group();
                        break;
                    case "INTO": aux = "q";
                                    auxLinea = m2.group();
                        break;
                    case "VALUES": aux = "v";
                                    auxLinea = m2.group();
                        break;
                }
            }
        }
        return aux;
    }
    
    //Este metodo analiza los errores separado en 3 modulos, primero llama al metodo de error lexico que esta en su propia clase, 
    //Si no fue error lexico se busca un error sintactico, si no fue error sintactico se llama al metodo de error semantico en la clase de este error
    public void error(String dato) {
        errorLinea();
        //errorLexico();
        
        System.out.println("Error recibe : " + dato);
        String prim = "";
        
        for (int i = 0; i < 22; i++) {
            if (dato.equals(primeros[i][0])) {
                prim = primeros[i][1];
            }
        }
        
        switch(prim) {
            case "s": this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Se esperaba palabra reservada.");
                break;
            case "i": this.view.errores.setText("2:204 " + "Linea " + lineaError + ".Se esperaba identificador.");
                break;
            case ",": this.view.errores.setText("2:205 " + "Linea " + lineaError + ".Se esperaba delimitador.");
                break;
            case ".": this.view.errores.setText("2:205 " + "Linea " + lineaError + ".Se esperaba delimitador.");
                break;
            case "w": this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Se esperaba palabra reservada.");
                break;
            case "y": this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Se esperaba palabra reservada.");
                break;
            case "r": this.view.errores.setText("2:208 " + "Linea " + lineaError + ".Se esperaba palabra operador relacional.");
                break;
            case "a": this.view.errores.setText("2:206 " + "Linea " + lineaError + ".Se esperaba constante.");
                break;
            case "d": this.view.errores.setText("2:206 " + "Linea " + lineaError + ".Se esperaba constante.");
                break;
            case "f": this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Se esperaba palabra reservada.");
                break;
            default: this.view.errores.setText("2:299 " + "Linea " + lineaError + ".Error sintactico");
                break;
        }
        
        
        //errorSemantico();
    }
    
    public void errorLinea() {
        System.out.println(auxLinea);
        String lineas [] = texto.split("\n");
        int linea = 1;
        
        for (int i = 0;i<lineas.length;i++) {
            Pattern p1 = Pattern.compile("(([>\\<])+([=])|[(\\)\\;\\+\\-\\–\\*\\/\\'\\’\\‘\\,\\.\\>\\<\\=]|([@0-9A-Za-z]*)+([#\\.\\%\\_\\-]*)?[0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)"
                    + "|([@A-Za-z]*)+([#\\%\\_\\-]*)?[A-Za-z0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)|[!\\$\\%\\&\\?\\¿\\¡\\_]|[a-zA-Z])");
            Matcher m2 = p1.matcher(lineas[i]);
            while (m2.find()) {
                if(m2.group().matches(auxLinea)) {
                    lineaError = linea;
                }
            }
            linea++;
        }
    }
    
    
}

