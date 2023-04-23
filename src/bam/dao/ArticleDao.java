package bam.dao;

import java.util.ArrayList;
import java.util.List;

import bam.dto.Article;
import bam.util.Util;

public class ArticleDao {
	private List<Article> articles;
	private int lastArticleId;

	public ArticleDao() {
		this.articles = new ArrayList<>();
		this.lastArticleId = 0;
	}

	public int setArticleId() {
		int id = lastArticleId + 1;
		lastArticleId = id;
		return id;
	}

	public void add(Article article) {
		articles.add(article);
	}

	public List<Article> getArticles(String searchKeyword) {
		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);

			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			return printArticles;
		}

		return articles;
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {

			int id = setArticleId();

			String title = "제목" + i;
			String body = "내용" + i;

			Article article = new Article(id, Util.getDateStr(), 2, title, body);
			articles.add(article);
		}
	}

}