package earth.app;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import earth.app.interfaces.SettingExceptions;

//components
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

//events
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/*Klasa DataPanel.*/
class DataPanel extends JPanel implements SettingExceptions {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // parent
    CoordinatesPanel parent;

    // objects
    MyImageLabel imageLabel;
    MyTextLabel textLabel;
    MyTextField textField;

    /* Konstruktor. */
    public DataPanel(CoordinatesPanel parent, ImageIcon icon, String text, Font fontLabel, Font fontField) {

        // parent
        this.parent = parent;

        // objects
        imageLabel = new MyImageLabel(icon);
        textLabel = new MyTextLabel(text, fontLabel);
        textField = new MyTextField(fontField);

        // gridBagLayout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        // gbc.weightx = 1;
        // gbc.weighty = 1;

        // imageLabel
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(imageLabel, gbc);

        // textLabel
        gbc.gridy = 0;
        gbc.gridx = 1;
        add(textLabel, gbc);

        // textField
        gbc.gridy = 0;
        gbc.gridx = 2;
        add(textField, gbc);

        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setVisible(true);
    }

    /* Zwraca napis. */
    public String getText() {
        return textField.getText();
    }

    /* Ustawia napis. */
    public void setText(String value) {
        textField.setText(value);
    }

    /* Wlacza opcje wyjatku w textField. */
    public void setExceptionTrue() {
        textField.setExceptionTrue();
    }

    /* Wylacza opcje wyjatku w textField. */
    public void setExceptionFalse() {
        textField.setExceptionFalse();
    }

    // ImageLabel
    class MyImageLabel extends JLabel {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 1L;

        /* Konstruktor. */
        MyImageLabel(ImageIcon icon) {
            super(icon);
        }
    }

    // TextLabel
    class MyTextLabel extends JLabel {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 1L;

        /* Konstruktor. */
        MyTextLabel(String text, Font font) {
            super(text);
            setFont(font);
            setBackground(Color.DARK_GRAY);
            setForeground(Color.WHITE);
            setPreferredSize(new Dimension(110, 15));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
        }
    }

    // TextField
    class MyTextField extends JTextField {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 1L;
        boolean isException;

        /* Konstruktor. */
        MyTextField(Font font) {
            super();
            setFont(font);
            setPreferredSize(new Dimension(50, 15));
            setHorizontalAlignment(JTextField.CENTER);
            setExceptionFalse();
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setExceptionFalse();
                }
            });
        }

        /* Wlacza opcje wyjatku. */
        public void setExceptionTrue() {
            setBackground(Color.RED);
            setForeground(Color.WHITE);
            isException = true;
        }

        /* Wylacza opcje wyjatku. */
        public void setExceptionFalse() {
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.BLACK);
            setText("");
            if (isException == true) {
                parent.addException(-1);
                isException = false;
            }
        }
    }

}
