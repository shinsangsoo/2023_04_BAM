package bam.controller;

import java.util.List;
import java.util.Scanner;

import bam.dto.Member;
import bam.util.Util;

public class MemberController extends Controller {
	
	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.lastMemberId = 0;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		
		switch(methodName) {
		case "join":
			doJoin();
			break;
		default: 
			System.out.println("명령어를 확인해주세요");
			break;
		}
	}
	
	private void doJoin() {
		System.out.println("== 회원 가입 ==");
		int id = lastMemberId + 1;
		lastMemberId = id;
		
		String loginId = null;
		
		while(true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if(isLoginIdDup(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			
			System.out.printf("%s은(는) 사용가능한 아이디입니다\n", loginId);
			break;
		}
		
		String loginPw = null;
		
		while(true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			String loginPwChk = sc.nextLine();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 확인해주세요");
				continue;
			}
			
			break;
		}
		
		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, Util.getDateStr(), loginId, loginPw, name);

		members.add(member);

		System.out.printf("%s회원님이 가입되었습니다\n", loginId);
	}
	
	private boolean isLoginIdDup(String loginId) {
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}
}