package com.yc.query.bloom;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @Author yucheng
 * @Date 2020/12/5 17:20
 */
public class BloomFilterTest {
    public static void main(String[] args) {
        // 总数量
        int total = 1000000;
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
        for (int i = 0; i < 1000000; i++) {
            bf.put("" + i);
        }

        Stopwatch stopwatch1 = Stopwatch.createStarted();
        System.out.println(bf.mightContain("1000001"));
        stopwatch1.stop();
        stopwatch1.elapsed(MILLISECONDS);
        System.out.println("time1 =" + stopwatch1);
    }
}
