package apache.commons.validator;


import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.RegexValidator;

import java.util.Date;

/**
 * Created by kingsonwu on 15/12/26.
 */
public class TestValidator {


    public static void main(String[] args) {
        // 获取日期验证
        DateValidator validator = DateValidator.getInstance();

        // 验证/转换日期
        Date fooDate = validator.validate("25/12/2015", "dd/MM/yyyy");
        if (fooDate == null) {
            // 错误 不是日期
            return;
        }

        //------------------

        //表达式验证
        // 设置参数
        boolean caseSensitive = false;
        String regex1   = "^([A-Z]*)(?:\\-)([A-Z]*)*$";
        String regex2   = "^([A-Z]*)$";
        String[] regexs = new String[] {regex1, regex1};

        // 创建验证
        RegexValidator validator2 = new RegexValidator(regexs, caseSensitive);

        // 验证返回boolean
        boolean valid = validator2.isValid("abc-def");

        // 验证返回字符串
        String result = validator2.validate("abc-def");

        // 验证返回数组
        String[] groups = validator2.match("abc-def");



        //-------------------

    }
}
