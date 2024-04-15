package jbomberman.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import jbomberman.JBomberMan;
import jbomberman.util.*;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GameScreen extends JPanel {
    final int GAME_WIDTH = JBomberMan.SNES_WIDTH;
    final int GAME_HEIGHT = 196;
    final int OFFSET = JBomberMan.SNES_HEIGHT - GAME_HEIGHT;

    public record Wall(int x, int y) {
    };

    public record Bomb(int x, int y) {
    };

    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Bomb> bombs = new ArrayList<>();
    Image image;
    int x = 31;
    int y = OFFSET + 15;
    // Player player = new Player(31, OFFSET + 15);

    @SafeVarargs
    public GameScreen(Observer<Screen>... observers) {
        try {
            image = ImageIO.read(new File("../assets/stages/battle/Battle Stage 01 Normal Zone.png"))
                    .getScaledInstance(GAME_WIDTH, GAME_HEIGHT, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // player icon white
        // # lives
        // SC
        // # score
        // timer
        // player icon black
        // PRESS START

        var panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("P"));
        panel.add(new JLabel("5"));
        panel.add(new JLabel("SC"));
        panel.add(new JLabel("400"));
        panel.add(new JLabel("T"));
        panel.add(new JLabel("P"));
        panel.add(new JLabel("PRESS START"));
        add(panel);
        var screenChanger = new ScreenChanger(observers);

        // for (int i = 31; i < 239 - 15; i += 15)
        // for (int j = OFFSET + 15; j < OFFSET + 191 - 15; j += 15)

        for (int i = 31 + 15; i < 239 - 15; i += 30)
            for (int j = OFFSET + 15 + 15; j < OFFSET + 191 - 15; j += 30)
                walls.add(new Wall(i, j));

        var inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "bomb");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "pause");

        getActionMap().put("bomb", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if there are no walls
                var bomb = new Bomb(x, y + 15);

                for (var wall : walls) {
                    if (bomb.x >= wall.x + 15 || bomb.x + 15 <= wall.x || bomb.y >= wall.y + 15
                            || bomb.y + 15 <= wall.y)
                        continue;

                    return;
                }

                bombs.add(bomb);
                var timer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        bombs.remove(bomb);
                    }
                });

                timer.setRepeats(false); // Only execute once
                timer.start(); // Go go go!
            }
        });

        int moveSpeed = 3;

        getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(x, y - moveSpeed);
            }
        });

        getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(x - moveSpeed, y);
            }
        });

        getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(x, y + moveSpeed);
            }
        });

        getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(x + moveSpeed, y);
            }
        });

        getActionMap().put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenChanger.notifySubscribers(Screen.PAUSE);
            }
        });
    }

    public void draw() {
    }

    public void move(int x, int y) {
        if (x < 31 || y < OFFSET + 15 || x >= 239 - 25 || y >= OFFSET + 191 - 25)
            return;

        for (var wall : walls) {
            if (x >= wall.x + 15 || x + 15 <= wall.x || y >= wall.y + 15 || y + 15 <= wall.y)
                continue;

            return;
        }

        for (var bomb : bombs) {
            if (x >= bomb.x + 15 || x + 15 <= bomb.x || y >= bomb.y + 15 || y + 15 <= bomb.y)
                continue;

            return;
        }

        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        var offset = JBomberMan.SNES_HEIGHT - GAME_HEIGHT;
        g.clearRect(0, 0, JBomberMan.SNES_WIDTH, JBomberMan.SNES_HEIGHT);
        g.drawImage(image, 0, offset, null);

        // g.setColor(Color.GRAY);
        // for (var wall : walls)
        // g.fillRect(wall.x, wall.y, 15, 15);

        g.setColor(Color.BLACK);
        for (var bomb : bombs)
            g.fillRect(bomb.x, bomb.y, 15, 15);

        g.setColor(Color.RED);
        g.fillRect(x, y, 15, 15);
    }

}
