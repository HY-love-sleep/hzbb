package com.hy.multithreading;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: 测试类
 * Author: yhong
 * Date: 2023/12/7
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor service = new ThreadPoolExecutor(5,
                10,
                60000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(30),
                new ThreadPoolExecutor.AbortPolicy());

        DiningPhilosophers d = new DiningPhilosophers();
        service.submit(() -> {
            try {
                d.wantsToEat(0,
                        () -> {
                            System.out.println("拿起左边的筷子");
                        },
                        () -> {
                            System.out.println("拿起右边的筷子");
                        },
                        () -> {
                            System.out.println("吃饭");
                        },
                        () -> {
                            System.out.println("放下左边的筷子");
                        },
                        () -> {
                            System.out.println("放下右边的筷子");
                        }
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            try {
                d.wantsToEat(1,
                        () -> {
                            System.out.println("拿起左边的筷子");
                        },
                        () -> {
                            System.out.println("拿起右边的筷子");
                        },
                        () -> {
                            System.out.println("吃饭");
                        },
                        () -> {
                            System.out.println("放下左边的筷子");
                        },
                        () -> {
                            System.out.println("放下右边的筷子");
                        }
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            try {
                d.wantsToEat(2,
                        () -> {
                            System.out.println("拿起左边的筷子");
                        },
                        () -> {
                            System.out.println("拿起右边的筷子");
                        },
                        () -> {
                            System.out.println("吃饭");
                        },
                        () -> {
                            System.out.println("放下左边的筷子");
                        },
                        () -> {
                            System.out.println("放下右边的筷子");
                        }
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            try {
                d.wantsToEat(3,
                        () -> {
                            System.out.println("拿起左边的筷子");
                        },
                        () -> {
                            System.out.println("拿起右边的筷子");
                        },
                        () -> {
                            System.out.println("吃饭");
                        },
                        () -> {
                            System.out.println("放下左边的筷子");
                        },
                        () -> {
                            System.out.println("放下右边的筷子");
                        }
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            try {
                d.wantsToEat(4,
                        () -> {
                            System.out.println("拿起左边的筷子");
                        },
                        () -> {
                            System.out.println("拿起右边的筷子");
                        },
                        () -> {
                            System.out.println("吃饭");
                        },
                        () -> {
                            System.out.println("放下左边的筷子");
                        },
                        () -> {
                            System.out.println("放下右边的筷子");
                        }
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        service.submit(() -> {
            try {
                d.wantsToEat(5,
                        () -> {
                            System.out.println("拿起左边的筷子");
                        },
                        () -> {
                            System.out.println("拿起右边的筷子");
                        },
                        () -> {
                            System.out.println("吃饭");
                        },
                        () -> {
                            System.out.println("放下左边的筷子");
                        },
                        () -> {
                            System.out.println("放下右边的筷子");
                        }
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
