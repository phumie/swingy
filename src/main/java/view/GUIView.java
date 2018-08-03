package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.GUIMap;
import filewriter.ReadFile;
import filewriter.WriteFile;
import model.heroes.Hero;

public class GUIView extends JFrame {
    private String player, heroType, artifact, heroData;
    private String[] check = null;
    private int level, attack, defense, hitpoints, xpoints, type; 
    private static final long serialVersionUID = 42L;
    private final JFrame welcomeFrame = new JFrame("Swingy");  
    private final JFrame PlayerFrame = new JFrame("Create Player");;
    private final JFrame createFrame = new JFrame("Create Hero");;
    private final JFrame selectFrame = new JFrame("Select Hero");
    private final JFrame statsFrame = new JFrame("Hero Stats");
    private final JFrame gameFrame = new JFrame("GAME PLAYING");
    private static JFrame gameOverFrame = new JFrame("Game Complete");
    private final JRadioButton warrior = new JRadioButton("WARRIOR");
    private final JRadioButton protector = new JRadioButton("PROTECTOR");
    private final JRadioButton master = new JRadioButton("MASTER");
    private final String[] items = ReadFile.ReadLine();
    private final JList heroList = new JList<>(items);
    private JLabel l1, l2;
    private JTextField playerName;
    private JTextArea area;
    private JButton welcomeBtn, createPlayer, selectPlayer;
    private Hero hero = new Hero();
    private GUIMap map;


    public GUIView (){
    }

    public void welcomeFrame(){
        l1 = new JLabel("WELCOME TO SWINGY");  
        l1.setBounds(220,200, 200,30);
        l2 = new JLabel("PLEASE ENTER YOUR NAME");  
        l2.setBounds(210,240, 200,30);
        playerName = new JTextField();
        playerName.setBounds(200, 280, 200, 30);
        welcomeBtn = new JButton("ENTER");
        welcomeBtn.setBounds(200, 320, 200, 30);  
        welcomeFrame.add(l1); 
        welcomeFrame.add(l2); 
        welcomeFrame.add(playerName);
        welcomeFrame.add(welcomeBtn); 
        welcomeFrame.setSize(600,600);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setLayout(null);  
        welcomeFrame.setVisible(true);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                player = playerName.getText().toString();
                player = player.trim();

                if (player.length() > 0){
                    check = player.split("\\s");

                    if (check != null)
                        player = String.join("_", check);

                    if (player.isEmpty())
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!");
                    else{
                        createFrame();
                        welcomeFrame.setVisible(false);
                        welcomeFrame.dispose();
                    } 
                }
                else
                    JOptionPane.showMessageDialog(null, "Blank spaces not allowed!");
                
            }
        });
    }

    public void createFrame(){
        createPlayer = new JButton("CREATE PLAYER");
        createPlayer.setBounds(210,240, 200,30);
        selectPlayer = new JButton("SELECT PLAYER");
        selectPlayer.setBounds(210,280, 200,30);
        PlayerFrame.add(createPlayer);
        PlayerFrame.add(selectPlayer);
        PlayerFrame.setSize(600,600); 
        PlayerFrame.setLocationRelativeTo(null);
        PlayerFrame.setLayout(null);  
        PlayerFrame.setVisible(true);
        PlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlayer();
                PlayerFrame.dispose();
            }
        });

        selectPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPlayer();
                PlayerFrame.setVisible(false);
                PlayerFrame.dispose();
            }
        });
    }

    public void createPlayer(){
        // JRadioButton warrior, protector, master;
        ButtonGroup group = new ButtonGroup();
        JButton enter;
    
        enter = new JButton("CONTINUE");

        warrior.setBounds(210,240, 200,30);
        protector.setBounds(210,260, 200,30);
        master.setBounds(210,280, 200,30);
        enter.setBounds(210,300, 200,30);

        group.add(warrior);
        group.add(protector);
        group.add(master);

        createFrame.add(warrior);
        createFrame.add(protector);
        createFrame.add(master);
        createFrame.add(enter);
        createFrame.setSize(600,600);
        createFrame.setLocationRelativeTo(null);
        createFrame.setLayout(null);
        createFrame.setVisible(true);
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (master.isSelected())
                    type = 3;
                else if (protector.isSelected())
                    type = 2;
                else if (warrior.isSelected())
                    type = 1;
                
                heroStats();
                createFrame.setVisible(false);
                createFrame.dispose();
            }
        });
        
    }

    public void selectPlayer(){
        JLabel l1, l2, l3, l4, l5, l6, l7, l8;
        JButton enter, exit;

        String[] data;

        l1 = new JLabel("SELECT EXISTING HERO");
        l1.setBounds(20, 20, 200, 30);
        
        heroList.setBounds(20, 50, 350, 520);
        enter = new JButton("Continue");
        enter.setBounds(400, 50, 100, 30);
        exit = new JButton("Quit Game");
        exit.setBounds(400, 105, 100, 30);

        heroList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                heroData = heroList.getSelectedValue().toString();
                hero = GameView.DBHero(heroData);
                   
            }
        });

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // JOptionPane.showMessageDialog(null, heroData);
                if (heroData == null)
                    JOptionPane.showMessageDialog(null, "Select hero first!");
                else{
                    // JOptionPane.showMessageDialog(null, heroData);
                    playGame();
                    selectFrame.setVisible(false);
                    selectFrame.dispose();
                }

            }
        });

        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFrame.dispose();
            }
        });

        selectFrame.add(l1);
        selectFrame.add(enter);
        selectFrame.add(exit);
        selectFrame.add(heroList);
        selectFrame.setSize(600, 600);
        selectFrame.setLocationRelativeTo(null);
        selectFrame.setLayout(null);
        selectFrame.setVisible(true);
        selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void heroStats(){
        hero = GameView.createHero(player, type);
        JLabel l1, l2, l3, l4, l5, l6, l7, l8;
        JButton enter;

        l8 = new JLabel("YOUR HERO STATS");
        l8.setBounds(200,200, 200,30);
        l1 = new JLabel("Player: " + player);
        l1.setBounds(200,220, 200,30);
        l2 = new JLabel("Hero: " + (heroType = hero.getHeroStats().getHeroType()));
        l2.setBounds(200,240, 200,30);
        l3 = new JLabel("Level: " +  (level = hero.getHeroStats().getLevel()));
        l3.setBounds(200,260, 200,30);
        l4 = new JLabel("Attack: " + (attack = hero.getHeroStats().getAttack()));
        l4.setBounds(200,280, 200,30);
        l5 = new JLabel("Defense: " + (defense = hero.getHeroStats().getDefense()));
        l5.setBounds(200,300, 200,30);
        l6 = new JLabel("Hitpoints: " + (hitpoints = hero.getHeroStats().getHitPoints()));
        l6.setBounds(200,320, 200,30);
        l7 = new JLabel("Artifact: " + (artifact = hero.getArtifact().getType()));
        l7.setBounds(200,340, 200,30);
        enter = new JButton("Continue");
        enter.setBounds(200,370, 200,30);

        statsFrame.add(l1);
        statsFrame.add(l2);
        statsFrame.add(l3);
        statsFrame.add(l4);
        statsFrame.add(l5);
        statsFrame.add(l6);
        statsFrame.add(l7);
        statsFrame.add(l8);
        statsFrame.add(enter);
        statsFrame.setSize(600, 600);
        statsFrame.setLocationRelativeTo(null);
        statsFrame.setLayout(null);
        statsFrame.setVisible(true);
        statsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                heroData = hero.getHeroStats().getHeroType() + " " + 
                    player.toString() + " " + hero.getHeroStats().getLevel() + " " +
                    hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
                    " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getXPoints() +
                    " " + artifact.toUpperCase();
                
                WriteFile.createFile();
                WriteFile.writeToFile(heroData);
                WriteFile.closeFile();
                playGame();
                statsFrame.setVisible(false);
                statsFrame.dispose();
            }
        });
    }

    public void playGame(){
        JLabel l1, l2;
        JButton north, south, east, west;

        l1 = new JLabel("****** GAME PLAYING ******");
        l1.setBounds(100,20, 200,30);
        map = new GUIMap(hero, gameFrame, player);

        area = map.printMap();
        

        north = new JButton("NORTH");
        north.setBounds(20,580, 100, 30);
        south = new JButton("SOUTH");
        south.setBounds(140,580, 100, 30);
        east = new JButton("EAST");
        east.setBounds(280,580, 100, 30);
        west = new JButton("WEST");
        west.setBounds(400,580, 100, 30);

        north.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPos(0, -1);
            }
        });

        south.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPos(0, 1);
            }
        });

        east.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPos(1, 0);
            }
        });

        west.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPos(-1, 0);
            }
        });

        area.setBounds(20,90, 800, 480);
        gameFrame.add(l1);
        gameFrame.add(area);
        gameFrame.add(north);
        gameFrame.add(south);
        gameFrame.add(east);
        gameFrame.add(west);
        gameFrame.setSize(850, 650);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void gameOver(){
        JLabel l1;
        JButton close;

        l1 = new JLabel("WHoop! Game complete.");
        l1.setBounds(200,240, 200,30);
        close = new JButton("Close game");
        close.setBounds(210,280, 200,30);

        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameOverFrame.dispose();
                System.exit(0);
            }
        });

        gameOverFrame.add(l1);
        gameOverFrame.add(close);
        gameOverFrame.setSize(600, 600);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setLayout(null);
        gameOverFrame.setVisible(true);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
