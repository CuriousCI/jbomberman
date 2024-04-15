package jbomberman;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jbomberman.view.*;
import jbomberman.util.*;

public class JBomberMan extends JFrame implements Observer<Screen> {
    public static int SNES_WIDTH = 256;
    public static int SNES_HEIGHT = 224;

    JPanel screens;

    public JBomberMan() {
        super("javasx - JBomberMan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        screens = new JPanel(new CardLayout());
        screens.add(new TitleScreen(this), Screen.TITLE.toString());
        screens.add(new GameScreen(this), Screen.GAME.toString());
        screens.add(new PasswordScreen(this), Screen.PASSWORD.toString());
        screens.add(new PauseScreen(this), Screen.PAUSE.toString());

        ((CardLayout) screens.getLayout()).show(screens, Screen.TITLE.toString());

        screens.setPreferredSize(new Dimension(SNES_WIDTH, SNES_HEIGHT));
        add(screens);
        pack();
    }

    @Override
    public void actionPerformed(Screen screen) {
        var layout = (CardLayout) screens.getLayout();
        layout.show(screens, screen.toString());
    }
}
