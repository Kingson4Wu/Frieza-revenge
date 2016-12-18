package com.kxw.kilim;

import java.math.BigDecimal;

/**
 * {<a href='http://www.ibm.com/developerworks/cn/java/j-javadev2-7.html'>@link</a>}
 *
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

/**
 *
 * Kilim 的 weaver
 在前面，我提到了 Kilim 通过其 weaver 执行字节码操作。这是一个简单的后处理过程，您在编译了类之后 运行它。weaver 然后将一些特殊代码添加到包含 Pausable 标记的各种类和方法中。
 调用 weaver 非常简单。举例而言，在清单 4 中，我使用 Ant 调用 Weaver。我需要做的只是告诉 Weaver 我需要的类在哪里，以及在哪里放置生成的字节码。在这个例子中，我让 Weaver 更改 target/classes 字典中的类，并将生成的字节码写回到该字典。
 清单 4. Ant 调用 Kilim 的 weaver
 <target name="weave" depends="compile" description="handles Kilim byte code weaving">
 <java classname="kilim.tools.Weaver" fork="yes">
 <classpath refid="classpath" />
 <arg value="-d" />
 <arg value="./target/classes" />
 <arg line="./target/classes" />
 </java>
 </target>
 更改代码之后，我就可以在运行时随意利用 Kilim 了，只要我在类路径中包含了它的 .jar 文件。
 */