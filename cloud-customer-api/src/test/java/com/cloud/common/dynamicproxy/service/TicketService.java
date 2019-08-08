package com.cloud.common.dynamicproxy.service;

public interface TicketService {
    //售票
    public void sellTicket();

    //问询
    public String inquire();

    //退票
    public void withdraw();
}
