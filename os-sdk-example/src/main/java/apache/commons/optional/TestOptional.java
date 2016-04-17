package apache.commons.optional;

import com.google.common.base.Optional;

/**
 * Created by kxw on 2015/10/11.
 *  {<a href='http://www.cnblogs.com/peida/archive/2013/06/14/Guava_Optional.html'>@link</a>}
 */
public class TestOptional {

    /**
     * 常用静态方法：
     * Optional.of(T)：获得一个Optional对象，其内部包含了一个非null的T数据类型实例，若T=null，则立刻报错。
     * Optional.absent()：获得一个Optional对象，其内部包含了空值
     * Optional.fromNullable(T)：将一个T的实例转换为Optional对象，T的实例可以不为空，也可以为空[Optional.fromNullable(null)，和Optional.absent()等价。
     */
    /**
     * 实例方法：
     　　1>. boolean isPresent()：如果Optional包含的T实例不为null，则返回true；若T实例为null，返回false
     　　2>. T get()：返回Optional包含的T实例，该T实例必须不为空；否则，对包含null的Optional实例调用get()会抛出一个IllegalStateException异常
     　　3>. T or(T)：若Optional实例中包含了传入的T的相同实例，返回Optional包含的该T实例，否则返回输入的T实例作为默认值
     　　4>. T orNull()：返回Optional实例中包含的非空T实例，如果Optional中包含的是空值，返回null，逆操作是fromNullable()
     　　5>. Set<T> asSet()：返回一个不可修改的Set，该Set中包含Optional实例中包含的所有非空存在的T实例，且在该Set中，每个T实例都是单态，如果Optional中没有非空存在的T实例，返回的将是一个空的不可修改的Set。
     */

    public void test() {
        Optional<Integer> possible = Optional.of(6);
        if (possible.isPresent()) {
            System.out.println("possible isPresent:" + possible.isPresent());
            System.out.println("possible value:" + possible.get());
        }
    }

    /**
     * 　Optional<T>的最常用价值在于，例如，假设一个方法返回某一个数据类型，调用这个方法的代码来根据这个方法的返回值来做下一步的动作，若该方法可以返回一个null值表示成功，或者表示失败，在这里看来都是意义含糊的，所以使用Optional<T>作为返回值，则后续代码可以通过isPresent()来判断是否返回了期望的值（原本期望返回null或者返回不为null，其意义不清晰），并且可以使用get()来获得实际的返回值。
     */


}
