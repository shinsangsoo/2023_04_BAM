package bam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.dto.Article;
import bam.dto.Member;
import bam.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;
	private int lastArticleId;
	private int lastMemberId;
	
	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
		lastArticleId = 0;
		lastMemberId = 0;
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);


		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("member join")) {
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

			} else if (cmd.equals("member list")) {
				
				for (int i = members.size() - 1; i >= 0; i--) {
					Member member = members.get(i);

					System.out.printf("%d	|	%s	|	%s	|	%s	\n", member.id, member.loginId, member.regDate, member.name);
				}
				
			} else if (cmd.equals("article write")) {
				System.out.println("== 게시물 작성 ==");
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, Util.getDateStr(), title, body);

				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			} else if (cmd.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다");
					continue;
				}
				
				List<Article> printArticles = articles;
				
				String searchKeyword = cmd.substring("article list".length()).trim();
				
				if(searchKeyword.length() > 0) {
					System.out.println("검색어 : " + searchKeyword);
					
					printArticles = new ArrayList<>();
					
					for(Article article : articles) {
						if(article.title.contains(searchKeyword)) {
							printArticles.add(article);
						}
					}
					
					if(printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다");
						continue;
					}
				}
				
				System.out.println("== 게시물 목록 ==");
				System.out.println("번호	|	제목	|	작성일	");

				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);

					System.out.printf("%d	|	%s	|	%s	\n", article.id, article.title, article.regDate);
				}
			} else if (cmd.startsWith("article detail ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.println("== 게시물 상세보기 ==");
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성일 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

			} else if (cmd.startsWith("article modify ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.println("== 게시물 수정 ==");
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다\n", id);

			} else if (cmd.startsWith("article delete ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				articles.remove(foundArticle);

				System.out.printf("%d번 게시물이 삭제되었습니다\n", id);

			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}
	
	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}
	
	private boolean isLoginIdDup(String loginId) {
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}
	
	private void makeTestData() {
		System.out.println("테스트용 게시물 데이터 5개 생성");
		
		for (int i = 1; i <= 5; i++) {
			
			int id = lastArticleId + 1;
			lastArticleId = id;
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			Article article = new Article(id, Util.getDateStr(), title, body);
			articles.add(article);
		}
	}
}