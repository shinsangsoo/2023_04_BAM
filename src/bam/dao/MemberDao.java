package bam.dao;

import java.util.ArrayList;
import java.util.List;

import bam.dto.Member;
import bam.util.Util;

public class MemberDao extends Dao {
	private List<Member> members;

	public MemberDao() {
		this.members = new ArrayList<>();
	}

	public void add(Member member) {
		members.add(member);
		lastId++;
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public boolean isLoginIdDup(String loginId) {
		Member member = getMemberByLoginId(loginId);

		if (member != null) {
			return false;
		}
		return true;
	}

	public void makeTestData() {
		for (int i = 1; i <= 3; i++) {

			String loginId = "test" + i;
			String loginPw = "test" + i;
			String name = "사용자" + i;

			Member member = new Member(setLastId(), Util.getDateStr(), loginId, loginPw, name);
			add(member);
		}
	}
}