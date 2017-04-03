package com.kxw;

import java.sql.SQLException;

/**
 * Created by kingsonwu on 17/3/19.
 *
 * @author kingsonwu
 * @date 2017/03/19
 */
public class Mocker<T extends Exception> {

    private void pleaseThrow(final Exception t) throws T{
        throw (T)t;
    }

    public static void main(final String[] args) {
        try{
            new Mocker<RuntimeException>().pleaseThrow(new SQLException());
        }/*catch (final SQLException ex){//类型被擦除
            ex.printStackTrace();
        }*/
        catch (final RuntimeException ex){//类型被擦除
            ex.printStackTrace();
        }
    }
}
