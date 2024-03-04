package com.hy.test;

import org.pcap4j.util.MacAddress;

import java.net.Inet4Address;
import java.net.InetAddress;
import com.hy.utils.MacAddressHelper;

/**
 * simple demo
 * Created by edward.gao on 28/09/2017.
 */
public class Test {

    /**
     * args[0] is the ip you want to detected
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        InetAddress addr1 = InetAddress.getByName("10.252.175.206");  // 本地以太网2
        InetAddress addr2 = InetAddress.getByName("172.16.36.140");  // 本地以太网3
        InetAddress addr3 = InetAddress.getByName("172.16.222.47");  // 本地以太网
        InetAddress mask = InetAddress.getByName("255.255.255.0");

        System.out.println(MacAddressHelper._isUnderSameSubNet(addr1, addr2, mask));
        System.out.println(MacAddressHelper._isUnderSameSubNet(addr1, addr3, mask));

        // IPv6 addresses and mask
        InetAddress addr1v6 = InetAddress.getByName("fe80::ae18:87fb:3829:ec5a%28");
        InetAddress maskv6 = InetAddress.getByName("ffff:ffff:ffff:ffff:0:0:0:0");
        InetAddress addr2v6 = InetAddress.getByName("fe80::36b9:70a7:f898:ef63%13");
        InetAddress addr3v6 = InetAddress.getByName("fe80::c34:f0d1:3ceb:b3f2%7");
        System.out.println(MacAddressHelper._isUnderSameSubNet(addr1v6, addr2v6, maskv6));
        System.out.println(MacAddressHelper._isUnderSameSubNet(addr3v6, addr2v6, maskv6));

        // Print local interfaces
        MacAddressHelper.getInstance().getLocalInterfaces().forEach(l -> System.out.println("Found interface " + l));
        if (args.length > 0) {
            MacAddress address = MacAddressHelper.getInstance().getMacAddress(Inet4Address.getByName(args[0]));
            System.out.println(String.format("ip=%s, mac=%s", args[0], address));
        }
        MacAddressHelper.getInstance().shutdown();
    }
}
