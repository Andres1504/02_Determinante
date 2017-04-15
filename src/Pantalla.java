

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Pantalla extends JFrame {

    private JComboBox cmbOrden;
    private String ordenes[] = {"Seleccione....", " 1 x 1 ", " 2 x 2 ", " 3 x 3 ",
        " 4 x 4 ", " 5 x 5 ", " 6 x 6 ", " 7 x 7 ", " 8 x 8 ", " 9 x 9 ", " 10 x 10 "};
    private Edit elementos[][];
    private JPanel pnlMatrizElementos;

    public Pantalla() {
        super("Determinante");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocation(100, 100);
        super.setSize(350, 100);
        super.setLayout(new BorderLayout());

        pnlMatrizElementos = new JPanel();
        super.add(pnlEncabezado(), BorderLayout.NORTH);
        super.add(pnlAcciones(), BorderLayout.SOUTH);
        super.add(pnlMatrizElementos, BorderLayout.CENTER);

        elementos = null;
        
        super.setVisible(true);
    }

    private JPanel pnlEncabezado() {
        JPanel pnlEncabezado = new JPanel();
        pnlEncabezado.setLayout(new BoxLayout(pnlEncabezado, BoxLayout.X_AXIS));
        cmbOrden = new JComboBox(ordenes);
        cmbOrden.setSelectedIndex(0);
        cmbOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generaEdit(((JComboBox) e.getSource()).getSelectedIndex());
            }
        });
        pnlEncabezado.add(new JLabel("Orden : "));
        pnlEncabezado.add(cmbOrden);
        return pnlEncabezado;
    }

    private JPanel pnlMatriz(int orden) {
        JPanel pnlMatriz = new JPanel();
        pnlMatriz.setLayout(null);
        elementos = new Edit[orden][orden];
        for (int i = 0; i < orden; i++) {
            for (int j = 0; j < orden; j++) {
                elementos[i][j] = new Edit();
                elementos[i][j].setBounds(20+i*50, 20+j*30, 50, 25);
                pnlMatriz.add(elementos[i][j]);
            }
            
        }
        super.setSize(orden*60 > 200 ? orden*60 : 200 , orden*30+100 > 200 ? orden*30+100 : 200 );
        return pnlMatriz;
    }

    private JPanel pnlAcciones() {
        JPanel pnlAcciones = new JPanel();
        pnlAcciones.setLayout(new BorderLayout());
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JButton btnCalcular = new JButton("Calcular!");
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
        pnlAcciones.add(btnCalcular, BorderLayout.EAST);
        pnlAcciones.add(new JPanel(), BorderLayout.CENTER);
        pnlAcciones.add(btnSalir, BorderLayout.WEST);
        return pnlAcciones;
    }

    private void generaEdit(int elementos) {
        super.remove(pnlMatrizElementos);
        System.out.println(elementos);
        pnlMatrizElementos = pnlMatriz(elementos);
        super.add(pnlMatrizElementos, BorderLayout.CENTER);
        super.repaint();
        
    }

    private void calcular() {
    //   Cmatrices mat = new Cmatrices();
    	double matriz[][] = new double[elementos.length][elementos.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = elementos[i][j].getDato();
               // System.out.println(elementos);
            }
        }
        double resultado = determinante(matriz);
        //DecimalFormat n = new DecimalFormat("#.##");
        JOptionPane.showMessageDialog(this, "El valor de la determinantes es: "+resultado);
        try{
			System.out.println("La determinante de la matriz es:" + determinante(matriz));
		}catch(Exception e){
			System.out.println("Todo esta mal");	
		}
    }

    
   
    
    
   private double determinante(double[][] matriz) {
        int size = matriz.length;
        if (size == 1) {
            return matriz[0][0];
        } else {
            boolean signo = true;
            double resultado = 0.0;
            for (int i = 0; i < size; i++) {
                if (signo) {
                    resultado += matriz[0][i] * determinante(submatriz(matriz, 0, i));
                } else {
                    resultado -= matriz[0][i] * determinante(submatriz(matriz, 0, i));
                }
                signo = !signo;
            }
            return resultado;
        }
    }

    private double[][] submatriz(double[][] matriz, int posX, int posY) {
        int size = matriz.length;
        double resultado[][] = new double[size - 1][size - 1];
        int x = 0, y = 0;
        for (int i = 0; i < size; i++) {
            if (i != posY) {
                for (int j = 0; j < size; j++) {
                    if (j != posX) {
                        resultado[x][y] = matriz[j][i];
                        x++;
                    }
                }
                x = 0;
                y++;
            }
        }
        return resultado;
    }

}
