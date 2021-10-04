
package murcielagoo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.geom.*;

public class Paleta extends JFrame implements ActionListener {
    Coor panel;

    int xt = 0, yt = 0, Sy = 1, Sx = 1;
    Double θ = 0.0;
    JMenuBar bar = new JMenuBar();
    JMenu Trasladar, Escalar, Rotar;
    JTextField x, y, xa, ya, rot;
    JLabel tx, ty;
    JButton b, c, d;

    public Paleta() {
        super("Plano Cartesiano");
        panel = new Coor();
        add(panel);
        Componentes();
    }

    public void Componentes() {// JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Plano Cartesiano");
        setSize(400, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setJMenuBar(bar);
        Trasladar = new JMenu("Trasladar");
        Escalar = new JMenu("Escalar");
        Rotar = new JMenu("Rotar");
        bar.add(Trasladar);
        bar.add(Escalar);
        bar.add(Rotar);

        tx = new JLabel("Tx :");
        x = new JTextField(6);
        Trasladar.add(tx);
        Trasladar.add(x);

        ty = new JLabel("Ty :");
        y = new JTextField(6);
        Trasladar.add(ty);
        Trasladar.add(y);
        b = new JButton("Aceptar");
        Trasladar.add(b);
        b.addActionListener(this);

        tx = new JLabel("Sx :");
        xa = new JTextField(6);
        Escalar.add(tx);
        Escalar.add(xa);

        ty = new JLabel("Sy :");
        ya = new JTextField(6);
        Escalar.add(ty);
        Escalar.add(ya);
        c = new JButton("Aceptar");
        Escalar.add(c);
        c.addActionListener(this);

        tx = new JLabel("θ: ");
        rot = new JTextField(6);
        Rotar.add(tx);
        Rotar.add(rot);
        d = new JButton("Aceptar");
        d.addActionListener(this);
        Rotar.add(d);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            xt = Integer.parseInt(x.getText());
            yt = Integer.parseInt(y.getText());
            repaint();

        }

        if (e.getSource() == c) {
            Sx = Integer.parseInt(xa.getText());
            Sy = Integer.parseInt(ya.getText());
            repaint();

        }

        if (e.getSource() == d) {
            θ = Math.toRadians(Double.parseDouble(rot.getText()));
            repaint();
        }
    }

    public class Coor extends JPanel { // Plano cartesiano con la figura
        public double coord_x(double x) {
            double r_x = x + this.getWidth() / 2;
            return r_x;
        }

        public double coord_y(double y) {
            double r_y = -y + this.getHeight() / 2;
            return r_y;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Dibujo lineas del plano
            g.setColor(Color.BLACK);
            Line2D linea_y = new Line2D.Double(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
            Line2D linea_x = new Line2D.Double(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
            g2.draw(linea_x);
            g2.draw(linea_y);

            if (xt == 0 && yt == 0) {
                g2.translate(this.getWidth()/2, this.getHeight()/2);
            } else {
                g2.translate(coord_x(xt) , coord_y(yt));    
            }
            

            Mu mur = new Mu();

            GeneralPath figura = new GeneralPath();

            figura.moveTo(mur.x[0], mur.y[0]);

            for (int i = 0; i < mur.x.length; i++) {
                figura.lineTo(mur.x[i], mur.y[i] * -1);
            }

            figura.closePath();
            g2.rotate(θ);

            

            g2.scale(Sx, Sy);

            

            g2.fill(figura);

            if (xt > this.getWidth() || yt > this.getHeight()) {
                xt = 0;
                yt = 0;
                JOptionPane.showMessageDialog(null, "Saliste Pantalla ");
            }

        }
    }
}
