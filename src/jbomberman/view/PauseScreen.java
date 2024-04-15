package jbomberman.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jbomberman.util.*;

public class PauseScreen extends JPanel {

    @SafeVarargs
    public PauseScreen(Observer<Screen>... observers) {
        var screenChanger = new ScreenChanger(observers);

        var continueGame = new JLabel("CONTINUE?");

        var yes = new JButton("YES");
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                screenChanger.notifySubscribers(Screen.GAME);
            }
        });

        var no = new JButton("NO");
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                screenChanger.notifySubscribers(Screen.TITLE);
            }
        });

        var code = new JLabel("1980");

        add(continueGame);
        add(yes);
        add(no);
        add(code);
    }

}
