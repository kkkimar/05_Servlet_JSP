package edu.kh.test;

public class IPInfo {

	private String ip;
	private String user;
	
	
	// 생성자 초기값 설정해주기
	public IPInfo(String ip, String user) {
		this.ip = ip;
		this.user = user;
		
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp (String ip) {
		this.ip = ip;
	}
	
	public String getUser() {
		return user;
	}
	
	@Override
	public String toString() {
		return ip + "/" + user;
	}
	
	
	
	
	
}
