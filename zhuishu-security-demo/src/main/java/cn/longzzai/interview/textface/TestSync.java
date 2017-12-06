package cn.longzzai.interview.textface;

import lombok.Data;

/**
 * @author longcho
 * 2017-11-19-9:32
 */
public class TestSync extends Thread{
    private String name;
    private Integer integer  = 0;

    public Integer getInteger() {
        return integer;
    }

    public TestSync(String name) {
        this.name = name;
    }

    @Override
    public void  run(){
        synchronized (this){
            for (int i = 0; i < 20; i++) {
                System.out.println("线程"+ this.name +"，前" + integer);

                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程"+ this.name +"，后" + integer);
                integer++;
            }
        }
    }

    public static void main(String[] args) {
        TestSync testSync = new TestSync("A");
        TestSync testSync1 = new TestSync("B");
        System.out.println(testSync.getInteger() == testSync1.getInteger());
        testSync.start();
        testSync1.start();
    }
}
