package com.hy.facade;

// HomeTheaterFacade充当了一个外观类，封装了音响、投影仪和灯光控制等子系统的复杂操作，以便客户端可以通过简单的调用来完成观影过程。
// 这样，客户端不需要了解各个子系统的具体操作，只需通过外观类的方法来控制整个家庭影院系统的行为。
public class FacadeExample {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        // 准备观影
        homeTheater.watchMovie();

        // 结束观影
        homeTheater.endMovie();
    }
}