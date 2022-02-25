// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Add your docs here. */
public class Keyboard 
    implements KeyListener{

    // Kind of irritating to change so I hope I dont have to do that
    public class Keys {
        public static final int A_ = 0;
        public static final int B_ = 1;
        public static final int C_ = 2;
        public static final int D_ = 3;
        public static final int E_ = 4;
        public static final int F_ = 5;
        public static final int G_ = 6;
        public static final int H_ = 7;
        public static final int I_ = 8;
        public static final int J_ = 9;
        public static final int K_ = 10;
        public static final int L_ = 11;
        public static final int M_ = 12;
        public static final int N_ = 13;
        public static final int O_ = 14;
        public static final int P_ = 15;
        public static final int Q_ = 16;
        public static final int R_ = 17;
        public static final int S_ = 18;
        public static final int T_ = 19;
        public static final int U_ = 20;
        public static final int V_ = 21;
        public static final int W_ = 22;
        public static final int X_ = 23;
        public static final int Y_ = 24;
        public static final int Z_ = 25;

    }

    // Is the first value in KeyEvent that is used to offset the array of keys
    private int firstKeyOffset;

    // An array of the keys you can use
    public Key keys[];

    public Keyboard() {

        firstKeyOffset = KeyEvent.VK_A;

        keys = new Key[26];

        // Initilizing the keys from A-Z
        for (int i = 0; i < 27; i++) {
            keys[i] = new Key(this, i);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        keys[e.getKeyCode() - firstKeyOffset].isPressed = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        keys[e.getKeyCode() - firstKeyOffset].isPressed = false;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
        
    }

    public boolean getKeyPressed(int id) {

        return keys[id].isPressed;

    }
}
