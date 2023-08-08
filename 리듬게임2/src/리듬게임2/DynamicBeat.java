package 리듬게임2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame{
	private Image screenImage;//더블버터링을 위한
	private Graphics screenGraphic;
	
	private Image introBackground = new ImageIcon(Main.class.getResource("../images/backimage.jpg")).getImage();//인트로 사진저장
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));
	
	private ImageIcon exitButtonbasic = new ImageIcon(Main.class.getResource("../images/closeingbutten.png"));
	private ImageIcon exitButtonentered = new ImageIcon(Main.class.getResource("../images/closebutten.png"));
	
	private JButton exitButton = new JButton(exitButtonbasic);
	
	private int mouseX,mouseY;//마우스의 위치
	
	
	public DynamicBeat() {
		setUndecorated(true);//메뉴바 안보임
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));//배경하양
		setLayout(null);//위치 고정
		
		exitButton.setBounds(1240,0,30,30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonentered);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonbasic);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);
		
		menuBar.setBounds(0,0,1280,30);//위치와 크기
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();
			setLocation(x-mouseX,y-mouseY);
			}
		});
		add(menuBar);//jframe에 추가
		
		
		
		Music introMusic = new Music("good-night-160166.mp3",true);
		introMusic.start();
	}
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);//단순이미지 그리기
		paintComponents(g);//역동적이지 않은 이미지 그리기
		this.repaint();
	}
}
