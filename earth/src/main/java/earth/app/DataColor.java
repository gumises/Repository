package earth.app;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

//components
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

//events
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/*Klasa DataColor.*/
class DataColor extends JPanel {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    // parent
    CoordinatesPanel parent;

    // objects
    MyImageLabel imageLabel;
    MyTextLabel textLabel;
    MyCheckBox checkBox;

    // icons
    ImageIcon colorTrueIcon;
    ImageIcon colorFalseIcon;

    /* Konstruktor. */
    public DataColor(CoordinatesPanel parent, ImageIcon colorTrueIcon, ImageIcon colorFalseIcon, String text,
            Font fontLabel) {

        // parent
        this.parent = parent;

        // icons
        this.colorTrueIcon = colorTrueIcon;
        this.colorFalseIcon = colorFalseIcon;

        // objects
        imageLabel = new MyImageLabel(colorTrueIcon);
        textLabel = new MyTextLabel(text, fontLabel);
        checkBox = new MyCheckBox(true);

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
        add(checkBox, gbc);

        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setVisible(true);
    }

    public void setIsColor(boolean isColor) {
        imageLabel.setIsColor(isColor);
        parent.setIsColor(isColor);
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

        /* Ustawia ikone true-kolorowa, false-czarno/biala */
        public void setIsColor(boolean isColor) {

            if (isColor) {
                setIcon(colorTrueIcon);
            } else {
                setIcon(colorFalseIcon);
            }
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

    // CheckBox
    class MyCheckBox extends JCheckBox {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = 1L;

        /* Konstruktor. */
        public MyCheckBox(boolean isSelected) {
            super("", isSelected);
            setPreferredSize(new Dimension(50, 15));
            setBackground(Color.DARK_GRAY);
            setForeground(Color.BLACK);
            setHorizontalAlignment(SwingConstants.CENTER);
            addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED)
                        setIsColor(true);
                    else
                        setIsColor(false);
                }
            });
        }
    }

}
