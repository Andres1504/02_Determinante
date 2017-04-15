
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.JTextField;


public class Edit extends JTextField implements KeyListener {

    double dato;

    public Edit() {
        super();
    }

    public double getDato() {
        if (super.getText().isEmpty()) {
            dato = 0;
        } else {
            dato = Double.valueOf(super.getText());
        }
        return dato;
    }

    public void setDato(double dato) {
        DecimalFormat n = new DecimalFormat("#.##");
        super.setText(n.format(dato));
        this.dato = dato;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if ((e.getKeyChar() < '0') || (e.getKeyChar() > '9')) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
