package bam.dao;

import java.util.ArrayList;
import java.util.List;

import bam.dto.Member;
import bam.util.Util;

public class MemberDao {
	private List<Member> members;
	private int lastMemberId;

	public MemberDao() {
		this.members = new ArrayList<>();
		this.lastMemberId = 0;
	}

	public int setMemberId() {
		int id = lastMemberId + 1;
		lastMemberId = id;
		return id;
	}

	public void add(Member member) {
		members.add(member);
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

			int id = setMemberId();

			String loginId = "test" + i;
			String loginPw = "test" + i;
			String name = "사용자" + i;

			Member member = new Member(id, Util.getDateStr(), loginId, loginPw, name);
			members.add(member);
		}
	}
}