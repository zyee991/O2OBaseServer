package com.o2o.web;

import com.jfinal.core.Controller;

public class QuickOperationController extends Controller {
	
	public void msg() {
		setAttr("title","消息");
		render("msg.html");
	}
	
	public void mail() {
		setAttr("title","邮件");
		render("mail.html");
	}
	
	public void workmate() {
		setAttr("title","同事");
		render("workmate.html");
	}
	
	public void chat() {
		setAttr("title","聊天");
		render("chat.html");
	}
}
