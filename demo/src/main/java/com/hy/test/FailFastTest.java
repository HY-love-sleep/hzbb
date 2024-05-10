package com.hy.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static cn.hutool.core.util.NumberUtil.add;

/**
 * Description: fail-fast机制
 *
 * @Author: yhong
 * Date: 2024/5/7
 */
public class FailFastTest {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<String>() {{
            add("Xiao Bai Tiao1");
            add("Xiao Bai Tiao1");
            add("Xiao Bai Tiao2");
            add("Xiao Bai Tiao3");
        }};

        Iterator iterator = lists.iterator();
        while(iterator.hasNext()){
            if (iterator.next().equals("Xiao Bai Tiao1")) {
                iterator.remove();
            }
        }
        System.out.println(lists);

    }
}
