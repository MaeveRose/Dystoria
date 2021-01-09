package com.maeverose.game.Renderer;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application extends JFrame {

    public Application() {

        initUI();
    }

    private void initUI() {
        add(new GenerationControls());
        //add(new Board());

        setSize(250, 200);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}