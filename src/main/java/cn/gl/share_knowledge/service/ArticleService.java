package cn.gl.share_knowledge.service;

import cn.gl.share_knowledge.bean.Article;
import cn.gl.share_knowledge.bean.User;
import cn.gl.share_knowledge.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    String rootDire = "article/";

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllByEmail(String email){
        List<Article> allTitle = articleRepository.getAllByEmail(email);
        return allTitle;
    }

    /**
     * 保存文章到磁盘
     * 先创建用户文件夹
     * 将文章放在用户文件夹下
     * 保存数据库记录
     * @return
     */
    public void postArticle(String email, String title,String content){
        ClassPathResource resource = new ClassPathResource(rootDire+email);
        File userDire = new File(resource.getPath());
        if (!resource.exists()){
            userDire.mkdirs();
        }
        try {
            Path path = Path.of(userDire.getPath() + "/" + title);
            BufferedWriter bufferedWriter = Files.newBufferedWriter(path);
            bufferedWriter.write(content);
            bufferedWriter.close();
            // 数据库保存记录
            Article article = new Article(email, title, path.toString(), LocalDate.now());
            article.setUpdateTime(LocalDateTime.now());
            articleRepository.save(article);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Article> getArticleById(Long id){
        Optional<Article> optArticle = articleRepository.findById(id);
        return optArticle;
    }

    public boolean titleAreadyExist(Article article, User user){
        File file = new File(rootDire+user.getEmail());
        String[] list = file.list();
        for(String name:list) {
            if (name.equals(article.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteArticleContent(String email, String fileName){
        File file = new File(rootDire+email+"/"+fileName);
        return file.delete();
    }

    public void writeArticleContent(Article article, User user){
        try (FileWriter fw = new FileWriter(rootDire+user.getEmail()+"/"+article.getTitle())){
            fw.write(article.getContent());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Article updateArticleRecord(Article article, User user){
        Optional<Article> dataArticleOpt = articleRepository.findById(article.getId());
        if (dataArticleOpt.isPresent()){
            Article dataArticle = dataArticleOpt.get();
            dataArticle.setContentPath(rootDire+user.getEmail()+"/"+article.getTitle());
            dataArticle.setTitle(article.getTitle());
            dataArticle.setUpdateTime(LocalDateTime.now());
//            System.out.println("local date: "+dataArticle.getLocalDate());
            // 每次保存会少一天，所以保存前加一天
            LocalDate localDate = dataArticle.getLocalDate();
            dataArticle.setLocalDate(localDate.plusDays(1));
            return articleRepository.save(dataArticle);
        }
        return null;
    }

    // 更新文章
    // 如果修改了文件名，且已有文件名，则更新失败，否者更新
    // 如果未修改文件名，则更行文件内容
    // 更新要同时修改数据库和文件系统
    public Article updateArticle(Article article, User user){
        writeArticleContent(article, user);
        Article updateArticle = updateArticleRecord(article, user);
        return updateArticle;
    }


    /**
     * 删除文章， 得到用户，根据用户email查询文章，如果包含，
     * 就删除数据库记录和文件系统中，否则空操作
     * 返回 admin 页面
     * @return
     */
    public void deleteArticle(User user, Long articleId){
        List<Article> allArticles = articleRepository.getAllByEmail(user.getEmail());
        allArticles.stream()
                .map(Article::getId)
                .forEach(id -> {
                    if (id == articleId){
                        Optional<Article> dataArticle = articleRepository.findById(id);
                        if (dataArticle.isPresent()){
                            Article article = dataArticle.get();
                            File file = new File(rootDire+user.getEmail()+"/"+article.getTitle());
                            file.delete();
                        }
                        articleRepository.deleteById(id);
                    }
                });

    }

}
