package by.it.halai.jd02_02;

import java.util.Deque;
import java.util.LinkedList;

public class Dispatcher {

    static final Integer planBuyer = 30;
    volatile static int countBuyer = 0;
    static final Deque<Buyer> buyerQueue = new LinkedList<>();
}
