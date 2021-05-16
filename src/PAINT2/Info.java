package PAINT2;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Info extends JDialog {

    JPanel botones, contenedor;
    JButton atras, siguiente, volver;
    JTextPane texto;
    JLabel titulo;
    int index = 0;

    String info[] = {
            "\u2023HERRAMIENTAS: \nTrasladar, rotar, deformar, escalar, reflejar a " +
                    "trav\u00E9s de un punto con acciones r\u00E1pidas. \n"+
                    "\u2023ATAJOS: \nTiene las transformaciones con valores predeterminados automatizadas con un clic. \n"+
                    "\u2023GR\u00C1FICOS: \nOpciones de relleno o color, textura, y las opciones delinea."
                    + "\u2023INFO: \nContiene \u00E9sta informaci\u00F3n,  as\u00ED como informaci\u00F3n acerca de los desarrolladores. \n",
            "TECLAS ACELERADORAS: \n\n\u2023ALT + R :Ejecuta la rotaci\u00F3n en el sentido de las manecillas del Reloj " +
                    "mediante un cuadro de di\u00E1logo.\n\u2023ALT + L :Ejecuta la rotaci\u00F3n en contra de las manecillas del " +
                    "Reloj mediante un cuadro de di\u00E1logo. \n\u2023ALT + S :Escala la figura a partir de un punto dado. \n"
                    + "\u2023ALT + M :Traslada la figura hasta el punto indicado mediante un cuadro de di\u00E1ogo. \n"
                    + "\u2023ALT + F :Refleja la figura al eje de las X, Y o X,Y. \n"
                    + "\u2023ALT + D :Deforma la figura a partir de un punto. \n"
                    + "\u2023ALT + I :Despliega las animaciones posibles que haremos. \n"
                    + "\u2023ALT + G :Color de relleno y gradientes.     \n"
                    + "\u2023ALT + T :Texturas.        \n"
                    + "\u2023ALT + O :Propiedades de contorno y l\u00EDnea. \n"
                    + "\u2023ALT + P :Acerca de los desarrolladores. \n",
            "\u2023CTRL + I :Ejecuta la rotaci\u00F3n en el sentido de las manecillas del Reloj n cantidad de grados. \n"
                    + "\u2023CTRL + D :Ejecuta la rotaci\u00F3n en contra de las manecillas del Reloj n cantidad de grados. \n"
                    + "\u2023CTRL + M :Hace un aumento de 1.01 a partir de un valor de escalamiento. \n"
                    + "\u2023CTRL + N :Hace una disminuci\u00F3n de .99 a partir de un valor de escalamiento. \n"
                    + "\u2023CTRL + X :Refleja la figura en el eje de las X. \n"
                    + "\u2023CTRL + Y :Refleja la figura en el eje de las Y. \n"
                    + "\u2023CTRL + R :Restaura la figura a sus valores determinados. \n"
                    + "\u2023F1 :Ayuda. \n"
                    + "\n      (Puedes ver estos atajos posicionando el cursor sobre las opciones) \n",
            "Por \u00FAltimo, cada figura presenta interaciones del rat\u00F3n como aumentar o disminuir su " +
                    "tama\u00F1o por la rueda del rat\u00F3n. O bien, rotar la figura en un sentido haciendo clic a un " +
                    "lado, incluso arrastar la figura. \n\nDiviertete y explora el programa. \n"
    };

    public Info(Main ref, boolean modal) {

        super(ref.v, modal);
        setSize(640, 480);
        setLocationRelativeTo(this);
        setUndecorated(true);
        getContentPane().setBackground(Color.GRAY);

        botones = new JPanel(new GridLayout(1, 3));
        atras = new JButton(new ImageIcon(getClass().getResource("/PAINT2/rec/atras.png")));
        atras.setEnabled(false);
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atras.setEnabled(true);
                siguiente.setEnabled(true);
                if (index > 0) index --;
                if (index == 0) atras.setEnabled(false);
                texto.setText(info[index]);
            }
        });
        siguiente = new JButton(new ImageIcon(getClass().getResource("/PAINT2/rec/adelante.png")));
        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguiente.setEnabled(true);
                atras.setEnabled(true);
                if (index < 3) index ++;
                if (index == 3) siguiente.setEnabled(false);
                texto.setText(info[index]);
            }
        });
        volver = new JButton(new ImageIcon(getClass().getResource("/PAINT2/rec/home.png")));
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(sa, .4f);
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);

        botones.add(atras);
        botones.add(volver);
        botones.add(siguiente);

        getContentPane().add(botones, BorderLayout.SOUTH);

        titulo = new JLabel("INFORMACI\u00D3N");
        titulo.setIcon(new ImageIcon(getClass().getResource("/PAINT2/rec/info.png")));
        titulo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        titulo.setForeground(Color.DARK_GRAY);
        getContentPane().add(titulo, BorderLayout.NORTH);

        texto = new JTextPane();
        texto.getStyledDocument().setParagraphAttributes(0, info.length, sa, false);
        texto.setText(info[index]);
        texto.setEnabled(false);
        texto.setOpaque(false);
        texto.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
        texto.setForeground(Color.BLACK);
        texto.setBounds(20, 20, 600, 400);

        contenedor = new JPanel();
        contenedor.setLayout(null);
        contenedor.add(texto);
        getContentPane().add(contenedor, BorderLayout.CENTER);

    }

    public void Mostrar() {
        setVisible(true);
    }

}