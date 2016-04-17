package com.kxw.kilim;
import kilim.Mailbox;
import kilim.Task;

public class CalculationCooperation {
    public static void main(String[] args) {
        Mailbox<Calculation> sharedMailbox = new Mailbox<Calculation>();

        Task deferred = new DeferredDivision(sharedMailbox);
        Task calculator = new Calculator(sharedMailbox);

        deferred.start();
        calculator.start();

    }
}