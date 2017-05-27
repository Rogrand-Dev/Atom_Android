package com.rogrand.demo.event;

public class SignupStepEvent {

    private int position;

    public SignupStepEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
