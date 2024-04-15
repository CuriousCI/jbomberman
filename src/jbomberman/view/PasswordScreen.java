package jbomberman.view;

import jbomberman.util.*;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class PasswordScreen extends JPanel {

    @SafeVarargs
    public PasswordScreen(Observer<Screen>... observers) {
        super(new FlowLayout());

        var screenChanger = new ScreenChanger(observers);

        for (int digit = 0; digit < 4; digit++) {
            add(new JTextField(20));
        }

    }
}
