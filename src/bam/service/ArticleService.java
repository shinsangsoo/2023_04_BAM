package bam.service;

import java.util.List;

import bam.dao.ArticleDao;
import bam.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = new ArticleDao();
	}

	public int setLastId() {
		return articleDao.setLastId();
	}

	public void add(Article article) {
		articleDao.add(article);
	}
	
	public List<Article> getArticles(String searchKeyword) {
		return articleDao.getArticles(searchKeyword);
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}

	public void makeTestData() {
		articleDao.makeTestData();
	}
}