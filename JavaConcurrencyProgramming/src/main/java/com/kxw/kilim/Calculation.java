package com.kxw.kilim;

import java.math.BigDecimal;

/**
 * {<a href='http://www.ibm.com/developerworks/cn/java/j-javadev2-7.html'>@link</a>}
 */
public class Calculation {
    private BigDecimal dividend;
    private BigDecimal divisor;
    private BigDecimal answer;

    public Calculation(BigDecimal dividend, BigDecimal divisor) {
        super();
        this.dividend = dividend;
        this.divisor = divisor;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public BigDecimal getDivisor() {
        return divisor;
    }

    public void setAnswer(BigDecimal ans){
        this.answer = ans;
    }

    public BigDecimal getAnswer(){
        return answer;
    }

    public String printAnswer() {
        return "The answer of " + dividend + " divided by " + divisor +
                " is " + answer;
    }
}