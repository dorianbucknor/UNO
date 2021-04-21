package UNO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class Card extends JPanel {
    private Color color;
    private int number;
    private String action;

    /**
     * Default Constructor
     */
    Card() {
        color = null;
        number = -1;
        createCardGFX();
    }

    /**
     * Creates a card
     * @param color card color
     * @param number card number
     */
    Card(Color color, int number) {
        this.color = color;
        this.number = number;
        createCardGFX();
    }
    Card(Color color, String action) {
        this.color = color;
        this.action = action;
        createCardGFX();
    }

    /**
     * Creates card graphics
     * @see java.awt.Color for color
     */
    private void createCardGFX() {

        String labelText;

        if (action != null){
            labelText = action;
        }else{
            labelText = Integer.toString(number);
        }

        setPreferredSize(new Dimension(10,180));
        setMaximumSize(new Dimension(10,180));
        setBackground(Color.white);
        setVisible(true);

        JLabel cardLbl1 = new JLabel();
        cardLbl1.setText(labelText);
        cardLbl1.setBackground(color);
        cardLbl1.setForeground(Color.WHITE);
        cardLbl1.setOpaque(true);
        cardLbl1.setVisible(true);


        JLabel cardLbl2 = new JLabel();
        cardLbl2.setText(labelText);
        cardLbl2.setBackground(color);
        cardLbl2.setForeground(Color.WHITE);
        cardLbl2.setHorizontalAlignment(JLabel.RIGHT);
        cardLbl2.setOpaque(true);
        cardLbl2.setVisible(true);

        JLabel unoLbl = new JLabel();
        unoLbl.setText("UNO");
        unoLbl.setHorizontalAlignment(JLabel.CENTER);
        unoLbl.setVisible(true);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        add(cardLbl1, BorderLayout.NORTH);
        add(unoLbl,BorderLayout.CENTER);
        add(cardLbl2,BorderLayout.SOUTH);

    }

    /**
     * @return card number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return card color
     */
    public Color getColor() {
        return color;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    /**
     * @param color card color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param number card number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Important helper functions
     * @param o card to check if equal
     * @return true if cards are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getNumber() == card.getNumber() && getColor().equals(card.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getNumber());
    }
}
