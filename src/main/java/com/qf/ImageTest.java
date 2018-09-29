package com.qf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by qifan on 2018/8/20.
 */
public class ImageTest {

    JFrame frame;

    public static void main(String[] args){
        ImageTest test = new ImageTest();
        test.go();
    }

    public void go(){
        frame = new JFrame();
        frame.setVisible(true);
        JButton button = new JButton("should I do it?");
        button.addActionListener(new AngelListener());
        button.addActionListener(new DeviListener());
        frame.getContentPane().add(BorderLayout.CENTER,button);
    }

    class AngelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Don't do it,you might regret id!");
        }
    }

    class DeviListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Come on,do it!");
        }
    }

}
