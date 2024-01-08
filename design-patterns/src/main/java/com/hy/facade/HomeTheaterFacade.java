package com.hy.facade;

// 外观类：家庭影院外观
class HomeTheaterFacade {
    private StereoSystem stereo;
    private Projector projector;
    private LightsControl lights;

    public HomeTheaterFacade() {
        stereo = new StereoSystem();
        projector = new Projector();
        lights = new LightsControl();
    }

    public void watchMovie() {
        System.out.println("Getting ready to watch a movie...");
        lights.turnOff();
        projector.turnOn();
        stereo.turnOn();
    }

    public void endMovie() {
        System.out.println("Ending the movie...");
        stereo.turnOff();
        projector.turnOff();
        lights.turnOn();
    }
}