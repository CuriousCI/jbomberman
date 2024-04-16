package jbomberman.view;

import jbomberman.util.*;
import jbomberman.model.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen extends JPanel implements Observer<User> {
    record Button(String text, Screen screen) {
    };

    JLabel nickname = new JLabel("Guest");
    JLabel games = new JLabel("GAMES: 0");
    JLabel victories = new JLabel("0");
    JLabel losses = new JLabel("0");
    JLabel level = new JLabel("LEVEL: 0");

    @SafeVarargs
    public TitleScreen(Observer<Screen>... observers) {
        // TODO: read userdata from file

        var profile = new JPanel(new FlowLayout());
        // profile.add(new JLabel("nickname")); // TODO: add avatar
        // profile.add(new JLabel("GAMES: 10, 7-3"));
        // profile.add(new JLabel("LEVEL: 10"));

        profile.add(nickname);
        profile.add(games);
        profile.add(victories);
        profile.add(losses);
        profile.add(level);
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

    @Override
    public void actionPerformed(User user) {
        nickname.setText(user.nickname());
        games.setText("GAMES: " + user.games());
        victories.setText("" + user.victories());
        losses.setText("" + (user.games() - user.victories()));
        level.setText("LEVEL: " + user.level());
    }
}
