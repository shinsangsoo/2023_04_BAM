package bam.dao;

import java.util.ArrayList;
import java.util.List;

import bam.dto.Article;
import bam.util.Util;

public class ArticleDao extends Dao {
	private List<Article> articles;

	public ArticleDao() {
		this.articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
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

			String title = "제목" + i;
			String body = "내용" + i;

			Article article = new Article(setLastId(), Util.getDateStr(), 2, title, body);
			add(article);
		}
	}

}