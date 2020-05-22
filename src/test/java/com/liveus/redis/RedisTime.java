package com.liveus.redis;

import redis.clients.jedis.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class RedisTime
{
    public static void main(String[] args)
    {
        //连接redis服务器(在这里是连接本地的)
/*        Jedis jedis = new Jedis("127.0.0.1", 7000);
        //jedis.auth("shen0410");
        jedis.set("root","shen0410");//string
        jedis.hmset("hash",new HashMap<String,String>(){{//hash
            this.put("02","上城区");
            this.put("03","下城区");
            this.put("04","江干区");
            this.put("05","拱墅区");
            this.put("06","西湖区");
            this.put("08","滨江区");
            this.put("09","萧山区");
            this.put("10","余杭区");
            this.put("11","下沙");
            this.put("12","风景区");
            this.put("22","桐庐县");
            this.put("27","淳安县");
            this.put("82","建德市");
            this.put("83","富阳区");
            this.put("85","临安区");
        }});
        jedis.lpush("list","string1","string2","string3","string3");
        jedis.sadd("set","string1","string2","string3","string3");
        jedis.zadd("zset",0,"1");
        jedis.zadd("zset",0,"2");
        jedis.zadd("zset",2,"4");
        jedis.zadd("zset",1,"5");
        int sum=0;
        long startTime=System.currentTimeMillis();
        //采用流水线
        Pipeline pipeline=jedis.pipelined();
        while (true)
        {
            long endTime=System.currentTimeMillis();
            if (endTime-startTime>1000)
            {
                break;
            }else
            {
                sum++;
                pipeline.get("root");
            }
        }
        pipeline.sync();
        System.out.println("redis每秒执行"+sum+"次查询");*/

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(1);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(5000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("127.0.0.1", 7000));
        nodes.add(new HostAndPort("127.0.0.1", 7001));
        nodes.add(new HostAndPort("127.0.0.1", 7002));
        nodes.add(new HostAndPort("127.0.0.1", 7003));
        nodes.add(new HostAndPort("127.0.0.1", 7004));
        nodes.add(new HostAndPort("127.0.0.1", 7005));
        JedisCluster cluster = new JedisCluster(nodes, poolConfig);
        cluster.hmset("hash",new HashMap<String,String>(){{//hash
            this.put("02","上城区");
            this.put("03","下城区");
            this.put("04","江干区");
            this.put("05","拱墅区");
            this.put("06","西湖区");
            this.put("08","滨江区");
            this.put("09","萧山区");
            this.put("10","余杭区");
            this.put("11","下沙");
            this.put("12","风景区");
            this.put("22","桐庐县");
            this.put("27","淳安县");
            this.put("82","建德市");
            this.put("83","富阳区");
            this.put("85","临安区");
        }});
        cluster.lpush("list","string1","string2","string3","string3");
        cluster.sadd("set","string1","string2","string3","string3");
        cluster.zadd("zset",0,"1");
        cluster.zadd("zset",0,"2");
        cluster.zadd("zset",2,"4");
        cluster.zadd("zset",1,"5");
        cluster.expire("list",10);//过期时间
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}