package com.kxw.memcached.xmemcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class Test2 {

	public static void main(String arg[]) throws IOException, TimeoutException,
			InterruptedException, MemcachedException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(

		AddrUtil.getAddresses("localhost:11211"));

		MemcachedClient client = builder.build();

		client.flushAll();

		if (!client.set("hello", 0, "world")) {

			System.err.println("set error");

		}

	if (client.add("hello", 0, "dennis")) {

			System.err.println("Add error,key is existed");

		}

		if (!client.replace("hello", 0, "dennis")) {

			System.err.println("replace error");

		}

		//client.append("hello", " good");

		//client.prepend("hello", "hello ");

		String name = client.get("hello", new StringTranscoder());

		System.out.println(name);

		client.deleteWithNoReply("hello");

	}

}
