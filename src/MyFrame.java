

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame {
    private JButton eatButton;
    private JButton sleepButton;
    private JButton cleanButton;
    private JPanel menuPanel;
    private JPanel characterPanel;
    private JPanel poopPanel;
    private JPanel tombstonePanel;
    private JPanel gagePanel;
    private JPanel namePanel;
    private Timer hungryTimer;
    private Timer sleepyTimer;
    private TamaManager tamaManager;
    private ActionListener actionListener;
    private JProgressBar satietyBar;
    private JProgressBar fatigueBar;
    
    public MyFrame(){
        super();

        setSize(500, 600);
        setLayout(new GridBagLayout()); // ���̾ƿ� �Ŵ��� ����

        BackgroundPanel backgroundPanel = new BackgroundPanel("src/img/backgroundImg.jpg"); // ���⿡ ���ϴ� �̹��� ��� �Է�
        this.setContentPane(backgroundPanel);
        this.setLayout(new GridBagLayout()); // ��� �г� ���� �ٸ� ������Ʈ���� �ø��� ���� ���̾ƿ� �缳��
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tamaManager = new TamaManager();
        String nickname = JOptionPane.showInputDialog("�г����� �Է��ϼ���");
        tamaManager.createTama(nickname);
        ImageIcon tamaIcon = new ImageIcon(tamaManager.getTama().getImgURL());
        // �̹��� ũ�� ����
        Image image = tamaIcon.getImage();
        Image resizedImage = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        tamaIcon = new ImageIcon(resizedImage);
        //menuPanel ���� ��� ���� �� ��ġ
        ImageIcon eatIcon = new ImageIcon(new ImageIcon("src/img/eatImg.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon sleepIcon = new ImageIcon(new ImageIcon("src/img/sleepImg.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        ImageIcon cleanIcon = new ImageIcon(new ImageIcon("src/img/cleanImg1.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        eatButton = new JButton(eatIcon);
        sleepButton = new JButton(sleepIcon);
        cleanButton = new JButton(cleanIcon);
        //actionListener ���� �� ��ġ
        actionListener = e -> {
            if(e.getSource() == eatButton){
                tamaManager.feed();
                repaint();
            }
            else if(e.getSource() == sleepButton){
                tamaManager.sleep();
                repaint();
            }
            else if(e.getSource() == cleanButton){
                tamaManager.clean();
                repaint();
            }
        };
        eatButton.addActionListener(actionListener);
        sleepButton.addActionListener(actionListener);
        cleanButton.addActionListener(actionListener);
        menuPanel = new JPanel();
        menuPanel.add(eatButton);
        menuPanel.add(sleepButton);
        menuPanel.add(cleanButton);
        // menuPanel�� �ϴܿ� ��ġ
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        menuPanel.setOpaque(false);
        add(menuPanel, gbc);


        //namePanel ���� ��� ���� �� ��ġ
        namePanel = new JPanel();
        // namePanel�� ��ܿ� ��ġ
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1; // ���� ���� �Ҵ�
        namePanel.setOpaque(false);
        add(namePanel, gbc);

        //gagePanel ���� ��� ���� �� ��ġ
        gagePanel = new JPanel();
        // gagePanel�� namePanel �Ʒ��� ��ġ
        gbc.gridy = 1;
        gagePanel.setOpaque(false);
        add(gagePanel, gbc);
        // ������ ������ �ʱ�ȭ �� ����
        satietyBar = new JProgressBar(0, 15); // �ִ� �������� 15�� ����
        satietyBar.setValue(tamaManager.getTama().getSatiety());
        satietyBar.setStringPainted(true); // ���ڷ� ���� �� ǥ��

        // �Ƿε� ������ �ʱ�ȭ �� ����
        fatigueBar = new JProgressBar(0, 15); // �ִ� �Ƿε��� 15�� ����
        fatigueBar.setValue(tamaManager.getTama().getFatigue());
        fatigueBar.setStringPainted(true);

        // gagePanel�� ������ �߰�
        gagePanel.setLayout(new GridLayout(2, 1)); // ���̾ƿ� ����
        gagePanel.add(satietyBar);
        gagePanel.add(fatigueBar);

        //characterPanel ���� ��� ���� �� ��ġ
        characterPanel = new JPanel();
        poopPanel = new JPanel();
        poopPanel.setOpaque(false);
        tombstonePanel= new JPanel();
        tombstonePanel.setOpaque(false);
        // �̹����� JLabel�� �����ϰ� characterPanel�� �߰�
        JLabel tamaLabel = new JLabel(tamaIcon);
        characterPanel.setOpaque(false);
        characterPanel.add(tamaLabel);
        characterPanel.add(poopPanel);
        characterPanel.add(tombstonePanel);
        // characterPanel�� gagePanel �Ʒ�, menuPanel ���� ��ġ
        gbc.gridy = 2;
        gbc.weighty = 0.01; // �ʿ��� ������ŭ�� �Ҵ�
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(characterPanel, gbc);

        repaint();
        setVisible(true);
    }
    public void start(){
        this.hungryTimer=new Timer(10000,e->{
            tamaManager.gettingHungry();
            satietyBar.setValue(tamaManager.getTama().getSatiety());
            repaint();
        });
        this.sleepyTimer=new Timer(20000,e->{
            tamaManager.gettingSleepy();
            fatigueBar.setValue(tamaManager.getTama().getFatigue());
            repaint();
        });
    }
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        // ��� �̹����� �޴� ������
        public BackgroundPanel(String fileName) {
            backgroundImage = new ImageIcon(fileName).getImage();
        }

        // ��� �̹��� �׸���
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}