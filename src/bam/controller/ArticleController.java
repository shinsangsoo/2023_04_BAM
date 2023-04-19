package bam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bam.dto.Article;
import bam.util.Util;

public class ArticleController {

	private List<Article> articles;
	private Scanner sc;
	private int lastArticleId;
	
	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
		this.lastArticleId = 0;
	}

	public void doWrite() {
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
	}

	public void showList(String cmd) {
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다");
			return;
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
				return;
			}
		}
		
		System.out.println("== 게시물 목록 ==");
		System.out.println("번호	|	제목	|	작성일	");

		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);

			System.out.printf("%d	|	%s	|	%s	\n", article.id, article.title, article.regDate);
		}
	}
	
}