package apache.commons.beanutils;

import com.kxw.bean.Kingson;
import com.kxw.bean.Person;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingsonwu on 15/12/26.
 */

public class TestBeanUtils {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {


        //clone
        Kingson kingson = new Kingson();

        Kingson person = (Kingson) BeanUtils.cloneBean(kingson);

        System.out.println(person.getCompany());


        //将一个Map对象转化为一个Bean
        Map map = new HashMap();
        map.put("name", "Torres");
        map.put("age", 30);
        map.put("id", 9);
        map.put("company", "Madrid");
        Person man = new Person();
        BeanUtils.populate(man, map);

        System.out.println(man.getAge());
        System.out.println(man.getName());

        ////  将一个Bean转化为一个Map对象
        Map map2 = BeanUtils.describe(man);

        System.out.println(map2.get("name"));


    }
}
   /* private AbcPeriodSortBatch getBatch(HttpServletRequest request){
        AbcPeriodSortBatch batch = new AbcPeriodSortBatch();

        try {
            BeanUtils.populate(batch, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("process params happend error :",e);
            batch = null;
        }*/
        /*String[] ids = request.getParameterValues("ids");
        batch.setIds(ids);

        //屏蔽
        String shield_time_from = request.getParameter("shield_time_from");
        String shield_time_to = request.getParameter("shield_time_to");
}
*/
