package com.rogrand.demo.event;

import me.yokeyword.fragmentation.SupportFragment;

public class StartBrotherEvent {

    public SupportFragment targetFragment;

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
