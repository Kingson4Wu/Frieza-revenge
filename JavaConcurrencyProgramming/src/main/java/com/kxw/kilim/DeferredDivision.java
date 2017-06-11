package com.kxw.kilim;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

import kilim.Mailbox;
import kilim.Pausable;
import kilim.Task;

public class DeferredDivision extends Task {

    private Mailbox<Calculation> mailbox;

    public DeferredDivision(Mailbox<Calculation> mailbox) {
        super();
        this.mailbox = mailbox;
    }

    @Override
    public void execute() throws Pausable, Exception {
        Random numberGenerator = new Random(System.currentTimeMillis());
        MathContext context = new MathContext(8);
        while (true) {
            System.out.println("I need to know the answer of something");
            mailbox.putnb(new Calculation(
                    new BigDecimal(numberGenerator.nextDouble(), context),
                    new BigDecimal(numberGenerator.nextDouble(), context)));
            Task.sleep(1000);
            Calculation answer = mailbox.getnb(); // no block
            if (answer != null && answer.getAnswer() != null) {
                System.out.println("Answer is: " + answer.printAnswer());
            }
        }
    }
}