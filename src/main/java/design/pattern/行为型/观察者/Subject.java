package design.pattern.行为型.观察者;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // 触发通知
        notifyAllObservers();
    }

    /**
     * 加入一个观察者
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * 通知所有加入的观察者状态的变化
     */
    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
