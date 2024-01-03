

import java.util.ArrayList;
import java.util.Random;

public class TamaManager {
    private Tamagochi tama;
    private ArrayList<Poop> poops;
    private ArrayList<Tombstone> tombstones;

    public TamaManager(){
        this.tama = null;
        poops = new ArrayList<Poop>();
        tombstones = new ArrayList<Tombstone>();
    }
    public void createTama(String nickname){
        int x=50, y=50, size=50;
        tama = new Tamagochi(x, y, size, "src/img/tamagochiImg.png", nickname);
    }
    public void feed(){
        Random random = new Random();
        // ���� �������� 10�̻��� ���, ���� Ȯ���� Tamagochi�� dieByEating() �޼ҵ� ȣ��. �� ��θ� ���¿��� ���� ���̸� ���� Ȯ���� ����.
        if(tama.getSatiety() >= 10) {
            if(Math.random() < 0.4) {
                tombstones.add(tama.dieByEating("��θ� ���¿��� �Դٰ� ü�ؼ�(�� ���) ����"));
            }
        }
        //�� ����. �����ϰ� 1~3��ŭ ������ ����
        tama.setSatiety(tama.getSatiety()+random.nextInt(3)+1);
        // ���� �������� 15�� �ʰ����� ���, Tamagochi�� dieByEat() �޼ҵ� ȣ��
        if(tama.getSatiety() >= 15){
            tombstones.add(tama.dieByEating("���� ������ �Դٰ� ������ ����"));
        }
    }
    public void sleep(){
        Random random= new Random();
        tama.setFatigue(tama.getFatigue()-random.nextInt(4));
    }
    public void clean(){
        // poop�� ��� ����
        poops.clear();
    }
    public void createPoop(){
        // poop ����
        int x=50, y=50, size=50;
        Poop poop = new Poop(x, y, size, "src/img/poop.png");
        poops.add(poop);
        // ���� poop�� 10�����, Tamagochi�� dieByPoop() �޼ҵ� ȣ��. �� poop�� 10���� �Ǹ� ����.
        if(poops.size() >= 10){
            tombstones.add(tama.dieByPoop("�˵� �ö� ����"));
        }
    }
    public void gettingHungry(){
        // �ý����� tama�� createTime�� ���� �ð��� ���Ͽ� �ð��� ����� �����ϰ�, '10��'���� �������� 1�� ���ҽ�Ų��.
        // ���� �������� 0�� �Ǹ�, Tamagochi�� dieByEat() �޼ҵ� ȣ��. �� ������� 0�� �Ǹ� ����.
        tama.setSatiety(tama.getSatiety()-1);
        if(tama.getSatiety() <= 0){
            tombstones.add(tama.dieByEating("����ļ� ����"));
        }
    }
    public void gettingSleepy() {
        // �ý����� tama�� createTime�� ���� �ð��� ���Ͽ� �ð��� ����� �����ϰ�, '20��'���� �Ƿε��� 1�� ������Ų��.
        // ���� �Ƿε��� 10�� �Ѿ��, ���� Ȯ���� Tamagochi�� dieBySleep() �޼ҵ� ȣ��.
        // �Ƿε��� 15�� �����ϸ� �׳� dieBySleep() ȣ��. �� �Ƿε��� 15�� �Ǹ� ����.
        tama.setFatigue(tama.getFatigue() + 1);
        if (tama.getFatigue() >= 10) {
            if (tama.getFatigue() == 15) {
                tombstones.add(tama.dieBySleeping("�ǰ￡ ���� ����"));
            } else if (Math.random() < 0.4) {
                tombstones.add(tama.dieBySleeping("�ǰ��ؼ� ����"));
            }
        }
    }
    public Tamagochi getTama(){
        return tama;
    }
}