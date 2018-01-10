package com.kxw.disruptor.bigfilemd5;

import com.lmax.disruptor.EventFactory;

/**
 * Created by kingsonwu on 18/1/10.
 */
public class FileMd5EventFactory  implements EventFactory<FileMd5Event> {
    @Override
    public FileMd5Event newInstance() {
        return new FileMd5Event();
    }
}
