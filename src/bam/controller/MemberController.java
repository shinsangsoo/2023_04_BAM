package bam.controller;

import java.util.List;
import java.util.Scanner;

import bam.dto.Member;
import bam.util.Util;

public class MemberController extends Controller {
	
	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	private Member loginedMember;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.lastMemberId = 0;
		this.loginedMember = null;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		
		switch(methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		default: 
			System.out.println("명령어를 확인해주세요");
			break;
		}
	}
	
	private void doJoin() {
		
		if (loginedMember != null) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
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
	
	private void doLogin() {
		
		if (loginedMember != null) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
		
		Member member = getMemberByLoginId(loginId);
		
		if (member == null) {
			System.out.println("일치하는 회원이 없습니다");
			return;
		}
		
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}

		loginedMember = member;
		
		System.out.printf("%s님 환영합니다\n", member.name);
		
	}
	
	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}
	
	private boolean isLoginIdDup(String loginId) {
		
		Member member = getMemberByLoginId(loginId);
		
		if (member != null) {
			return false;
		}
		return true;
	}
	
	@Override
	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개 생성");
		
		for (int i = 1; i <= 3; i++) {
			
			int id = lastMemberId + 1;
			lastMemberId = id;
			
			String loginId = "test" + i;
			String loginPw = "test" + i;
			String name = "사용자" + i;
			
			Member member = new Member(id, Util.getDateStr(), loginId, loginPw, name);
			members.add(member);
		}
	}
}