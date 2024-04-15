package jbomberman.view;

import jbomberman.util.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen extends JPanel {

    record Button(String text, Screen screen) {
    };

    @SafeVarargs
    public TitleScreen(Observer<Screen>... observers) {

        var profile = new JPanel(new FlowLayout());
        profile.add(new JLabel("nickname")); // TODO: add avatar
        profile.add(new JLabel("GAMES: 10, 7-3"));
        profile.add(new JLabel("LEVEL: 10"));

        add(profile);
        // - nickname
        // - avatar
        // - games played
        // - won
        // - lost
        // - level
        var screenChanger = new ScreenChanger(observers);

        var buttons = new Button[] {
                new Button("NORMAL GAME", Screen.GAME),
                new Button("BATTLE MODE", Screen.GAME),
                new Button("PASSWORD", Screen.PASSWORD),
        };

        for (var button : buttons) {
            var b = new JButton(button.text);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    screenChanger.notifySubscribers(button.screen);
                }
            });

            add(b);
        }
    }
}
