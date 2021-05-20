import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Frame {
    private JFrame frame;
    private Container pane;
    private int minHeight = 720, minWidth = 1024;
    private final int screenX, screenY;
    String[][] ques = new String[11][6];
    JRadioButton a, b, c, d;
    JLabel q, top;
    JButton submit;

    Frame() {
        frame = new JFrame("Quix");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(minWidth, minHeight);
        frame.setMinimumSize(new Dimension(minWidth, minHeight));
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        pane = frame.getContentPane();
        screenY = frame.getHeight();
        screenX = frame.getWidth();
    }

    private JLabel setBackground() throws IOException {
        InputStream imgStream = Frame.class.getResourceAsStream("res/veil_art.jpg");
        BufferedImage myImg = ImageIO.read(imgStream);
        ImageIcon ii = new ImageIcon(myImg);
        JLabel bgImage = new JLabel(ii);
        pane.add(bgImage);
        bgImage.setBounds(0, 0, screenX, screenY);
        return bgImage;
    }

    public void screen2() throws IOException {
        frame.setLayout(null);
        pane.repaint();

        JLabel bgImage=setBackground();

//        NEXT BUTTON
        JButton next = new JButton("NEXT");
        bgImage.add(next);
        next.setBounds((int) (screenX * 0.1), (int) (screenY * 0.8), (int) (screenX * 0.8), (int) (screenY * 0.08));
        next.setBackground(new Color(255, 137, 2));
        next.setFont(new Font("Algerian", Font.PLAIN, 55));
        next.setForeground(Color.WHITE);

//        INSTRUCTIONS
        JLabel inst = new JLabel("INSTRUCTIONS:");
        Font ins = new Font("Algerian", Font.HANGING_BASELINE, 70);
        bgImage.add(inst);
        inst.setBounds(60, 120, screenX, 100);
        inst.setFont(ins);

        Font f = new Font("Franklin Gothic Demi", Font.TRUETYPE_FONT, 23);
        JLabel i1, i2, i3;
        i1 = new JLabel("- There are a total of 10 questions that you have to answer.");
        bgImage.add(i1);
        i1.setBounds(70, inst.getY() + 150, screenX, 40);
        i1.setFont(f);
        i2 = new JLabel("- You have to first select a correct option from the provided four optiions and then click on SUBMIT.");
        bgImage.add(i2);
        i2.setBounds(70, i1.getY() + 70, screenX, 40);
        i2.setFont(f);
        i3 = new JLabel("- If you don't click on SUBMIT after selecting your answer then it will not be evaluated.");
        bgImage.add(i3);
        i3.setBounds(70, i2.getY() + 70, screenX, 40);
        i3.setFont(f);

        next.addActionListener(e -> {
            pane.removeAll();
            try {
                quizScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }



    void quizScreen() throws IOException {
        pane.repaint();
        JLabel bkg=setBackground();

        JButton next = new JButton("NEXT");
        submit =new JButton();

        a = new JRadioButton();
        a.setSelected(true);
        a.setOpaque(false);
        b = new JRadioButton();
        b.setOpaque(false);
        c = new JRadioButton();
        c.setOpaque(false);
        d = new JRadioButton();
        d.setOpaque(false);
        q = new JLabel();
        top = new JLabel();

        top.setFont(new Font("Arial", Font.BOLD, 20));
        top.setForeground(Color.blue);

        bkg.add(top);
        top.setBounds(30, 40, 200, 30);
        bkg.add(q);
        q.setBounds(top.getX(), top.getY()+100, screenX, 70);
        bkg.add(a);
        a.setBounds(top.getX(),q.getY()+100, screenX, 30);
        bkg.add(b);
        b.setBounds(top.getX(),a.getY()+50, screenX, 30);
        bkg.add(c);
        c.setBounds(top.getX(),b.getY()+50, screenX, 30);
        bkg.add(d);
        d.setBounds(top.getX(),c.getY()+50, screenX, 30);

        q.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        Font opt = new Font("times new Roman", Font.PLAIN, 21);
        a.setFont(opt);
        b.setFont(opt);
        c.setFont(opt);
        d.setFont(opt);
        ButtonGroup bg = new ButtonGroup();
        bg.add(a);
        bg.add(b);
        bg.add(c);
        bg.add(d);

        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Calibri",Font.BOLD,20));
        bkg.add(next);
        next.setBounds(screenX-300, screenY-200, 110, 60);
        
        submit =new JButton("SUBMIT");
        submit.setBounds(next.getX()-130, next.getY(), 110,60);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Calibri",Font.BOLD,20));
        bkg.add(submit);

        load();

        next.addActionListener(e -> {
            if (e.getSource() == next && x == 10) {
                pane.removeAll();
                try {
                    finalscreen();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

            changeText(ques[x][0], ques[x][1], ques[x][2], ques[x][3], ques[x][4], ques[x][5]);
            a.setForeground(Color.black);
            b.setForeground(Color.black);
            c.setForeground(Color.black);
            d.setForeground(Color.black);
            submit.setEnabled(true);

        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a.isSelected())
                    a.setForeground(Color.red);
                if (b.isSelected())
                    b.setForeground(Color.red);
                if (c.isSelected())
                    c.setForeground(Color.red);
                if (d.isSelected())
                    d.setForeground(Color.red);

                if (a.getText().equals(ques[x - 1][5]))
                    a.setForeground(Color.green);
                if (b.getText().equals(ques[x - 1][5]))
                    b.setForeground(Color.green);
                if (c.getText().equals(ques[x - 1][5]))
                    c.setForeground(Color.green);
                if (d.getText().equals(ques[x - 1][5]))
                    d.setForeground(Color.green);

                submit.setEnabled(false);

                if (a.isSelected()) {
                    if (a.getText().equals(ques[x - 1][5]))
                        co++;
                }
                if (b.isSelected()) {
                    if (b.getText().equals(ques[x - 1][5]))
                        co++;
                }

                if (c.isSelected()) {
                    if (c.getText().equals(ques[x - 1][5]))
                        co++;
                }
                if (d.isSelected()) {
                    if (d.getText().equals(ques[x - 1][5]))
                        co++;
                }

            }
        });

        changeText(ques[0][0], ques[0][1], ques[0][2], ques[0][3], ques[0][4], ques[0][5]);
    }

    private int x = 0, co = 0;

    void changeText(String q, String a, String b, String c, String d, String ans) {
        x++;
        this.a.setSelected(true);
        this.q.setText(q);
        this.a.setText(a);
        this.b.setText(b);
        this.c.setText(c);
        this.d.setText(d);
        this.top.setText("Question: " + x + " of 10");

    }

    void load() throws IOException {
        Random rd = new Random();
        String path = String.valueOf(1 + rd.nextInt(7));
        path = path + ".txt";

        InputStream inp = Frame.class.getResourceAsStream("res\\" + path);

        InputStreamReader isr=new InputStreamReader(inp);
        BufferedReader bin = new BufferedReader(isr);

        int i, j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 6; j++)
                ques[i][j] = bin.readLine();
            String t = bin.readLine();
        }
    }
    void finalscreen() throws IOException {

        pane.repaint();
        JLabel bkg = setBackground();

        JLabel res=new JLabel("RESULT...");
        bkg.add(res);
        res.setBounds((int)(0.07*screenX),(int)(0.1*screenY),screenX,200);

        res.setFont(new Font("BankGothic Lt BT",Font.BOLD,150));

        JLabel res2=new JLabel("You have scored "+co+" marks out of 10!");
        res2.setFont(new Font("BankGothic Lt BT",Font.BOLD,70));
        res2.setBounds((int)(screenX*0.1),(int)(screenY*0.45),screenX,150);
        bkg.add(res2);

        JButton fns=new JButton("FINISH");
        bkg.add(fns);
        fns.setBounds((int) (screenX * 0.1), (int) (screenY * 0.8), (int) (screenX * 0.8), (int) (screenY * 0.08));
        fns.setBackground(new Color(255, 137, 2));
        fns.setFont(new Font("BankGothic Lt BT", Font.PLAIN, 45));
        fns.setForeground(Color.WHITE);

        res2.setForeground(Color.BLACK);

        fns.addActionListener(e -> System.exit(0));
    }
}
