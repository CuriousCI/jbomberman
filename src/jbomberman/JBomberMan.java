package jbomberman;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jbomberman.view.*;
import jbomberman.util.*;

/*
 * 
    @author: Indicates the author of the code. It's applied at the class, package, or overview level and is not included in the Javadoc output. It's not recommended to use this tag due to frequent changes in authorship.
    @param: Documents a method or constructor parameter. (ex. @param count Sets the number of widgets you want included) 
    @deprecated: Marks a class or method as no longer in use. It's positioned prominently in the Javadoc output and should be accompanied by a @see or {@link} tag.
    @return: Describes what a method returns.
    @see: Creates a "see also" list. Use {@link} for the content to be linked.
    {@link}: Used to create links to other classes or methods. For example, {@link Foo#bar} links to the method bar in the class Foo.
    @since: Indicates the version since the feature was added.
    @throws or @exception: Documents the exceptions a method can throw. Your code must indicate an exception thrown for this tag to validate.
    @Override: Checks if the method is an override, used with interfaces and abstract classes.

 */

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
