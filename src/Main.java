import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Main {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(256, 224);
        frame.setVisible(true);

        var cardLayout = new CardLayout();
        var cards = new JPanel(cardLayout);

        var game = new Game();
        game.setLocation(0, 0);
        game.setFocusable(true);
        game.setSize(600, 600);

        var start = new JPanel();
        var play = new JButton();
        play.setText("Play");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cardLayout.show(cards, "1");
            }
        });
        start.add(play);

        frame.add(cards);
        cards.add(start, "0");
        cards.add(game, "1");
        cardLayout.show(cards, "0");
    }

    static class Game extends JPanel {
        public record Wall(int x, int y) {

        };

        int x, y;
        Wall[] walls;

        Game() {
            x = 0;
            y = 0;

            walls = new Wall[3];
            walls[0] = new Wall(20, 40);
            walls[1] = new Wall(40, 40);
            walls[2] = new Wall(60, 20);

            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
                    "move");
            this.getActionMap().put("move", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
        }

        public void move() {
            var e = 'D';

            switch (e) {
                case 'W':
                    y -= 20;
                    break;
                case 'A':
                    x -= 20;
                    break;
                case 'S':
                    y += 20;
                    break;
                case 'D':
                    x += 20;
                    break;
            }

            boolean found = false;
            for (var w : walls)
                if (w.x() == x && w.y() == y) {
                    found = true;
                    break;
                }

            if (found) {
                switch (e) {
                    case 'W':
                        y += 20;
                        break;
                    case 'A':
                        x += 20;
                        break;
                    case 'S':
                        y -= 20;
                        break;
                    case 'D':
                        x -= 20;
                        break;
                }
            }

            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            g.clearRect(0, 0, 600, 600);

            g.setColor(Color.GRAY);
            for (int i = 0; i <= 600; i += 20) {
                for (int j = 0; j <= 600; j += 20) {
                    boolean found = false;
                    for (var w : walls)
                        if (w.x() == i && w.y() == j) {
                            found = true;
                            break;
                        }

                    if (found)
                        g.setColor(Color.GREEN);

                    g.drawRect(i + 1, j + 1, 18, 18);

                    if (found)
                        g.setColor(Color.GRAY);
                }
            }

            g.setColor(Color.RED);
            g.drawRect(x, y, 20, 20);
        }

        // @Override
        // public void mouseReleased(MouseEvent e) {
        // }
        //
        // @Override
        // public void mousePressed(MouseEvent e) {
        // }
        //
        // @Override
        // public void mouseExited(MouseEvent e) {
        // }
        //
        // @Override
        // public void mouseClicked(MouseEvent e) {
        // System.out.println("Something happened");
        // }
        //
        // @Override
        // public void mouseEntered(MouseEvent e) {
        // }
    }

}
