package com.kxw.memcached.spymemcached;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.Transcoder;

/**
 * @author yingzi.zhu
 *         <a href='http://www.cnblogs.com/atio/p/3198230.html'>@link</a>
 */
public class SpyMemcachedManager {

    private List<String> servers;   // 192.168.159.129:11211
    private MemcachedClient memClient;
    public static int DEFAULT_TIMEOUT = 5;
    public static TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;

    public SpyMemcachedManager(List<String> servers) {
        this.servers = servers;
    }

    public SpyMemcachedManager(String server) {
        this.servers = Arrays.asList(server);
    }

    public void connect() throws IOException {
        if (memClient != null) {
            return;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < servers.size(); i++) {
            String server = servers.get(i);
            buf.append(server).append(" ");
        }
        memClient = new MemcachedClient(AddrUtil.getAddresses(buf.toString()));
    }

    public void disConnect() {
        if (memClient == null) {
            return;
        }
        memClient.shutdown();
    }

    public void addObserver(ConnectionObserver obs) {
        memClient.addObserver(obs);
    }

    public void removeObserver(ConnectionObserver obs) {
        memClient.removeObserver(obs);
    }


    public boolean set(String key, Object value) {
        return set(key, value, 0);
    }

    public boolean set(String key, Object value, int expire) {
        Future<Boolean> f = memClient.set(key, expire, value);
        return getBooleanValue(f);
    }

    public Object get(String key) {
        return memClient.get(key);
    }

    public Object asyncGet(String key) {
        Object obj = null;
        Future<Object> f = memClient.asyncGet(key);
        try {
            obj = f.get(SpyMemcachedManager.DEFAULT_TIMEOUT,
                    SpyMemcachedManager.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return obj;
    }

    public boolean add(String key, Object value, int expire) {
        Future<Boolean> f = memClient.add(key, expire, value);
        return getBooleanValue(f);
    }

    public boolean replace(String key, Object value, int expire) {
        Future<Boolean> f = memClient.replace(key, expire, value);
        return getBooleanValue(f);
    }

    public boolean delete(String key) {
        Future<Boolean> f = memClient.delete(key);
        return getBooleanValue(f);
    }

    public boolean flush() {
        Future<Boolean> f = memClient.flush();
        return getBooleanValue(f);
    }

    public Map<String, Object> getMulti(Collection<String> keys) {
        return memClient.getBulk(keys);
    }

    public Map<String, Object> getMulti(String[] keys) {
        return memClient.getBulk(keys);
    }

    public Map<String, Object> asyncGetMulti(Collection<String> keys) {
        Map map = null;
        Future<Map<String, Object>> f = memClient.asyncGetBulk(keys);
        try {
            map = f.get(SpyMemcachedManager.DEFAULT_TIMEOUT,
                    SpyMemcachedManager.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }

    public Map<String, Object> asyncGetMulti(String[] keys) {
        Map map = null;
        Future<Map<String, Object>> f = memClient.asyncGetBulk(keys);
        try {
            map = f.get(SpyMemcachedManager.DEFAULT_TIMEOUT,
                    SpyMemcachedManager.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }

    public long increment(String key, int by, long defaultValue, int expire) {
        return memClient.incr(key, by, defaultValue, expire);
    }

    public long increment(String key, int by) {
        return memClient.incr(key, by);
    }

    public long decrement(String key, int by, long defaultValue, int expire) {
        return memClient.decr(key, by, defaultValue, expire);
    }

    public long decrement(String key, int by) {
        return memClient.decr(key, by);
    }

    public long asyncIncrement(String key, int by) {
        Future<Long> f = memClient.asyncIncr(key, by);
        return getLongValue(f);
    }

    public long asyncDecrement(String key, int by) {
        Future<Long> f = memClient.asyncDecr(key, by);
        return getLongValue(f);
    }

    public void printStats() throws IOException {
        printStats(null);
    }

    public void printStats(OutputStream stream) throws IOException {
        Map<SocketAddress, Map<String, String>> statMap =
                memClient.getStats();
        if (stream == null) {
            stream = System.out;
        }
        StringBuffer buf = new StringBuffer();
        Set<SocketAddress> addrSet = statMap.keySet();
        Iterator<SocketAddress> iter = addrSet.iterator();
        while (iter.hasNext()) {
            SocketAddress addr = iter.next();
            buf.append(addr.toString() + "/n");
            Map<String, String> stat = statMap.get(addr);
            Set<String> keys = stat.keySet();
            Iterator<String> keyIter = keys.iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                String value = stat.get(key);
                buf.append("  key=" + key + ";value=" + value + "/n");
            }
            buf.append("/n");
        }
        stream.write(buf.toString().getBytes());
        stream.flush();
    }

    public Transcoder getTranscoder() {
        return memClient.getTranscoder();
    }

    private long getLongValue(Future<Long> f) {
        try {
            Long l = f.get(SpyMemcachedManager.DEFAULT_TIMEOUT,
                    SpyMemcachedManager.DEFAULT_TIMEUNIT);
            return l.longValue();
        } catch (Exception e) {
            f.cancel(false);
        }
        return -1;
    }

    private boolean getBooleanValue(Future<Boolean> f) {
        try {
            Boolean bool = f.get(SpyMemcachedManager.DEFAULT_TIMEOUT,
                    SpyMemcachedManager.DEFAULT_TIMEUNIT);
            return bool.booleanValue();
        } catch (Exception e) {
            f.cancel(false);
            return false;
        }
    }
}