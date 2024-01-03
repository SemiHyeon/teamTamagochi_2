

import java.awt.*;
import java.sql.Timestamp;

public class Tamagochi extends Drawable{
    private int satiety;
    private int fatigue;

    //poop�� �Ŵ����� �ִ°� �´� �� ���Ƽ� ����.
    private int level;

    private Timestamp createTime;
    private String nickname;

    public Tamagochi(int x, int y, int size, String imgURL, String nickname) {
        super(x, y, size, imgURL);
        this.satiety = 8;
        this.fatigue = 0;
        this.level = 1;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.nickname = nickname;
    }

    public void display(Graphics g){
        Image img = Toolkit.getDefaultToolkit().getImage(getImgURL());
        g.drawImage(img, getX(), getY(), getSize(), getSize(), null);
    }

    public Tombstone dieByEating(String causeOfDeath){
        // ���� �ۼ�, �� ����
        return new Tombstone(getX(), getY(), getSize(), "src/img/tombstoneImg1.png",causeOfDeath);
    }
    public Tombstone dieBySleeping(String causeOfDeath){
        // ���� �ۼ�, �� ����
        return new Tombstone(getX(), getY(), getSize(), "src/img/tombstoneImg1.png",causeOfDeath);
    }

    public Tombstone dieByPoop(String causeOfDeath){
        // ���� �ۼ�, �� ����
        return new Tombstone(getX(), getY(), getSize(), "src/img/tombstoneImg1.png",causeOfDeath);
    }

    //-------------------------getter, setter-------------------------
    public int getSatiety() {
        return satiety;
    }
    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }
    public int getFatigue() {
        return fatigue;
    }
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public String getNickname() {
        return nickname;
    }
    public String getImgURL() {
        return super.getImgURL();
    }
}