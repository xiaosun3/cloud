package com.cloud.common.dynamicproxy.service;

/**
 * @auth Administrator
 */
public class TicketServiceImpl implements TicketService{


    @Override
    public void sellTicket() {
        System.out.println("售票............");
    }

    @Override
    public String inquire() {
        System.out.println("问询............");
        return " inquire() 询问";
    }

    @Override
    public void withdraw() {
        System.out.println("退票............");
    }
}
