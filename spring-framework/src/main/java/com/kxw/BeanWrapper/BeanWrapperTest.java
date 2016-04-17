package com.kxw.BeanWrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.BeanWrapperImpl;

/**
 * <a href='http://tech.it168.com/a2009/0622/593/000000593376_1.shtml'>@link</a>
 */
public class BeanWrapperTest {
    public static void main(String[] args) {
        Company c = new Company();
        BeanWrapper bwComp = new BeanWrapperImpl(c);
        // setting the company name...
        bwComp.setPropertyValue("name", "Some Company Inc.");
        // ... can also be done like this:
        PropertyValue v = new PropertyValue("name", "Some Company Inc.");
        bwComp.setPropertyValue(v);

        // ok, lets create the director and tie it to the company:
        Employee jim = new Employee();
        BeanWrapper bwJim = new BeanWrapperImpl(jim);
        bwJim.setPropertyValue("name", "Jim Stravinsky");
        bwComp.setPropertyValue("managingDirector", jim);

        // retrieving the salary of the managingDirector through the company
        Float salary = (Float) bwComp.getPropertyValue("managingDirector.salary");
    }
}
